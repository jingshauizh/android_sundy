package com.jingshuai.android.fregmentapp.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.interfaces.OnButtonClickListenerIF;

/**
 * Created by eqruvvz on 7/26/2016.
 */
public class MyDialogFragment extends DialogFragment implements View.OnClickListener{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View theView = inflater.inflate(R.layout.frag_my_dialog,
                container, false);
        View yesButton = theView.findViewById(R.id.btnYes);
        yesButton.setOnClickListener(this);
        yesButton.requestFocus();
    // Connect the No button click event
        View noButton = theView.findViewById(R.id.btnNo);
        noButton.setOnClickListener(this);
        return theView;
    }



    @Override
    public void onClick(View v) {
        int buttonId = v.getId();
        // Notify the Activity of the button selection
        OnButtonClickListenerIF parentActivity =
                (OnButtonClickListenerIF) getActivity();
        parentActivity.onButtonClick(buttonId);
        // Close the dialog fragment
        dismiss();
    }
}
