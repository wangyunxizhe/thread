package com.yuan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyPool2 {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 使用两种方式，比较10000个线程每个线程往list中添加一个元素，哪种的耗时比较短
         */

        //第一种方式：正常创建
        Long s1 = System.currentTimeMillis();
        final Random random = new Random();
        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            };
            t.start();
            t.join();
        }
        System.err.println("普通耗时：" + (System.currentTimeMillis() - s1));
        System.err.println("list1.size：" + list.size());

        //第二种方式：使用线程池创建
        Long s2 = System.currentTimeMillis();
        final List<Integer> list2 = new ArrayList<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    list2.add(random.nextInt());
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.err.println("线程池耗时：" + (System.currentTimeMillis() - s2));
        System.err.println("list2.size：" + list.size());
    }

    /**
     * 从控制台输出的耗时可以看出使用线程池创建10000个线程要比普通方式快相当多
     * 原因：
     * 1，普通方式把大部分资源投入在创建线程上
     */

}
