package com.example.videoapp.media.crasy;


import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;


import com.example.videoapp.R;

import java.io.IOException;

public class ActivityediaAudio extends Activity {
    private final String TAG= ActivityediaAudio.class.getName();
    private int mMedia;
    private static final String MEDIA = "media";
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityedia_audio);
        Bundle mBundle = getIntent().getExtras();
        mMedia = mBundle.getInt(MEDIA);
       Log.i(TAG,"mMedia="+mMedia);
        playMedia(mMedia);
    }

    private void playMedia(int media){
        switch(media){
            case 12:
                String  path = "/storage/sdcard1/MP3/111.mp3";
                try {
                    mMediaPlayer = new MediaPlayer();
                    Log.i(TAG,"path="+path);
                    mMediaPlayer.setDataSource(path);

                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                    mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mMediaPlayer.seekTo(130000);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                String  netpath = "http://sc.111ttt.com/up/mp3/347508/FCAF062BECD1C24FAED2A355EF51EBDD.mp3";
                Uri _uri = Uri.parse(netpath);
                mMediaPlayer = MediaPlayer.create(this,_uri) ;
                //mMediaPlayer.seekTo(30000);
                mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mMediaPlayer.seekTo(130000);
                    }
                });
                mMediaPlayer.start();
                break;
            default:


        }
    }

    @Override
    protected void onDestroy() {
        if(mMediaPlayer != null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer=null;
        }
        super.onDestroy();
    }
}
