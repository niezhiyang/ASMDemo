package com.nzy.asmdemo;

/**
 * @author niezhiyang
 * since 12/7/21
 */
public class ThreadCreate {
    public static ThreadDemo newThreadCreate(String name){
        return new ThreadDemo(name);
    }
}
