package com.yuan.test5;

/**
 * 会造成线程死锁的一种情况
 */
public class MySyncRunnable13 implements Runnable {

    static MySyncRunnable13 r1 = new MySyncRunnable13();
    static MySyncRunnable13 r2 = new MySyncRunnable13();

    @Override
    public void run() {
        synchronized (r1) {
            System.out.println("锁对象是r1的代码块1，我是线程" + Thread.currentThread().getName());
            synchronized (r2) {
                System.out.println("锁对象是r2的代码块1，我是线程" + Thread.currentThread().getName());
            }
        }

        synchronized (r2) {
            System.out.println("锁对象是r2的代码块2，我是线程" + Thread.currentThread().getName());
            synchronized (r1) {
                System.out.println("锁对象是r1的代码块2，我是线程" + Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {//只要t1或者t2任何一个存在，就一直死循环
            //和MyErrorRunnable类的主线程中让t1，t2都join是一个目的
        }
        System.out.println("完成");
    }
}
