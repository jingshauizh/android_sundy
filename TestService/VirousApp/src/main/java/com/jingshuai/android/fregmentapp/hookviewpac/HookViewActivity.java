/*****
 * http://blog.csdn.net/wangwangli6/article/details/63687748
 *  Hook技术之View点击劫持
 */
package com.jingshuai.android.fregmentapp.hookviewpac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jingshuai.android.fregmentapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HookViewActivity extends AppCompatActivity {

    @BindView(R.id.btnHook1)
     Button hookButton1;
    @BindView(R.id.btnHook2)
     Button hookButton2;
    @BindView(R.id.mlinearLayout)
     LinearLayout mLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook_view);
        ButterKnife.bind(this);
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                HookViewClickUtil.hookView(mLinearLayout);
            }
        });
    }



    @OnClick(R.id.btnHook1) void sayHello() {
        Log.i("TAG","btnHook1 clicked");
    }
    @OnClick(R.id.btnHook2) void sayHello1() {
        Log.i("TAG","btnHook2 clicked");
    }
    @OnClick(R.id.mlinearLayout) void sayHello2() {
        Log.i("TAG","mLinearLayout clicked");
    }
}
