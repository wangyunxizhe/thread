package com.wy;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示 ReentrantLock 公平锁的特性（默认不公平，可设置为公平）
 * synchronized是不公平锁，A线程持有锁时，其余线程只能进入阻塞队列。当A释放锁后，阻塞队列中的线程会一起去抢，没有先来后到的说法
 */
public class Test05 {

    //创建时传入true，即是公平锁
    private static ReentrantLock lock = new ReentrantLock(true);

}
