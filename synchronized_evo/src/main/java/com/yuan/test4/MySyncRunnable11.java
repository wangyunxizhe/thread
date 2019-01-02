package com.yuan.test4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁的两种形式，method1和method2是等价的两个方法，只是实现形式不同
 * <p>
 * 可以看出synchronized只是帮我们完成了lock()和unlock()两个步骤
 */
public class MySyncRunnable11 {

    Lock lock = new ReentrantLock();

    public synchronized void method1() {
        System.out.println("这里是synchronized形式的锁");
    }

    public void method2() {
        lock.lock();
        try {
            System.out.println("这里是Lock形式的锁");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MySyncRunnable11 r = new MySyncRunnable11();
        r.method1();
        r.method2();
    }
}
