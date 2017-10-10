package com.jingshuai.android.fregmentapp.artmemmory;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jingshuai.android.fregmentapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MemmaryActivity extends AppCompatActivity {

    @BindView(R.id.buttonNormal)
    Button buttonNormal;
    @BindView(R.id.buttonLarge)
    Button buttonLarge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memmary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @OnClick(R.id.buttonNormal)
    public void getNormalMemmarySize(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int normalHeap = activityManager.getMemoryClass();
        Toast.makeText(MemmaryActivity.this,"getNormalMemmarySize="+normalHeap,Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.buttonLarge)
    public void getLargeMemmarySize(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int largeHeap = activityManager.getLargeMemoryClass();
        Toast.makeText(MemmaryActivity.this,"getLargeMemmarySize="+largeHeap,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
