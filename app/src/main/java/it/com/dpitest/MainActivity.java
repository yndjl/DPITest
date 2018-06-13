package it.com.dpitest;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button pingYuan, pingXiu, shouYuan, shouXiu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pingYuan = (Button) findViewById(R.id.btn1);
        pingYuan.setOnClickListener(this);
        pingXiu = (Button) findViewById(R.id.btn2);
        pingXiu.setOnClickListener(this);
        shouYuan = (Button) findViewById(R.id.btn3);
        shouYuan.setOnClickListener(this);
        shouXiu = (Button) findViewById(R.id.btn4);
        shouXiu.setOnClickListener(this);
    }

    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;

    private static void setCustomDensity(Activity activity, final Application application) {
        final DisplayMetrics appDispalyMetrics = application.getResources().getDisplayMetrics();
        if (sNoncompatDensity == 0) {
            sNoncompatDensity = appDispalyMetrics.density;
            sNoncompatScaledDensity = appDispalyMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        final float targetDensity = appDispalyMetrics.widthPixels / 360;
        final float targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        final int targetDensityDpi = (int) (160 * targetDensity);

        appDispalyMetrics.density = targetDensity;
        appDispalyMetrics.scaledDensity = targetScaledDensity;
        appDispalyMetrics.densityDpi = targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn1:
                intent.setClass(this, Main1Activity.class);
                break;
            case R.id.btn2:
                intent.setClass(this, Main2Activity.class);
                break;
            case R.id.btn3:
                intent.setClass(this, Main3Activity.class);
                break;
            case R.id.btn4:
                intent.setClass(this, Main4Activity.class);
                break;
        }
        startActivity(intent);
    }
}
