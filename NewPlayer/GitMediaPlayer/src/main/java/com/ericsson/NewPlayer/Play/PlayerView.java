package com.ericsson.NewPlayer.Play;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ericsson.NewPlayer.R;
import com.ericsson.mvp.util.EventUtil;
import com.ericsson.mvp.view.MyView;

/**
 * Created by eyngzui on 8/17/2017.
 */

public class PlayerView extends MyView {
    private ImageButton likeBtn;
    private ImageButton infoBtn;
    private ImageButton cacheBtn;
    private TextView videoTitle;
    private TextView videoSubTitle;
    private TextView videoInfo;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_play_video;
    }


    @Override
    public void initWidget() {
        likeBtn = findViewById(R.id.play_like);
        infoBtn = findViewById(R.id.play_info_icon);
        cacheBtn = findViewById(R.id.play_cache_icon);
        videoTitle = findViewById(R.id.play_title);
        videoSubTitle = findViewById(R.id.play_subtitle);
        videoInfo = findViewById(R.id.play_detailinfo);
    }


    @Override
    public void bindEvent() {
        EventUtil.click(presenter, likeBtn, infoBtn, cacheBtn);
    }

    public void setVideoTitle(String title) {
        videoTitle.setText(title);
    }

    public String getVideoTitle() {
        return videoTitle.getText().toString();
    }

    public String getVideoSubTitle() {
        return videoSubTitle.getText().toString();
    }

    public void setVideoSubTitle(String videoSubTitle) {
        this.videoSubTitle.setText(videoSubTitle);
    }

    public void setVideoInfo(String videoInfo) {
        this.videoInfo.setText(videoInfo);
    }

    public void updateLikeIcon(boolean like) {
        if (like) {
            likeBtn.setImageResource(R.drawable.heart_fav);
        } else {
            likeBtn.setImageResource(R.drawable.heart_unfav);
        }
    }

    private void updateDetialInfoIcons(boolean show) {
        if (!show) {
            infoBtn.setImageResource(R.drawable.info);
            cacheBtn.setVisibility(View.VISIBLE);
        } else {
            infoBtn.setImageResource(R.drawable.close);
            cacheBtn.setVisibility(View.GONE);
        }
    }

    public void showDetialInfo(String info) {
        if (videoInfo != null) {
            updateDetialInfoIcons(true);
            videoInfo.setText(info);
            videoInfo.setVisibility(View.VISIBLE);
        }
    }

    public void hideDetialInfo() {
        if (videoInfo != null) {
            updateDetialInfoIcons(false);
            videoInfo.setVisibility(View.GONE);
        }
    }
}
