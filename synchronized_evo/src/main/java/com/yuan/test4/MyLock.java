package com.yuan.test4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 展示Lock类中的方法
 * <p>
 * synchronized与Lock类比较：前者不可中断，如果锁已被某个线程获取到，其他线程只能一直等下去。
 * 后者可以中断已经获取到锁的线程的执行，或者让等待时间过长的线程停止等待，退出。
 */
public class MyLock {

    public static void main(String[] args) throws Exception {
        Lock lock = new ReentrantLock();
        lock.lock();//可根据自己的业务决定在哪上锁
        lock.unlock();//可根据自己的业务决定在哪解锁
        lock.tryLock();//尝试获得锁，返回值为Boolean
        lock.tryLock(10, TimeUnit.SECONDS);//在10秒内尝试获得锁，获取不到就放弃。参数2为时间单位
    }

}
