package com.framework.app.net;

/**
 * 观察网络的接口
 */

public interface NetChangeObserver {
    /**
     * 网络连接回调 type为网络类型
     */
    void onNetConnected(NetUtils.NetType type);

    /**
     * 没有网络
     */
    void onNetDisConnect();
}
