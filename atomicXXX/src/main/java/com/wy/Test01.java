package com.wy;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger 原子整型
 */
public class Test01 {

    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(0);

        //保证了自增和获取这两个动作是原子的，确保了线程安全
        System.out.println(i.incrementAndGet());//等同于++i
        System.out.println(i.getAndIncrement());//等同于i++

        System.out.println(i.get());

        //自减与自增原理一致
        System.out.println(i.decrementAndGet());
        System.out.println(i.getAndDecrement());

        //指定值的自增
        System.out.println(i.getAndAdd(5));//除了默认的自增1，还可以指定自增的数字，5++
        System.out.println(i.addAndGet(5));//++5

        //update同样也是原子操作，如果同一时间有别的线程先改了value，这里会拿到改完以后的值再做乘法，再返回
        System.out.println(i.updateAndGet(value -> value * 5));
        System.out.println(i.getAndUpdate(value -> value * 5));
    }

}
