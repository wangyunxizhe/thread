package com.yuan.demo3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 与demo2包中的不同：ScheduledThreadPool相关的方法可以满足线程池延迟执行任务的需求
 * 测试 ScheduleExecutorService 接口中延迟执行任务和重复执行任务的功能
 */
public class ScheduleExecutorServiceDemo03 {

    public static void main(String[] args) {
        //创建一个具备延迟执行任务的能力线程池对象
        ScheduledExecutorService se = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            int n = 0;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "自定义线程名称：" + n++);
            }
        });
        //与 ScheduleExecutorServiceDemo02 不同的地方，延迟间隔的3s是任务执行结束之后才开始算的，
        //本例中任务跑了2s，跑完再开始计算间隔的3s，所以每个任务之间实际隔了5s
        se.scheduleWithFixedDelay(new MyRunnable3(1), 1,3, TimeUnit.SECONDS);
        System.out.println("主线程结束");
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
        try {
            //模拟业务处理了2s
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程：" + name + " 执行了任务id：" + id);
    }
}

