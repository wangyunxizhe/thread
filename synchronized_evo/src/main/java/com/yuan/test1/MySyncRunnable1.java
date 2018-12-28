package com.yuan.test1;

/**
 * 对象锁示例1，代码块形式
 * <p>
 * 可以看出在没有同步代码块的保护下，两个线程会同时执行，加上之后才会串行执行
 * 注：可以用idea断点调试，分别观察两个线程当前的生命周期。
 * 在debug窗口下可切换线程，放到Thread下，打开代码窗口，执行this.getState()即可查看
 */
public class MySyncRunnable1 implements Runnable {

    static MySyncRunnable1 r = new MySyncRunnable1();

    //如果实际业务中，需要锁住不同需求的代码块，那么一个this（对象锁）就不够用了，这时可以自己创建锁来用
    Object lock1 = new Object();
    Object lock2 = new Object();

    @Override
    public void run() {
        //把需要同步的代码部分保护起来，这样线程就会串行执行，而不会并行（交叉）执行了
        synchronized (this) {
            System.out.println("对象锁的代码块形式，当前线程是：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);//休眠3秒，让两个线程的执行顺序更明显，便于观察
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束。");
        }

        synchronized (lock1) {
            System.out.println("对象锁的代码块形式，我是lock1锁住的代码块，当前线程是：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);//休眠3秒，让两个线程的执行顺序更明显，便于观察
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lock1部分代码块，" + Thread.currentThread().getName() + "运行结束。");
        }

        synchronized (lock2) {
            System.out.println("对象锁的代码块形式，我是lock2锁住的代码块，当前线程是：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);//休眠3秒，让两个线程的执行顺序更明显，便于观察
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lock2部分代码块，" + Thread.currentThread().getName() + "运行结束。");
        }
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
