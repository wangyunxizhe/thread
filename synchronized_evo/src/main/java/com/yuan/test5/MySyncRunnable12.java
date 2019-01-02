package com.yuan.test5;

/**
 * 注意事项：锁对象为空的情况
 */
public class MySyncRunnable12 implements Runnable {

    static MySyncRunnable12 r = null;//可以看出当锁对象为空时，根本就没有执行run()方法，更不会执行method()方法

    public synchronized void method() {
        System.out.println("执行了同步方法，当前线程是：" + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("执行了run方法");
        method();
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {//只要t1或者t2任何一个存在，就一直死循环
            //和MyErrorRunnable类的主线程中让t1，t2都join是一个目的
        }
        System.out.println("完成");
    }

}
