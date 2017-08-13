package com.jingshuai.android.fregmentapp.service.binder;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

import com.jingshuai.android.fregmentapp.service.Dog;

import java.util.List;

/**
 * Created by Administrator on 2017/7/29 0029.
 */

public  interface IDogManager extends IInterface {
    static final String DESCRIPTOR = "com.example.administrator.writebindercodeexample.IDogManager";
    static final int TRANSACTION_getDogList = IBinder.FIRST_CALL_TRANSACTION + 0;
    static final int TRANSACTION_addDog = IBinder.FIRST_CALL_TRANSACTION + 1;

    public List<Dog> getDogList() throws RemoteException;

    public void addDog(Dog dog) throws RemoteException;
}
