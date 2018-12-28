package com.yuan.two;

/**
 * 该类对volatile修饰的变量，进行了变量在复合操作下是否能保证原子性进行了测试
 * （该章节的参考在父工程的“多线程共享变量之内存可见性.txt”中）
 * <p>
 * 从输出可以看出，有时结果并不是500，所以在变量的++/--这样的复合操作下，volatile并不能保证变量number的原子性
 */
public class VolatileTest {

    public volatile int number = 0;

    public int num = 0;//改造后变量

    public int getNumber() {
        return this.number;
    }

    public int getNum() {
        return this.num;
    }

    public void autoAdd() {
        this.number++;
    }

    //改造后的方法
    public synchronized void autoAdd2() {
        this.num++;
        //也可以像下面这样写，使锁的粒度更小，代码执行效率更高
//        synchronized (this){
//            this.num++;
//        }
    }

    public static void main(String[] args) {
        System.out.println("开始运行");
        final VolatileTest test = new VolatileTest();
        for (int i = 0; i < 500; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test.autoAdd();
                    test.autoAdd2();
                }
            }).start();
        }

        //如果还有子线程在运行，主线程就一直让出CPU资源，直到所有的子线程都执行完毕，
        //主线程再跳出while循环继续执行下面的代码
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println("number：" + test.getNumber());
        System.out.println("num：" + test.getNum());//运用改造后的变量和add方法，可以保证了num变量的原子性，每次都是500
    }

}
