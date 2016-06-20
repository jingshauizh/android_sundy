package com.example.videoapp.media.crasy;

import android.app.Activity;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.videoapp.R;

import java.util.HashMap;

public class SoundPoolActivity extends Activity {
    private SoundPool mSoundPool;
    private HashMap musicId = new HashMap();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sound_pool);
//        mSoundPool = new SoundPool(1,2,2);
//        String  path = "/storage/sdcard1/MP3/111.mp3";
//
//        mSoundPool.load(path,1);
//        mSoundPool.play(mSoundPool.load(path,1),1,1,0,0,1);
//
//    }

    Button btn1, btn2, btn3;
//创建一个SoundPool对象
    SoundPool soundPool;
//定义一个HashMap用于存放音频流的ID
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_pool);
        String path = "/storage/sdcard1/MP3/sound.ogg";
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
//初始化soundPool,设置可容纳12个音频流，音频流的质量为5，
        soundPool = new SoundPool(12, 0, 5);

//通过load方法加载指定音频流，并将返回的音频ID放入musicId中
// musicId.put(1, soundPool.load(this, R.raw.sound_ringer_normal, 1));
        musicId.put(1, soundPool.load(path,1));
        musicId.put(2, soundPool.load(this, R.raw.sound_ringer_silent, 1));
        musicId.put(3, soundPool.load(this, R.raw.sound_view_clicked, 1));
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                switch (v.getId()) {
                    case R.id.btn1://播放指定的音频流
                        soundPool.play((int) musicId.get(1), 1, 1, 0, 0, 1);
                        break;
                    case R.id.btn2:
                        soundPool.play((int) musicId.get(2), 1, 1, 0, 0, 1);
                        break;
                    case R.id.btn3:
                        soundPool.play((int) musicId.get(3), 1, 1, 0, 0, 1);
                        break;
                    default:
                        break;
                }
            }
        };
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);

    }

}

