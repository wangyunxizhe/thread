package com.wy;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class Test05 {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);

//        test1(es);

        test2(es);

        es.shutdown();
    }

    //CyclicBarrier与CountDownLatch区别1：前者调用await()之后，1会等待2也执行完毕，然后一起结束
    private static void test1(ExecutorService es) {
        CyclicBarrier barrier = new CyclicBarrier(2);

        es.submit(() -> {
            System.out.println("task1 begin...");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                //计算逻辑与CountDownLatch的countDown()方法类似
                barrier.await();//2-1=1
                System.out.println("task1 end...");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        es.submit(() -> {
            System.out.println("task2 begin...");
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                barrier.await();//1-1=0
                System.out.println("task2 end...");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
    }

    //CyclicBarrier与CountDownLatch区别2：前者可以重复被使用，CountDownLatch的计数不能重复使用
    private static void test2(ExecutorService es) {
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            System.out.println("task1 task2 finish...");
        });

        for (int i = 0; i < 3; i++) {
            es.submit(() -> {
                System.out.println("task1 begin...");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    barrier.await();//2-1=1
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

            es.submit(() -> {
                System.out.println("task2 begin...");
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
