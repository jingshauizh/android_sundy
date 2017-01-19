package com.mvp.jingshuai.android_iod.datamapper;

import android.content.Context;

import com.mvp.jingshuai.android_iod.api.ApiModule;
import com.mvp.jingshuai.android_iod.api.ApiService;
import com.mvp.jingshuai.android_iod.api.InfoResponse;
import com.mvp.jingshuai.android_iod.config.DemoConfig;
import com.mvp.jingshuai.data.model.InfoObjectModel;
import com.mvp.jingshuai.domain.BusinessIF;

import java.io.IOException;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Response;

/**
 * Created by eqruvvz on 1/11/2017.
 */
public class Dataimpl implements BusinessIF {



    @Inject
    transient ApiService mApiService;


    public Dataimpl() {
        super();
    }
    public Dataimpl(Context context) {
        super();
        ApiModule mApiModule = new ApiModule();
        mApiService = mApiModule.apiService(new DemoConfig(context));
    }



    @Override
    public void getCurrentInfoObject(long startTime) {

    }

    @Override
    public Boolean getDetailedInfoObjectById(String infoObjectId) {

        return true;
//
//        info = mApiService.allinfoObjects("IOD_SINTEL","en");
//        try{
//            Response<InfoResponse> response = info.execute();
//            if (response.isSuccess()) {
//                return response.body().getmInfoObjects().get(0);
//            }
//        }
//        catch(IOException er){
//            return null;
//        }
//        return null;
    }

}
