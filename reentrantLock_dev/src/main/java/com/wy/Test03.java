package com.wy;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示 ReentrantLock 可打断的特性（锁超时）。主动，控制权在自己手中
 */
public class Test03 {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("t1尝试获取锁");
            //拿不到就立即返回，不等待
            boolean isLock = lock.tryLock();

            //带参数：会在1S内一直尝试获取锁，这个过程也可以像Test02中那样被打断
//            lock.tryLock(1, TimeUnit.SECONDS);

            if (!isLock) {
                System.out.println("t1没获取到锁");
                return;
            }
            try {
                System.out.println("t1获取到锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        //让主线程在t1启动前，上锁
        lock.lock();
        System.out.println("主线程获取到锁");

        t1.start();
    }

}
