package com.yuan;

import java.util.concurrent.*;

public class MyPool1 {

    public static void main(String[] args) {
        //虽然jdk提供了自带的创建线程池的方法，但工作中并步建议使用，因为这样的创建方式对线程池的控制粒度比较小
//        ExecutorService executorService = Executors.newFixedThreadPool(1);

        // 线程池的7个参数
        // 参数1：核心线程数。参数2：最大线程数。参数3：最大线程数的空闲时间。参数4：参数3的时间单位
        // 参数5：阻塞队列（对线程池细粒度控制的关键）
        // 参数6：线程工厂（可以给每个线程命名，方便调试时定位问题）
        // 参数7：拒绝策略
        ThreadPoolExecutor myPool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setName("wyuan");
                        return t;
                    }
                },
                new ThreadPoolExecutor.AbortPolicy());

        //模拟线程池执行任务
        myPool.execute(() -> {
            for (int i = 0; i < 10; i++) {
                System.err.println(i);
            }
        });

        //调用shutdown()线程池会由RUNNING转为SHUTDOWN状态
        myPool.shutdown();

        //调用shutdown()线程池会由RUNNING转为STOP状态
        myPool.shutdownNow();
        /**
         * 以上两种状态都会转为TIDYING（过渡状态），
         * SHUTDOWN状态会处理完未结束的线程任务，等到工作线程为空时转到TIDYING状态，
         * STOP状态等到工作线程为空时就会转到TIDYING状态，
         * 最终，到达TIDYING状态后，都会调用terminated()，到达TERMINATED状态
         */

    }

}
