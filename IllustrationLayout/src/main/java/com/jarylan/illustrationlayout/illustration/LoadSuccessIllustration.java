package com.jarylan.illustrationlayout.illustration;

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * <pre>
 *     @author : jarylan
 *     e-mail : jarylan@foxmail.com
 *     time   : 2018/1/19
 *     desc   : 加载成功插图，空的；显示原内容
 *     version: 1.0
 * </pre>
 */
public class LoadSuccessIllustration extends BaseIllustration {

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected List<Integer> getClickIds() {
        return null;
    }

    @Override
    protected void onCreateView(View rootView) {

    }

    @Override
    public void onVisible(Context context, View rootView, String describe) {
    }

    @Override
    public void onInvisible(Context context, View rootView) {
    }
}
