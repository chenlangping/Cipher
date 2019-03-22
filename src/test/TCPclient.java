package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TCPclient {

    public static void main(String[] args) {

        String server = "127.0.0.1"; // 目标服务器地址
        String sendMessage = "hellow world!!"; // 发送消息内容
        System.out.println("Send Message: " + sendMessage);
        byte[] message = sendMessage.getBytes();
        int serverPort = Integer.parseInt("1025"); // 目标服务器端口
        System.out.println("Server port: " + serverPort);
        Socket socket = null;
        try {
            socket = new Socket(server, serverPort); // 创建客户端套接字，连接服务器
            System.out.println("Connect to server......");
            InputStream inputstream = socket.getInputStream();
            OutputStream outputstream = socket.getOutputStream(); // 获取服务器数据流
            outputstream.write(message); // 写入消息
            int RcvBytes = 0;
            int rcvbyte;
            while (RcvBytes < message.length) {
                rcvbyte = inputstream.read(message, RcvBytes, message.length - RcvBytes);
                if (rcvbyte == -1) {
                    throw new SocketException("Connection closed prematurely");
                }
                RcvBytes += rcvbyte;
            }
            // 注意， 此时message仍为byte[], 需要转换成String
            System.out.println("Receive: " + new String(message));
        } catch (UnknownHostException error) {
            error.printStackTrace();//
        } catch (IOException error) {
            error.printStackTrace();//
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                    System.out.println("结束");
                }
            } catch (IOException error) {
                error.printStackTrace();
            }

        }
    }

}