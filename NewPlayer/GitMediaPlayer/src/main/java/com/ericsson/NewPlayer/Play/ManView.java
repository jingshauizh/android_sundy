package com.ericsson.NewPlayer.Play;

import android.widget.ImageButton;

import com.ericsson.NewPlayer.R;
import com.ericsson.mvp.util.EventUtil;
import com.ericsson.mvp.view.MyView;

/**
 * Created by eyngzui on 8/17/2017.
 */

public class ManView extends MyView {
    private ImageButton exitBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_play;
    }


    @Override
    public void initWidget() {
        exitBtn = findViewById(R.id.play_back);
    }


    @Override
    public void bindEvent() {
        EventUtil.click(presenter, exitBtn);
    }
}
