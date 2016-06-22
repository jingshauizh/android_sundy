package com.example.eventapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eventapp.R;

public class AcDialog extends Activity {

    private Button returnButton;
    private EditText inputEditor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_dialog);

        returnButton = (Button)findViewById(R.id.returnButton);
        inputEditor = (EditText)findViewById(R.id.et);

        //和前面一样，只是用到了返回式Activity的基本方法，虽然这里已经是个Dialog了，但却和普通Activity无异
        returnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String input = inputEditor.getText().toString();
                Intent i = new Intent(AcDialog.this, ActivityEvent.class);
                Bundle b = new Bundle();
                b.putString("str", input);
                i.putExtras(b);
                AcDialog.this.setResult(RESULT_OK, i);
                AcDialog.this.finish();
            }
        });
    }
}

