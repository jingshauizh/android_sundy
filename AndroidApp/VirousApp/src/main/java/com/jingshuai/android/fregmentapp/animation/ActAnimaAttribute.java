package com.jingshuai.android.fregmentapp.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.jsnative.ButterKnifeActivity;
import com.jingshuai.appcommonlib.log.MLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class ActAnimaAttribute extends ButterKnifeActivity {

    private List<Animator> btn1mAnimators ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_anima_attribute);
        super.onCreate(savedInstanceState);
        btn1mAnimators = new ArrayList<Animator>();
    }



    @OnClick(R.id.btn_anima_1)
    public void onBtn1Click(View v){
        Button btn = (Button)v;

        ObjectAnimator btn1mAnimator = ObjectAnimator.ofFloat(v,"alpha",1f,0f);
        btn1mAnimator.setDuration(5000l);
        btn1mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        btn1mAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //btn1mAnimator.start();
        btn1mAnimators.add(btn1mAnimator);
    }


    @OnClick(R.id.btn_anima_2)
    public void onBtn2Click(View v){
        Button btn = (Button)v;

        ObjectAnimator btn1mAnimator = ObjectAnimator.ofInt(v,"Right",300,800);
        btn1mAnimator.setDuration(5000l);
        btn1mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        btn1mAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //btn1mAnimator.start();
        btn1mAnimators.add(btn1mAnimator);
    }


    @OnClick(R.id.btn_anima_3)
    public void onBtn3Click(View v){
        Button btn = (Button)v;

        ObjectAnimator btn1mAnimator = ObjectAnimator.ofInt(v,"Left",0,800);
        btn1mAnimator.setDuration(5000l);
        btn1mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        btn1mAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //btn1mAnimator.start();
        btn1mAnimators.add(btn1mAnimator);
        btn1mAnimator = ObjectAnimator.ofInt(v,"Right",200,1000);
        btn1mAnimator.setDuration(5000l);
        btn1mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        btn1mAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //btn1mAnimator.start();
        btn1mAnimators.add(btn1mAnimator);
    }

    @OnClick(R.id.btn_anima_4)
    public void onBtn4Click(View v){
        Button btn = (Button)v;
        AnimatorSet mSet = new AnimatorSet();

        ObjectAnimator btn1mAnimator = ObjectAnimator.ofInt(v,"Top",0,400);
        btn1mAnimator.setDuration(5000l);
        btn1mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        btn1mAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //btn1mAnimator.start();
        btn1mAnimators.add(btn1mAnimator);
        ObjectAnimator btn1mAnimator2 = ObjectAnimator.ofInt(v,"Bottom",100,500);
        btn1mAnimator2.setDuration(5000l);
        btn1mAnimator2.setRepeatCount(ValueAnimator.INFINITE);
        btn1mAnimator2.setRepeatMode(ValueAnimator.REVERSE);
        //btn1mAnimator.start();
        btn1mAnimators.add(btn1mAnimator2);

        mSet.playTogether(btn1mAnimators);
        mSet.start();
    }

    private void cancleAnimations(){
        MLog.i("btn1mAnimators.size()="+btn1mAnimators.size());
        if(btn1mAnimators != null && btn1mAnimators.size() > 0){
            for (Animator ani:btn1mAnimators
                 ) {
                if(ani.isRunning()){
                    ani.cancel();
                }

                //MLog.i("ani status="+ani.);


            }
        }
    }

    @Override
    protected void onDestroy() {
        cancleAnimations();
        super.onDestroy();

    }
}
