package com.mvp.jingshuai.leakcanaryapp.LeakActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mvp.jingshuai.leakcanaryapp.MainActivity;
import com.mvp.jingshuai.leakcanaryapp.MenuActivity;
import com.mvp.jingshuai.leakcanaryapp.R;

public class InnerClassActivity extends AppCompatActivity {
    private static Object inner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_static_activitys);
        Button saButton = findViewById(R.id.btn_static_activitys);
        saButton.setText("InnerClassActivity");
        saButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createInnerClass();
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
    }


    void createInnerClass() {
        class InnerClass {
        }
        inner = new InnerClass();
    }
}
