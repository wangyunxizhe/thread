package com.yuan.one;

/**
 * Created by wangy on 2018/12/21.
 */
public class MyRunnable implements Runnable {

    private int ticketsCon = 5;//假设一共有5张票

    @Override
    public void run() {
        while (ticketsCon > 0) {
            ticketsCon--;
            System.out.println(Thread.currentThread().getName() + "卖了一张票，剩余票数为：" + ticketsCon);
        }
    }

}
