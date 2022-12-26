package com.yuan;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join框架是Java7提供了的一个用于并行执行任务的框架。
 * ForkJoinPool是Java中提供了一个线程池，特点是用来执行分治任务。
 * 主题思想是将大任务分解为小任务，然后继续将小任务分解，直至能够直接解决为止，然后再依次将任务的结果合并。
 *
 * 主要用法和之前的线程池是相同的，也是把任务交给线程池去执行，线程池中也有任务队列来存放任务。
 * 但是 ForkJoinPool 线程池和之前的线程池有两点非常大的不同之处。第一点它非常适合执行可以产生子任务的任务，子任务基本都是相同逻辑的重复计算。
 * 第二点不同之处在于内部结构，之前的线程池所有的线程共用一个队列，但 ForkJoinPool 线程池中每个线程都有自己独立的任务队列，如图所示。
 */
public class MyPool5ForkJoin {

    public static void main(String[] args) {
        //如果未传参数，那么线程池线程数=CPU核数
        ForkJoinPool myPool = new ForkJoinPool(4);
        System.out.println(myPool.invoke(new MyForkJoinTask(5)));
    }

}

//任务：计算1-n之间整数的和
class MyForkJoinTask extends RecursiveTask<Integer> {

    private int n;

    public MyForkJoinTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        //根据主线程中的入参 new MyForkJoinTask(5) 进行逻辑拆分：5+new MyForkJoinTask(4) -> 4+new MyForkJoinTask(3)
        // -> 3+new MyForkJoinTask(2) -> 2+new MyForkJoinTask(1)

        //如果n传的就是1，那直接返回
        if (n == 1) {
            System.out.println(Thread.currentThread().getName() + " join() " + n);
            return n;
        }
        MyForkJoinTask t1 = new MyForkJoinTask(n - 1);
        t1.fork();//让一个线程去执行此任务，这个线程的来源就是主线程中的 ForkJoinPool 线程池
        System.out.println(Thread.currentThread().getName() + " fork() " + n + " " + t1);

        Integer newVal = t1.join();
        int result = n + newVal;
        System.out.println(Thread.currentThread().getName() + " join() " + n + " " + t1 + " = " + result);
        return result;
    }

    @Override
    public String toString() {
        return "MyForkJoinTask{" +
                "n=" + n +
                '}';
    }
}
