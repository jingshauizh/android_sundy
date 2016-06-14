package com.example.eventapp;

import android.app.Activity;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    private Button mButtonHello;
    private Button mImageView;
    private LinearLayout myLayout;
    private String TAG = "Event MainActivity";
    private EditText mEditText;

    private String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_my_layout);
        initActivity();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG,"onResume setText ="+temp);
        if(temp != null){
            mEditText.setText(temp);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState !=null){
            temp  = (String) savedInstanceState.get("tempInput");
            Log.w(TAG,"onRestoreInstanceState get out ="+temp);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mEditText != null){
            String tempInput = mEditText.getText().toString();
            Log.w(TAG,"onSaveInstanceState test ="+tempInput);
            outState.putString("tempInput",tempInput);
        }

    }


    private void initActivity(){
        mButtonHello = (Button)findViewById(R.id.button1);
        mImageView = (Button)findViewById(R.id.button2);
        myLayout = (LinearLayout)findViewById(R.id.my_layout);
        mEditText= (EditText) findViewById(R.id.edt_info);

        mButtonHello.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                Log.i(TAG,"button1 setOnClickListener");
//               Intent _intent = new Intent(MainActivity.this,ActivityDialog.class);
//               startActivity(_intent);

               Intent _intent = new Intent(MainActivity.this,Activityenu.class);
               startActivity(_intent);
            }
        });

//        myLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.i(TAG,"myLayout onTouch");
//                return false;
//            }
//        });
//        mButtonHello.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.i(TAG,"button1 onTouch");
//                return false;
//            }
//        });
//
//        mButtonHello.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG,"button1 setOnClickListener");
//            }
//        });
//
//        mImageView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.i(TAG,"button2  onTouch");
//                return false;
//            }
//        });
//
//        mImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent mintent = new Intent(MainActivity.this, ActvityStateSave.class);
//                startActivity(mintent);
//                Log.i(TAG,"button2  setOnClickListener");
//            }
//        });
    }
}
