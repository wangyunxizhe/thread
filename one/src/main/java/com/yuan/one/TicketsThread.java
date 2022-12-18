package com.yuan.one;

/**
 * 从例子可以看出继承Thread的方式构建线程，3个窗口累计卖了15张票，超出了原本定义的总数5张
 * 因为继承Thread的方式，不能让类变量（ticketsCon）共享
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
