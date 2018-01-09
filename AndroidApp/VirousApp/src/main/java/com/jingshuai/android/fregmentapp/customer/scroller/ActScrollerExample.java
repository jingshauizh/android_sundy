package com.jingshuai.android.fregmentapp.customer.scroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jingshuai.android.fregmentapp.R;

public class ActScrollerExample extends AppCompatActivity {

    private LinearLayout layout;

    private Button scrollToBtn;

    private Button scrollByBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_scroller_example);
        layout = (LinearLayout) findViewById(R.id.layout);
        scrollToBtn = (Button) findViewById(R.id.customer_scroller_scroll_to_btn);
        scrollByBtn = (Button) findViewById(R.id.customer_scroller_scroll_by_btn);
        scrollToBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.scrollTo(-60, -100);
            }
        });
        scrollByBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.scrollBy(-60, -100);
            }
        });
    }
}
