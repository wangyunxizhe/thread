package com.yuan.test3;

/**
 * 可重入粒度测试：调用不同类中的同步方法（用父类来模拟）
 */
public class MySyncRunnable10 {

    public synchronized void doSomething() {
        System.out.println("这里是父类的方法");
    }

}

class TestClass extends MySyncRunnable10 {

    @Override
    public synchronized void doSomething() {
        System.out.println("这里是子类的方法");
        super.doSomething();
    }

    public static void main(String[] args) {
        TestClass t = new TestClass();
        t.doSomething();//从输出结果可以看出，即执行了子类的同步方法，也执行了父类的同步方法，
        // 所以不同类中同步方法也是可重入的。
    }

}
