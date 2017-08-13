package com.jingshuai.android.fregmentapp.service.binder;




import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by Administrator on 2016/1/27.
 */
public abstract class DogManagerImpl extends Binder implements IDogManager {

    public DogManagerImpl() {
        this.attachInterface(this, DESCRIPTOR);
    }

    public static com.jingshuai.android.fregmentapp.service.binder.IDogManager asInterface(android.os.IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        //如果是同1个进程，也就是说进程内通信的话 我们就返回括号内里的对象
        if (((iin != null) && (iin instanceof com.jingshuai.android.fregmentapp.service.binder.IDogManager))) {
            return ((com.jingshuai.android.fregmentapp.service.binder.IDogManager) iin);
        }
        //如果不是同一进程，是2个进程之间相互通信，那我们就得返回这个Stub.Proxy 看上去叫Stub 代理的对象了
        return new com.jingshuai.android.fregmentapp.service.binder.DogManagerImpl.Proxy(obj);
    }


    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_getDogList: {
                data.enforceInterface(DESCRIPTOR);
                java.util.List<com.jingshuai.android.fregmentapp.service.Dog> _result = this.getDogList();
                reply.writeNoException();
                reply.writeTypedList(_result);
                return true;
            }
            case TRANSACTION_addDog: {
                data.enforceInterface(DESCRIPTOR);
                com.jingshuai.android.fregmentapp.service.Dog _arg0;
                if ((0 != data.readInt())) {
                    _arg0 = com.jingshuai.android.fregmentapp.service.Dog.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                this.addDog(_arg0);
                reply.writeNoException();
                return true;
            }
        }
        return super.onTransact(code, data, reply, flags);
    }

    private static class Proxy extends DogManagerImpl {
        private android.os.IBinder mRemote;

        Proxy(android.os.IBinder remote) {
            mRemote = remote;
        }

        @Override
        public android.os.IBinder asBinder() {
            return mRemote;
        }

        public java.lang.String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }

        @Override
        public java.util.List<com.jingshuai.android.fregmentapp.service.Dog> getDogList() throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            java.util.List<com.jingshuai.android.fregmentapp.service.Dog> _result;
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                mRemote.transact(DogManagerImpl.TRANSACTION_getDogList, _data, _reply, 0);
                _reply.readException();
                _result = _reply.createTypedArrayList(com.jingshuai.android.fregmentapp.service.Dog.CREATOR);
            } finally {
                _reply.recycle();
                _data.recycle();
            }
            return _result;
        }
        /*

         相当于 service 定义好并且注册好了
         client 和 service 的 通信 就是 调用binder 接口 发送 请求 取结果的 过程
         也就是 remote.transact();

         还有就是 asinterface 中 查找 binder 的 对象  本地的 还是 remote 的  都在 asInterface



         */
        @Override
        public void addDog(com.jingshuai.android.fregmentapp.service.Dog dog) throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                if ((dog != null)) {
                    _data.writeInt(1);
                    dog.writeToParcel(_data, 0);
                } else {
                    _data.writeInt(0);
                }
                mRemote.transact(DogManagerImpl.TRANSACTION_addDog, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }
    }

}
