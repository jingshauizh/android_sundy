package com.jingshuai.android.fregmentapp.pattern.command;

import com.jingshuai.appcommonlib.log.MLog;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jings on 2020/4/17.
 */

public class Camera implements IOrderTarget {
    public void open(){
        MLog.d("Camera","Camera open");
        Hashtable mtable =new Hashtable();
        HashMap mmap = new HashMap();

        ConcurrentHashMap conMap = new ConcurrentHashMap();
    }

    public void close(){
        MLog.d("Camera","Camera close");
    }
}
