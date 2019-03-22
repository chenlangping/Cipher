package test;

import javax.swing.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

//import java.net.SocketException;

public class Cthread extends Thread {
    static final int BUFSIZE = 1024;
    Socket client = null;
    String send = "";
    JTextArea ta = new JTextArea();
    String message = null;
    String recvMessage = null;
    int recvSize = 0;
    String randomnumber = "";
    boolean isFirsttime = true;
    private int Port = -1;
    private ServerSocket servSocket = null;
    private byte[] buf;

    public void setPort(int Port) {
        this.Port = Port;
    }

    public void setServerSocket(ServerSocket servSocket) {
        this.servSocket = servSocket;
    }

    public void setBuf(byte[] buf) {
        this.buf = buf;
    }

    public void setTextArea(JTextArea ta) {
        this.ta = ta;
    }

    public String getMessage() {
        return recvMessage;
    }

    public int getRecvSize() {
        return recvSize;
    }

    public void setRandomnumber(String randomnumber) {
        this.randomnumber = randomnumber;
    }


    public void run() {
        while (true) {
            try {
                recvSize = 0;
                client = servSocket.accept();
                // 连接建立完成
                ta.append("连接完成!");
                ta.append("\n");
                SocketAddress clientAddress = client.getRemoteSocketAddress();
                InputStream inputstream = client.getInputStream();
                OutputStream outputstream = client.getOutputStream(); // 获取客户端数据流


                SDH server = new SDH("23", "5", randomnumber);
                byte[] rannumbyt = server.A.toString().getBytes();
                System.out.println("g^a=" + randomnumber);
                outputstream.write(rannumbyt);
                System.out.println("已发送");

                //inputstream.read(buf);
                //server.setK(client.B.toString());


                while ((recvSize = inputstream.read(buf)) != -1) {
                    if (isFirsttime) {
                        recvMessage = new String(buf);
                        //buf = null;
                        //buf = new byte[BUFSIZE];
                        recvMessage = recvMessage.substring(0, recvSize);
                        server.setK(recvMessage);
                        ta.append("通过DH协商的密钥是" + server.K.toString() + "\n");
                        ta.selectAll();
                        outputstream.write(buf, 0, recvSize);        //服务器把原消息发回
                        recvMessage = null;
                        isFirsttime = false;
                        buf = null;
                        buf = new byte[BUFSIZE];
                    } else {
                        recvMessage = new String(buf);
                        //buf = null;
                        //buf = new byte[BUFSIZE];
                        recvMessage = recvMessage.substring(0, recvSize);
                        ta.append("获得" + clientAddress + "的消息:" + recvMessage + "\n");
                        ta.selectAll();
                        outputstream.write(buf, 0, recvSize);        //服务器把原消息发回
                    }
                }
                ta.append("与客户端" + clientAddress + "断开");
                ta.append("\n");

            } catch (Exception e) {
            }
        }
        // System.out.println("结束");
    }
}
