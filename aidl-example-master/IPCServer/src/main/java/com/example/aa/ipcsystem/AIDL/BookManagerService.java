package com.example.aa.ipcsystem.AIDL;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.aa.ipcsystem.Book;
import com.example.aa.ipcsystem.IBookManager;
import com.example.aa.ipcsystem.INewBookArriveListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Administrator on 16-5-22.
 */
public class BookManagerService extends Service{
    private static final String  TAG= "BMS";
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<Book>();
    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);
    private Integer booksize = 0;
    private CopyOnWriteArrayList<INewBookArriveListener> mListenersList = new CopyOnWriteArrayList<INewBookArriveListener>();
    private Binder mBinder = new IBookManager.Stub(){
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
            //onNewBookArrived(book);
        }

        @Override
        public void registerListener(INewBookArriveListener listener) throws RemoteException {
            if(!mListenersList.contains(listener)){
                mListenersList.add(listener);
            }
            Log.w(TAG, "Listener size:" + mListenersList.size());

        }

        @Override
        public void unRegisterListener(INewBookArriveListener listener) throws RemoteException {
            if(mListenersList.contains(listener)){
                mListenersList.remove(listener);
            }
            Log.w(TAG, "Listener size:" + mListenersList.size());
        }
    };



    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2,"IOS"));
        new Thread(new ServiceWorker()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private void onNewBookArrived(Book book) throws RemoteException{
        for(INewBookArriveListener listener:mListenersList){
            listener.onNewBookArrived(book);
        }
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed.set(true);
        super.onDestroy();
    }

    private class ServiceWorker implements Runnable{
        @Override
        public void run() {
            while(!mIsServiceDestoryed.get()){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.w(TAG, "Work check size=" + mBookList.size());
                if(mBookList.size() != booksize){
                    booksize = mBookList.size();
                    try {
                        Log.w(TAG, "Work check send event booksize="+booksize);
                        onNewBookArrived(mBookList.get(booksize-1));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
