package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

public class Client {

    static final int WIDTH = 600;// 宽度
    static final int HEIGHT = 800;// 高度
    static final int BUFSIZE = 1024;
    // private static final int BUFSIZE = 64; //放到线程里去定义

    String plaintext = "";// 保存及时加密用户输入的明文
    String ciphertext = "";// 保存及时加密用户输入的密文
    String key = "";// 保存及时加密用户输入的密钥

    JFrame jf = null;
    int Port = -1;
    String send = "";
    String receive = "";
    Socket socket = null;
    String server = null;
    byte[] message = null;
    byte[] temp = null;
    boolean cansendmessage = false;
    byte[] rannum = new byte[BUFSIZE];
    InputStream inputstream = null;
    OutputStream outputstream = null;
    int RcvBytes = 0;
    int rcvbyte;
    int randomnumber = 0;
    String cell = "01101100";
    JFileChooser fileChooser = null;

    public Client() {
        jf = new JFrame("双机客户端");
        jf.setSize(WIDTH, HEIGHT);
        jf.setResizable(false);// 不可调整大小
        jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                jf.dispose();
            }
        });// 设置关闭

        fileChooser = new JFileChooser("D:\\");

        JPanel contentPane = new JPanel();// 放入frame中的中间容器，之后的panels均放在该容器下
        contentPane.setLayout(null);
        jf.setContentPane(contentPane);

        JTextArea ta1 = new JTextArea(10, 15);
        ta1.setBackground(null);
        ta1.setLineWrap(true);
        ta1.setEditable(false);
        ta1.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        JScrollPane jScrollPane3 = new JScrollPane(ta1);
        jScrollPane3.setBounds(30, 20, 535, 250);
        contentPane.add(jScrollPane3);

        JLabel jl1 = new JLabel("端口号:");
        jl1.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        jl1.setBounds(390, 300, 70, 40);
        contentPane.add(jl1);

        JTextField tf1 = new JTextField("8000", 5);
        tf1.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        tf1.setBounds(470, 300, 100, 40);
        contentPane.add(tf1);

        JLabel jl2 = new JLabel("目标ip地址:");
        jl2.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        jl2.setBounds(20, 300, 160, 40);
        contentPane.add(jl2);

        JTextField tf2 = new JTextField("127.0.0.1", 50);
        tf2.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        tf2.setBounds(130, 300, 160, 40);
        contentPane.add(tf2);

        JButton b1 = new JButton("建立连接");
        b1.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        b1.setBounds(250, 370, 130, 45);
        contentPane.add(b1);

        JButton b2 = new JButton("断开连接");
        b2.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        b2.setBounds(410, 370, 130, 45);
        contentPane.add(b2);

        JLabel jl3 = new JLabel("消息:");
        jl3.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        jl3.setBounds(20, 550, 50, 40);
        contentPane.add(jl3);

        JTextField tf3 = new JTextField(50);
        tf3.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        tf3.setBounds(70, 550, 310, 40);
        contentPane.add(tf3);

        JLabel jl4 = new JLabel("密钥:");
        jl4.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        jl4.setBounds(20, 610, 50, 40);
        contentPane.add(jl4);

        JTextField tf4 = new JTextField(50);
        tf4.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        tf4.setBounds(70, 610, 310, 40);
        contentPane.add(tf4);

        JButton b3 = new JButton("发送");
        b3.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        b3.setBounds(300, 680, 80, 45);
        contentPane.add(b3);

        JButton b4 = new JButton("返回");
        b4.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        b4.setBounds(410, 680, 80, 45);
        contentPane.add(b4);

        JButton b5 = new JButton("明文文件");
        b5.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        b5.setBounds(410, 555, 125, 30);
        contentPane.add(b5);

        JButton b6 = new JButton("密钥文件");
        b6.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        b6.setBounds(410, 615, 125, 30);
        contentPane.add(b6);

        JLabel jl5 = new JLabel("加密算法:");
        jl5.setFont(new Font("TimesRoman", Font.BOLD, 20));// 设置字体风格、大小等
        jl5.setBounds(170, 460, 100, 35);
        contentPane.add(jl5);

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("不加密");
        comboBox.addItem("Autokey");
        comboBox.addItem("Caesar");
        comboBox.addItem("CA");
        comboBox.addItem("ColumnPermutation");
        comboBox.addItem("DES");
        comboBox.addItem("Permutation");
        comboBox.addItem("Keyword");
        comboBox.addItem("Vigenere");
        comboBox.setBounds(300, 460, 100, 30);
        contentPane.add(comboBox);

        b1.addActionListener(new ActionListener() { // 建立连接确定事件处理
            public void actionPerformed(ActionEvent Event) {
                server = tf2.getText();// 目标服务器地址
                int serverPort = Integer.parseInt(tf1.getText()); // 目标服务器端口
                if (socket == null) {
                    try {
                        socket = new Socket(server, serverPort); // 创建客户端套接字，连接服务器
                        inputstream = socket.getInputStream();
                        outputstream = socket.getOutputStream();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(jf, "连接发生错误");
                    }
                } else
                    JOptionPane.showMessageDialog(jf, "连接发生错误");

                try {
                    inputstream.read(rannum);
                    CDH client = new CDH("23", "5", new String(rannum), Integer.toString((int) (Math.random() * 100)));
                    outputstream.write(client.B.toString().getBytes());
                    ta1.append("连接成功！\n" + "通过DH协商得到的密钥是" + client.K.toString() + "\n");
                    ta1.selectAll();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        b2.addActionListener(new ActionListener() { // 断开连接事件处理
            public void actionPerformed(ActionEvent Event) {
                if (socket == null) {
                    JOptionPane.showMessageDialog(jf, "未建立连接");
                } else
                    try {
                        socket.close();
                        socket = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        });

        b3.addActionListener(new ActionListener() { // 发送事件处理
            public void actionPerformed(ActionEvent Event) {
                send = tf3.getText();
                if (comboBox.getSelectedItem().toString().equals("不加密")) {
                    message = send.getBytes();
                    cansendmessage = true;
                } else if (comboBox.getSelectedItem().toString().equals("Autokey")) {
                    plaintext = tf3.getText();
                    key = tf4.getText();
                    if (Algorithm.isOnlyletter(plaintext) && Algorithm.isOnlyletter(key)) {
                        send = new Encrypt().Autocipher(plaintext, key);
                        message = send.getBytes();
                        cansendmessage = true;
                    } else if (!Algorithm.isOnlyletter(plaintext) && Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "明文输入有误!");
                        cansendmessage = false;
                    } else if (Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "密钥输入有误!");
                        cansendmessage = false;
                    } else if (!Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "明文和密钥输入有误!");
                        cansendmessage = false;
                    }
                } else if (comboBox.getSelectedItem().toString().equals("Caesar")) {
                    plaintext = tf3.getText();
                    key = tf4.getText();
                    if (Algorithm.isOnlyletter(plaintext) && Algorithm.isOnlyletter(key)) {
                        send = new Encrypt().Caesar(plaintext, key);
                        message = send.getBytes();
                        cansendmessage = true;
                    } else if (!Algorithm.isOnlyletter(plaintext) && Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "明文输入有误!");
                        cansendmessage = false;
                    } else if (Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "密钥输入有误!");
                        cansendmessage = false;
                    } else if (!Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "明文和密钥输入有误!");
                        cansendmessage = false;
                    }
                } else if (comboBox.getSelectedItem().toString().equals("CA")) {
                    plaintext = tf3.getText();
                    key = tf4.getText();
                    if (Algorithm.isOnlyzeroandone(plaintext) && 0 < Integer.parseInt(key) && 255 > Integer.parseInt(key) && Algorithm.isOnlynumber(key)) {
                        send = new Encrypt().CA(plaintext, key, cell);
                        message = send.getBytes();
                        cansendmessage = true;
                    } else {
                        JOptionPane.showMessageDialog(jf, "明文或密钥输入有误!");
                        cansendmessage = false;
                    }
                } else if (comboBox.getSelectedItem().toString().equals("ColumnPermutation")) {
                    plaintext = tf3.getText();
                    key = tf4.getText();
                    if (Algorithm.isOnlyletter(plaintext) && Algorithm.isOnlyletter(key) && key.length() <= plaintext.length()) {
                        send = new Encrypt().ColumnPermutationcipher(plaintext, key);
                        message = send.getBytes();
                        cansendmessage = true;
                    } else if (!Algorithm.isOnlyletter(plaintext) && Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "明文输入有误!");
                        cansendmessage = false;
                    } else if (Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "密钥输入有误!");
                        cansendmessage = false;
                    } else if (!Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "明文和密钥输入有误!");
                        cansendmessage = false;
                    } else {
                        JOptionPane.showMessageDialog(jf, "密钥长度不能大于明文!");
                        cansendmessage = false;
                    }
                } else if (comboBox.getSelectedItem().toString().equals("DES")) {
                    plaintext = tf3.getText();
                    key = tf4.getText();
                    if (plaintext.length() > 0 && key.length() == 7) {
                        send = new Encrypt().DES(plaintext, key);
                        message = send.getBytes();
                        cansendmessage = true;
                    } else if (plaintext.length() == 0) {
                        JOptionPane.showMessageDialog(jf, "未检测到明文!");
                        cansendmessage = false;
                    } else if (key.length() != 7) {
                        JOptionPane.showMessageDialog(jf, "密钥长度不符合要求!");
                        cansendmessage = false;
                    }
                } else if (comboBox.getSelectedItem().toString().equals("Permutation")) {
                    plaintext = tf3.getText();
                    key = tf4.getText();
                    if (Algorithm.isOnlyletter(plaintext) && Algorithm.isOnlyletter(key) && key.length() <= plaintext.length()) {
                        send = new Encrypt().Permutationcipher(plaintext, key);
                        message = send.getBytes();
                        cansendmessage = true;
                    } else if (!Algorithm.isOnlyletter(plaintext) && Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "明文输入有误!");
                        cansendmessage = false;
                    } else if (Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "密钥输入有误!");
                        cansendmessage = false;
                    } else if (!Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "明文和密钥输入有误!");
                        cansendmessage = false;
                    } else {
                        JOptionPane.showMessageDialog(jf, "密钥长度不能大于明文!");
                        cansendmessage = false;
                    }
                } else if (comboBox.getSelectedItem().toString().equals("Keyword")) {
                    plaintext = tf3.getText();
                    key = tf4.getText();
                    if (Algorithm.isOnlyletter(plaintext) && Algorithm.isOnlyletter(key)) {
                        send = new Encrypt().Keyword(plaintext, key);
                        message = send.getBytes();
                        cansendmessage = true;
                    } else if (!Algorithm.isOnlyletter(plaintext) && Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "明文输入有误!");
                        cansendmessage = false;
                    } else if (Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "密钥输入有误!");
                        cansendmessage = false;
                    } else if (!Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "明文和密钥输入有误!");
                        cansendmessage = false;
                    }
                } else if (comboBox.getSelectedItem().toString().equals("Vigenere")) {
                    plaintext = tf3.getText();
                    key = tf4.getText();
                    if (Algorithm.isOnlyletter(plaintext) && Algorithm.isOnlyletter(key)) {
                        send = new Encrypt().Vigenere(plaintext, key);
                        message = send.getBytes();
                        cansendmessage = true;
                    } else if (!Algorithm.isOnlyletter(plaintext) && Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "明文输入有误!");
                        cansendmessage = false;
                    } else if (Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "密钥输入有误!");
                        cansendmessage = false;
                    } else if (!Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "明文和密钥输入有误!");
                        cansendmessage = false;
                    }
                }
                if (cansendmessage) {

                    try {

                        // 获取服务器数据流
                        outputstream.write(message); // 写入消息
                        ta1.append("消息：“");
                        ta1.append(new String(message));
                        ta1.append("”已成功发送!\n");
                        cansendmessage = false;

                    } catch (Exception e) {
                        // 异常处理
                    }
                }

            }
        });


        b4.addActionListener(new ActionListener() { // 返回事件处理
            public void actionPerformed(ActionEvent Event) {
                jf.dispose();
            }
        });


        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = null;
                int result = -1;
                if (e.getActionCommand().equals("明文文件")) {
                    fileChooser.setApproveButtonText("确定");
                    fileChooser.setDialogTitle("打开文件");
                    result = fileChooser.showOpenDialog(jf);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        file = fileChooser.getSelectedFile();
                        try {
                            FileInputStream in = new FileInputStream(file);
                            byte[] byt = new byte[1024];
                            int len = in.read(byt);
                            plaintext = new String(byt, 0, len);
                            tf3.setText(plaintext);
                        } catch (Exception e2) {
                            // 异常处理
                        }
                    } else if (result == JFileChooser.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(jf, "未选择");
                    }
                }
            }
        });

        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = null;
                int result = -1;
                if (e.getActionCommand().equals("密钥文件")) {
                    fileChooser.setApproveButtonText("确定");
                    fileChooser.setDialogTitle("打开文件");
                    result = fileChooser.showOpenDialog(jf);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        file = fileChooser.getSelectedFile();
                        try {
                            FileInputStream in = new FileInputStream(file);
                            byte[] byt = new byte[1024];
                            int len = in.read(byt);
                            key = new String(byt, 0, len);
                            tf4.setText(key);
                        } catch (Exception e2) {
                            // 异常处理
                        }
                    } else if (result == JFileChooser.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(jf, "未选择");
                    }
                }
            }
        });

        jf.setVisible(true);// 设置可见
    }

}
