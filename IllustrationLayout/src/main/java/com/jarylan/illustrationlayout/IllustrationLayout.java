package com.jarylan.illustrationlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.jarylan.illustrationlayout.listener.OnReloadListener;
import com.jarylan.illustrationlayout.illustration.BaseIllustration;
import com.jarylan.illustrationlayout.illustration.LoadSuccessIllustration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     @author : jarylan
 *     e-mail : jarylan@foxmail.com
 *     time   : 2018/1/18
 *     desc   : 可添加缺省页插图的布局
 *     version: 1.0
 * </pre>
 */
public class IllustrationLayout extends FrameLayout {

    private Context mContext;
    private Map<Class<? extends BaseIllustration>, BaseIllustration> illustrations = new HashMap<>();
    private List<View> childViews = new ArrayList<>();
    private Class<? extends BaseIllustration> currentIllustration;
    private OnReloadListener mOnReloadListener;

    public IllustrationLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < getChildCount(); i++) {
                    if (null != getChildAt(i)) {
                        childViews.add(getChildAt(i));
                    }
                }
                //默认添加成功状态
                addIllustrationView(new LoadSuccessIllustration());
            }
        });
    }

    public IllustrationLayout addIllustrationView(final BaseIllustration illustration) {
        if (!illustrations.containsKey(illustration.getClass())) {
            illustration.setOnReloadListener(mContext, mOnReloadListener);
            illustrations.put(illustration.getClass(), illustration);
            if (illustration.getRootView() != null) {
                post(new Runnable() {
                    @Override
                    public void run() {
                        addView(illustration.getRootView(), getWidth(), getHeight());

                    }
                });
                setVisibilty(illustration.getRootView(), GONE);
            }
        }
        return this;
    }

    public IllustrationLayout setOnReloadListener(OnReloadListener mOnReloadListener) {
        this.mOnReloadListener = mOnReloadListener;
        for (Class key : illustrations.keySet()) {
            illustrations.get(key).setOnReloadListener(mContext,mOnReloadListener);
        }
        return this;
    }

    public Class<? extends BaseIllustration> getCurrentIllustration() {
        return currentIllustration;
    }

    /**
     * 设置成功状态
     */
    public void showSuccessIllustration() {
        showIllustration(LoadSuccessIllustration.class,"");
    }

    /**
     * 设置缺省页插画
     * @param illustration 缺省页插图类
     */
    public void showIllustration(Class<? extends BaseIllustration> illustration) {
        if (ThreadUtil.isMainThread()) {
            showIllustrationView(illustration,"");
        } else {
            postShowIllustration(illustration,"");
        }
    }

    /**
     * 设置缺省页插画
     * @param illustration 缺省页插图类
     * @param describe 提示文本
     */
    public void showIllustration(Class<? extends BaseIllustration> illustration,String describe) {
        if (ThreadUtil.isMainThread()) {
            showIllustrationView(illustration,describe);
        } else {
            postShowIllustration(illustration,describe);
        }
    }

    private void postShowIllustration(final Class<? extends BaseIllustration> illustration, final String describe) {
        post(new Runnable() {
            @Override
            public void run() {
                showIllustrationView(illustration,describe);
            }
        });
    }

    private void showIllustrationView(Class<? extends BaseIllustration> illustration,String describe) {
        if (illustration != null && currentIllustration == illustration) {
            return;
        }
        currentIllustration = illustration;
        for (Class key : illustrations.keySet()) {
            View rootView = illustrations.get(key).getRootView();
            if (key == illustration) {
                setVisibilty(rootView, VISIBLE);
                illustrations.get(key).onVisible(mContext, rootView,describe);
            }else{
                if(rootView != null && rootView.getVisibility() == VISIBLE) {
                    setVisibilty(rootView, GONE);
                    illustrations.get(key).onInvisible(mContext, rootView);
                }
            }
        }
        for (int i = 0; i < childViews.size(); i++) {
            if (illustration == LoadSuccessIllustration.class) {
                setVisibilty(childViews.get(i), VISIBLE);
            } else {
                setVisibilty(childViews.get(i), GONE);
            }
        }
    }

    private void setVisibilty(View view, int visibilty) {
        if (view == null) {
            return;
        }
        view.setVisibility(visibilty);
    }
}
