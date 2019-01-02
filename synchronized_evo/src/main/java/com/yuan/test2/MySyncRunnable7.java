package com.yuan.test2;

/**
 * 解决第6种情况：同时访问静态synchronized和非静态synchronized的方法
 * <p>
 * 因为加上static修饰的同步方法持有的锁是类对象，而没有加static修饰的同步方法持有的锁是实例对象，
 * 两把锁不是同一个，所以自然没有冲突，可以同时进行访问
 */
public class MySyncRunnable7 implements Runnable {

    static MySyncRunnable7 r = new MySyncRunnable7();

    public synchronized static void method1() {
        System.out.println("~~~~~这里是静态同步方法1~~~~~" +
                "当前线程是：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);//休眠3秒，让两个线程的执行顺序更明显，便于观察
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束。");
    }

    public synchronized void method2() {
        System.out.println("=====这里是非静态同步方法2=====" +
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
