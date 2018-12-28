package com.yuan.one;

/**
 * 从例子可以看出3个窗口累计卖了15张票
 */
public class TicketsThread {

    public static void main(String[] args) {
        //创建3个线程，模拟3个窗口同时在卖票
        MyThread t1 = new MyThread("窗口1");
        MyThread t2 = new MyThread("窗口2");
        MyThread t3 = new MyThread("窗口3");
        t1.start();
        t2.start();
        t3.start();
    }

}
