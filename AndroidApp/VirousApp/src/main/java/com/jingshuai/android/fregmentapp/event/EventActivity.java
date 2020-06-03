package com.jingshuai.android.fregmentapp.event;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.appcommonlib.log.MLog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventActivity extends AppCompatActivity {

    @BindView( R.id.imageViewc )
    public ImageView imageButton ;
    @BindView( R.id.constraintLayoutc )
    public ConstraintLayout constraintLayout ;
    @BindView( R.id.linearLayoutc )
    public LinearLayout linearLayout ;
    @BindView( R.id.relativeLayoutc )
    public RelativeLayout relativeLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_customer);
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MLog.i(Constaint.LOG_TAG,"EventActivity dispatchTouchEvent default type="+ev.getAction());
        boolean returnflag = super.dispatchTouchEvent(ev);
        MLog.i(Constaint.LOG_TAG,"EventActivity dispatchTouchEvent return="+returnflag);
        return returnflag;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MLog.i(Constaint.LOG_TAG,"EventActivity onTouchEvent onTouchEvent type="+event.getAction());
        boolean returnflag = super.onTouchEvent(event);
        MLog.i(Constaint.LOG_TAG,"EventActivity onTouchEvent return="+returnflag);
        return returnflag;
    }

}
