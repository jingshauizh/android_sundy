package jings.ex.android.com.dagger2demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import javax.inject.Inject;
import jings.ex.android.com.dagger2demo.di.module.component.DaggerDataBaseComponenet;
import jings.ex.android.com.dagger2demo.di.module.component.DaggerHttpComponent;
import jings.ex.android.com.dagger2demo.entity.DatabaseModuleObj;
import jings.ex.android.com.dagger2demo.entity.HttpModuleObj;

public class Main2Activity extends AppCompatActivity {
    @Inject
    HttpModuleObj httpModuleObj;

    @Inject
    DatabaseModuleObj databaseModuleObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show();
            }
        });

        DaggerDataBaseComponenet.builder().httpComponent(DaggerHttpComponent.builder().build()).build().injectMain2Activity(this);

        Log.i("zjs","httpModuleObj="+httpModuleObj.hashCode());
        Log.i("zjs","databaseModuleObj="+databaseModuleObj.hashCode());
    }
}
