package com.jarylan.illustrationlayout.illustration;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.jarylan.illustrationlayout.listener.OnReloadListener;

import java.util.List;

/**
 * <pre>
 *     @author : jarylan
 *     e-mail : jarylan@foxmail.com
 *     time   : 2018/1/19
 *     desc   : 自定义插图请集成此类
 *     version: 1.0
 * </pre>
 */
public abstract class BaseIllustration implements View.OnClickListener {

    private View rootView;
    private Context mContext;
    private OnReloadListener onReloadListener;

    /**
     * xml 布局 id；
     *
     * @return id
     */
    protected abstract @LayoutRes
    int getLayoutId();

    /**
     * 需要点击事件的控件 id ; 可添加多个需要点击的控件， null 表示不需要点击事件
     *
     * @return ids
     */
    protected abstract List<Integer> getClickIds();

    /**
     * 创建 View 时回调一次
     *
     * @param rootView 根布局
     */
    protected abstract void onCreateView(View rootView);

    /**
     * 该插图显示时的回调
     *
     * @param context  context
     * @param rootView 该插图的布局
     * @param describe 错误信息显示描述，传空则使用 xml 定义的描述
     */
    public abstract void onVisible(Context context, View rootView, String describe);

    /**
     * 该插图消失时回调
     * @param context  context
     * @param rootView 该插图的布局
     */
    public abstract void onInvisible(Context context, View rootView);

    public void setOnReloadListener(Context mContext, OnReloadListener onReloadListener) {
        this.mContext = mContext;
        this.onReloadListener = onReloadListener;
    }

    public View getRootView() {
        if (getLayoutId() == 0) {
            return null;
        }
        if (rootView == null) {
            rootView = View.inflate(mContext, getLayoutId(), null);
            onCreateView(rootView);
            List<Integer> ids = getClickIds();
            if (ids != null) {
                for (int i = 0; i < ids.size(); i++) {
                    rootView.findViewById(ids.get(i)).setOnClickListener(this);
                }
            }
        }
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (onReloadListener != null) {
            onReloadListener.onReload(v);
        }
    }
}
