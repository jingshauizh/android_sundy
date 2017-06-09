package com.jingshuai.android.fregmentapp.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import com.jingshuai.android.fregmentapp.fragment.BookListFragment;
import com.jingshuai.android.fregmentapp.fragment.DescriptionFragment;
import com.jingshuai.android.fregmentapp.R;

public class ListActivity extends Activity implements BookListFragment.MyListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_activity_list);
    }

    @Override
    public void showDescription(int index) {
        FragmentManager fragmentManager = getFragmentManager();
        // Get the book description fragment
        DescriptionFragment bookDescFragment = (DescriptionFragment)
                fragmentManager.findFragmentById
                        (R.id.fragmentDescription);
        // Display the book title
        if (bookDescFragment == null || !bookDescFragment.isVisible()) {
            Intent intent = new Intent(this, DescActivity.class);
            intent.putExtra("bookIndex", index);
            startActivity(intent);
        } else {
            // Use contained fragment to display description
            bookDescFragment.setBook(index);
        }
    }

}

