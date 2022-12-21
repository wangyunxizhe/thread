package com.yuan.demo1;

import java.util.List;

/**
 * 自定义线程池演示 -- 线程类，负责跑任务
 * 设计一个集合，用于保存所有任务
 */
public class MyWorker extends Thread {

    private String name;//线程名
    private List<Runnable> tasks;

    public MyWorker(String name, List<Runnable> tasks) {
        super(name);
        this.tasks = tasks;
    }

    @Override
    public void run() {
        //判断集合中是否有任务，如果有，就一直执行任务
        while (tasks.size() > 0) {
            Runnable r = tasks.remove(0);
            r.run();
        }
    }
}
