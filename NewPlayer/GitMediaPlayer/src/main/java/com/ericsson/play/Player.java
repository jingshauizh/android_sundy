package com.ericsson.play;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.Formatter;
import java.util.Locale;

/**
 * Created by eyngzui on 8/10/2017.
 */

public class Player {
    private final static String TAG = "PlayerTest";
    private SimpleExoPlayerView exoPlayerView;
    private SimpleExoPlayer exoPlayer;
    private Uri playUri;
    private ProgressBar progressBar;
    private Context context;

    public Player(Context context, SimpleExoPlayerView playerView, ProgressBar progressBar){
        this.exoPlayerView = playerView;
        this.progressBar = progressBar;
        this.context = context;
    }

    public void initialize(){
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTackSelectionFactory);
        LoadControl loadControl = new DefaultLoadControl();
        exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
        exoPlayerView.setPlayer(exoPlayer);
    }

    public void play(String url){
        playUri = Uri.parse(url);
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        DataSource.Factory dataSourceFactory
                = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context,"ExoNewPlayer"),bandwidthMeter);
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource videoSource = new ExtractorMediaSource(playUri,dataSourceFactory,extractorsFactory,
                null,null);
        exoPlayer.prepare(videoSource);
        exoPlayer.addListener(eventListener);
        exoPlayer.setPlayWhenReady(true);
    }


    private ExoPlayer.EventListener eventListener = new ExoPlayer.EventListener() {
        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest) {
            Log.i(TAG,"onTimelineChanged");
        }

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
            Log.i(TAG,"onTracksChanged");
        }

        @Override
        public void onLoadingChanged(boolean isLoading) {
            Log.i(TAG,"onLoadingChanged");
        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            Log.i(TAG,"onPlayerStateChanged: playWhenReady = "+String.valueOf(playWhenReady)
                    +" playbackState = "+playbackState);
            switch (playbackState){
                case ExoPlayer.STATE_ENDED:
                    Log.i(TAG,"Playback ended!");
                    //Stop playback and return to start position
                    setPlayPause(false);
                    exoPlayer.seekTo(0);
                    break;
                case ExoPlayer.STATE_READY:
                    progressBar.setVisibility(View.GONE);
                    Log.i(TAG,"ExoPlayer ready! pos: "+exoPlayer.getCurrentPosition()
                            +" max: "+stringForTime((int)exoPlayer.getDuration()));
                    break;
                case ExoPlayer.STATE_BUFFERING:
                    Log.i(TAG,"Playback buffering!");
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case ExoPlayer.STATE_IDLE:
                    Log.i(TAG,"ExoPlayer idle!");
                    break;
            }
        }

        private String stringForTime(int timeMs) {
            StringBuilder mFormatBuilder;
            Formatter mFormatter;
            mFormatBuilder = new StringBuilder();
            mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
            int totalSeconds =  timeMs / 1000;

            int seconds = totalSeconds % 60;
            int minutes = (totalSeconds / 60) % 60;
            int hours   = totalSeconds / 3600;

            mFormatBuilder.setLength(0);
            if (hours > 0) {
                return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
            } else {
                return mFormatter.format("%02d:%02d", minutes, seconds).toString();
            }
        }


        @Override
        public void onPlayerError(ExoPlaybackException error) {
            Log.i(TAG,"onPlaybackError: "+error.getMessage());
        }

        @Override
        public void onPositionDiscontinuity() {
            Log.i(TAG,"onPositionDiscontinuity");
        }

    };

    private void setPlayPause(boolean play){
        exoPlayer.setPlayWhenReady(play);
    }

    public void pause(){
        setPlayPause(false);
    }

    public void stop(){
        exoPlayer.stop();
    }

    public void release(){
        exoPlayer.release();
    }
}
