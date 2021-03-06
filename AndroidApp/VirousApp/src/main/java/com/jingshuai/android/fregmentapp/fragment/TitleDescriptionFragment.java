package com.jingshuai.android.fregmentapp.fragment;

import android.support.v4.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.appcommonlib.log.MLog;


public class TitleDescriptionFragment extends Fragment {
    private final String LOG_TAG = "TitleDesc";
    private static String mDescription;
    private TextView mBookDescriptionTextView;
    private String[] mBookDescriptions;

    public TitleDescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewHierarchy = inflater.inflate(R.layout.frag_book_desc, container, false);
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
        Log.i(LOG_TAG,"index="+index);
        MLog.i("index="+index);
        if (index == 1) {
            mBookDescriptionTextView.setText(bookDescription);
        } else {
            mBookDescriptionTextView.setText(bookDescription2);
        }
    }
}
