package com.jingshuai.android.fregmentapp.customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jingshuai.android.fregmentapp.R;

public class Customer_View_Activity extends AppCompatActivity {
    private RadarView mRadarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_iod_view);
       // mRadarView = (RadarView)findViewById(R.id.radarView);
       // mRadarView.setTextValue("876");
    }
}
