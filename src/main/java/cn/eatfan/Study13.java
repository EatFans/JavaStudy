package cn.eatfan;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Java 线程创建与管理
 */
public class Study13 {
    public static void main(String[] args){
        /*
            在Java中使用多线程主要有四种方式：
            1、通过继承 Thread 类来实现多线程
            2、通过实现 Runnable 接口来实现多线程
            3、使用 Callable 来实现多线程
            4、通过线程池 ExecutorService 来实现多线程
         */

        /*
            1、通过继承 Thread 类
         */
        // 创建一个已经定义好的线程类的实例对象
        MyThread myThread = new MyThread();
        // 调用这个实例对象中的start()方法，启动该线程，在该线程中执行run()方法中的操作
        myThread.start();

        /*
            2、通过实现 Runnable 接口
         */
        // 创建一个实现Runnable接口的MyRunnable类实例对象
        MyRunnable myRunnable = new MyRunnable();
        // 再创建一个Thread类的实例对象，并将上面MyRunnable实例对象传入
        Thread thread = new Thread(myRunnable);
        // 调用thread实例对象中的start()方法，启动该线程
        thread.start();

        // 在某类的内部通过Runnable创建线程
        Thread newThread = new Thread(new Runnable() {
            /**
             *  在这里实现Runnable接口中的run()方法，该方法也是该线程启动后，开始执行该方法中的操作
             */
            @Override
            public void run() {
                System.out.println("MyRunnable 2 线程正在运行...");
                try {
                    // 模拟长时间任务，通过sleep方法让线程休眠2秒
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // 捕获并处理可能的InterruptedException异常
                    System.out.println(e.getMessage());
                }
                System.out.println("MyRunnable 2 线程结束。");
            }
        });
        // 调用newThread对象中的start()方法，启动该线程
        newThread.start();

        /*
           3、 使用 Callable
         */
        // 创建Callable对象
        MyCallable myCallable = new MyCallable();
        // 使用FutureTask包装Callable对象
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        // 创建线程并传递FutureTask
        Thread thread3 = new Thread(futureTask);
        // 启动线程
        thread3.start();

        /*
            4、通过线程池 ExecutorService
         */
        // 创建一个固定大小的线程池，在这里定义了线程池大小为1
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        // 提交任务到线程池
        // 当一个线程中的任务完成结束了后，该线程会被释放，回归线程池，可以将线程重复继续使用，直到线程池关闭
        executorService.submit(new MyTask());
        // 关闭线程池
        executorService.shutdown();
    }

}

/**
 * 定义MyThread类继承了Java中的Thread类，用于测试继承Thread实现线程任务
 */
class MyThread extends Thread {
    /**
     * 这里重写从父类Thread类中继承的run()方法，该方法也是该线程启动后，开始执行该方法中的操作
     */
    @Override
    public void run(){
        // run()方法中定义线程的任务
        System.out.println("MyThread线程正在运行...");
        try {
            // 模拟长时间任务，通过sleep方法让线程休眠2秒
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // 捕获并处理可能的InterruptedException异常
            System.out.println(e.getMessage());
        }
        System.out.println("MyThread线程结束。");
    }
}

/**
 * 定义MyRunnable类实现Runnable接口
 */
class MyRunnable implements Runnable {
    /**
     * 在这里实现Runnable接口中的run()方法，该方法也是该线程启动后，开始执行该方法中的操作
     */
    @Override
    public void run(){
        System.out.println("MyRunnable线程正在运行...");
        try {
            // 模拟长时间任务，通过sleep方法让线程休眠2秒
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // 捕获并处理可能的InterruptedException异常
            System.out.println(e.getMessage());
        }
        System.out.println("MyRunnable线程结束。");
    }
}


/**
 * 定义MyCallable类来实现Callable接口创建线程
 */
class MyCallable implements Callable<String> {
    /**
     * 这里实现Callable中的call()方法，该方法也是该线程启动后，开始执行该方法的操作，最后返回一个结果，这里返回的是一个String类型的数据
     * @return String类型
     * @throws Exception 异常
     */
    @Override
    public String call() throws Exception {
        // 线程要执行的代码，可以返回结果
        System.out.println("MyCallable线程正在运行...");
        try {
            // 模拟长时间任务，通过sleep方法让线程休眠2秒
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // 捕获并处理可能的InterruptedException异常
            System.out.println(e.getMessage());
        }
        System.out.println("MyCallable 线程结束。");
        return "Callable result";
    }
}

/**
 * 定义MyTask类实现 Runnable接口，用于测试通过线程池来实现多线程
 */
class MyTask implements Runnable {
    /**
     * 在这里实现Runnable接口中的run()方法，该方法也是该线程启动后，开始执行该方法中的操作
     */
    @Override
    public void run() {
        // 线程要执行的代码
        System.out.println("MyTask 线程正在运行中....");
        try {
            // 模拟长时间任务，通过sleep方法让线程休眠2秒
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // 捕获并处理可能的InterruptedException异常
            System.out.println(e.getMessage());
        }
        System.out.println("MyTask 线程结束。");
    }
}


