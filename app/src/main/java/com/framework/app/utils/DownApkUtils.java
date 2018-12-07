package com.framework.app.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载apk
 */
public class DownApkUtils {

    private static ProgressDialog uploadDialog;
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void downLoadApk(final String apkPath, final Context activity) {
        if (TextUtils.isEmpty(apkPath)) return;
        uploadDialog = new ProgressDialog(activity);
        uploadDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        uploadDialog.setMessage("正在下载更新...");
        uploadDialog.setCancelable(false);
        uploadDialog.show();
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(apkPath);
                    installApk(file, activity);
                    dismissUploadDialog();
                } catch (Exception e) {
                    ToastUtils.show("下载失败");
                    dismissUploadDialog();
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private static void dismissUploadDialog() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (uploadDialog != null) {
                    uploadDialog.dismiss();
                }
            }
        });
    }

    private static File getFileFromServer(String path) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = null;
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(10000);
                final int lengthOfFile = conn.getContentLength();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (uploadDialog != null) {
                            uploadDialog.setMax(100);
                        }
                    }
                });
                InputStream is = conn.getInputStream();
                file = new File(Environment.getExternalStorageDirectory(), "lhtx.apk");
                if (file.exists()) {
                    file.delete();
                }
                FileOutputStream fos = new FileOutputStream(file);
                BufferedInputStream bis = new BufferedInputStream(is);
                byte[] buffer = new byte[1024];
                int len;
                int total = 0;
                while ((len = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    total += len;
                    final int finalTotal = total;
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (uploadDialog != null) {
                                uploadDialog.setProgress(finalTotal * 100 / lengthOfFile);
                            }
                        }
                    });
                }
                fos.close();
                bis.close();
                is.close();
            } catch (IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.show("下载失败");
                    }
                });
                e.printStackTrace();
            }
            return file;
        } else {
            ToastUtils.show("未挂载SD卡，无法下载");
            return null;
        }
    }

    // 安装apk
    private static void installApk(File file, Context activity) {
        if (file == null) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean hasInstallPermission = activity.getPackageManager().canRequestPackageInstalls();
            if (hasInstallPermission) {
                //安装应用
                startInstallApk(file,activity);
            } else {
                //跳转至“安装未知应用”权限界面，引导用户开启权限
                Uri selfPackageUri = Uri.parse("package:" + activity.getPackageName());
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, selfPackageUri);
                //startActivityForResult(intent, REQUEST_CODE_UNKNOWN_APP);
                activity.startActivity(intent);
            }
        }else {
            //安装应用
            startInstallApk(file,activity);
        }


    }

    private static void startInstallApk(File file, Context activity){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        activity.startActivity(intent);
    }
}
