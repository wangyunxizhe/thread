package com.wy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

/**
 * CountDownLatch 改进使用
 */
public class Test02 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService es = Executors.newFixedThreadPool(4);
        //前3个任务模拟处理业务
        es.submit(() -> {
            System.out.println("begin...");

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //跑完业务就减去一个线程计数
            latch.countDown();
            System.out.println("end..." + latch.getCount());
        });

        es.submit(() -> {
            System.out.println("begin...");

            try {
                sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //跑完业务就减去一个线程计数
            latch.countDown();
            System.out.println("end..." + latch.getCount());
        });

        es.submit(() -> {
            System.out.println("begin...");

            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //跑完业务就减去一个线程计数
            latch.countDown();
            System.out.println("end..." + latch.getCount());
        });

        //最后一个线程负责等待
        es.submit(()->{
            try {
                System.out.println("waiting...");
                latch.await();
                System.out.println("wait end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

}
