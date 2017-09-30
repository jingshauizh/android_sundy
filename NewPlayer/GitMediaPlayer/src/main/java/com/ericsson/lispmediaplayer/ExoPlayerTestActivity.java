package com.ericsson.lispmediaplayer;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.ericsson.NewPlayer.R;
import com.ericsson.play.Player;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

public class ExoPlayerTestActivity extends Activity {
    private final static String TAG = "ExoPlayerTestActivity";
    Player player;
    Uri playerUri = Uri.parse("https://storage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%20Hangin'%20with%20the%20Google%20Search%20Bar.mp4");
    //Uri playerUri = Uri.parse("https://sdvideos.s3.cn-north-1.amazonaws.com.cn/allnew2/%E6%9D%8E%E5%A2%9E%E7%83%88%2B%E6%85%A2%E6%80%A7%E8%85%B9%E6%B3%BB%E4%B8%8E%E2%80%9C%E7%BB%93%E8%82%A0%E7%82%8E.mp4");
    //Uri playerUri = Uri.parse("http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8");
    //Uri playerUri = Uri.parse("http://yt-dash-mse-test.commondatastorage.googleapis.com/media/feelings_vp9-20130806-manifest.mpd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_exo_player_test);

        Player player = new Player(this, (SimpleExoPlayerView)findViewById(R.id.exoView), (ProgressBar)findViewById(R.id.progressBar));
        player.initialize();
        player.play("https://storage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%20Hangin'%20with%20the%20Google%20Search%20Bar.mp4");

    }


    @Override
    protected void onPause() {
        Log.i(TAG,"HomeUITActivity.onPause.");
        super.onPause();
        player.stop();
    }

    @Override
    protected void onStop() {
        Log.i(TAG,"HomeUITActivity.onStop.");
        super.onStop();
        player.release();
    }
}
