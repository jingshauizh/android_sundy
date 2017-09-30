package com.ericsson.NewPlayer.Play;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.ericsson.NewPlayer.R;
import com.ericsson.mvp.presenter.FragmentPresenter;
import com.ericsson.play.Player;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

/**
 * Created by eyngzui on 8/17/2017.
 */

public class PlayerFragment extends FragmentPresenter<PlayerView> implements View.OnClickListener {
    private static final String TAG = "PlayerFragment";

    private String videoUrl;
    private String videoTitle;
    private String videoSubtitle;
    private String videoInfo;
    private Boolean videoShowInfo = false;
    private Boolean videoLike;
    private Player exoPlayer;
    private PlayerView playerView;
    private PlayerModel playerModel;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_like:
                // TODO send request to server for changing LIKE or NOT
                videoLike = !videoLike;
                playerView.updateLikeIcon(videoLike);
                break;
            case R.id.play_info_icon:
                videoShowInfo = !videoShowInfo;
                if (videoShowInfo) {
                    playerView.showDetialInfo(videoInfo);
                } else {
                    playerView.hideDetialInfo();
                }
                break;
            case R.id.play_cache_icon:
                //TODO download video
                break;
        }
    }


    @Override
    public Class getViewClass() {
        return super.getViewClass();
    }

    @Override
    public void create(Bundle savedInstance) {
        super.create(savedInstance);
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        playerView = this.view;
        playerModel = new PlayerModel(getContext());
        Bundle bundle = getFragmentManager().findFragmentByTag("PlayerFragment").getArguments();
        videoUrl = bundle.getString("videoUrl").trim();
        videoTitle = bundle.getString("videoTitle").trim();
        videoSubtitle = bundle.getString("videoSubtitle").trim();
        videoInfo = bundle.getString("videoInfo").trim();
        videoLike = bundle.getBoolean("videoLike");


        exoPlayer = new Player(getContext(), (SimpleExoPlayerView) view.findViewById(R.id.play_view), (ProgressBar) view.findViewById(R.id.play_progressbar));
        exoPlayer.initialize();
        exoPlayer.play(videoUrl);

        playerView.setVideoTitle(videoTitle);
        playerView.setVideoSubTitle(videoSubtitle);
        playerView.setVideoInfo(videoInfo);
        playerView.updateLikeIcon(videoLike);

        // playerView.setVideoTitle(playerModel.getVideoTitle());
        // playerView.setVideoSubTitle(playerModel.getVideoSubtitle());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        exoPlayer.stop();
        exoPlayer.release();
    }
}
