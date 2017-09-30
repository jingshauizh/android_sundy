package com.ericsson.NewPlayer.HomePage.moviefrag;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ericsson.NewPlayer.HomePage.RecyclerHomeAdapter;
import com.ericsson.NewPlayer.R;
import com.ericsson.NewPlayer.VideoData;
import com.ericsson.mvp.view.MyView;

import java.util.List;

/**
 * Created by eqruvvz on 8/22/2017.
 */

public class RecirclerFragView extends MyView implements IRecirclerFragView {
    private RecyclerHomeAdapter adapter;
    private RecyclerView mRecyclerView;
    private Context context;

    @Override
    public View getScrollableView(){
        return mRecyclerView;
    }
    @Override
    public void initViewData(List<VideoData.VideosBean> mDatas ) {
        adapter = new RecyclerHomeAdapter(context, mDatas);
        if(mRecyclerView!=null){
            mRecyclerView.setAdapter(adapter);
        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(context, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
    }

    @Override
    public void setOnItemClickLisener(RecyclerHomeAdapter.OnItemClickListener pListener) {
        adapter.setListener(pListener);
    }

    @Override
    public void refreshView() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshView(List<VideoData.VideosBean> mDatas) {
        adapter.setmDatas(mDatas);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void initWidget() {
        mRecyclerView= (RecyclerView)findViewById(R.id.rcv_homeUI_view);
        context = rootView.getContext();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_ui;
    }
}
