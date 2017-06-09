package com.jingshuai.android.fregmentapp.hookviewpac;

import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;

/**
 * Created by eqruvvz on 3/23/2017.
 */
public class HookViewClickUtil {public static HookViewClickUtil getInstance() {
    return UtilHolder.mHookViewClickUtil;
}

private static class UtilHolder {

    private static HookViewClickUtil mHookViewClickUtil = new HookViewClickUtil();}

    public static void hookView(View view) {
    try {
        //Class.forName(xxx.xx.xx) 返回的是一个类,
        // .newInstance() 后才创建一个对象
        // Class.forName(xxx.xx.xx);的作用是要求JVM查找并加载指定的类，
        // 也就是说JVM会执行该类的静态代码段
        Class viewClazz = Class.forName("android.view.View");

        //事件监听器都是这个实例保存的
        //方法返回一个Method对象，它反映此Class对象所表示的类或接口的指定已声明方法。
        Method listenerInfoMethod = viewClazz.getDeclaredMethod("getListenerInfo");
        if (!listenerInfoMethod.isAccessible()) {
           //将一个类的成员变量置为 public
            listenerInfoMethod.setAccessible(true);
        }

        //拿到 ListenerInfo
        Object listenerInfoObj = listenerInfoMethod.invoke(view);

        //ListenerInfo 类
        Class listenerInfoClazz = Class.forName("android.view.View$ListenerInfo");

        //ListenerInfo 类的 mOnClickListener Field
        Field onClickListenerField = listenerInfoClazz.getDeclaredField("mOnClickListener");

        if (!onClickListenerField.isAccessible()) {
            //将一个类的成员变量置为 public
            onClickListenerField.setAccessible(true);
        }

        //在java的反射中,通过字段获取对象
        View.OnClickListener mOnClickListener = (View.OnClickListener) onClickListenerField.get(listenerInfoObj);
        //自定义代理事件监听器
        View.OnClickListener onClickListenerProxy = new OnClickListenerProxy(mOnClickListener);
        //更换
        onClickListenerField.set(listenerInfoObj, onClickListenerProxy);
    } catch (Exception e) {
        e.printStackTrace();
    }}//自定义的代理事件监听器

    private static class OnClickListenerProxy implements View.OnClickListener {

        private View.OnClickListener object;

        private int MIN_CLICK_DELAY_TIME = 1000;

        private long lastClickTime = 0;

        private OnClickListenerProxy(View.OnClickListener object) {
            this.object = object;
        }

        @Override
        public void onClick(View v) {
            //点击时间控制
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (object != null && (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME)) {
                lastClickTime = currentTime;
                Log.e("OnClickListenerProxy", "OnClickListenerProxy");
                object.onClick(v);
            }
            else{
                Log.e("OnClickListenerProxy", "too many click");
            }
        }
    }
}
