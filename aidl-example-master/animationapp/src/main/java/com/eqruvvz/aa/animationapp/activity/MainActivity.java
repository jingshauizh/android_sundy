package com.eqruvvz.aa.animationapp.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.eqruvvz.aa.animationapp.R;

public class MainActivity extends ActivityBase {

    private ListView lv_main_activity;
    private ArrayAdapter lvMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        initMainListView();

    }

    private void initMainListView(){
        lv_main_activity = (ListView)findViewById(R.id.lv_main_activity);
        String [] menuList = this.getResources().getStringArray(R.array.MenuActivityMain);
        lvMainAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, menuList);
        lv_main_activity.setAdapter(lvMainAdapter);
        lv_main_activity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_value_animator) {
            this.openActivity(ObejctAnimActivity.class);
        }

        if (id == R.id.action_roatate) {
            this.openActivity(ActivityRoatate.class);
        }

        return super.onOptionsItemSelected(item);
    }
}
