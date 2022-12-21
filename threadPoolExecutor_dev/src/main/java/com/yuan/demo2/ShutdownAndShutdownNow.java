package com.yuan.demo2;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ShutdownAndShutdownNow {

    public static void main(String[] args) {
        //验证MyPool1中最后关于shutdown和shutdownNow的说法
//        testShutdown();

        testShutdownNow();
    }

    //测试 shutdown() === 不再接受新任务，未完结的任务（包括阻塞队列中的任务）会继续处理
    private static void testShutdown() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        //提交10个任务
        for (int i = 1; i <= 10; i++) {
            es.submit(new MyRunnable4(i));
        }
        //关闭线程池
        es.shutdown();
        //关闭后，再尝试提交 新 任务
        es.submit(new MyRunnable4(666));
    }

    //测试 shutdownNow() === 如果线程池中还有缓存的任务没有执行，则取消执行。并返回这些任务
    private static void testShutdownNow() {
        ExecutorService es = Executors.newSingleThreadExecutor(new ThreadFactory() {
            int n = 1;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "自定义线程名称" + n++);
            }
        });

        //提交10个任务
        for (int i = 1; i <= 10; i++) {
            es.submit(new MyRunnable4(i));
        }
        //立刻关闭线程池
        List<Runnable> undones = es.shutdownNow();
        System.out.println(undones);
    }

}

class MyRunnable4 implements Runnable {

    private int id;

    public MyRunnable4(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("线程：" + name + " 执行了任务 id：" + id);
    }

    @Override
    public String toString() {
        return "MyRunnable4{" +
                "id=" + id +
                '}';
    }
}
