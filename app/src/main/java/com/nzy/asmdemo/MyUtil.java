package com.nzy.asmdemo;

import android.util.Log;

/**
 * @author niezhiyang
 * since 2020/12/14
 */
public class MyUtil {

    public void getTime() {
        long start = System.currentTimeMillis();

        Log.e("ssss","sssss");
        long end = System.currentTimeMillis();

        System.out.println(end - start);


    }

    @DebugLog
    public void getNoWtime() {

        System.out.println("开始了---------");
    }
}
