package com.jingshuai.android.fregmentapp.test.commonadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.activity.DescActivity;
import com.jingshuai.android.fregmentapp.fragment.TitleDescriptionFragment;
import com.jingshuai.android.fregmentapp.interfaces.OnListFragmentIndexChangeListenerIF;


public class CommonAdapterActivity extends FragmentActivity implements OnListFragmentIndexChangeListenerIF {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_common_adapter);
    }

    @Override
    public void onListFragmentInteraction(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Get the book description fragment
        TitleDescriptionFragment bookDescFragment = (TitleDescriptionFragment)
                fragmentManager.findFragmentById
                        (R.id.cu_fragmentDescription);
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
