package com.ericsson.NewPlayer.CachePage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ericsson.NewPlayer.R;

/**
 * Created by Carson_Ho on 16/5/23.
 */
public class Fragment2 extends Fragment

    {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
        savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cache_downloading, null);
        return view;
    }

    }
