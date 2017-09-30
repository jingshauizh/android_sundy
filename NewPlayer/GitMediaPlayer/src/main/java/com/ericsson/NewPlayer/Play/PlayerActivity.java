package com.ericsson.NewPlayer.Play;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.ericsson.NewPlayer.R;
import com.ericsson.NewPlayer.VideoData;
import com.ericsson.mvp.presenter.ActivityPresenter;

import java.util.List;

public class PlayerActivity extends ActivityPresenter<ManView> implements View.OnClickListener {

    private static final String TAG = "PlayerActivity";
    private String videoUrl;
    private String videoTitle;
    private String videoSubtitle;
    private String videoInfo;
    private Boolean videoLike;
    private Fragment playerFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private VideoData.VideosBean videosBean;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.play_back) {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent intent = getIntent();
        //videoUrl = intent.getStringExtra("videoUrl");
        videosBean = (VideoData.VideosBean) intent.getSerializableExtra("videoBean");
        videoUrl = videosBean.getVideoUrl();
        videoTitle = videosBean.getTitle();
        videoSubtitle = videosBean.getSubtitle();
        videoInfo = videosBean.getInfo();
        videoLike = videosBean.isLike();

        List<VideoData.VideosBean> videosBeanlist = (List<VideoData.VideosBean>) intent.getSerializableExtra("listMovies");


        findViewById(R.id.play_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("videoUrl", videoUrl);
        bundle.putString("videoTitle", videoTitle);
        bundle.putString("videoSubtitle", videoSubtitle);
        bundle.putString("videoInfo", videoInfo);
        bundle.putBoolean("videoLike", videoLike);
        // bundle.putSerializable("videoBean", videosBean);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        playerFragment = new PlayerFragment();
        playerFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.play_video_fragment, playerFragment, "PlayerFragment");

        RelatedRecyclerFragment mRelatedRecyclerFragment = RelatedRecyclerFragment.newInstance();
        if(videosBeanlist != null){
            Log.i(TAG, "videosBeanlist size = " + videosBeanlist.size());
            mRelatedRecyclerFragment.setDatas(videosBeanlist);
        }

        fragmentTransaction.add(R.id.play_related_fragment, mRelatedRecyclerFragment, "RelatedRecyclerFragment");

        CommentRecyclerFragment mCommentRecyclerFragment = CommentRecyclerFragment.newInstance();
        fragmentTransaction.add(R.id.play_comment_fragment, mCommentRecyclerFragment, "CommentRecyclerFragment").commit();


    }

}
