package com.wy;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.Thread.sleep;

/**
 * 需要在主线程中获取子线程的返回值
 */
public class Test04 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Test04 t = new Test04();

        System.out.println("begin...");

        ExecutorService es = Executors.newCachedThreadPool();

        Future<String> f1 = es.submit(() -> {
            String result = t.task("1号任务");
            return result;
        });

        Future<String> f2 = es.submit(() -> {
            String result = t.task("2号任务");
            return result;
        });

        Future<String> f3 = es.submit(() -> {
            String result = t.task("3号任务");
            return result;
        });

        Future<String> f4 = es.submit(() -> {
            String result = t.task("4号任务");
            return result;
        });

        System.out.println("f1 = " + f1.get());
        System.out.println("f2 = " + f2.get());
        System.out.println("f3 = " + f3.get());
        System.out.println("f4 = " + f4.get());

        System.out.println("执行完毕");

        es.shutdown();

    }

    public String task(String arg) {
        String result = arg;
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

}
