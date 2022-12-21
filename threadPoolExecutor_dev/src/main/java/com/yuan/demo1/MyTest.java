package com.yuan.demo1;

/**
 * 自定义线程池演示 -- 测试类
 */
public class MyTest {

    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(2,4,20);
        //提交多个任务
        for (int i = 0; i < 10; i++) {
            //创建任务对象，并提交给线程池
            MyTask myTask=new MyTask(i);
            pool.submit(myTask);
        }
    }

}
