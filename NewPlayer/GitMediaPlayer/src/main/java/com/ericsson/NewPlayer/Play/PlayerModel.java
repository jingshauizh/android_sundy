package com.ericsson.NewPlayer.Play;

import android.content.Context;

import com.ericsson.mvp.model.Imodel;

/**
 * Created by eyngzui on 8/17/2017.
 */

public class PlayerModel implements Imodel {
    private Context context;

    public PlayerModel(Context context) {
        this.context = context;
    }

    public String getVideoTitle() {
        return "视频标题";
    }

    public String getVideoSubtitle() {
        return "视频副标题";
    }
}
