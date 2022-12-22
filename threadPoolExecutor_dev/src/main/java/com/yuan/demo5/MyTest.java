package com.yuan.demo5;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 主线程
 * 模拟共有10件商品，总共20个客户去抢
 */
public class MyTest {

    public static void main(String[] args) {
        //线程池
        ThreadPoolExecutor myPool = new ThreadPoolExecutor(3, 5,
                1, TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(15));

        //任务
        for (int i = 1; i <= 20; i++) {
            MyTask task = new MyTask("客户"+i);
            myPool.submit(task);
        }

        //关闭线程池
        myPool.shutdown();

    }

}
