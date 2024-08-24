package cn.eatfan;

import java.util.concurrent.Callable;

/**
 * Java 线程创建与管理
 */
public class Study13 {
    public static void main(String[] args){
        /*
            在Java中使用多线程主要有四种方式：
            1、通过继承 Thread 类来实现多线程
            2、通过实现 Runnable 接口来实现多线程
            3、使用 Callable 和 Future 来实现多线程
            4、通过线程池 ExecutorService 来实现多线程

         */

    }

}

class MyThread extends Thread {
    @Override
    public void run(){

    }
}

class MyRunnable implements Runnable {
    @Override
    public void run(){

    }
}


class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return null;
    }
}

class MyTask implements Runnable {
    @Override
    public void run() {
        // 线程要执行的代码
        System.out.println("Task is running.");
    }
}


