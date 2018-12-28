package com.yuan.test1;

/**
 * 类锁示例2，synchronized（*.class）{ 代码块 }形式
 */
public class MySyncRunnable4 implements Runnable {

    static MySyncRunnable4 r1 = new MySyncRunnable4();
    static MySyncRunnable4 r2 = new MySyncRunnable4();

    public void method() {
        synchronized (MySyncRunnable4.class) {
            System.out.println("类锁的synchronized（*.class）{ 代码块 }形式，" +
                    "当前线程是：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);//休眠3秒，让两个线程的执行顺序更明显，便于观察
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束。");
        }
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
