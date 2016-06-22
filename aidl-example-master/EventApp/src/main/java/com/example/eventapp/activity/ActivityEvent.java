package com.example.eventapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.eventapp.Activityenu;
import com.example.eventapp.R;

public class ActivityEvent extends ActivityBase {
    private final String TAG="ActivityEvent";
    private AlertDialog alertDialog;
    private  int BTN_ID=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_event);
        Log.i(TAG,"onCreate ");
        addButton();
    }

    private void addButton(){
        RelativeLayout _RelativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout_event);
        RelativeLayout.LayoutParams btParams = new RelativeLayout.LayoutParams (RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);  //设置按钮的宽度和高度
        Button _button = new Button(this);
        _button.setId(R.id.button_id_7);
        _button.setText("Open DiaLog");
        _RelativeLayout.addView(_button,btParams);
        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Open DiaLog Button Clicked! ");
                if(alertDialog == null){
                    AlertDialog.Builder _Builder = new AlertDialog.Builder(ActivityEvent.this)
                            .setMessage("Alert dialog message")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.i(TAG,"DiaLog Button No! ");
                                }
                            })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.i(TAG,"DiaLog Button Yes! ");
                                }
                            });
                    alertDialog = _Builder.create();
                }
                alertDialog.show();
            }
        });

        Button _button2 = new Button(this);
        _button2.setText("Open DiaLog Activity");
        _button2.setId(R.id.button_id_6);
        RelativeLayout.LayoutParams btParams2 = new RelativeLayout.LayoutParams (RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        btParams2.addRule(RelativeLayout.BELOW,_button.getId());
        //btParams2.addRule(RelativeLayout.TRUE);
        _RelativeLayout.addView(_button2,btParams2);
        _button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityEvent.this, AcDialog.class);
                startActivityForResult(i,4);
            }
        });

        Button _button3 = new Button(this);
        _button3.setText("Open Activity");
        _button3.setId(R.id.button_id_8);
        RelativeLayout.LayoutParams btParams3 = new RelativeLayout.LayoutParams (RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        btParams3.addRule(RelativeLayout.BELOW,_button2.getId());
        //btParams2.addRule(RelativeLayout.TRUE);
        _RelativeLayout.addView(_button3,btParams3);
        _button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityEvent.this, Activityenu.class);
                startActivityForResult(i,4);
            }
        });
    }



    @Override
    protected void onStop() {
        Log.i(TAG,"onStop ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"onDestroy ");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG,"onPause ");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.i(TAG,"onResume ");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i(TAG,"onStart ");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG,"onRestart ");
        super.onRestart();
    }
}
