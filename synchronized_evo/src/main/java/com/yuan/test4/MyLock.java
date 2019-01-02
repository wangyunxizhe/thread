package com.yuan.test4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 展示Lock类中的方法
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
