package com.jarylan.illustrationlayout.listener;

import android.view.View;

/**
 * <pre>
 *     @author : jarylan
 *     e-mail : jarylan@foxmail.com
 *     time   : 2018/1/19
 *     desc   : 重新加载监听器
 *     version: 1.0
 * </pre>
 */
public interface OnReloadListener {

    /**
     * 重新加载回调
     * @param v 被点击的 view
     */
    void onReload(View v,Class illustration);

}
