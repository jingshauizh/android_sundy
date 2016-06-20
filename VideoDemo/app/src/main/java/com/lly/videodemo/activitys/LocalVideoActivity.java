package com.lly.videodemo.activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.lly.videodemo.R;
import com.lly.videodemo.activitys.fragment.VideoFragment;

import org.xutils.view.annotation.ContentView;

/**
 * LocalVideoActivity[v 1.0.0]
 *
 * @author 雷子
 * @description 本地视频播放
 */
@ContentView(R.layout.activity_sinceresepak_layout)
public class LocalVideoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createFragment();
    }

    /**
     * 创建一个Fragment
     */
    private void createFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        VideoFragment videoFragment = new VideoFragment();
        fragmentTransaction.add(R.id.layout_container, videoFragment);
        fragmentTransaction.commit();
    }

}
