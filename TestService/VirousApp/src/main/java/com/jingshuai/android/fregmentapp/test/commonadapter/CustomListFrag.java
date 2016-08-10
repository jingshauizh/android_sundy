package com.jingshuai.android.fregmentapp.test.commonadapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.dummy.DummyContent;
import com.jingshuai.android.fregmentapp.interfaces.OnListFragmentIndexChangeListenerIF;
import com.jingshuai.appcommonlib.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomListFrag extends Fragment {


    private OnListFragmentIndexChangeListenerIF mListener;
    private ListView mListView;
    private CommonAdapter mCommonAdapter;

    public CustomListFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.frag_custom_list, container, false);
        mListView = (ListView)view.findViewById(R.id.lv_custom_frag);
        List<DummyContent.DummyItem> titleList = initListItem();
        mCommonAdapter = new ListAdapter(getActivity(),titleList,R.layout.item_fragment);
        mListView.setAdapter(mCommonAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onListFragmentInteraction(position);
            }
        });
        return view;
    }

    private List<DummyContent.DummyItem> initListItem(){
        String[] bookTitles = getResources().getStringArray(R.array.bookTitles);
        List<DummyContent.DummyItem> tempList = new ArrayList<DummyContent.DummyItem>();
        for (int i=0;i<bookTitles.length;i++) {
            DummyContent.DummyItem tempItem = new DummyContent.DummyItem(String.valueOf(i),bookTitles[i],null);
            tempList.add(tempItem);
        }
        return tempList;
    }

/*    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onListFragmentInteraction(0);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentIndexChangeListenerIF) {
            mListener = (OnListFragmentIndexChangeListenerIF) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
