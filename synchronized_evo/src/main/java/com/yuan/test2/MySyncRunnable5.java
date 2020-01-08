package com.yuan.test2;

/**
 * 解决第4种情况：同时访问同步方法与非同步方法
 * 结果：并行执行。没加锁的方法并不会收到加锁方法的影响。
 */
public class MySyncRunnable5 implements Runnable {

    static MySyncRunnable5 r = new MySyncRunnable5();

    public synchronized void method1() {
        System.out.println("~~~~~这里是同步方法~~~~~" +
                "当前线程是：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);//休眠3秒，让两个线程的执行顺序更明显，便于观察
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束。");
    }

    public void method2() {
        System.out.println("=====这里是非同步方法=====" +
                "当前线程是：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);//休眠3秒，让两个线程的执行顺序更明显，便于观察
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束。");
    }

    @Override
    public void run() {
        //达到了2个线程同时访问同步方法与非同步方法
        if (Thread.currentThread().getName().equals("abc")) {
            method1();
        } else {
            method2();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(r);
        t1.setName("abc");
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {//只要t1或者t2任何一个存在，就一直死循环
            //和MyErrorRunnable类的主线程中让t1，t2都join是一个目的
        }
        System.out.println("完成");
    }
}
