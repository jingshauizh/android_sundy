package com.example.eventapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.eventapp.AnimationActivity;
import com.example.eventapp.R;

public class ActivitMenu extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_activit_menu);
        this.setContentView(this.addRelativeLayout());
    }


    private RelativeLayout addRelativeLayout() {
        RelativeLayout container = new RelativeLayout(this);
        Button btn1 = new Button(this);
        btn1.setId(R.id.button_id_1);
        btn1.setText("上");
        Button btn2 = new Button(this);
        btn2.setId(R.id.button_id_2);
        btn2.setText("下");
        Button btn3 = new Button(this);
        btn3.setId(R.id.button_id_3);
        btn3.setText("左");
        Button btn4 = new Button(this);
        btn4.setId(R.id.button_id_4);
        btn4.setText("右");
        Button btn5 = new Button(this);
        btn5.setId(R.id.button_id_5);
        btn5.setText("中");
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(100,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams lp2 =new RelativeLayout.LayoutParams(100,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(100,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(100,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams(100,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp5.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        lp1.addRule(RelativeLayout.ABOVE, btn5.getId());
        lp1.addRule(RelativeLayout.ALIGN_LEFT, btn5.getId());
        lp2.addRule(RelativeLayout.BELOW, btn5.getId());
        lp2.addRule(RelativeLayout.ALIGN_LEFT, btn5.getId());
        lp3.addRule(RelativeLayout.LEFT_OF, btn5.getId());
        lp3.addRule(RelativeLayout.ALIGN_BASELINE, btn5.getId());
        lp4.addRule(RelativeLayout.RIGHT_OF, btn5.getId());
        lp4.addRule(RelativeLayout.ALIGN_TOP, btn5.getId());

        container.addView(btn5, lp5);
        container.addView(btn1, lp1);
        container.addView(btn2, lp2);
        container.addView(btn3, lp3);
        container.addView(btn4, lp4);

        return container;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_activity_event){
            this.openActivity(ActivityEvent.class);
        }
        if(id == R.id.action_activity_animation){
            this.openActivity(AnimationActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }
}
