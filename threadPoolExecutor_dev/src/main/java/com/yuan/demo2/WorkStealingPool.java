package com.yuan.demo2;

import java.util.concurrent.*;

public class WorkStealingPool {

    public static void main(String[] args) throws InterruptedException {
        //创建一个具有抢占式操作的线程池（这是1.8之后新增的线程池），不同处之一：每个线程都有一个任务队列存放任务
        //线程数=CPU核数（不传参默认也是）
        ExecutorService executorService5 = Executors.newWorkStealingPool(Runtime.getRuntime().availableProcessors());
        LinkedBlockingDeque<Future<String>> strings = new LinkedBlockingDeque<>();
        // CPU 核数
        System.out.println("CPU核数：" + Runtime.getRuntime().availableProcessors());
        CountDownLatch countDownLatch = new CountDownLatch(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < Runtime.getRuntime().availableProcessors() + 1; i++) {
            Future<String> submit = executorService5.submit(new Callable<String>() {
                @Override
                public String call() {
                    System.out.println("展示线程：" + Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                    return "展示线程：" + Thread.currentThread().getName();
                }
            });
            strings.offer(submit);
        }

        countDownLatch.await();

        System.out.println("over");
        executorService5.shutdown();

        strings.forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

}
