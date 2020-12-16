package com.nzy.asmdemo;

/**
 * @author niezhiyang
 * since 2020/12/14
 */
public class MyUtil {

    public void getTime(int now,int last,String time) {
        long start = System.currentTimeMillis();

        long end = System.currentTimeMillis();

        System.out.println(end - start);


    }
    @DebugLog
    public void getNoWtime(int now,int last,String time){

        System.out.println("你好");
    }
}
