package com.yuan.test3;

/**
 * 可重入粒度测试：调用类中另外的同步方法
 */
public class MySyncRunnable9 {

    private synchronized void method1() {
        System.out.println("这里是method1方法");
        method2();
    }

    private synchronized void method2() {
        System.out.println("这里是method2方法");
    }

    public static void main(String[] args) {
        MySyncRunnable9 r = new MySyncRunnable9();
        r.method1();//从输出可以看出，调用同步方法1时，同样也执行了1内部的同步方法2
    }

}
