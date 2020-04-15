package com.arouter.jingshuai.javademo.concurrent.countdownlatch.actcountdownlatch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.arouter.jingshuai.javademo.concurrent.threadclass.ThreadOnNext;
import com.arouter.jingshuai.javademo.mvp.BasePresenterImpl;
import com.jingshuai.appcommonlib.log.MLog;

import org.reactivestreams.Subscriber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ActCountdownlatchPresenter extends BasePresenterImpl<ActCountdownlatchContract.View> implements ActCountdownlatchContract.Presenter{
    @NonNull
    private CompositeDisposable mCompositeDisposable;
    private PublishSubject<String> subject ;
    private  Observable<String> observable;
    private ObservableEmitter<String> mObservableEmitter;

    public ActCountdownlatchPresenter() {
        MLog.i("ActCountdownlatchPresenter constructor init");
        this.mCompositeDisposable = new CompositeDisposable();
        subject = PublishSubject.create();
        initObservable();
    }

    @Override
    public void startRun() {
        MLog.i("ActCountdownlatchPresenter startRun");

        subject.onNext("ActCountdownlatchPresenter startRun");
    }

    @Override
    public void sendMore() {
        MLog.i("sendMore startRun mObservableEmitter"+mObservableEmitter);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if(mObservableEmitter != null){
//                    mObservableEmitter.onNext("sendMore 我来发射数据");
//                }
//            }
//        }).start();
        //new Thread(new ThreadOnNext(mObservableEmitter)).start();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new ThreadOnNext(mObservableEmitter));
        executorService.shutdown();

    }

    @Override
    public void attachView(ActCountdownlatchContract.View view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void subscribe() {
        super.subscribe();
        subMsg();

    }

    //http://www.sohu.com/a/122043966_468731
    //一篇博客让你了解RxJava

    @Override
    public void unsubscribe() {
        super.unsubscribe();
        //observable.unsubscribeOn(Schedulers.computation());

    }

    private void initObservable(){
        observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                //执行一些其他操作
                //.............
                //执行完毕，触发回调，通知观察者
                mObservableEmitter = e;
                e.onNext("我来发射数据");
            }
        });
    }

    private void subMsg() {



        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            //观察者接收到通知,进行相关操作
            public void onNext(String aLong) {
                System.out.println("我接收到数据了");
                mView.showMessage("\"我接收到数据了:\""+aLong);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);




    }


}
