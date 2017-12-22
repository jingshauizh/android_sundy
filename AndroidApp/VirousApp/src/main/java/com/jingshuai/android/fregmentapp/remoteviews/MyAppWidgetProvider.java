package com.jingshuai.android.fregmentapp.remoteviews;

/**
 * Created by eqruvvz on 12/11/2017.
 */

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.jingshuai.android.fregmentapp.R;

/**
 * 桌面小部件演示
 * @author Administrator
 *
 */
public class MyAppWidgetProvider extends AppWidgetProvider {
    public static final String TAG = "MyAppWidgetProvider";
    public static final String CLICK_ACTION = "com.example.remoteviews";

    public MyAppWidgetProvider() {
        super();
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Toast.makeText(context, "第一次添加小部件会调用我", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Toast.makeText(context, "最后一个小部件已被删除！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Toast.makeText(context, "小部件已被删除！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.i(TAG, "onReceive:action=" + intent.getAction());
        // 这里判断自己的action，做自己的事情
        if (intent.getAction().equals(CLICK_ACTION)) {
            Toast.makeText(context, "我在旋转", Toast.LENGTH_SHORT).show();
            new Thread(new Runnable() {

                @Override
                public void run() {
                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
                            R.drawable.image);

                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

                    for (int i = 0; i < 37; i++) {
                        float degree = (i * 10) % 360;
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                                R.layout.layout_remote_widget);

                        remoteViews.setImageViewBitmap(R.id.iv_img,
                                rotateBitmap(context, bitmap, degree));

                        Intent intent = new Intent();
                        intent.setAction(CLICK_ACTION);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                                context, 0, intent, 0);

                        remoteViews.setOnClickPendingIntent(R.id.iv_img, pendingIntent);
                        appWidgetManager.updateAppWidget(
                                new ComponentName(context, MyAppWidgetProvider.class), remoteViews);
                        SystemClock.sleep(30);
                    }
                }

            }).start();
        }
    }

    /**
     * 每次桌面小部件更新时都调用一次该方法
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Toast.makeText(context, "每次更新小部件会调用我！", Toast.LENGTH_SHORT).show();

        final int counter = appWidgetIds.length;
        Log.i(TAG, "counter = "+ counter);
        for (int i = 0; i < counter; i++) {
            int appWidgetId = appWidgetIds[i];
            onWidgetUpdate(context, appWidgetManager, appWidgetId);
        }
    }

    /**
     * 桌面小部件更新
     * @param context
     * @param appWidgetManager
     * @param appWidgetId
     */
    private void onWidgetUpdate(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        Log.i(TAG, "appWidgetId = " + appWidgetId);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_remote_widget);

        // 桌面小部件单击事件发送的Intent广播
        Intent intent = new Intent();
        intent.setAction(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.iv_img, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    /**
     * 旋转
     * @param context
     * @param bitmap
     * @param degree
     * @return
     */
    private Bitmap rotateBitmap(Context context, Bitmap bitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        Bitmap tmpBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(),
                matrix, true);
        return tmpBitmap;
    }
}