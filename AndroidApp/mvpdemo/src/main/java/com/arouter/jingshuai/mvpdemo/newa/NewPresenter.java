package com.arouter.jingshuai.mvpdemo.newa;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import com.arouter.jingshuai.mvpdemo.retrofit.ClientType;
import com.arouter.jingshuai.mvpdemo.retrofit.api.ApiManager;
import com.arouter.jingshuai.mvpdemo.retrofit.api.WanAndroidApi;
import com.arouter.jingshuai.mvpdemo.retrofit.model.ProjectBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eqruvvz on 2/28/2018.
 */

public class NewPresenter implements IContract.Ipresenter{

    private final static String LOG_TAG = "NewPresenter";
    private IContract.IView mView;
    private NewsModel mNewsModel;
    private Activity mActivity;
    public NewPresenter(IContract.IView pView){
        this.mView = pView;
        mView.setPresenter(this);
        mNewsModel = new NewsModel();
        mActivity =(Activity) pView;

    }
    @Override
    public void getNextNews() {
        String news = mNewsModel.getNextNews();
        mView.updateNews(news);

    }

    public static class MyHandler extends Handler {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }

        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
        }

        public String getMessageName(Message message) {
            return super.getMessageName(message);
        }

        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            return super.sendMessageAtTime(msg, uptimeMillis);
        }

        public String toString() {
            return super.toString();
        }
    }


    public void get12306Test(Context context, ClientType type) {
        ApiManager.getInstence()
            .get12306Service(context, type)
            .get12306Test()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<ResponseBody>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    addDisposable(d);
                }

                @Override
                public void onNext(@NonNull ResponseBody responseBody) {
                    try {
                        Log.v("111",responseBody.string());
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.v("111",e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });
    }

    public void testRetroFit(){
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        WanAndroidApi wanapi = retrofit.create(WanAndroidApi.class);

        Call<ResponseBody> projectCall = wanapi.example();

        projectCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.v(LOG_TAG,"success response="+response.toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v(LOG_TAG,"failure response="+t.getMessage());
            }
        });



    }

    //将所有正在处理的Subscription都添加到CompositeSubscription中。统一退出的时候注销观察
    private CompositeDisposable mCompositeDisposable;

    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    //在界面退出等需要解绑观察者的情况下调用此方法统一解绑，防止Rx造成的内存泄漏
    public void dispose() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }


}
