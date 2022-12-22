package com.yuan.demo6;

/**
 * 任务类 -- 负责跑业务的线程
 */
public class MyTask implements Runnable {

    //用户姓名
    private String username;

    //取款金额
    private double money;

    //由于是模拟让两张银行卡同时取同一账户里的款，所以这里的总金额要对两张卡共享，故加 static 修饰
    private static double total = 1000;

    public MyTask(String username, double money) {
        this.username = username;
        this.money = money;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(username + " 正在使用线程：" + name + " 取款 " + money + " 元");

        //便于观察
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (MyTask.class) {
            if (total - money > 0) {
                System.out.println(username + " 使用线程：" + name + " 取款 " + money + " 元，成功，余额：" + (total - money));
                total -= money;
            }else {
                System.out.println(username + " 使用线程：" + name + " 取款 " + money + " 元，失败，余额：" + total);
            }
        }
    }

}
