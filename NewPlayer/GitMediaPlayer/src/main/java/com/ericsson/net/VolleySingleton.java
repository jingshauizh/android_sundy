package com.ericsson.net;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by eyngzui on 8/9/2017.
 */

public class VolleySingleton {
    private static final String TAG = "NewPlayerVolley";
    private static VolleySingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    private VolleySingleton(Context context){
        this.context = context;
        requestQueue = getRequestQueue();
    }


    public static synchronized VolleySingleton getInstance(Context context){
        if (instance == null){
            instance = new VolleySingleton(context);
        }

        return instance;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request){
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequests(){
        if(requestQueue != null){
            requestQueue.cancelAll(TAG);
        }
    }
}
