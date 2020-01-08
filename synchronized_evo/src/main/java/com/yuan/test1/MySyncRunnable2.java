package com.yuan.test1;

/**
 * 对象锁示例2，方法锁形式
 * <p>
 * 这样的形式也可以达到让线程一个一个执行的效果，而不会一起执行
 * 与“1”中的代码块形式的区别：代码块形式手动指定锁对象，方法锁形式synchronized关键字直接修饰方法，锁对象默认是this
 */
public class MySyncRunnable2 implements Runnable {

    static MySyncRunnable2 r = new MySyncRunnable2();

    public synchronized void method() {
        System.out.println("对象锁的方法锁形式，当前线程是：" + Thread.currentThread().getName());
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
