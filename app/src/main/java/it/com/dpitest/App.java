package it.com.dpitest;

import android.app.Application;
import android.content.res.Configuration;

import com.yatoooon.screenadaptation.ScreenAdapterTools;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ScreenAdapterTools.init(this);
    }

//旋转适配,如果应用屏幕固定了某个方向不旋转的话(比如qq和微信),下面可不写.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ScreenAdapterTools.getInstance().reset(this);
    }
}