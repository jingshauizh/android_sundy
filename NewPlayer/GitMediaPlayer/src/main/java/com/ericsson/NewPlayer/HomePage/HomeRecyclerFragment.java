package com.ericsson.NewPlayer.HomePage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ericsson.NewPlayer.HomePage.moviefrag.AHomeUIFragmentPresenter;
import com.ericsson.NewPlayer.HomePage.moviefrag.IRecirclerFragView;
import com.ericsson.NewPlayer.HomePage.moviefrag.MovieFragModel;
import com.ericsson.NewPlayer.HomePage.moviefrag.RecirclerFragView;
import com.ericsson.NewPlayer.Play.PlayerActivity;
import com.ericsson.NewPlayer.R;
import com.ericsson.mvp.presenter.IPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerFragment extends AHomeUIFragmentPresenter<RecirclerFragView> implements IPresenter<RecirclerFragView> {

    private IRecirclerFragView mView;

    private List<String> mDatas = new ArrayList<>();
    private Activity activity;
    private RecyclerHomeAdapter adapter;
    private RecyclerView mRecyclerView;


    private MovieFragModel mMovieFragModel;
    private RecirclerFragView mRecirclerFragView;

    private String videoDataUrl = "http://150.236.223.175:8008/db";


    public static HomeRecyclerFragment newInstance() {
        return new HomeRecyclerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View freg_view = inflater.inflate(R.layout.fragment_home_ui, container, false);
        mRecyclerView = (RecyclerView) freg_view.findViewById(R.id.rcv_homeUI_view);
        activity = getActivity();
        initDatas();
        adapter = new RecyclerHomeAdapter(activity, null);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(activity, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        adapter.setListener(new RecyclerHomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //Toast.makeText(activity, "第" + position + "项被点击了", Toast.LENGTH_SHORT).show();
                //String playerUrl = "https://storage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%20Hangin'%20with%20the%20Google%20Search%20Bar.mp4";

                //VideoData.VideosBean videosBean = new VideoData.VideosBean();
                //videosBean.setVideoUrl(playerUrl);
                //videosBean.setLike(position == 0);
                //videosBean.setTitle("这是第几个标题：" + position);
                //videosBean.setSubtitle("这是第几个副标题：" + position);
                //videosBean.setInfo("这是第几个影片的详细内容解释：" + position);

                //Intent playerIntent = new Intent(activity.getApplicationContext(), PlayerActivity.class);
                //playerIntent.putExtra("videoBean", videosBean);
                //startActivity(playerIntent);

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


        mMovieFragModel = new MovieFragModel();

        return freg_view;
    }

    @Override
    public View getScrollableView() {
        return mRecyclerView;
    }


    private void initDatas() {
        for (int i = 0; i < 50; i++) {
            mDatas.add("Move Title" + String.valueOf(i + 1));
        }
    }


}
