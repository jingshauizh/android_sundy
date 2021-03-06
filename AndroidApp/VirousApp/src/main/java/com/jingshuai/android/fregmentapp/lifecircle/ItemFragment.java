package com.jingshuai.android.fregmentapp.lifecircle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.lifecircle.dummy.DummyContent;
import com.jingshuai.android.fregmentapp.lifecircle.dummy.DummyContent.DummyItem;
import com.jingshuai.appcommonlib.log.MLog;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
        MLog.d("ItemFragment constractor");
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        MLog.d("ItemFragment onCreate");
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list2, container, false);
        MLog.d("ItemFragment onCreateView");
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        MLog.d("ItemFragment onAttach");
        super.onAttach(context);

        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onStart() {
        MLog.d("ItemFragment onStart");
        super.onStart();

    }

    @Override
    public void onResume() {
        MLog.d("ItemFragment onResume");
        super.onResume();

    }

    @Override
    public void onPause() {
        MLog.d("ItemFragment onPause");
        super.onPause();

    }

    @Override
    public void onStop() {
        MLog.d("ItemFragment onStop");
        super.onStop();

    }

    @Override
    public void onDestroyView() {
        MLog.d("ItemFragment onDestroyView");
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        MLog.d("ItemFragment onDestroy");
        super.onDestroy();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        MLog.d("ItemFragment onActivityCreated");
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDetach() {
        MLog.d("ItemFragment onDetach");
        super.onDetach();

        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
