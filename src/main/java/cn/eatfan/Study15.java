package cn.eatfan;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

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

        System.out.println(" ");  // 分割线

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

        System.out.println(" "); // 分界线

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
    }
}
