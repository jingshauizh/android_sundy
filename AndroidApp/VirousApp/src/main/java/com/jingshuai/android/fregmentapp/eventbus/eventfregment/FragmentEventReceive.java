package com.jingshuai.android.fregmentapp.eventbus.eventfregment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.eventbus.FirstEvent;
import com.jingshuai.appcommonlib.log.MLog;

import org.greenrobot.eventbus.*;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentEventReceive.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentEventReceive#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEventReceive extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private final String TAG = "FragmentEventReceive";

    private OnFragmentInteractionListener mListener;
    private Button mbutton;
    private TextView tv;

    public FragmentEventReceive() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEventReceive.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEventReceive newInstance(String param1, String param2) {
        FragmentEventReceive fragment = new FragmentEventReceive();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //注册EventBus
        EventBus.getDefault().register(this);
        View fregView = inflater.inflate(R.layout.fragment_fragment_event_receive, container, false);
        tv = (TextView)fregView.findViewById(R.id.tv2);
        mbutton =(Button) fregView.findViewById(R.id.sendmessage2);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MLog.i(TAG, "send message from FragmentEventReceive");
                EventBus.getDefault().post(
                        new FirstEvent("FragmentEventReceive btn clicked"));
            }
        });
        return fregView;

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEventMainThread(FirstEvent event) {

        String msg = "onEventMainThread收到了消息：" + event.getMsg();
        MLog.i(TAG, msg);
        tv.setText(msg);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
