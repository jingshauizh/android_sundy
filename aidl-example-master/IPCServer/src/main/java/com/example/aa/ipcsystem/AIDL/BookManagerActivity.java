package com.example.aa.ipcsystem.AIDL;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.aa.ipcsystem.Book;
import com.example.aa.ipcsystem.IBookManager;
import com.example.aa.ipcsystem.INewBookArriveListener;
import com.example.aa.ipcsystem.MessengerService;
import com.example.aa.ipcsystem.R;
import com.example.aa.ipcsystem.Util.MyConstantce;

import java.util.Date;
import java.util.List;

public class BookManagerActivity extends AppCompatActivity {
    private static final String TAG ="BookManagerActivity";
    private Button mButtonAddBook;
    private IBookManager bookManager;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MyConstantce.NEW_BOOK_ARRIVE:
                    Log.w(TAG, "receive new book:" + msg.obj);
                    Book mbook = (Book)msg.obj;
                    Log.w(TAG, "receive new book id:" + mbook.bookId);
                    Log.w(TAG, "receive new book name:" + mbook.bookName);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent _intent = new Intent(BookManagerActivity.this,BookManagerService.class);
        bindService(_intent, mConnection, Context.BIND_AUTO_CREATE);

        mButtonAddBook=(Button)findViewById(R.id.buttonAddBook);
        mButtonAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(bookManager != null){
                        bookManager.addBook(new Book((int)new Date().getTime(),"new book"));
                    }
                    Log.w(TAG, "query list size:" + bookManager.getBookList().size());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private ServiceConnection mConnection = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
             bookManager = IBookManager.Stub.asInterface(service);

            try {
                List booklist = bookManager.getBookList();
                Log.w(TAG, "List type =:" +booklist.getClass().getCanonicalName());
                Log.w(TAG, "query list:" + booklist.toString());
                bookManager.registerListener(mlstener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }


    private INewBookArriveListener mlstener = new INewBookArriveListener.Stub(){
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            mHandler.obtainMessage(MyConstantce.NEW_BOOK_ARRIVE,newBook).sendToTarget();
        }
    } ;

}
