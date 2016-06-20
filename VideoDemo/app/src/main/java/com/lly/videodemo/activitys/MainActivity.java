package com.lly.videodemo.activitys;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.lly.videodemo.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.btn1)
    private Button btn1;


    @Event(value = {R.id.btn1})
    private void onViewClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn1:
                intent.setClass(this, LocalVideoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
