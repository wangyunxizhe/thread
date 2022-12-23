package com.wy;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock解决synchronized产生的死锁
 */
public class Test04 {

    public static void main(String[] args) {
        KuaiZi k1 = new KuaiZi("1");
        KuaiZi k2 = new KuaiZi("2");
        KuaiZi k3 = new KuaiZi("3");
        KuaiZi k4 = new KuaiZi("4");
        KuaiZi k5 = new KuaiZi("5");

        new Person("张三", k1, k2).start();
        new Person("李四", k2, k3).start();
        new Person("王二", k3, k4).start();
        new Person("赵五", k4, k5).start();
        new Person("刘六", k5, k1).start();
    }

}

class Person extends Thread {

    KuaiZi left;
    KuaiZi right;

    public Person(String name, KuaiZi left, KuaiZi right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {

            //使用synchronized这样锁，就会产生死锁
            //尝试获得左手的筷子
            /*synchronized (left) {
                //尝试获得右手的筷子
                synchronized (right) {
                    eat();
                }
            }*/

            //尝试获得左手的筷子
            boolean leftFlag = left.tryLock();
            if (leftFlag) {
                try {
                    //尝试获得右手的筷子
                    boolean rightFlag = right.tryLock();
                    if (rightFlag) {
                        try {
                            eat();
                        } finally {
                            //释放右手的筷子
                            right.unlock();
                        }
                    }
                } finally {
                    //释放左手的筷子
                    left.unlock();
                }
            }

        }
    }

    private void eat() {
        System.out.println(Thread.currentThread().getName() + " eating...");
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

//改造1：KuaiZi继承ReentrantLock
//class KuaiZi {
class KuaiZi extends ReentrantLock {

    String name;

    public KuaiZi(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "筷子{" +
                "name='" + name + '\'' +
                '}';
    }
}
