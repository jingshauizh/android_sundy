package com.ericsson.NewPlayer.HomePage.datalayer.net;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.ericsson.NewPlayer.HomePage.datalayer.localdb.MovieIdal;
import com.ericsson.NewPlayer.HomePage.datalayer.model.MovieList;
import com.ericsson.NewPlayer.HomePage.datalayer.model.MovieModel;
import com.ericsson.NewPlayer.HomePage.movie.MovieType;
import com.ericsson.NewPlayer.HomePage.moviefrag.IMovieFragModel;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by eqruvvz on 9/11/2017.
 */

public class NetDataManager {
    private final String TAG = "NetDataManager";
    private MovieIdal _MovieIdal;
    private CompositeDisposable _disposables ;
    private String movieRquestUrl = "http://150.236.223.172:8008/db";
    private IMovieFragModel.OnPageRefreshListener mlisenter ;

    public NetDataManager(){
        _MovieIdal = new MovieIdal();
        _disposables = new CompositeDisposable();
    }

    public void retuestMoviesList(final MovieType pMovieType,final IMovieFragModel.OnPageRefreshListener lisenter) {
        mlisenter = lisenter;
        Observable.just("")
                .flatMap(new Function<String, Observable<JSONObject>>() {
                    @Override
                    public Observable<JSONObject> apply(@NonNull String s) throws Exception {
                        JSONObject jSONObject = NetDataManager.stringRequest(Request.Method.GET, movieRquestUrl);
                        return Observable.just(jSONObject);
                        //return Observable.just(null);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONObject>() {

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(JSONObject jsonObject) {
                        //Log.e(TAG, "onNext " + jsonObject.toString());
                        Gson gson = new Gson();

//                        MovieList videoDataList =
//                                gson.fromJson(jsonObject.toString(), MovieList.class);


                        MovieList videoDataList = gson.fromJson(JsonData.VIDEO_JSON_DATA, MovieList.class);
                        //save data todo
                        List<MovieModel> ovieModellist = videoDataList.castResultToMovieModellist();
                        _MovieIdal.saveAll(ovieModellist);
                        if(mlisenter !=null){
                            mlisenter.onSuccess();
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        VolleyError cause = (VolleyError) throwable.getCause();
                        if(cause != null && cause.networkResponse != null){
                            String s = new String(cause.networkResponse.data, Charset.forName("UTF-8"));
                            Log.e(TAG, s);
                            Log.e(TAG, cause.toString());
                        }

                        //todo
                        Gson gson = new Gson();
                        MovieList videoDataList = gson.fromJson(JsonData.VIDEO_JSON_DATA, MovieList.class);
                        //save data todo
                        List<MovieModel> ovieModellist = videoDataList.castResultToMovieModellist();
                        _MovieIdal.saveAll(ovieModellist);
                        if(mlisenter !=null){
                            mlisenter.onSuccess();
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onCompleted");
                        if(mlisenter !=null){
                            mlisenter.onSuccess();
                        }
                    }
                }) ;

    }


    public static JSONObject stringRequest(int method, String url) throws Exception {
        RequestFuture<String> requestFuture = RequestFuture.newFuture();
        StringRequest stringRequest = new StringRequest(method, url, requestFuture, requestFuture);
        MyVolley.getRequestQueue().add(stringRequest);
        JSONObject rootJson = new JSONObject(requestFuture.get());
        return rootJson;
    }

    public void onDestroy(){
        _disposables.clear();
    }


}
