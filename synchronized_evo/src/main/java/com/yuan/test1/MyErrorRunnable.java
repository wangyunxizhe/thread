package com.yuan.test1;

/**
 * 没有使用同步的情况下，让两个线程同时对i进行++操作
 * <p>
 * 从输出结果可以看出并不是预期的200000
 */
public class MyErrorRunnable implements Runnable {

    static MyErrorRunnable r = new MyErrorRunnable();

    static int i = 0;

    //没有任何同步处理的方法
    public void errorMethod() {
        for (int j = 0; j < 100000; j++) {
            i++;
        }
    }

    //改造后的方法1
    public void method1() {
        synchronized (this) {
            for (int j = 0; j < 100000; j++) {
                i++;
            }
        }
    }

    //改造后的方法2
    public void method2() {
        synchronized (MyErrorRunnable.class) {
            for (int j = 0; j < 100000; j++) {
                i++;
            }
        }
    }

    @Override
    public void run() {
//        errorMethod();
        method1();
//        method2();
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();//保证t1执行完之后才继续下面的代码
        t2.join();//保证t2执行完之后才继续下面的代码
        System.out.println(i);
    }
}
