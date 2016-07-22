package com.jingshuai.android.fregmentapp;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentDescription extends Fragment {

    private static String mDescription;
    private TextView mBookDescriptionTextView;
    private String[] mBookDescriptions;


    public FragmentDescription() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewHierarchy = inflater.inflate(R.layout.fragment_book_desc, container, false);
        mBookDescriptionTextView = (TextView) viewHierarchy.findViewById(R.id.textView);
        return viewHierarchy;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setBook(int index) {
        String bookDescription = getResources().getString(R.string.dynamicUiDescription);
        String bookDescription2 = getResources().getString(R.string.dynamicUiDescription2);
        if (index == R.id.dynamicUiBook1) {
            mBookDescriptionTextView.setText(bookDescription);
        } else {
            mBookDescriptionTextView.setText(bookDescription2);
        }
    }


}
