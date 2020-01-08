package com.yuan.one;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * 自定义守护线程类
 *
 * 在java中，守护线程最典型的应用就是 GC (垃圾回收器)
 *
 */
public class MyDaemonThread implements Runnable {

    //不断的向指定文件中写“word0，word1,...”
    private void writeToFile() throws Exception {
        File filename = new File("daemon.txt");
        OutputStream os = new FileOutputStream(filename, true);
        int count = 0;
        while (count < 999) {
            os.write(("\r\nword" + count).getBytes());
            System.out.println("守护线程：" + Thread.currentThread().getName() + "向文件中写入了word" + count++);
            Thread.sleep(1000);
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "：进入守护线程");
        try {
            writeToFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "：退出守护线程");
    }

}
