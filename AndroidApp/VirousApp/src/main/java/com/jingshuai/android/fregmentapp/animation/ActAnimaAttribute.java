package com.jingshuai.android.fregmentapp.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.jsnative.ButterKnifeActivity;

import butterknife.OnClick;

public class ActAnimaAttribute extends ButterKnifeActivity {

    private  ObjectAnimator btn1mAnimator ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_anima_attribute);
        super.onCreate(savedInstanceState);

    }

    private void cancleAnimation(){
        if(btn1mAnimator != null){
            btn1mAnimator.cancel();
        }
    }

    @OnClick(R.id.btn_anima_1)
    public void onBtn1Click(View v){
        Button btn = (Button)v;
        cancleAnimation();
        btn1mAnimator = ObjectAnimator.ofFloat(v,"alpha",1f,0f);
        btn1mAnimator.setDuration(5000l);
        btn1mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        btn1mAnimator.setRepeatMode(ValueAnimator.REVERSE);
        btn1mAnimator.start();
    }


    @OnClick(R.id.btn_anima_2)
    public void onBtn2Click(View v){
        Button btn = (Button)v;
        cancleAnimation();
        btn1mAnimator = ObjectAnimator.ofInt(v,"Right",300,800);
        btn1mAnimator.setDuration(5000l);
        btn1mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        btn1mAnimator.setRepeatMode(ValueAnimator.REVERSE);
        btn1mAnimator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(btn1mAnimator != null){
            btn1mAnimator.cancel();
        }
    }
}
