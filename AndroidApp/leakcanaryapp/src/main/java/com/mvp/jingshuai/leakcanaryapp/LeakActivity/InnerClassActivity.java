package com.mvp.jingshuai.leakcanaryapp.LeakActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jingshuai.appcommonlib.log.MLog;
import com.mvp.jingshuai.leakcanaryapp.ExampleApplication;
import com.mvp.jingshuai.leakcanaryapp.LeakBaseActivity;
import com.mvp.jingshuai.leakcanaryapp.MainActivity;
import com.mvp.jingshuai.leakcanaryapp.MenuActivity;
import com.mvp.jingshuai.leakcanaryapp.R;
/*****
 *
 *
 * 其实内部类都会持有一个外部类引用，这里这个外部类就是MainActivity，
 * 然而内部类实例又是static静态变量其生命周期与Application生命周期一样，
 * 所以在MainActivity关闭的时候，内部类静态实例依然持有对MainActivity的引用，
 * 导致MainActivity无法被回收释放，引发内存泄漏。LeakCanary检测内存泄漏结果如下：
 *
 * 对于这种泄漏的解决办法就是将内部类改成静态内部类，不再持有对MainActivity的引用即可，
 *
 *
 * ****/
public class InnerClassActivity extends LeakBaseActivity {
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
//                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
//                startActivity(intent);
                MLog.i("Act_innerClass  finish()");
                InnerClassActivity.this.finish();
            }
        });

        //finish();
    }


    void createInnerClass() {

        inner = new InnerClass();
    }

//    static class InnerClass {
//    }

    class InnerClass {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
