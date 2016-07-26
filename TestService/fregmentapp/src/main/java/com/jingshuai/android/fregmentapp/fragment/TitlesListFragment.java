package com.jingshuai.android.fregmentapp.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jingshuai.android.fregmentapp.R;

/**
 * Created by eqruvvz on 7/26/2016.
 */
public class TitlesListFragment extends ListFragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] bookTitles =
                getResources().getStringArray(R.array.bookTitles);
        ArrayAdapter<String> bookTitlesAdapter =
                new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, bookTitles);
        setListAdapter(bookTitlesAdapter);
    }


    @Override
    public void onListItemClick(ListView l, View v,int position, long id) {
        // Access the Activity and cast to the inteface
        OnListFragmentIndexChangeListener listener =
                (OnListFragmentIndexChangeListener)getActivity();
        // Notify the Activity of the selection
        listener.onListFragmentInteraction(position);
    }
}
