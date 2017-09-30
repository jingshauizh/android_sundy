package com.ericsson.NewPlayer.Play;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ericsson.NewPlayer.HomePage.RecyclerHomeAdapter;
import com.ericsson.NewPlayer.HomePage.base.HeaderViewPagerFragment;
import com.ericsson.NewPlayer.R;
import com.ericsson.NewPlayer.VideoData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RelatedRecyclerFragment extends HeaderViewPagerFragment {

    private List<VideoData.VideosBean> mDatas =null;
    private Activity activity;
    private RecyclerHomeAdapter adapter;
    private RecyclerView mRecyclerView;


    public static RelatedRecyclerFragment newInstance() {
        return new RelatedRecyclerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View freg_view = inflater.inflate(R.layout.fragment_play_related, container, false);
        mRecyclerView = (RecyclerView) freg_view.findViewById(R.id.play_related_view);
        activity = getActivity();
        //initDatas();
        adapter = new RecyclerHomeAdapter(activity, mDatas);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        adapter.setListener(new RecyclerHomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //Toast.makeText(activity, "第" + position + "项被点击了", Toast.LENGTH_SHORT).show();
                activity.finish();
                Intent playerIntent = new Intent(activity.getApplicationContext(), PlayerActivity.class);
                playerIntent.putExtra("videoBean", mDatas.get(position));
                playerIntent.putExtra("listMovies", (Serializable) mDatas);
                activity.startActivity(playerIntent);
            }

            @Override
            public void onItemLongClick(View v, int position) {
                Toast.makeText(activity, "第" + position + "项被long点击了", Toast.LENGTH_SHORT).show();
            }
        });
        return freg_view;
    }

    @Override
    public View getScrollableView() {
        return mRecyclerView;
    }


    public void setDatas(List<VideoData.VideosBean> pDatas) {
        mDatas = pDatas;
    }

}
