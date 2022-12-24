package com.wy;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 场景模拟：等待10个玩家都加载进游戏，则游戏开始
 */
public class Test03 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(10);
        Random r = new Random();
        String[] all = new String[10];
        //提交10次任务（模拟10个玩家）
        for (int j = 0; j < 10; j++) {
            //lambda表达式只能引入局部常量，不能引入局部变量，将j复制给k后，再传入到下面的lambda表达式中
            int k = j;
            es.submit(() -> {
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(r.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    all[k] = i + "%";
                    //小技巧：不换行打印，再使用 杠r 覆盖掉前一次的打印，模拟从0加载到100的效果
                    System.out.print("\r" + Arrays.toString(all));
                }
                latch.countDown();
            });
        }

        latch.await();
        System.out.println("\n游戏开始");
        es.shutdown();
    }

}
