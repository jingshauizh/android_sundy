package com.example.appmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SubMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
       // menu.clear();
        //getMenuInflater().inflate(R.menu.game_main, menu);
        for(int i=0;i<menu.size();i++){
            int id = menu.getItem(i).getItemId();
            if (id == R.id.game_settings18) {
                menu.add(0,34,198,"aaaadddddda");
                SubMenu _SubMenu= menu.addSubMenu(0,35,193,"sub aaaadddddda");
                _SubMenu.add(0, 1, 0, "红色");
                _SubMenu.add(0, 2, 0, "绿色");
                _SubMenu.add(0, 3, 0, "蓝色");
                break;
            }
        }


        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.game_settings18) {
//            Toast.makeText(this,"item order="+item.getOrder(),Toast.LENGTH_SHORT).show();
//            return true;
//        }
        Intent _intent = new Intent(MainActivity.this,ActivityMenu.class);
        startActivity(_intent);
        return super.onOptionsItemSelected(item);
    }
}
