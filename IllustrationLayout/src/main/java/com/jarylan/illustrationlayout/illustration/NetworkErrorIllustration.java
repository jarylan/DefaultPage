package com.jarylan.illustrationlayout.illustration;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jarylan.illustrationlayout.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     @author : jarylan
 *     e-mail : jarylan@foxmail.com
 *     time   : 2018/1/22
 *     desc   : 无网络
 *     version: 1.0
 * </pre>
 */
public class NetworkErrorIllustration extends BaseIllustration {

    private TextView tvContent;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_illustration_network_error;
    }

    @Override
    protected List<Integer> getClickIds() {
        List<Integer> ids = new ArrayList<>();
        ids.add(R.id.tv_reload);
        return ids;
    }

    @Override
    protected void onCreateView(View rootView) {
        tvContent = rootView.findViewById(R.id.tv_network_error);
    }

    @Override
    public void onVisible(Context context, View rootView, String describe) {
        if (tvContent != null && !TextUtils.isEmpty(describe)) {
            tvContent.setText(describe);
        }
    }

    @Override
    public void onInvisible(Context context, View rootView) {

    }
}
