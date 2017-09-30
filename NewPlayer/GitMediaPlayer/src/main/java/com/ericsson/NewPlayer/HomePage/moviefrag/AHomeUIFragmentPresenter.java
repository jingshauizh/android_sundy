/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package com.ericsson.NewPlayer.HomePage.moviefrag;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ericsson.NewPlayer.HomePage.base.HeaderViewPagerFragment;
import com.ericsson.mvp.presenter.IPresenter;
import com.ericsson.mvp.util.GenericUtil;
import com.ericsson.mvp.view.IView;

public abstract class AHomeUIFragmentPresenter<T extends IView> extends HeaderViewPagerFragment implements IPresenter<T> {
    protected T view;
    private final String TAG = "AHomeUIFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.i(TAG, "AHomeUIFragmentPresenter start onCreateViw");
        super.onCreateView(inflater, container, savedInstanceState);
        try {
            view = getViewClass().newInstance();
            View _view = view.create(inflater, container);
            view.bindPresenter(this);
            view.bindEvent();
            created(savedInstanceState);
            Log.i(TAG, "AHomeUIFragmentPresenter start onCreateViw finished");
            return _view;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public Class<T> getViewClass() {
        // TODO Auto-generated method stub
        return GenericUtil.getViewClass(getClass());
    }

    @Override
    public void create(Bundle savedInstance) {
        // TODO Auto-generated method stub

    }

    @Override
    public void created(Bundle savedInstance) {
        // TODO Auto-generated method stub

    }
}
