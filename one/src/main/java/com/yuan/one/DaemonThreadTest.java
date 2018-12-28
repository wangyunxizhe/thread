package com.yuan.one;

import java.util.Scanner;

/**
 * 守护线程测试
 *
 * 可以用java自带的jstack.exe查看线程情况，如线程状态，哪些是守护线程，哪些是用户线程，
 * 具体位置在C:\Program Files\Java\jdk1.8.0_181\bin\jstack.exe
 */
public class DaemonThreadTest {

    public static void main(String[] args) {
        System.out.println("进入主线程：" + Thread.currentThread().getName());
        MyDaemonThread myDaemonThread = new MyDaemonThread();
        Thread thread = new Thread(myDaemonThread);
        thread.setDaemon(true);//将该线程设置为守护线程
        thread.start();
        //当控制台输入东西之后结束主线程
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        System.out.println("退出主线程：" + Thread.currentThread().getName());
    }

}
