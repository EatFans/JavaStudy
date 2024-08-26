package cn.eatfan;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Java Socket编程
 */
public class Study19 {
    public static void main(String[] args){
        /*
            Java Socket 编程是用来实现网络通信的技术。
            通过 Socket，我们可以在网络上建立客户端和服务器之间的连接，并进行数据传输。
         */

        // 服务器端代码
        // 服务器端代码用于接收客户端的连接请求，并处理从客户端发送的数据。
        // 服务器端口号
        int port = 12345;
        // ServerSocket 是服务器端用来监听客户端连接请求的类
        // new ServerSocket(port) 创建一个服务器套接字，绑定到指定的端口。
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("服务器已启动，等待客户端连接...");
            // 监听客户端连接请求
            // serverSocket.accept() 方法用于监听客户端的连接请求，并返回一个 Socket 对象表示与客户端的连接。
            Socket clientSocket = serverSocket.accept();
            System.out.println("客户端已连接，客户端地址: " + clientSocket.getInetAddress());
            // 获取输入流，从客户端读取数据
            // clientSocket.getInputStream() 方法获取输入流，用于从客户端读取数据。
            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[1024];
            // 使用 inputStream.read(buffer) 从客户端读取数据到缓冲区。
            int bytesRead = inputStream.read(buffer);
            String receivedMessage = new String(buffer, 0, bytesRead);
            System.out.println("接收到来自客户端的消息: " + receivedMessage);
            // 获取输出流，向客户端发送数据
            // clientSocket.getOutputStream() 方法获取输出流，用于向客户端发送数据。
            OutputStream outputStream = clientSocket.getOutputStream();
            String responseMessage = "消息已收到";
            // 使用 outputStream.write(responseMessage.getBytes()) 向客户端发送响应数据
            outputStream.write(responseMessage.getBytes());
            // 关闭资源
            // 使用 close() 方法关闭流和套接字，释放资源。
            inputStream.close();
            outputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        // 客户端代码
        // 客户端代码用于连接到服务器，并发送数据到服务器端。
        // 服务器的地址和端口号
        String serverAddress = "localhost";
        int serverPort = 12345;
        // Socket 是客户端用来与服务器端建立连接的类。
        // new Socket(serverAddress, port) 创建一个客户端套接字，连接到指定的服务器地址和端口。
        try (Socket socket = new Socket(serverAddress, serverPort)) {
            // 获取输出流，向服务器发送数据
            // socket.getOutputStream() 获取输出流，用于向服务器发送数据。
            OutputStream outputStream = socket.getOutputStream();
            String message = "Hello, Server!";
            // 使用 outputStream.write(message.getBytes()) 向服务器发送数据。
            outputStream.write(message.getBytes());
            // 获取输入流，从服务器接收数据
            // socket.getInputStream() 获取输入流，用于从服务器接收数据。
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            // 使用 inputStream.read(buffer) 从服务器读取数据到缓冲区。
            int bytesRead = inputStream.read(buffer);
            String responseMessage = new String(buffer, 0, bytesRead);
            System.out.println("接收到来自服务器的消息: " + responseMessage);
            // 关闭资源
            // 使用 close() 方法关闭流和套接字，释放资源。
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
