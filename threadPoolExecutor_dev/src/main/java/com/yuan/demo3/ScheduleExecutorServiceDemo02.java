package com.yuan.demo3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 与demo2包中的不同：ScheduledThreadPool相关的方法可以满足线程池延迟执行任务的需求
 * 测试 ScheduleExecutorService 接口中延迟执行任务和重复执行任务的功能
 */
public class ScheduleExecutorServiceDemo02 {

    public static void main(String[] args) {
        //创建一个具备延迟执行任务的能力线程池对象
        ScheduledExecutorService se = Executors.newScheduledThreadPool(3, new ThreadFactory() {
            int n = 0;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "自定义线程名称：" + n++);
            }
        });
        //scheduleAtFixedRate：按固定间隔执行任务，若任务时长未超2s，那么执行间隔为2s。若任务时长超过2s，那么执行完任务会立即执行下个任务
        //先等待1s，再按固定间隔2s执行任务
        se.scheduleAtFixedRate(new MyRunnable2(1), 1,2, TimeUnit.SECONDS);
        System.out.println("主线程结束");
    }

}

class MyRunnable2 implements Runnable {

    private int id;

    public MyRunnable2(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        try {
            //模拟业务处理了1.5s
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程：" + name + " 执行了任务id：" + id);
    }
}

