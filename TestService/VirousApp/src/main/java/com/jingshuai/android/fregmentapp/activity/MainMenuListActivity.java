package com.jingshuai.android.fregmentapp.activity;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.interfaces.OnListFragmentIndexChangeListenerIF;
import com.jingshuai.android.fregmentapp.test.commonadapter.CommonAdapterActivity;
import com.jingshuai.android.fregmentapp.test.okhttp.OkHttpActivity;
import com.jingshuai.appcommonlib.activity.ActivityLibBase;

/**
 * Created by eqruvvz on 8/1/2016.
 */
public class MainMenuListActivity extends ActivityLibBase implements OnListFragmentIndexChangeListenerIF {
    @Override
    public void setContentView() {
        setContentView(R.layout.act_main_menu_list);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected boolean cancelRequest() {
        return false;
    }

    @Override
    public void onListFragmentInteraction(int index) {
        switch(index){
            case 0:
                openActivity(TitlesActivity.class);
                break;
            case 1:
                openActivity(MainActivity.class);
                break;
            case 2:
                openActivity(OkHttpActivity.class);
                break;
            case 3:
                openActivity(CommonAdapterActivity.class);
                break;
            default:
                openActivity(TitlesActivity.class);
                break;
        }
    }
}
