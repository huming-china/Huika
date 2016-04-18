package com.huika.hkmall.ui.main;

import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import com.huika.hkmall.R;
import com.huika.lib.core.ext.ui.act.BaseAct;
import java.io.File;

/**
 * Created by zhangchao on 2015/12/16.
 */
public class WelcomeActivity extends BaseAct {

    public static final String IS_FIRST_INSTALL = "isFirstInstall";
    public static final String HK_MALL = "hkMall";
    private ImageView mImageView;
    private static final long ANIMATIONDURATION = 2000;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;

    @Override
    public void setRootView() {
        mImageView = new ImageView(this);
        mImageView.setBackgroundResource(R.drawable.welcome_bg);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        setContentView(mImageView);
        sp = getSharedPreferences(HK_MALL, 0);
        edit = sp.edit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }

    private void forwardStep() {

        if (firstsInstall()) {// 首次安装
            skipActivity(this, GuideActivity.class);
        } else {
            skipActivity(this, MainActivity.class);
        }
    }

    private boolean firstsInstall() {
        File files = getFilesDir();
        File installFile = new File(files, "install");

        int newVC = 0;
        try {
            newVC = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (Exception e) {
        }

        boolean firstInstall = installFile.exists();
        if (!firstInstall) {// 文件夹不存在，则表示初次安装
            installFile.mkdirs();
            try {
                new File(installFile, newVC + "").createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
            edit.putBoolean(IS_FIRST_INSTALL, true);
            edit.commit();
            return true;
        } else {
            String[] fs = installFile.list();
            if (fs == null || fs.length == 0) {// 上一个版本为空
                try {
                    new File(installFile, newVC + "").createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
            String lastV = fs[0];
            if (newVC > Integer.parseInt(lastV)) {
                try {
                    new File(installFile, newVC + "").createNewFile();
                    for (String vf : fs) {
                        File temp = new File(installFile, vf);
                        if (temp.exists()) temp.delete();
                    }
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            edit.putBoolean(IS_FIRST_INSTALL, false);
            edit.commit();
        }
        return false;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2F, 0.9F);
        alphaAnimation.setDuration(ANIMATIONDURATION);
        animationSet.addAnimation(alphaAnimation);
        // 监听动画过程
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                forwardStep();
            }
        });
        mImageView.startAnimation(animationSet);
    }
}
