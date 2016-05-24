package com.eqruvvz.aa.animationapp.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.eqruvvz.aa.animationapp.R;
import com.eqruvvz.aa.animationapp.activity.util.AnimationUtil;

public class ObejctAnimActivity extends ActivityBase {

    private Button mButtonAnima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obejct_anim);
        mButtonAnima = (Button)this.findViewById(R.id.button_anima_btn);

        mButtonAnima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMsg("btn clicked");
                //AnimationUtil.performAnimate(mButtonAnima, mButtonAnima.getWidth(), 1000);
                AnimationUtil.performAnimate(mButtonAnima);
            }
        });
    }
}
