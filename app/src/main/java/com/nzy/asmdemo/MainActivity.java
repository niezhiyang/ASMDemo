package com.nzy.asmdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private View mViewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewById = findViewById(R.id.tv_text);

        ThreadDemo demo = new ThreadDemo();


        System.out.println("--------"+demo.getName());
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//            }
//        }.start();

        ThreadFactory factory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
            }
        };
        thread.start();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 5, 5, TimeUnit.SECONDS, new SynchronousQueue<>(), factory);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.e("sss","ddd");
            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Nullable
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return super.onRetainCustomNonConfigurationInstance();
    }


}