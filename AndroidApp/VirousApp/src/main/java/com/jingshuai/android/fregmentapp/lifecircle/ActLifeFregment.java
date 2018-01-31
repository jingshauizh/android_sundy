package com.jingshuai.android.fregmentapp.lifecircle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.lifecircle.dummy.DummyContent;
import com.jingshuai.appcommonlib.log.MLog;

public class ActLifeFregment extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MLog.i("AppCompatActivity onCreate start");
        super.onCreate(savedInstanceState);
        MLog.i("AppCompatActivity setContentView start");
        setContentView(R.layout.activity_act_life_fregment);
        MLog.i("AppCompatActivity onCreate");
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public ActLifeFregment() {
        super();
        MLog.i("AppCompatActivity constractor");

    }

    @Override
    protected void onPostResume() {
        MLog.i("AppCompatActivity onPostResume");
        super.onPostResume();

    }

    @Override
    protected void onStart() {
        MLog.i("AppCompatActivity onStart");
        super.onStart();

    }

    @Override
    protected void onStop() {
        MLog.i("AppCompatActivity onStop");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        MLog.i("AppCompatActivity onDestroy");
        super.onDestroy();

    }

    @Override
    protected void onPause() {
        MLog.i("AppCompatActivity onPause");
        super.onPause();

    }

    @Override
    protected void onResume() {
        MLog.i("AppCompatActivity onResume");
        super.onResume();

    }

    @Override
    protected void onRestart() {
        MLog.i("AppCompatActivity onRestart");
        super.onRestart();

    }

    @Override
    public void onAttachedToWindow() {
        MLog.i("AppCompatActivity onAttachedToWindow");
        super.onAttachedToWindow();

    }

    @Override
    public void onDetachedFromWindow() {
        MLog.i("AppCompatActivity onDetachedFromWindow");
        super.onDetachedFromWindow();

    }
}
