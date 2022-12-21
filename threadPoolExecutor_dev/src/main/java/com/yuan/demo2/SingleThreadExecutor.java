package com.yuan.demo2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class SingleThreadExecutor {

    public static void main(String[] args) {
//        testNoArgs();

        testWithArgs();
        //结论：效果一样，第二种可以自定义线程名
    }

    //测试无参 newSingleThreadExecutor()
    private static void testNoArgs() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        //提交10个任务
        for (int i = 1; i <= 10; i++) {
            es.submit(new MyRunnable3(i));
        }
    }

    //测试带参 newSingleThreadExecutor()
    private static void testWithArgs() {
        ExecutorService es = Executors.newSingleThreadExecutor(new ThreadFactory() {
            int n = 1;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "自定义线程名称" + n++);
            }
        });

        //提交10个任务
        for (int i = 1; i <= 10; i++) {
            es.submit(new MyRunnable3(i));
        }
    }

}

class MyRunnable3 implements Runnable {

    private int id;

    public MyRunnable3(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("线程：" + name + " 执行了任务 id：" + id);
    }
}
