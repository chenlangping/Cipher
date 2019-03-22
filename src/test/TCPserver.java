package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class TCPserver {

    private static final int BUFSIZE = 64;

    public static void main(String[] args) {
        int Port = 1025; // 服务器端口号
        ServerSocket servSocket = null;
        int recvSize = 0;
        byte[] buf = new byte[BUFSIZE];
        try {
            servSocket = new ServerSocket(Port); // 建立服务器套接字
            while (true) {
                Socket client = servSocket.accept();
                SocketAddress clientAddress = client.getRemoteSocketAddress();
                System.out.println("Accept a client " + clientAddress);
                InputStream inputstream = client.getInputStream();
                OutputStream outputstream = client.getOutputStream(); // 获取客户端数据流
                while ((recvSize = inputstream.read(buf)) != -1) {
                    String recvMessage = buf.toString();
                    System.out.println("Message: " + new String(buf));

                    outputstream.write(buf); // 服务器把原消息发回
                    System.out.println(buf.toString());
                    System.out.println(buf);
                }
                client.close(); // 关闭套接字
                System.out.println("结束");
            }
        } catch (IOException error) {
            error.printStackTrace();//
        }
    }

}
