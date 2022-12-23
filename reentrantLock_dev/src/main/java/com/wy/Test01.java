package com.wy;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示 ReentrantLock 可重入的特性
 * synchronized也具有可重入的特性
 */
public class Test01 {

    //与synchronized的区别：ReentrantLock是对象级别创建，synchronized是关键字修饰
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        //在主方法中 lock对象 上锁
        lock.lock();
        try {
            System.out.println("进入主方法");
            //还未到解锁代码，就调用m1方法
            m1();
        } finally {
            lock.unlock();
        }
    }

    public static void m1(){
        //m1中 lock对象 再上锁（发生重入，若不可重入，线程会被阻塞）
        lock.lock();
        try {
            System.out.println("进入m1方法");
            //还未到解锁代码，就调用m2方法
            m2();
        } finally {
            lock.unlock();
        }
    }

    public static void m2(){
        //m2中 lock对象 再上锁（发生重入，若不可重入，线程会被阻塞）
        lock.lock();
        try {
            System.out.println("进入m2方法");
        } finally {
            lock.unlock();
        }
    }

}
