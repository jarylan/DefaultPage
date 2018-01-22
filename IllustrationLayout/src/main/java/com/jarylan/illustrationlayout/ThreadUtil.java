package com.jarylan.illustrationlayout;

import android.os.Looper;

/**
 * <pre>
 *     @author : jarylan
 *     e-mail : jarylan@foxmail.com
 *     time   : 2018/1/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ThreadUtil {

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

}
