package com.yuan.demo4;

import java.util.concurrent.*;

/**
 * 演示获取异步计算结果
 */
public class FutureDemo {

    public static void main(String[] args) throws Exception {
        //创建一个线程池
        ExecutorService es = Executors.newCachedThreadPool();
        //创建Callable类型的任务类
        Future<Integer> result = es.submit(new MyCall(1, 1));

//        test1(result);

//        boolean isCancel = result.cancel(true);
//        System.out.println("取消任务执行的结果：" + isCancel);

        //由于任务自身耗时就需要2S，这里只等待了1S，故会报错且拿不到执行结果
        Integer v = result.get(1, TimeUnit.SECONDS);
        System.out.println("任务执行的结果是：" + v);
    }

    //正常测试
    private static void test1(Future<Integer> result) throws Exception {
        //判断任务是否已经完成
        boolean isDone = result.isDone();
        System.out.println("第一次判断任务是否完成：" + isDone);
        //如果在任务正常完成之前将其取消，则返回true
        boolean isCancelled = result.isCancelled();
        System.out.println("第一次判断任务是否取消：" + isCancelled);
        //会一直阻塞，直到有返回值
        Integer v = result.get();
        System.out.println("任务执行的结果是：" + v);

        //再次判断任务是否已经完成
        boolean isDone2 = result.isDone();
        System.out.println("第二次判断任务是否完成：" + isDone2);
        boolean isCancelled2 = result.isCancelled();
        System.out.println("第二次判断任务是否取消：" + isCancelled2);
    }

}

/**
 * 与继承Thread，实现Runnable的不同处，实现Callable是带返回值的
 */
class MyCall implements Callable<Integer> {

    private int a;
    private int b;

    public MyCall(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer call() throws Exception {
        String name = Thread.currentThread().getName();
        System.out.println("线程：" + name + " 准备开始计算。。。");
        Thread.sleep(2000);//模拟算了2s
        System.out.println("线程：" + name + "计算完成！");
        return a + b;
    }
}
