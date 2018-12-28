package com.yuan.one;

/**
 * Created by wangy on 2018/12/21.
 */
public class MyThread extends Thread {

    private int ticketsCon = 5;//假设一共有5张票
    private String name;//线程名

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (ticketsCon > 0) {
            ticketsCon--;
            System.out.println(name + "卖了一张票，剩余票数为：" + ticketsCon);
        }
    }
}
