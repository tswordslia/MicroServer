package com.example.tswords.microservertest;

import android.content.Context;

/**
 * Created by Tswords on 2017/7/29.
 */

public class MyGlobalContext {
    private static Context context;
    public static Context getmContext() {
        return context;
    }
    public static void setmContext(Context mContext) {
        context = mContext;
    }



}
