package com.jingshuai.android.fregmentapp.lifecircle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.lifecircle.dummy.DummyContent;

public class ActLifeFregment extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_life_fregment);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
