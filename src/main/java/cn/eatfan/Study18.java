package cn.eatfan;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;

/**
 * Java NIO与NIO.2
 */
public class Study18 {
    public static void main(String[] args){
        /*
            Java NIO 基本概念：
	            •	通道 (Channel): 类似于传统的流 (Stream)，但它们是双向的，可以同时进行读写操作。
	            •	缓冲区 (Buffer): 用于存储数据的容器。所有数据都要通过缓冲区进行读写。
	            •	选择器 (Selector): 用于非阻塞 I/O 操作，允许一个线程管理多个通道。
         */
        // 读取文件
        try (FileInputStream fis = new FileInputStream("input.txt");
             // FileChannel 是 NIO 中用于文件读写的通道。
             // 通过 FileInputStream 或 FileOutputStream 获取通道实例。
             FileChannel readChannel = fis.getChannel()) {
            // ByteBuffer 是 NIO 中的核心类，用于在通道与文件之间传递数据。
            // allocate(int capacity) 方法分配一个指定容量的缓冲区。
            // 分配一个容量为1024字节的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // read(ByteBuffer dst) 方法将通道中的数据读入缓冲区。
            // 将数据从通道读入缓冲区
            int bytesRead = readChannel.read(buffer);
            // 打印读取到的字节数
            System.out.println("Bytes read: " + bytesRead);
            // 翻转缓冲区，从写模式切换到读模式
            buffer.flip();
            // 读取缓冲区中的数据
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        // 写入文件
        try (FileOutputStream fos = new FileOutputStream("output.txt");
             FileChannel writeChannel = fos.getChannel()) {
            // 创建要写入的数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // put(byte[] src) 方法将数据写入缓冲区，get() 方法从缓冲区读取数据。
            buffer.put("Hello, NIO!".getBytes());
            // 翻转缓冲区，从写模式切换到读模式，以便通道可以从中读取数据
            buffer.flip();
            // 将缓冲区的数据写入通道（即文件）
            writeChannel.write(buffer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=======================");

        /*
            Java NIO.2 基本概念
            NIO.2 是 Java 7 中引入的一组新的 I/O API，进一步扩展了 NIO 的功能，尤其是文件系统的操作。
            NIO.2 提供了更现代的文件系统操作，如遍历目录树、监控文件变化以及异步 I/O 操作。
         */
        // Paths.get(String first, String... more) 方法用于获取文件路径的实例。
        Path source = Paths.get("input.txt");
        Path destination = Paths.get("output.txt");
        // 使用 NIO.2 复制文件
        try {
            // Files.copy(Path source, Path target, CopyOption... options) 方法用于复制文件。
            // StandardCopyOption.REPLACE_EXISTING 选项用于在目标文件已存在时替换它。
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // 使用 NIO.2 遍历目录树
        try {
            // Files.walk(Path start, FileVisitOption... options) 方法用于递归遍历目录树，返回一个流 (Stream)
            // filter(Files::isRegularFile) 用于过滤掉目录，只保留文件。
            Files.walk(Paths.get("."))
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // 监控目录的变化
        // WatchService 是 NIO.2 提供的文件监控服务，用于监控目录中的变化。
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            Path dir = Paths.get(".");
            // dir.register(WatchService watcher, WatchEvent.Kind<?>... events) 用于注册需要监控的目录和事件类型。
            dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
            System.out.println("Watching directory for changes...");
            WatchKey key;
            // watchService.take() 方法会阻塞，直到发生一个注册的事件。
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
                }
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
