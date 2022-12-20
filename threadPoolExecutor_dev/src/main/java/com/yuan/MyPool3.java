package com.yuan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyPool3 {

    public static void main(String[] args) {
        /**
         * 观察以下3种线程池，哪种完成的最快
         * 造成速度快慢的原因是因为传递给new ThreadPoolExecutor构造函数中那7个入参（MyPool1中有笔记）各有不同，
         * 尤其是参数5（阻塞队列）的性质不同
         */
        //快，内部入参决定100个MyTask就会new出100个线程来干活（前提是MyTask里睡了1S，后续Task进了阻塞队列，从而new出新线程来处理排队的Task）
        //如果没有睡1S（业务几乎不耗时）那么就会线程复用，不会出现100个线程对应100个MyTask
        ExecutorService es1 = Executors.newCachedThreadPool();
        //慢，内部入参核心、非核心线程数都为10，每次从阻塞队列中取10个出来处理。注意：如果这里传入100，效果会跟es1一样
        ExecutorService es2 = Executors.newFixedThreadPool(10);
        //最慢，内部入参核心、非核心线程数都为1，单线程处理（就是Fixed的单一版本）
        ExecutorService es3 = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 100; i++) {
            es1.execute(new MyTask(i));
//            es2.execute(new MyTask(i));
//            es3.execute(new MyTask(i));
        }
    }

}

class MyTask implements Runnable {

    int i = 0;

    public MyTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.err.println(Thread.currentThread().getName() + "--" + i);
        try {
            Thread.sleep(1000l);//模拟正在处理业务逻辑
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
