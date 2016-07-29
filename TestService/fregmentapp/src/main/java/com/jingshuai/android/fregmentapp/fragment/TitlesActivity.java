package com.jingshuai.android.fregmentapp.fragment;


import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.activity.DescActivity;
import com.jingshuai.android.fregmentapp.fragment.dummy.DummyContent;
import com.jingshuai.android.fregmentapp.interfaces.OnButtonClickListener;

public class TitlesActivity extends FragmentActivity implements TitlesFragment.OnListFragmentInteractionListener,OnListFragmentIndexChangeListener,OnButtonClickListener {
    private Button mbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fragment_titlelist);
        mbutton = (Button)findViewById(R.id.btn_titlesactivity_open_dialog);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragment theDialog = new MyDialogFragment();
                theDialog.show(getFragmentManager(), null);
            }
        });
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

    @Override
    public void onListFragmentInteraction(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Get the book description fragment
        TitleDescriptionFragment bookDescFragment = (TitleDescriptionFragment)
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

    @Override
    public void onButtonClick(int buttonId) {

    }
}

