package jings.ex.android.com.dagger2demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import javax.inject.Inject;

import jings.ex.android.com.dagger2demo.di.module.component.DaggerDataBaseComponenet;
import jings.ex.android.com.dagger2demo.di.module.component.DaggerHttpComponent;
import jings.ex.android.com.dagger2demo.di.module.component.HttpComponent;
import jings.ex.android.com.dagger2demo.entity.DatabaseModuleObj;
import jings.ex.android.com.dagger2demo.entity.HttpModuleObj;
import jings.ex.android.com.dagger2demo.scope.AppScope;
import jings.ex.android.com.dagger2demo.scope.UserScope;

public class MainActivity extends AppCompatActivity {

    @Inject
    HttpModuleObj httpModuleObj;
    @Inject
    HttpModuleObj httpModuleObj2;

    @Inject
    DatabaseModuleObj databaseModuleObj;

    @Inject
    DatabaseModuleObj databaseModuleObj2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerDataBaseComponenet.builder().httpComponent(DaggerHttpComponent.builder().build()).build().injectMainActivity(this);

        Log.i("zjs","httpModuleObj="+httpModuleObj.hashCode());
        Log.i("zjs","databaseModuleObj="+databaseModuleObj.hashCode());
        int zjs = Log.i("zjs", "databaseModuleObj2=" + databaseModuleObj2.hashCode());

        findViewById(R.id.btn_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tIntent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(tIntent);
            }
        });

        findViewById(R.id.btn_id).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });


    }
}
