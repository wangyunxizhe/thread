package com.yuan.test3;

/**
 * 可重入粒度测试：递归调用本方法
 * 结论：同一个方法是“可重入”的
 *
 * 可重入的含义：同一线程的外层函数获得锁之后，内层函数可以直接再次获取该锁
 */
public class MySyncRunnable8 {

    int a = 0;

    private synchronized void method1() {
        System.out.println("这里是method1方法，a=" + a);
        if (a == 0) {
            a++;
            method1();
        }
    }

    public static void main(String[] args) {
        MySyncRunnable8 r = new MySyncRunnable8();
        r.method1();//从输出可以看出，执行了2次method1方法，说明同一个方法这种情况是可重入的
    }

}
