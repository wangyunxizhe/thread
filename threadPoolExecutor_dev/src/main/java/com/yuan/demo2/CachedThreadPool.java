package com.yuan.demo2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CachedThreadPool {

    public static void main(String[] args) {
//        testNoArgs();

        testWithArgs();
        //结论：效果一样，第二种可以自定义线程名
    }

    //测试无参newCachedThreadPool()
    private static void testNoArgs() {
        //与MyPool3演示类似，线程忙时会创建新线程，不忙时可复用。创建出来的线程空闲时间默认60s，超出则销毁
        ExecutorService es = Executors.newCachedThreadPool();
        //提交任务
        for (int i = 1; i <= 10; i++) {
            es.submit(new MyRunnable(i));
        }
    }

    //测试带参newCachedThreadPool()
    private static void testWithArgs() {
        ExecutorService es = Executors.newCachedThreadPool(new ThreadFactory() {
            int n = 1;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "自定义线程名称" + n++);
            }
        });

        //提交任务
        for (int i = 1; i <= 10; i++) {
            es.submit(new MyRunnable(i));
        }
    }

}

class MyRunnable implements Runnable {

    private int id;

    public MyRunnable(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("线程：" + name + " 执行了任务 id：" + id);
    }
}
