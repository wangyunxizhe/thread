package com.yuan.test1;

/**
 * 类锁示例1，static形式
 * <p>
 * 因为2个线程是不同对象创建，所以在method()方法没加static修饰前，就算有synchronized关键字，2个线程也是可以同时访问的。
 * 在加上static修饰后，2个线程才可串行执行
 */
public class MySyncRunnable3 implements Runnable {

    static MySyncRunnable3 r1 = new MySyncRunnable3();
    static MySyncRunnable3 r2 = new MySyncRunnable3();

    public static synchronized void method() {
        System.out.println("类锁的static形式，当前线程是：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);//休眠3秒，让两个线程的执行顺序更明显，便于观察
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束。");
    }

    @Override
    public void run() {
        method();
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
