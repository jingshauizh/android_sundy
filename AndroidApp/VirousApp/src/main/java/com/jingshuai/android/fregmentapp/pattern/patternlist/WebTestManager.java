package com.jingshuai.android.fregmentapp.pattern.patternlist;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

/**
 * Created by jings on 2020/4/17.
 */

public class WebTestManager {
    public static final String KEY_URL = "upurl";
    public static final String KEY_COLOR = "color";
    public static final String KEY_PARAMS = "params";
    private Context mContext;

    public WebTestManager(Context mContext) {
        this.mContext = mContext;
    }

    public void excuteTest(){
        //testCaseErrorparams();
        testCaseUPActivityFriends();
    }


    //error color
    public void testCaseErrorColor(){
        Intent intent = new Intent("android.intent.action.VIEW");
        String url = "uplink://";
        intent.setData(Uri.parse(url));
        //intent.putExtra(KEY_URL,"http://www.baidu.com");
        intent.putExtra(KEY_URL,"http://www.baidu.com");
        intent.putExtra(KEY_COLOR,"ergsd");
        try {
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //error params
    public void testCaseErrorparams(){
        Intent intent = new Intent("android.intent.action.VIEW");
        String url = "uplink://";
        intent.setData(Uri.parse(url));
        //intent.putExtra(KEY_URL,"http://www.baidu.com");
        intent.putExtra(KEY_URL,"http://www.baidu.com");
        intent.putExtra(KEY_PARAMS,"ergsd");
        try {
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    public void testCaseErrorView(){
        Intent intent = new Intent("android.intent.action.VIEW");
        String url = "upchat://";
        intent.setData(Uri.parse(url));
        //intent.putExtra(KEY_URL,"http://www.baidu.com");
        intent.putExtra(KEY_URL,"http://www.baidu.com");
        intent.putExtra(KEY_PARAMS,"ergsd");
        try {
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //UPActivityFriends
    public void testCaseUPActivityFriends(){
        Intent intent = new Intent("android.intent.action.VIEW");
        String url = "uplinkapp://upactivityfriends";
        intent.setData(Uri.parse(url));
        //intent.putExtra(KEY_URL,"http://www.baidu.com");
        intent.putExtra(KEY_URL,"http://www.baidu.com");
        intent.putExtra(KEY_PARAMS,"ergsd");
        if (mContext.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {

            try {
                mContext.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            Log.v("web","??? activity");
        }

    }

    //UPActivityChatList
    public void testCaseUPActivityChatList(){
        Intent intent = new Intent("android.intent.action.VIEW");
        String url = "uplinkapp://upactivitychatlist";
        intent.setData(Uri.parse(url));
        //intent.putExtra(KEY_URL,"http://www.baidu.com");
        intent.putExtra(KEY_URL,"http://www.baidu.com");
        intent.putExtra(KEY_PARAMS,"ergsd");
        try {
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //upactivitysearchorganize
    public void testCaseupactivitysearchorganize(){
        Intent intent = new Intent("android.intent.action.VIEW");
        String url = "uplinkapp://upactivitysearchorganize";
        intent.setData(Uri.parse(url));
        //intent.putExtra(KEY_URL,"http://www.baidu.com");
        intent.putExtra(KEY_URL,"http://www.baidu.com");
        intent.putExtra(KEY_PARAMS,"ergsd");
        try {
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //UPActivityMine
    public void testCaseUPActivityMine(){
        Intent intent = new Intent("android.intent.action.VIEW");
        String url = "uplinkapp://upactivitymine";
        intent.setData(Uri.parse(url));
        //intent.putExtra(KEY_URL,"http://www.baidu.com");
        intent.putExtra(KEY_URL,"http://www.baidu.com");
        intent.putExtra(KEY_PARAMS,"ergsd");
        try {
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //UPActivityAllGroup
    public void testCaseUPActivityAllGroup(){
        Intent intent = new Intent("android.intent.action.VIEW");
        String url = "uplinkapp://upactivityallgroup";
        intent.setData(Uri.parse(url));
        //intent.putExtra(KEY_URL,"http://www.baidu.com");
        intent.putExtra(KEY_URL,"http://www.baidu.com");
        intent.putExtra(KEY_PARAMS,"ergsd");
        try {
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //UPActivityAllService
    public void testCaseUPActivityAllService() {
        Intent intent = new Intent("android.intent.action.VIEW");
        String url = "uplinkapp://upactivityallservice";
        intent.setData(Uri.parse(url));
        //intent.putExtra(KEY_URL,"http://www.baidu.com");
        intent.putExtra(KEY_URL, "http://www.baidu.com");
        intent.putExtra(KEY_PARAMS, "ergsd");
        try {
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
