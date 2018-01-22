package com.jarylan.illustrationlayout.illustration;


import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jarylan.illustrationlayout.R;

import java.util.List;

/**
 * <pre>
 *     @author : jarylan
 *     e-mail : jarylan@foxmail.com
 *     time   : 2018/1/19
 *     desc   : 加载中插图
 *     version: 1.0
 *
 * </pre>
 */
public class LoadingIllustration extends BaseIllustration {

    private TextView tvLoadingText;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_illustration_loading;
    }

    @Override
    protected List<Integer> getClickIds() {
        return null;
    }

    @Override
    protected void onCreateView(View rootView) {
        tvLoadingText = rootView.findViewById(R.id.tv_loading);
    }

    @Override
    public void onVisible(Context context, View rootView, String describe) {
        if (tvLoadingText != null && !TextUtils.isEmpty(describe)) {
            tvLoadingText.setText(describe);
        }
    }

    @Override
    public void onInvisible(Context context, View rootView) {
    }
}
