package com.yuan;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyPool4 {

    public static void main(String[] args) {
        //根据控制台打印，会在执行到第31次时报错
        //注意点：Task10-19报错会晚于Task20-29（知识点：提交优先级，执行优先级）
        ThreadPoolExecutor myPool = new ThreadPoolExecutor(10, 20, 0,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 100; i++) {
            myPool.execute(new MyPoolTask(i));
        }
    }

}

class MyPoolTask implements Runnable {

    int i = 0;

    public MyPoolTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "--" + i);
        try {
            Thread.sleep(1000l);//模拟正在处理业务逻辑
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
