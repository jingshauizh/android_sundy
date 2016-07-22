package com.jingshuai.android.fregmentapp.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import com.jingshuai.android.fregmentapp.FragmentDescription;
import com.jingshuai.android.fregmentapp.R;

public class DescActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);

        Intent intent = getIntent();
        int bookIndex = intent.getIntExtra("bookIndex", -1);
        if (bookIndex != -1) {
            // Use FragmentManager to access BookDescFragment
            FragmentManager fm = getFragmentManager();
            FragmentDescription bookDescFragment = (FragmentDescription)
                    fm.findFragmentById(R.id.fragmentDescription);
            // Display the book title
            bookDescFragment.setBook(bookIndex);
        }

    }

}
