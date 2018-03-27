package com.framework.app.activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.framework.app.R;
import com.framework.app.adapter.PersonViewHolder;
import com.framework.app.base.BaseActivity;
import com.framework.app.bean.DataProvider;
import com.framework.app.bean.Person;
import com.framework.app.pulltorefresh.BaseViewHolder;
import com.framework.app.pulltorefresh.RecyclerArrayAdapter;
import com.framework.app.pulltorefresh.RecycleviewRefreshView;

import butterknife.BindView;

public class MyRecycleviewActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{


    @BindView(R.id.recyclerView)
    RecycleviewRefreshView recyclerView;

    private RecyclerArrayAdapter<Person> adapter;
    private Handler handler = new Handler();

    private int page = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycleview;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Person>(this,recyclerView.getSwipeToRefresh()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new PersonViewHolder(parent);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       /* //刷新
                        if (!hasNetWork) {
                            adapter.pauseMore();
                            return;
                        }*/
                        adapter.addAll(DataProvider.getPersonList(page));
                        page++;
                    }
                }, 2000);
            }
        });
        adapter.setNoMore(R.layout.view_nomore, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {
                adapter.pauseMore();
            }

            @Override
            public void onNoMoreClick() {

            }
        });
        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                adapter.remove(position);
                return true;
            }
        });
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });
        recyclerView.setRefreshListener(this);
        onRefresh();
    }

    @Override
    protected LinearLayout getTopView() {
        return null;
    }

    @Override
    public void onRefresh() {
        page = 0;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                //刷新
                /*if (!hasNetWork) {
                    adapter.pauseMore();
                    return;
                }*/
                adapter.addAll(DataProvider.getPersonList(page));
                page=1;
            }
        }, 2000);
    }

}
