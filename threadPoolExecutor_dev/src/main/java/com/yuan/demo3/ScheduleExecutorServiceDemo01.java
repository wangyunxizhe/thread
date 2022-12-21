package com.yuan.demo3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 与demo2包中的不同：ScheduledThreadPool相关的方法可以满足线程池延迟执行任务的需求
 * 测试 ScheduleExecutorService 接口中延迟执行任务和重复执行任务的功能
 */
public class ScheduleExecutorServiceDemo01 {

    public static void main(String[] args) {
        //创建一个具备延迟执行任务的能力线程池对象
        ScheduledExecutorService se = Executors.newScheduledThreadPool(3);
        //创建多个任务对象，提交任务，延迟2s执行
        for (int i = 0; i < 10; i++) {
            se.schedule(new MyRunnable(i), 2, TimeUnit.SECONDS);
        }
        System.out.println("主线程结束");
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
        System.out.println("线程：" + name + " 执行了任务id：" + id);
    }
}
