package com.nzy.asmdemo;

/**
 * @author niezhiyang
 * since 12/7/21
 */
public class ThreadDemo {
    private String name;

    public ThreadDemo() {
        ThreadCreate.newThreadCreate("大哥");
    }

    public ThreadDemo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
