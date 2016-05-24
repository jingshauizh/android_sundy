package com.eqruvvz.aa.animationapp.activity.util;

import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by Administrator on 16-5-12.
 */
public final class AnimationUtil {



    public static void performAnimate(final View target,final int start, final int end){
        ValueAnimator _ValueAnimator = ValueAnimator.ofInt(start,end);
        _ValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private IntEvaluator mEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int currentValue = (Integer) animation.getAnimatedValue();
                float fraction = animation.getAnimatedFraction();
                target.getLayoutParams().width = mEvaluator.evaluate(fraction, start, end);
                target.requestLayout();
            }


        });
        _ValueAnimator.setRepeatMode(ValueAnimator.INFINITE);

        _ValueAnimator.setDuration(5000).start();
    }

    public static void performAnimate(View pTarget){
        ViewWrapper wrapper = new ViewWrapper(pTarget);
        ObjectAnimator.ofInt(wrapper,"width",1000).setDuration(5000).start();
    }

    private static class ViewWrapper{
        private View mTarget;
        public ViewWrapper(View ptarget){
            this.mTarget=ptarget;
        }

        public int getWidth(){
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int pWidth){
            mTarget.getLayoutParams().width = pWidth;
            mTarget.requestLayout();
        }
    }

}
