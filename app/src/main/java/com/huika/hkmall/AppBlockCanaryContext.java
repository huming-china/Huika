package com.huika.hkmall;

import com.github.moduth.blockcanary.BlockCanaryContext;
import java.io.File;

public class AppBlockCanaryContext extends BlockCanaryContext {
    // override to provide context like app qualifier, uid, network type, block threshold, log save path

    @Override public String getQualifier() {
        return null;
    }

    @Override public String getUid() {
        return null;
    }

    @Override public String getNetworkType() {
        return null;
    }

    @Override public int getConfigDuration() {
        return 0;
    }

    // this is default block threshold, you can set it by phone's performance
    @Override
    public int getConfigBlockThreshold() {
        return 500;
    }

    // if set true, notification will be shown, else only write log file
    @Override
    public boolean isNeedDisplay() {
        return BuildConfig.DEBUG;
    }

    // path to save log file
    @Override
    public String getLogPath() {
        return "/blockcanary/performance";
    }

    @Override public boolean zipLogFile(File[] files, File file) {
        return false;
    }

    @Override public void uploadLogFile(File file) {

    }
}