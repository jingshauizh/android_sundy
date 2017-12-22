package com.jingshuai.android.fregmentapp.remoteviews;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RemoteViews;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.glidesample.GlideActivity;
import com.jingshuai.android.fregmentapp.hookviewpac.HookViewActivity;
import com.jingshuai.appcommonlib.log.MLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * A placeholder fragment containing a simple view.
 */
public class RemoteViewActivityFragment extends Fragment {
    @BindView(R.id.rmv_buttonOr)
     Button buttonOr;
    @BindView(R.id.rmv_buttonRemote)
     Button buttonRemote;

    private Context mContext;

    private final Integer  NOTIFICATIONS_ID = 10010;

    public RemoteViewActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View freg_view = inflater.inflate(R.layout.fragment_remote_view, container, false);
        ButterKnife.bind(this,freg_view);
        mContext = this.getActivity();
        return freg_view;
    }

    @OnClick(R.id.rmv_buttonOr)
    public void onOriginalClick(View v){
        MLog.i("onOriginalClick ed");
        NotificationManager mNotifyMgr =
                (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(
                mContext, 0, new Intent(mContext, HookViewActivity.class), 0);

        Notification notification = new Notification.Builder(mContext)
                .setSmallIcon(R.drawable.image)
                .setContentTitle("Original notification")
                .setContentText("Hello Original!")
                .setContentIntent(contentIntent)
                //.build();// getNotification()
                .getNotification();

        mNotifyMgr.notify(NOTIFICATIONS_ID+1, notification);

    }

    @OnClick(R.id.rmv_buttonRemote)
    public void onRemoteClick(View v){
        MLog.i("onTemoteClick ed");
        NotificationManager mNotifyMgr =
                (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(
                mContext, 0, new Intent(mContext, GlideActivity.class), 0);
        RemoteViews mRemoteViews=new RemoteViews("com.jingshuai.android.fregmentapp", R.layout.remoteview_layout);
        mRemoteViews.setTextViewText(R.id.rmv_textViewRemoteView,"You have a message");
        mRemoteViews.setTextColor(R.id.rmv_textViewRemoteView,getResources().getColor(R.color.black));

        Notification notification = new Notification.Builder(mContext)
                .setSmallIcon(R.drawable.image)
                .setContentTitle("Original notification")
                .setContentText("Hello Original!")
                .setContentIntent(contentIntent)
                .setContent(mRemoteViews)
                //.build();// getNotification()
                .getNotification();

        mNotifyMgr.notify(NOTIFICATIONS_ID, notification);

    }


    @OnClick(R.id.rmv_buttonRemoteUpdate)
    public void onRemoteClickUpdate(View v){
        MLog.i("rmv_buttonRemoteUpdate ed");
        NotificationManager mNotifyMgr =
                (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(
                mContext, 0, new Intent(mContext, GlideActivity.class), 0);
        RemoteViews mRemoteViews=new RemoteViews("com.jingshuai.android.fregmentapp", R.layout.remoteview_layout);
        mRemoteViews.setTextViewText(R.id.rmv_textViewRemoteView,"You have a updated message");
        mRemoteViews.setTextColor(R.id.rmv_textViewRemoteView,getResources().getColor(R.color.black));

        Notification notification = new Notification.Builder(mContext)
                .setSmallIcon(R.drawable.image)
                .setContentTitle("Original notification")
                .setContentText("Hello Original!")
                .setContentIntent(contentIntent)
                .setContent(mRemoteViews)
                //.build();// getNotification()
                .getNotification();

        mNotifyMgr.notify(NOTIFICATIONS_ID, notification);

    }


    @OnClick(R.id.rmv_buttonRemoteCancel)
    public void onRemoteClickCancel(View v){
        MLog.i("rmv_buttonRemoteCancel ed");
        NotificationManager mNotifyMgr =
                (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);

        mNotifyMgr.cancel(NOTIFICATIONS_ID);


    }


}
