package com.yuan.one;

/**
 * Runnable避免了继承Thread方式的缺陷，因为java时单继承
 * 从两个方式的卖票例子还可以看出Runnable方式可以被多个线程（Thread对象：t1,t2,t3）共享，
 * 适合于多个线程处理同一资源的情况
 */
public class TicketsRunnable {

    public static void main(String[] args) {
        MyRunnable r1 = new MyRunnable();
        Thread t1=new Thread(r1,"窗口1");
        Thread t2=new Thread(r1,"窗口2");
        Thread t3=new Thread(r1,"窗口3");
        t1.start();
        t2.start();
        t3.start();
    }

}
