package com.yuan.demo6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 主线程
 * 模拟2张银行卡去同一账户中取钱，账户总共就1000元，要取的金额是800，故只有1张卡能取到钱
 */
public class MyTest {

    public static void main(String[] args) {
        //线程池
        ExecutorService myPool = Executors.newFixedThreadPool(2, new ThreadFactory() {
            int id = 1;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "ATM" + id++);
            }
        });
        //创建2个任务并提交
        for (int i = 1; i <= 2; i++) {
            MyTask myTask = new MyTask("客户" + i, 800);
            myPool.submit(myTask);
        }
        //关闭线程池
        myPool.shutdown();
    }

}
