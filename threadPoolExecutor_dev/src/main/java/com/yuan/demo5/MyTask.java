package com.yuan.demo5;

/**
 * 任务类 -- 负责跑业务的线程
 */
public class MyTask implements Runnable {

    //商品数量（static修饰：无论MyTask创建多少个，num就一份）
    private static int num = 10;

    //客户名称
    private String userName;

    public MyTask(String userName) {
        this.userName = userName;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(userName + " 正在使用 线程：" + name + " 参与秒杀任务。。。");

        //便于观察
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (MyTask.class) {
            if (num > 0) {
                System.out.println(userName + "使用 线程：" + name + " 秒杀了第 " + num-- + " 件商品");
            }else {
                System.out.println(userName + "使用 线程：" + name + " 秒杀失败了！！！");
            }
        }
    }

}
