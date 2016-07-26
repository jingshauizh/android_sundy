package com.jingshuai.android.fregmentapp.fragment;


import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.activity.DescActivity;
import com.jingshuai.android.fregmentapp.fragment.dummy.DummyContent;

public class TitlesActivity extends FragmentActivity implements TitlesFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fragment_titlelist);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Get the book description fragment
        TitleDescriptionFragment bookDescFragment = (TitleDescriptionFragment)
                fragmentManager.findFragmentById
                        (R.id.fragmentDescription);
        // Display the book title
        if (bookDescFragment == null || !bookDescFragment.isVisible()) {
            Intent intent = new Intent(this, DescActivity.class);
            intent.putExtra("bookIndex", item.id);
            startActivity(intent);
        } else {
            // Use contained fragment to display description
            bookDescFragment.setBook(Integer.valueOf(item.id));
        }
    }



}

