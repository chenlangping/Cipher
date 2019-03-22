package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Connect {

    static final int WIDTH = 600;// 宽度
    static final int HEIGHT = 800;// 高度
    static final int BUFSIZE = 64;
    // private static final int BUFSIZE = 64; //放到线程里去定义

    JFrame jf = null;
    int Port = -1;
    String send = "";
    String receive = "";
    Socket socket = null;

    public Connect() {
        jf = new JFrame("双机密码");
        jf.setSize(WIDTH, HEIGHT);
        jf.setResizable(false);// 不可调整大小
        jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                jf.dispose();
            }
        });// 设置关闭
        jf.setVisible(true);// 设置可见
        JPanel contentPane = new JPanel();// 放入frame中的中间容器，之后的panels均放在该容器下
        jf.setContentPane(contentPane);

        JLabel jl1 = new JLabel("端口号:");
        contentPane.add(jl1);

        JTextField tf1 = new JTextField(5);
        contentPane.add(tf1);

        JButton b1 = new JButton("建立连接");
        contentPane.add(b1);

        JTextArea ta1 = new JTextArea(10, 15);
        contentPane.add(ta1);

        b1.addActionListener(new ActionListener() { // 确定事件处理
            public void actionPerformed(ActionEvent Event) {
                // 点击建立连接
                ta1.append("等待连接........\n");
                int Port = 1025; // 服务器端口
                ServerSocket servSocket = null;
                int recvSize = 0;
                byte[] buf = new byte[BUFSIZE];

                try {
                    servSocket = new ServerSocket(Port); // 建立服务器套接字

                    Cthread connecthread = new Cthread();
                    connecthread.setPort(Port);
                    connecthread.setServerSocket(servSocket);
                    connecthread.setBuf(buf);
                    connecthread.setTextArea(ta1);
                    connecthread.start();

                } catch (IOException error) {
                    error.printStackTrace();
                }
            }

        });

        JLabel jl2 = new JLabel("目标服务器端口号:");
        contentPane.add(jl2);

        JTextField tf2 = new JTextField(5);
        contentPane.add(tf2);

        JLabel jl3 = new JLabel("目标服务器ip地址:");
        contentPane.add(jl3);

        JTextField tf3 = new JTextField(15);
        contentPane.add(tf3);

        JButton b2 = new JButton("建立连接");
        contentPane.add(b2);

        JButton b3 = new JButton("断开连接");
        contentPane.add(b3);

        b2.addActionListener(new ActionListener() { // 建立连接确定事件处理
            public void actionPerformed(ActionEvent Event) {
                String server = "127.0.0.1"; // 目标服务器地址
                String sendMessage = "hellow world!!"; // 发送消息内容
                // String server = args[0];
                // String sendMessage = args[1];
                System.out.println("Send Message: " + sendMessage);

                byte[] message = sendMessage.getBytes();
                int serverPort = Integer.parseInt("1025"); // 目标服务器端口

                System.out.println("Server port: " + serverPort);

                try {
                    socket = new Socket(server, serverPort); // 创建客户端套接字，连接服务器
                    /*
                     * System.out.println("Connect to server......");
                     * InputStream inputstream = socket.getInputStream();
                     * OutputStream outputstream =socket.getOutputStream();
                     * //获取服务器数据流
                     *
                     * outputstream.write(message); //写入消息
                     *
                     * int RcvBytes = 0; int rcvbyte;
                     *
                     * while(RcvBytes < message.length){ rcvbyte =
                     * inputstream.read(message, RcvBytes,message.length -
                     * RcvBytes); if(rcvbyte == -1){ throw new SocketException(
                     * "Connection closed prematurely"); } RcvBytes += rcvbyte;
                     * } //注意， 此时message仍为byte[], 需要转换成String
                     * System.out.println("Receive: "+ new String(message));
                     */

                } catch (Exception e) {
                    // 异常处理
                }

            }

        });

        b2.addActionListener(new ActionListener() { // 确定事件处理
            public void actionPerformed(ActionEvent Event) {

            }
        });

        JTextArea ta2 = new JTextArea(10, 15);
        contentPane.add(ta2);

        JButton b4 = new JButton("发送");
        contentPane.add(b4);

        b4.addActionListener(new ActionListener() { // 确定事件处理
            public void actionPerformed(ActionEvent Event) {
                // 点击发送
                send = ta2.getText();
                System.out.println(send);
                byte[] message = send.getBytes();
                try {

                    InputStream inputstream = socket.getInputStream();
                    OutputStream outputstream = socket.getOutputStream(); // 获取服务器数据流
                    outputstream.write(message); // 写入消息
                    /*
                     * int RcvBytes = 0; int rcvbyte; while(RcvBytes <
                     * message.length){ rcvbyte = inputstream.read(message,
                     * RcvBytes,message.length - RcvBytes); if(rcvbyte == -1){
                     * throw new SocketException("Connection closed prematurely"
                     * ); } RcvBytes += rcvbyte; }
                     */
                    // 注意， 此时message仍为byte[], 需要转换成String
                } catch (Exception e) {
                    // 异常处理
                }

            }
        });

    }
}