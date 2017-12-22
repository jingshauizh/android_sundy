package com.mvp.jingshuai.leakcanaryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.mvp.jingshuai.leakcanaryapp.LeakActivity.FileNotCloseLeakActivity;
import com.mvp.jingshuai.leakcanaryapp.LeakActivity.StaticActivitysAct;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MainActivity extends Activity {

    private TestHelper mTestHelper;
    @BindView(R.id.btn_Static)
    Button gotoTestBtn;

    private Unbinder mUnbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTestHelper = TestHelper.getOutInstance(this);
        mUnbinder = ButterKnife.bind(this);


    }
    @OnClick(R.id.btn_Static)
    public void gotoTestBtnClick(View v){
        goToTest();
    }

    private void goToTest() {
        Intent intent = new Intent(this, StaticActivitysAct.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_go_to_test) {
            goToTest();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //utterKnife移除了ButterKnife.unBind()方法,当时取而代之的是ButterKnife.bind(this)会返回一个Unbinder的引用,通过Unbinder的unbind()方法进行unbind.
        mUnbinder.unbind();
        ExampleApplication.getmRefWatcher().watch(this);
    }
}
