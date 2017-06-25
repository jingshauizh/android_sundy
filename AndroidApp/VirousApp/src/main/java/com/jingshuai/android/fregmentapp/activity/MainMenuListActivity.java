package com.jingshuai.android.fregmentapp.activity;

import com.jingshuai.android.fregmentapp.event.EventActivity;
import com.jingshuai.android.fregmentapp.hookviewpac.HookViewActivity;
import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.customer.Customer_View_Activity;
import com.jingshuai.android.fregmentapp.eventbus.MainEventActivity;
import com.jingshuai.android.fregmentapp.interfaces.OnListFragmentIndexChangeListenerIF;
import com.jingshuai.android.fregmentapp.service.ServiceActivity;
import com.jingshuai.android.fregmentapp.test.commonadapter.CommonAdapterActivity;
import com.jingshuai.android.fregmentapp.test.okhttp.OkHttpActivity;
import com.jingshuai.appcommonlib.activity.ActivityLibBase;
import com.jingshuai.android.fregmentapp.service.binder_aidl.activity.*;
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
            case 4:
                openActivity(Customer_View_Activity.class);
                break;
            case 5:
                openActivity(ServiceActivity.class);
                break;
            case 6:
                openActivity(MainEventActivity.class);
                break;
            case 7:
                openActivity(HookViewActivity.class);
                break;
            //

            case 8:
                openActivity(AIDLMainActivity.class);
                break;
            case 9:
                openActivity(CustomMainActivity.class);
                break;
            case 10:
                openActivity(AIDLIODActivity.class);
                break;
            case 11:
                openActivity(IODActivity.class);
                break;
            case 12:
                openActivity(EventActivity.class);
                break;
            default:
                openActivity(TitlesActivity.class);
                break;
        }
    }
}
