package com.eqruvvz.aa.animationapp.activity.fregment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.eqruvvz.aa.animationapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ActivityRoatateFragment extends Fragment {

    private Button rotateButton = null;
    private Button scaleButton = null;
    private Button alphaButton = null;
    private Button translateButton = null;
    private ImageView image = null;
    protected void showMsg(String msg){
        Integer duration =1000;
        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();

    }
    public ActivityRoatateFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view =  inflater.inflate(R.layout.fragment_activity_roatate, container, false);
        rotateButton = (Button)_view.findViewById(R.id.rotateButton);
        scaleButton = (Button)_view.findViewById(R.id.scaleButton);
        alphaButton = (Button)_view.findViewById(R.id.alphaButton);
        translateButton = (Button)_view.findViewById(R.id.translateButton);
        image = (ImageView)_view.findViewById(R.id.image);

        rotateButton.setOnClickListener(new RotateButtonListener());
        scaleButton.setOnClickListener(new ScaleButtonListener());
        alphaButton.setOnClickListener(new AlphaButtonListener());
        translateButton.setOnClickListener(new TranslateButtonListener());
        return _view;
    }

    class AlphaButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            //创建一个AnimationSet对象，参数为Boolean型，
            //true表示使用Animation的interpolator，false则是使用自己的
            AnimationSet animationSet = new AnimationSet(true);
            //创建一个AlphaAnimation对象，参数从完全的透明度，到完全的不透明
            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
            //设置动画执行的时间
            alphaAnimation.setDuration(2000);
            //将alphaAnimation对象添加到AnimationSet当中
            animationSet.addAnimation(alphaAnimation);
            //使用ImageView的startAnimation方法执行动画
            image.startAnimation(animationSet);
        }
    }
    class RotateButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            AnimationSet animationSet = new AnimationSet(true);
            //参数1：从哪个旋转角度开始
            //参数2：转到什么角度
            //后4个参数用于设置围绕着旋转的圆的圆心在哪里
            //参数3：确定x轴坐标的类型，有ABSOLUT绝对坐标、RELATIVE_TO_SELF相对于自身坐标、RELATIVE_TO_PARENT相对于父控件的坐标
            //参数4：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
            //参数5：确定y轴坐标的类型
            //参数6：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
            RotateAnimation rotateAnimation = new RotateAnimation(0, 36000,
                    Animation.RELATIVE_TO_SELF,0.3f,
                    Animation.RELATIVE_TO_SELF,0.3f);
            rotateAnimation.setDuration(3000);
            animationSet.addAnimation(rotateAnimation);
            image.startAnimation(animationSet);
        }
    }

    private class DeleteAnimationListener implements Animation.AnimationListener {

        public void onAnimationEnd(Animation animation) {
            // TODO Auto-generated method stub
            image.setImageResource(R.drawable.bauty2);
        }

        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub

        }

        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub

        }

    }





    class ScaleButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            AnimationSet animationSet = new AnimationSet(true);


            //参数1：x轴的初始值
            //参数2：x轴收缩后的值
            //参数3：y轴的初始值
            //参数4：y轴收缩后的值
            //参数5：确定x轴坐标的类型
            //参数6：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
            //参数7：确定y轴坐标的类型
            //参数8：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    1f, 0.0f,1f,1f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);
            scaleAnimation.setDuration(2000);
            scaleAnimation.setStartOffset(0);
            animationSet.addAnimation(scaleAnimation);
            animationSet.setAnimationListener(new DeleteAnimationListener());
            //image.startAnimation(animationSet);

            ScaleAnimation scaleAnimation2 = new ScaleAnimation(
                    0f, 1f,1f,1f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);
            scaleAnimation2.setDuration(2000);
            scaleAnimation2.setStartOffset(2000l);
            animationSet.addAnimation(scaleAnimation2);
            image.startAnimation(animationSet);
        }
    }
    class TranslateButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            AnimationSet animationSet = new AnimationSet(true);
            //参数1～2：x轴的开始位置
            //参数3～4：y轴的开始位置
            //参数5～6：x轴的结束位置
            //参数7～8：y轴的结束位置
            TranslateAnimation translateAnimation =
                    new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF,0f,
                            Animation.RELATIVE_TO_PARENT,0.5f,
                            Animation.RELATIVE_TO_SELF,0f,
                            Animation.RELATIVE_TO_PARENT,0.5f);
            translateAnimation.setDuration(1000);

            TranslateAnimation translateAnimation2 =
                    new TranslateAnimation(
                            Animation.RELATIVE_TO_PARENT,0f,
                            Animation.RELATIVE_TO_PARENT,0.5f,
                            Animation.RELATIVE_TO_PARENT,1f,
                            Animation.RELATIVE_TO_PARENT,0.5f);
            translateAnimation.setDuration(1000);

            TranslateAnimation translateAnimation3 =
                    new TranslateAnimation(
                            Animation.RELATIVE_TO_PARENT,1f,
                            Animation.RELATIVE_TO_PARENT,0.5f,
                            Animation.RELATIVE_TO_PARENT,1f,
                            Animation.RELATIVE_TO_PARENT,0.5f);
            translateAnimation.setDuration(1000);

            TranslateAnimation translateAnimation4 =
                    new TranslateAnimation(
                            Animation.RELATIVE_TO_PARENT,0f,
                            Animation.RELATIVE_TO_PARENT,0.5f,
                            Animation.RELATIVE_TO_PARENT,1f,
                            Animation.RELATIVE_TO_PARENT,0.5f);
            translateAnimation.setDuration(1000);

            TranslateAnimation translateAnimation5 =
                    new TranslateAnimation(
                            Animation.RELATIVE_TO_PARENT,0f,
                            Animation.RELATIVE_TO_PARENT,1f,
                            Animation.RELATIVE_TO_PARENT,0f,
                            Animation.RELATIVE_TO_PARENT,1f);
            translateAnimation.setDuration(1000);

            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
            //设置动画执行的时间
            alphaAnimation.setDuration(1000);
            animationSet.setRepeatMode(Animation.RESTART);
            animationSet.addAnimation(translateAnimation);
            animationSet.addAnimation(translateAnimation2);
            animationSet.addAnimation(translateAnimation3);
            animationSet.addAnimation(translateAnimation4);
            animationSet.addAnimation(translateAnimation5);
            animationSet.addAnimation(alphaAnimation);
            animationSet.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    image.getLayoutParams().width=0;
                    image.getLayoutParams().height=0;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            image.startAnimation(animationSet);

        }
    }
}
