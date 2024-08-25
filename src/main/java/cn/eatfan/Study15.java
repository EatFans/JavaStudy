package cn.eatfan;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Java 并发包
 */
public class Study15 {
    public static void main(String[] args){
        /*
            Java 并发包简介：
            Java并发包位于java.util.concurrent包中，它包含了许多用于多线程编程的类和接口。
            这些类和接口提供了高度灵活性和控制力，能够帮助开发人员编写高效且可维护的多线程应用程序

         */
        /*
            Java 并发集合类：
            ConcurrentHashMap
            ConcurrentHashMap是一种高效的并发哈希表，用于存储键值对。它允许多个线程同时读取而不需要锁定整个表，从而提高了读取操作的性能。
         */
        // 创建一个ConcurrentHashMap实例
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        // 向map中添加初始值
        map.put("apple", 10);
        map.put("banana", 20);
        map.put("orange", 30);
        // 创建多个线程来并发地访问和修改map
        Thread thread1 = new Thread(() -> {
            // 获取并打印初始值
            System.out.println(Thread.currentThread().getName() + " - Apple: " + map.get("apple"));
            // 更新apple的值
            map.put("apple", map.get("apple") + 5);
            System.out.println(Thread.currentThread().getName() + " - Updated Apple: " + map.get("apple"));
        });
        Thread thread2 = new Thread(() -> {
            // 获取并打印初始值
            System.out.println(Thread.currentThread().getName() + " - Banana: " + map.get("banana"));
            // 更新banana的值
            map.put("banana", map.get("banana") + 10);
            System.out.println(Thread.currentThread().getName() + " - Updated Banana: " + map.get("banana"));
        });
        Thread thread3 = new Thread(() -> {
            // 获取并打印初始值
            System.out.println(Thread.currentThread().getName() + " - Orange: " + map.get("orange"));
            // 更新orange的值
            map.put("orange", map.get("orange") + 15);
            System.out.println(Thread.currentThread().getName() + " - Updated Orange: " + map.get("orange"));
        });
        // 启动线程
        thread1.start();
        thread2.start();
        thread3.start();
        // 等待所有线程执行完毕
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        // 打印最终的map内容
        System.out.println("Final map: " + map);
        /*
            上面代码展示了如何使用 ConcurrentHashMap 来在多线程环境中安全地存储和修改数据。它的高效性和线程安全性使其成为处理并发任务时的首选数据结构之一。
         */

        System.out.println("=========================");  // 分割线

        /*
            ConcurrentLinkedQueue
            ConcurrentLinkedQueue是一个线程安全的队列实现，用于支持高并发的队列操作。它使用无锁算法来实现高效的并发性能。
         */
        // 创建一个ConcurrentLinkedQueue实例
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        // 添加元素到队列中
        queue.add("Element 1");
        queue.add("Element 2");
        queue.add("Element 3");
        // 创建并启动多个线程来访问队列
        Thread producerThread = new Thread(() -> {
            // 生产者线程向队列中添加元素
            for (int i = 4; i <= 6; i++) {
                queue.add("Element " + i);
                System.out.println(Thread.currentThread().getName() + " - Added: Element " + i);
                try {
                    // 模拟一些延迟
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        Thread consumerThread1 = new Thread(() -> {
            // 消费者线程从队列中取出元素
            while (true) {
                String element = queue.poll();
                if (element != null) {
                    System.out.println(Thread.currentThread().getName() + " - Polled: " + element);
                } else {
                    break; // 如果队列为空，退出循环
                }
                try {
                    // 模拟一些处理时间
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        Thread consumerThread2 = new Thread(() -> {
            // 第二个消费者线程从队列中取出元素
            while (true) {
                String element = queue.poll();
                if (element != null) {
                    System.out.println(Thread.currentThread().getName() + " - Polled: " + element);
                } else {
                    break; // 如果队列为空，退出循环
                }
                try {
                    // 模拟一些处理时间
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        // 启动线程
        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();
        // 等待所有线程完成
        try {
            producerThread.join();
            consumerThread1.join();
            consumerThread2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        // 最终队列状态检查
        System.out.println("Final queue size: " + queue.size());
        /*
            ConcurrentLinkedQueue 的 add() 方法用于向队列中添加元素，而 poll() 方法则用于安全地从队列中移除并返回队列头部的元素。
	        由于 ConcurrentLinkedQueue 是线程安全的，因此不需要显式同步，即使在多线程环境下也能正常工作。
	        这种无界非阻塞队列非常适合在高并发的场景中使用，如任务队列、消息队列等。
         */

        System.out.println("============================="); // 分界线

        /*
            CopyOnWriteArrayList
            CopyOnWriteArrayList是一个线程安全的列表实现，它在写入操作时创建一个新的副本，而不是直接修改原始列表，从而避免了写入冲突。
         */
        // 创建一个CopyOnWriteArrayList实例
        List<String> list = new CopyOnWriteArrayList<>();
        // 添加元素到列表中
        list.add("Element 1");
        list.add("Element 2");
        list.add("Element 3");
        // 创建并启动多个线程来访问和修改列表
        Thread writerThread = new Thread(() -> {
            // 写线程向列表中添加元素
            for (int i = 4; i <= 6; i++) {
                list.add("Element " + i);
                System.out.println(Thread.currentThread().getName() + " - Added: Element " + i);
                try {
                    // 模拟一些延迟
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        Thread readerThread1 = new Thread(() -> {
            // 读线程迭代列表中的元素
            for (String element : list) {
                System.out.println(Thread.currentThread().getName() + " - Read: " + element);
                try {
                    // 模拟一些处理时间
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        Thread readerThread2 = new Thread(() -> {
            // 第二个读线程迭代列表中的元素
            for (String element : list) {
                System.out.println(Thread.currentThread().getName() + " - Read: " + element);
                try {
                    // 模拟一些处理时间
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        // 启动线程
        writerThread.start();
        readerThread1.start();
        readerThread2.start();
        // 等待所有线程完成
        try {
            writerThread.join();
            readerThread1.join();
            readerThread2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        // 最终列表状态检查
        System.out.println("Final list size: " + list.size());
        System.out.println("Final list content: " + list);
        /*
            线程安全：由于 CopyOnWriteArrayList 的写时复制特性，读操作可以在不需要加锁的情况下安全进行。这意味着即使在多个线程同时访问的情况下，数据也不会不一致。
	        读多写少场景的适用性：CopyOnWriteArrayList 的写操作性能相对较低，因为每次写入都需要复制数组。然而，在读操作较多且写操作较少的场景中，它可以提供非常好的性能和安全性。
	        迭代时的线程安全性：在 CopyOnWriteArrayList 上进行的迭代操作使用的是迭代开始时的快照（snapshot），所以即使在迭代期间其他线程修改了列表，迭代操作仍然是安全的。

         */

        System.out.println("========================"); // 分界线

        /*
            Executor框架
            java.util.concurrent.Executor框架是一种用于管理和执行线程任务的机制。它将任务的提交与任务的执行解耦，使线程池的管理变得更加简单和灵活。
         */
        // 创建一个固定大小为3的线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // 提交多个任务给线程池执行
        for (int i = 1; i <= 5; i++) {
            // 创建WorkerThread类的实例对象，实例对象数据类型为Runnable
            Runnable worker = new WorkerThread("Task " + i);
            executor.execute(worker); // 任务提交给线程池
        }
        // 关闭线程池，不再接受新任务
        executor.shutdown();
        // 等待所有任务完成
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // 如果任务超时，强制关闭线程池
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("所有任务执行完毕!");

        System.out.println("========================"); // 分界线

        // 除了固定大小的线程池外，Executor 框架还提供了 ScheduledExecutorService 接口，可以用于定时任务的执行。
        // 创建一个ScheduledExecutorService线程池
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        // 安排一个任务在延迟2秒后执行
        scheduler.schedule(() -> {
            System.out.println("任务在延迟2秒后执行");
        }, 2, TimeUnit.SECONDS);
        // 安排一个任务每隔3秒执行一次
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("周期性任务，每隔3秒执行一次");
        }, 0, 3, TimeUnit.SECONDS);
        // 运行10秒后关闭调度器
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        scheduler.shutdown();
        System.out.println("调度器已关闭");
        /*
             Executor 框架简化了多线程编程，特别是在管理线程池和调度任务方面。
             Executor 框架提供了灵活的任务提交和执行机制，使得多线程编程变得更加容易和高效。
         */

        System.out.println("========================="); // 分界线

        /*
            线程池
            线程池是一组可用于执行任务的线程的集合。Java并发包提供了多种类型的线程池，包括FixedThreadPool、CachedThreadPool、ScheduledThreadPool等。
         */
        // 创建一个固定大小为3的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        // 提交5个任务给线程池执行
        for (int i = 1; i <= 5; i++) {
            Runnable task = new Task("Task " + i);
            threadPool.execute(task); // 将任务提交给线程池执行
        }
        // 关闭线程池，线程池不再接受新任务
        threadPool.shutdown();
        try {
            // 等待所有任务完成
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                // 如果等待超时，则强制关闭线程池
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            // 处理中断异常
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("所有任务执行完毕!");

        System.out.println("=========================");

        /*
            同步器
            同步器是一种用于控制多个线程之间同步的机制。java.util.concurrent包提供了多种同步器，如CountDownLatch、CyclicBarrier、Semaphore等
         */
        // 创建一个CountDownLatch，初始计数为3
        CountDownLatch latch = new CountDownLatch(3);
        // 创建并启动三个线程
        for (int i = 0; i < 3; i++) {
            new Thread(new Worker(latch)).start();
        }
        try {
            // 主线程等待，直到计数器减为零
            latch.await();
            System.out.println("所有工作线程已经完成，主线程继续执行。");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("============================");

        /*
            锁机制
            Java并发包提供了多种锁机制，用于控制多线程对共享资源的访问
         */
        // synchronized 关键字
        // synchronized 是 Java 提供的最简单的锁机制，应用于方法或代码块中，确保同一时刻只有一个线程可以执行被 synchronized 修饰的代码
        // 创建Counter2实例对象
        Counter2 counter2 = new Counter2();
        // 创建多个线程同时访问共享资源 counter2
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter2.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter2.increment();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        // 由于使用了synchronized锁机制，count的值是2000，而不是一个不确定的值
        System.out.println("Final count: " + counter2.getCount());

        System.out.println("===============================");

        // synchronized 修饰代码块
        // 除了修饰方法，synchronized 还可以修饰代码块，使得代码块中的操作在多线程环境下是原子的，且不会被其他线程打断。
        // 创建counter3实例对象
        Counter3 counter3 = new Counter3();
        // 创建多个线程同时访问共享资源 counter3
        Thread h1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++){
                counter3.increment();
            }
        });
        Thread h2 = new Thread(() -> {
           for (int i = 0; i < 1000; i++){
               counter3.increment();
           }
        });
        h1.start();
        h2.start();
        try {
            h1.join();
            h2.join();
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Final count: " + counter3.getCount());
        // 这里使用了 synchronized 修改代码块方式，count 的值是2000，而不是一个不确定的值

        System.out.println("=================================");

        // ReentrantLock 锁
        // ReentrantLock是一个可重入锁，允许线程在持有锁的情况下再次获取锁，而不会导致死锁。
        Counter4 counter4 = new Counter4();
        Thread g1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter4.increment();
            }
        });
        Thread g2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter4.increment();
            }
        });
        g1.start();
        g2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Final count: " + counter4.getCount());

        System.out.println("========================");

        // ReentrantReadWriteLock
        // ReentrantReadWriteLock 是一种读写锁，适用于读多写少的场景。
        Data data = new Data();
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                data.write(i);
            }
        });
        Thread reader1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                data.read();
            }
        });
        Thread reader2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                data.read();
            }
        });

        writer.start();
        reader1.start();
        reader2.start();
    }
}

/**
 * 定义一个Data类，用于测试ReentrantReadWriteLock
 */
class Data {
    private int value = 0;
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    public void write(int value) {
        rwLock.writeLock().lock(); // 获取写锁
        try {
            this.value = value;
            System.out.println(Thread.currentThread().getName() + " 写入值: " + value);
        } finally {
            rwLock.writeLock().unlock(); // 释放写锁
        }
    }

    public int read() {
        rwLock.readLock().lock(); // 获取读锁
        try {
            System.out.println(Thread.currentThread().getName() + " 读取值: " + value);
            return value;
        } finally {
            rwLock.readLock().unlock(); // 释放读锁
        }
    }
}

/**
 * 定义一个Counter4类，用来测试ReentrantLock锁
 */
class Counter4 {
    private int count = 0;
    private final Lock lock = new ReentrantLock(); // 创建锁对象
    public void increment() {
        lock.lock(); // 获取锁
        try {
            count++;
        } finally {
            lock.unlock(); // 确保锁的释放
        }
    }
    public int getCount() {
        return count;
    }
}

/**
 * 定义一个Counter3类，用于测试synchronized修饰代码块
 */
class Counter3 {
    private int count = 0;
    public void increment() {
        synchronized (this) { // 锁住当前实例对象
            count++;
        }
    }
    public int getCount() {
        return count;
    }
}

/**
 * 定义Counter2类，用于测试锁机制 synchronized 关键字
 */
class Counter2 {
    private int count = 0;
    // synchronized 修饰实例方法，锁住的是当前实例
    public synchronized void increment() {
        count++;
    }
    public int getCount() {
        return count;
    }
}

/**
 * 定义一个Worker类实现Runnable接口，用于测试同步器
 */
class Worker implements Runnable {
    private CountDownLatch latch;

    public Worker(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 正在执行任务...");
        try {
            Thread.sleep(2000); // 模拟任务执行
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + " 完成任务。");
        latch.countDown(); // 每完成一个任务，计数器减一
    }
}

/**
 * 定义一个Task类来实现Runnable接口，用于测试线程池
 */
class Task implements Runnable {
    private final String name;

    public Task(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 正在执行任务: " + name);
        try {
            // 模拟任务执行时间
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + " 完成任务: " + name);
    }
}

/**
 * 定义一个WorkerThread 类实现Runnable接口，这个类用于测试Executor
 */
class WorkerThread implements Runnable {
    private final String command;

    public WorkerThread(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 开始执行任务: " + command);
        try {
            // 模拟任务执行需要的时间
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + " 完成任务: " + command);
    }
}
