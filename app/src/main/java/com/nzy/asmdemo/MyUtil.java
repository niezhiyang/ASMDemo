package com.nzy.asmdemo;

/**
 * @author niezhiyang
 * since 2020/12/14
 */
public class MyUtil {
    @DebugLog
    public void getTime(int now) {
        long start = System.currentTimeMillis();

        long end = System.currentTimeMillis();

        System.out.println(end - start);


    }

    public void getNoWtime(){
        System.out.println("你好");
    }
}
