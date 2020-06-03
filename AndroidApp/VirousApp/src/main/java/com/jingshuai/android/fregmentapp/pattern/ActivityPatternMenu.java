package com.jingshuai.android.fregmentapp.pattern;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.pattern.patternlist.PaternTestManager;
import com.jingshuai.android.fregmentapp.pattern.patternlist.WebTestManager;

public class ActivityPatternMenu extends AppCompatActivity {

    @BindView(R.id.btn_command_pattern)
    Button hookButton1;




    WebTestManager tWebTestManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command_pattern);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show();
            }
        });
        tWebTestManager = new WebTestManager(ActivityPatternMenu.this);
    }


    @OnClick(R.id.btn_command_pattern) void runTest() {
       //start command pattern test
        PaternTestManager.runTest();
    }

    @OnClick(R.id.btn_command_web_color) void runWebTestColor() {
        //start command pattern test

        tWebTestManager.testCaseErrorColor();
    }
    @OnClick(R.id.btn_command_web_parameter) void runWebTestPara() {
        //start command pattern test
        tWebTestManager.testCaseErrorparams();
    }
    @OnClick(R.id.btn_command_web_chatlist) void runWebTestchatlist() {
        //start command pattern test
        tWebTestManager.testCaseUPActivityChatList();
    }
    @OnClick(R.id.btn_command_web_friends) void runWebTestFriends() {
        //start command pattern test
        tWebTestManager.testCaseUPActivityFriends();
    }
    @OnClick(R.id.btn_command_web_UPActivitySearchOrganize) void runWebTestSearchOrganize() {
        //start command pattern test

        tWebTestManager.testCaseupactivitysearchorganize();
    }
    @OnClick(R.id.btn_command_webUPActivityMine) void runWebTestUPActivityMine() {
        //start command pattern test
        tWebTestManager.testCaseUPActivityMine();
    }
    @OnClick(R.id.btn_command_webUPActivityAllService) void runWebTestService() {
        //start command pattern test
        tWebTestManager.testCaseUPActivityAllService();
    }
    @OnClick(R.id.btn_command_webUPActivityAllGroup) void runWebTestAllgroup() {
        //start command pattern test
        tWebTestManager.testCaseUPActivityAllGroup();
    }

}
