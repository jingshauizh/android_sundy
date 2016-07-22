package com.jingshuai.android.fregmentapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class FragmentBookList extends Fragment {

    private MyListener mMyListener;
    private RadioButton button1;
    private RadioGroup mRadioGroup;

    public FragmentBookList() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMyListener = (MyListener) getActivity();
        return inflater.inflate(R.layout.fragment_book_list, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        mRadioGroup = (RadioGroup) getActivity().findViewById(R.id.bookSelectGroup);
        mRadioGroup.setOnCheckedChangeListener(new MyTitleCheckedListener());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mMyListener = null;
    }


    public interface MyListener {
        void showDescription(int index);
    }

    class MyTitleCheckedListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            mMyListener.showDescription(checkedId);
        }
    }

}
