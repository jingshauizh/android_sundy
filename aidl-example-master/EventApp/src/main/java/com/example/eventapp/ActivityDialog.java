package com.example.eventapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class ActivityDialog extends Activity {
    private Button mDialogButton;
    private AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_dialog);
        mDialogButton = (Button)findViewById(R.id.DialogButton);
        mDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(ActivityDialog.this);
                View longinDialogView = layoutInflater.inflate(R.layout.setting_layout, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityDialog.this);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("this is the content view!!!");
                builder.setTitle("this is the title view!!!");
                builder.setView(longinDialogView);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}
