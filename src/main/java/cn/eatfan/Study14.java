package cn.eatfan;

/**
 * Java 线程同步与锁机制
 */
public class Study14 {
    public static void main(String[] args){
        /*
            线程同步的概念:
	        多个线程同时访问共享资源（如变量或对象）时，如果不进行适当的同步，可能会导致数据不一致的问题。
	        Java通过synchronized关键字实现线程同步，确保同一时间只有一个线程可以执行同步代码块或方法，从而避免数据竞态问题

            Counter类的同步方法:
	        Counter类中的increment()方法被synchronized修饰，意味着这个方法在某一时刻只能被一个线程执行。线程进入这个方法后，
	        其他试图调用此方法的线程将被阻塞，直到当前线程执行完该方法并释放锁。
	        getCount()方法没有使用synchronized，因为它只是简单地返回当前计数值，读取操作通常不需要同步。

            Runnable实现类CounterIncrementer:
	        该类实现了Runnable接口，便于在多个线程中使用。它持有一个Counter对象的引用，并在run()方法中多次调用increment()方法，
	        模拟多个线程对共享资源的并发访问。

         */

        Counter counter = new Counter();
        // 创建两个线程，并传入相同的Counter实例
        Thread t1 = new Thread(new CounterIncrementer(counter), "Thread-1");
        Thread t2 = new Thread(new CounterIncrementer(counter), "Thread-2");

        // 启动线程
        t1.start();
        t2.start();

        // 每个线程都会安全地增加共享计数器的值，不会出现数据错乱的情况。这就是线程同步和锁机制的作用所在。
    }
}

/**
 * 定义Counter类为一个计数器类，包含共享的计数器变量
 */
class Counter {
    private int count = 0;

    /**
     * 增加计数的方法，通过synchronized关键字保证线程安全
     */
    public synchronized void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + " incremented count to: " + count);
    }

    /**
     * 获取当前计数值的方法
     * @return 当前的计数值
     */
    public int getCount() {
        return count;
    }
}

/**
 * 定义一个CounterIncrementer类为计数器增量器类，实现Runnable接口，以便在线程中运行
 */
class CounterIncrementer implements Runnable {
    private final Counter counter;

    /**
     * 构造方法，传入共享的Counter实例
     * @param counter 共享的计数器对象
     */
    public CounterIncrementer(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        // 对计数器进行5次增量操作
        for (int i = 0; i < 5; i++) {
            counter.increment(); // 由于increment()方法是同步的，因此在同一时间只有一个线程能执行该方法
            try {
                Thread.sleep(100); // 让线程休眠100毫秒，模拟处理时间
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
    }
}