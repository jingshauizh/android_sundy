package com.jingshuai.android.fregmentapp.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.fragment.BookListFragment;
import com.jingshuai.android.fregmentapp.fragment.DescriptionFragment;

public class MainActivity extends Activity implements BookListFragment.MyListener{

    private final String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main_fregment);
    }

    public void onSelectedBookChanged(int bookIndex) {
        FragmentManager fragmentManager = getFragmentManager();
        DescriptionFragment bookDescFragment = (DescriptionFragment)fragmentManager.findFragmentById(R.id.fragmentDescription);
        bookDescFragment.setBook(bookIndex);
    }

    @Override
    public void showDescription(int index) {
        if(1 == index){
            //show desc1
            Log.i(TAG,"index="+index);
        }
        else{
            //show desc2
            Log.i(TAG,"index="+index);
        }
        onSelectedBookChanged(index);
    }
}
