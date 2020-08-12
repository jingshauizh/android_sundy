package jings.ex.android.com.fix;

import android.content.Context;
import android.util.Log;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * Created by jings on 2020/8/12.
 */

public class Enjoyfix {
    private static final String LOG_TAG = "enfix";
    public static void excute(ClassLoader classLoader, Context context) {
        // ??classloader

        try {
            Class<?> aClass = classLoader.loadClass("com.enjoy.enjoyfix.BugPatch");
            Log.v(LOG_TAG,"111111111111111111111111");
            Log.v(LOG_TAG,aClass.getName());
            System.out.println(aClass);
        } catch (ClassNotFoundException e) {
            Log.v(LOG_TAG,e.getMessage());
            e.printStackTrace();
        }

        try {
            classLoader.loadClass("android.app.Activity");
        } catch (ClassNotFoundException e) {
            Log.v(LOG_TAG,e.getMessage());
            e.printStackTrace();
        }

        /**
         * 1?dex??
         */
        PathClassLoader pathClassLoader =
            new PathClassLoader("/sdcard/fix.dex", context.getClassLoader());

        // /data/data/packagename : ????
        // 2: dex???odex???????????????????sd????
        DexClassLoader dexClassLoader =
            new DexClassLoader("/sdcard/fix.dex", context.getCodeCacheDir().getAbsolutePath(), null,
                context.getClassLoader());

        try {
            Class<?> aClass = pathClassLoader.loadClass("com.enjoy.enjoyfix.BugPatch");
            Log.v(LOG_TAG,"2222222222222");
            Log.v(LOG_TAG,aClass.getName());
            System.out.println(aClass);
        } catch (ClassNotFoundException e) {
            Log.v(LOG_TAG,e.getMessage());
            e.printStackTrace();
        }

        try {
            Class<?> aClass = dexClassLoader.loadClass("com.enjoy.enjoyfix.BugPatch");
            System.out.println(aClass);
        } catch (ClassNotFoundException e) {
            Log.v(LOG_TAG,e.getMessage());
            e.printStackTrace();
        }
    }
}
