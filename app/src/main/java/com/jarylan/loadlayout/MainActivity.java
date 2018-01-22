package com.jarylan.loadlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jarylan.illustrationlayout.IllustrationLayout;
import com.jarylan.illustrationlayout.illustration.NetworkErrorIllustration;
import com.jarylan.illustrationlayout.listener.OnReloadListener;
import com.jarylan.illustrationlayout.illustration.LoadFailIllustration;
import com.jarylan.illustrationlayout.illustration.LoadingIllustration;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.loadlayout)
    IllustrationLayout loadlayout;

    private final static int LOAD_SUCCESS = 0;
    private final static int LOAD_FAIL = 1;
    private final static int LOAD_NETWORK = 2;

    private final static String[] networkErrorStrings = {"如果人生没有 Wifi，那将毫无意义","生活只有眼前的 Wifi","未来和远方的 Wifi 是不存在的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadlayout.addIllustrationView(new LoadingIllustration())
                .addIllustrationView(new LoadFailIllustration())
                .addIllustrationView(new NetworkErrorIllustration())
                .setOnReloadListener(new OnReloadListener() {
                    @Override
                    public void onReload(View v) {
                        if (loadlayout.getCurrentIllustration().equals(NetworkErrorIllustration.class)) {
                            loading(LOAD_FAIL);
                        } else {
                            loading(LOAD_SUCCESS);
                        }
                    }
                });
        loadlayout.post(new Runnable() {
            @Override
            public void run() {
                refresh();
            }
        });
    }

    @OnClick(R.id.bt_refresh)
    public void refresh() {
        loading(LOAD_NETWORK);
    }

    private void loading(final int nextStatus) {
        loadlayout.showIllustration(LoadingIllustration.class);
        loadlayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (nextStatus) {
                    case LOAD_FAIL:
                        loadFail();
                        break;
                    case LOAD_NETWORK:
                        networkError();
                        break;
                    default:
                        loadSuccess();
                        break;
                }

            }
        }, 2000);
    }

    private void networkError() {
        //替换提示的文本
        loadlayout.showIllustration(NetworkErrorIllustration.class,getNetworkErrorTip());
    }

    private void loadFail() {
        //提示文本直接使用 layout xml 引用的
        loadlayout.showIllustration(LoadFailIllustration.class);
    }

    private void loadSuccess() {
        loadlayout.showSuccessIllustration();
    }

    int pisition = 0;
    private String getNetworkErrorTip(){
        if(pisition >= networkErrorStrings.length){
            pisition = 0;
        }
        return networkErrorStrings[pisition++];
    }
}
