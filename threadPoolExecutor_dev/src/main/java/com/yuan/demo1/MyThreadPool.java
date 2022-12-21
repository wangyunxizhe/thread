package com.yuan.demo1;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 自定义线程池演示 -- 线程池类
 */
public class MyThreadPool {

    //任务队列（注意控制线程安全问题）
    private List<Runnable> tasks = Collections.synchronizedList(new LinkedList<>());

    //当前线程数量
    private int num;

    //核心线程数量
    private int coreSize;

    //最大线程数量
    private int maxSize;

    //任务队列长度
    private int workSize;

    public MyThreadPool(int coreSize, int maxSize, int workSize) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.workSize = workSize;
    }

    //提交任务
    public void submit(Runnable r) {
        //判断当前集合中任务的数量，是否超出了最大任务数量
        if (tasks.size() >= workSize) {
            System.out.println("任务：" + r + "被丢弃了。。。");
        } else {
            tasks.add(r);
            //执行任务
            execTask(r);
        }
    }

    //执行任务
    private void execTask(Runnable r) {
        //判断当前线程池中的线程总数量，是否超出了核心数
        if (num < coreSize) {
            new MyWorker("我是核心线程" + num, tasks).start();
            num++;
        } else if (num < maxSize) {
            new MyWorker("我是非核心线程" + num, tasks).start();
            num++;
        } else {
            System.out.println("任务：" + r + " 被缓存了");
        }
    }

}
