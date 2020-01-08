package com.yuan.test2;

/**
 * 解决第7种情况：方法抛出异常后，会释放锁
 * <p>
 * 从代码执行可以看出：第一个线程一旦抛出了异常，第二个线程会立刻进入同步方法，这意味着第一个线程已经将锁释放。
 * 原理：当某个线程在执行加锁的方法时抛出了异常，那么JVM会帮该线程释放锁以便让下个线程进入加锁的方法。
 */
public class MySyncWithException implements Runnable {

    static MySyncWithException r = new MySyncWithException();

    public synchronized void method1() {
        System.out.println("~~~~~这里是静态同步方法1~~~~~" +
                "当前线程是：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);//休眠3秒，让两个线程的执行顺序更明显，便于观察
            throw new Exception("人为抛出异常");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
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
