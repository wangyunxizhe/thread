package com.wy;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * 演示 ReentrantLock 可打断的特性。被动，控制权在其他线程手中
 * synchronized无此特性，如线程A持有锁，线程B在等待，B无法中断等待
 */
public class Test02 {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1尝试获取锁");
                //注意：像Test01中的lock()是无法被打断的，需要使用如下方法才能实现 可打断的效果
                //如果没有竞争，那么此方法就会获取 lock 对象锁
                //如果有竞争，则进入阻塞队列，可以被其他线程调用t1实例的 interrupt() 进行打断
                lock.lockInterruptibly();
//                lock.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("t1没有获得锁，返回");
                return;
            }
            try {
                System.out.println("t1获取到锁啦");
            } finally {
                lock.unlock();
            }
        }, "t1");

        //让主线程在t1启动前，上锁
        lock.lock();

        t1.start();

        //主线程睡1S
        sleep(1000);
        System.out.println("主线程 打断 t1");
        t1.interrupt();
    }

}
