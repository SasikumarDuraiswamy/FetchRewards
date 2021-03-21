package com.android.fetchrewards;

import android.app.Application;

public class FetchRewardsApplication extends Application {

    private static FetchRewardsApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static FetchRewardsApplication getInstance() {
        return mInstance;
    }

}
