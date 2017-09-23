// (c)2016 Flipboard Inc, All Rights Reserved.

package com.rengwuxian.rxjavasamples.module.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.rengwuxian.rxjavasamples.BaseFragment;
import com.rengwuxian.rxjavasamples.MainActivity;
import com.rengwuxian.rxjavasamples.R;
import com.rengwuxian.rxjavasamples.adapter.ItemListAdapter;
import com.rengwuxian.rxjavasamples.module.cache_6.data.Data;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class TestFragment extends BaseFragment {
    @Bind(R.id.loadingTimeTvTest) TextView loadingTimeTv;
    @Bind(R.id.swipeRefreshLayoutTest) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.cacheRvTest) RecyclerView cacheRv;
    ItemListAdapter adapter = new ItemListAdapter();
    private long startingTime;
    private Context context;

    private String movieRquestUrl = "http://150.236.223.172:8008/db";

    @OnClick(R.id.clearMemoryCacheBtTest)
    void clearMemoryCache() {
        Data.getInstance().clearMemoryCache();
        adapter.setItems(null);
        Toast.makeText(getActivity(), R.string.memory_cache_cleared, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.clearMemoryAndDiskCacheBtTest)
    void clearMemoryAndDiskCache() {
        Data.getInstance().clearMemoryAndDiskCache();
        adapter.setItems(null);
        Toast.makeText(getActivity(), R.string.memory_and_disk_cache_cleared, Toast.LENGTH_SHORT).show();
    }


//    void load() {
//        swipeRefreshLayout.setRefreshing(true);
//        startingTime = System.currentTimeMillis();
//        unsubscribe();
//        subscription = Data.getInstance()
//                .subscribeData(new Observer<List<Item>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                        swipeRefreshLayout.setRefreshing(false);
//                        Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onNext(List<Item> items) {
//                        swipeRefreshLayout.setRefreshing(false);
//                        int loadingTime = (int) (System.currentTimeMillis() - startingTime);
//                        //loadingTimeTv.setText(getString(R.string.loading_time_and_source, loadingTime, Data.getInstance().getDataSourceText()));
//                        adapter.setItems(items);
//                    }
//                });
//    }
    @OnClick(R.id.loadBtTest)
    void loadPictures(){
        final String movieType="movieType";
        Observable.just(movieType)
               .flatMap(new Func1<String, Observable<JSONObject>>() {
            @Override
            public Observable<JSONObject> call(String movieType) {
                try {
                        JSONObject getUserInoo = RxVolleyRequest.stringRequest(Request.Method.GET, movieRquestUrl);
                        return Observable.just(getUserInoo);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Observable.error(new Exception("应用出现错误"));
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JSONObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(JSONObject s) {
                        Toast.makeText(context, s.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        ;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        ButterKnife.bind(this, view);
        cacheRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        cacheRv.setAdapter(adapter);
        context = getActivity();
        VolleyManager.init(context);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);
        return view;
    }



    @Override
    protected int getDialogRes() {
        return R.layout.dialog_cache;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_cache;
    }
}
