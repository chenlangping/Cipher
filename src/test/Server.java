package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static final int WIDTH = 600;// 宽度
    static final int HEIGHT = 800;// 高度
    static final int BUFSIZE = 1024;
    // private static final int BUFSIZE = 64; //放到线程里去定义

    BigInteger primeA = new BigInteger("23");
    String plaintext = "";// 保存及时加密用户输入的明文
    String ciphertext = "";// 保存及时加密用户输入的密文
    String key = "";// 保存及时加密用户输入的密钥

    JFrame jf = null;
    int Port = -1;
    String send = "";
    String receive = "";
    Socket socket = null;
    Cthread connecthread = null;
    int recvSize = 0;
    String p = "23";
    String g = "5";
    String randomnumber = "";
    String cell = "01101100";

    public Server() {
        jf = new JFrame("双机服务器");
        jf.setSize(WIDTH, HEIGHT);
        jf.setResizable(false);// 不可调整大小
        jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                jf.dispose();
            }
        });// 设置关闭

        JPanel contentPane = new JPanel();// 放入frame中的中间容器，之后的panels均放在该容器下
        contentPane.setLayout(null);
        jf.setContentPane(contentPane);

        JTextArea ta1 = new JTextArea(10, 15);
        //contentPane.add(ta1);
        ta1.setBackground(null);
        ta1.setLineWrap(true);
        ta1.setEditable(false);
        ta1.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        JScrollPane jScrollPane3 = new JScrollPane(ta1);
        jScrollPane3.setBounds(20, 20, 550, 300);
        contentPane.add(jScrollPane3);

        JLabel jl1 = new JLabel("端口号:");
        jl1.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        jl1.setBounds(60, 370, 90, 40);
        contentPane.add(jl1);

        JTextField tf1 = new JTextField("8000", 5);
        tf1.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        tf1.setBounds(160, 370, 100, 40);
        contentPane.add(tf1);

        JButton b1 = new JButton("建立连接");
        b1.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        b1.setBounds(400, 370, 120, 40);
        contentPane.add(b1);

        JLabel jl5 = new JLabel("加密算法:");
        jl5.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        jl5.setBounds(55, 480, 100, 40);
        contentPane.add(jl5);

        JLabel jl4 = new JLabel("密钥:");
        jl4.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        jl4.setBounds(70, 550, 50, 40);
        contentPane.add(jl4);

        JTextField tf4 = new JTextField(50);
        tf4.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        tf4.setBounds(160, 550, 310, 40);
        contentPane.add(tf4);

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("不解密");
        comboBox.addItem("Autokey");
        comboBox.addItem("Caesar");
        comboBox.addItem("CA");
        comboBox.addItem("ColumnPermutation");
        comboBox.addItem("DES");
        comboBox.addItem("Permutation");
        comboBox.addItem("Keyword");
        comboBox.addItem("Vigenere");
        comboBox.setBounds(160, 480, 100, 40);
        contentPane.add(comboBox);

        JButton b3 = new JButton("解密");
        b3.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        b3.setBounds(420, 470, 80, 40);
        contentPane.add(b3);

        b1.addActionListener(new ActionListener() { // 建立连接事件处理
            public void actionPerformed(ActionEvent Event) {
                // 点击建立连接
                ta1.append("等待连接........\n");
                int Port = Integer.parseInt(tf1.getText()); // 服务器端口
                ServerSocket servSocket = null;
                int recvSize = 0;
                byte[] buf = new byte[BUFSIZE];

                try {
                    servSocket = new ServerSocket(Port); // 建立服务器套接字
                    connecthread = new Cthread();
                    connecthread.setPort(Port);
                    connecthread.setServerSocket(servSocket);
                    connecthread.setBuf(buf);
                    connecthread.setTextArea(ta1);
                    connecthread.setRandomnumber(Integer.toString((int) (Math.random() * 100)));
                    connecthread.start();
                } catch (IOException error) {
                    error.printStackTrace();
                }
            }
        });

        b3.addActionListener(new ActionListener() { // 点击解密事件处理
            public void actionPerformed(ActionEvent Event) {
                receive = connecthread.getMessage();
                recvSize = connecthread.getRecvSize();
                receive = receive.substring(0, recvSize);
                ciphertext = receive;
                key = tf4.getText();
                //System.out.println("ciphertext= " + ciphertext + "  length = " + ciphertext.length());
                //System.out.println("key= " + key + "  length = " + key.length());
                if (comboBox.getSelectedItem().toString().equals("不解密")) {
                    ta1.append("收到的消息为:" + receive);
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("Autokey")) {
                    ta1.append("解密后为：");
                    ta1.append(new Decrypt().Autocipher(ciphertext, key));
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("Caesar")) {
                    ta1.append("解密后为：");
                    ta1.append(new Decrypt().Caesar(ciphertext, key));
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("CA")) {
                    ta1.append("解密后为：");
                    ta1.append(new Decrypt().CA(ciphertext, key, cell));
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("ColumnPermutation")) {
                    ta1.append("解密后为：");
                    ta1.append(new Decrypt().ColumnPermutationcipher(ciphertext, key));
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("DES")) {
                    ta1.append("解密后为：");
                    ta1.append(new Decrypt().DES(ciphertext, key));
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("Permutation")) {
                    ta1.append("解密后为：");
                    ta1.append(new Decrypt().Permutationcipher(ciphertext, key));
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("Playfair")) {
                    ta1.append("解密后为：");
                    char[] plaintext1 = new Playfairalgorithm().decode(ciphertext.toCharArray(),
                            key.toLowerCase().toCharArray());
                    ta1.append(new String(plaintext1));
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("Keyword")) {
                    ta1.append("解密后为：");
                    ta1.append(new Decrypt().Keyword(ciphertext, key));
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("Vigenere")) {
                    ta1.append("解密后为：");
                    ta1.append(new Decrypt().Vigenere(ciphertext, key));
                    ta1.append("\n");
                }
                // System.out.println(receive);
            }
        });


        jf.setVisible(true);// 设置可见
    }

}