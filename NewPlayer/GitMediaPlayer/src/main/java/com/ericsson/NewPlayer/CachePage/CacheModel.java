package com.ericsson.NewPlayer.CachePage;

import android.content.Context;

import com.ericsson.mvp.model.Imodel;

import java.util.List;

/**
 * Created by ehaoqii on 9/5/2017.
 */

public class CacheModel implements Imodel {
    private Context context;

    public CacheModel(Context context) {
        this.context = context;
    }
    public List<String> getCacheTitle(){
        return null;
    }
}
