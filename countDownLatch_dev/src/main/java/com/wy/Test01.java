package com.wy;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

/**
 * CountDownLatch 基本使用
 */
public class Test01 {

    public static void main(String[] args) throws InterruptedException {
        //创建时传入3，表示总计有3个线程
        CountDownLatch latch = new CountDownLatch(3);

        new Thread(() -> {
            System.out.println("begin...");

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //跑完业务就减去一个线程计数
            latch.countDown();
            System.out.println("end..." + latch.getCount());
        }).start();

        new Thread(() -> {
            System.out.println("begin...");

            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //跑完业务就减去一个线程计数
            latch.countDown();
            System.out.println("end..." + latch.getCount());
        }).start();

        new Thread(() -> {
            System.out.println("begin...");

            try {
                sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //跑完业务就减去一个线程计数
            latch.countDown();
            System.out.println("end..." + latch.getCount());
        }).start();

        System.out.println("waiting...");
        latch.await();
        System.out.println("wait end");
    }

}
