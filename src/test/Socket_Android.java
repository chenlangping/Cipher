package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Socket_Android extends JDialog {

    static final int WIDTH = 600;
    static final int HEIGHT = 800;
    static String mString = null;
    private static BufferedReader in = null;
    private static PrintWriter out = null;
    private static String msg = null;
    int i = 0;// 接受到的密文放入字符串数组，i即为下标
    int n = 0;// 因为接收到的字符串可能不能立即解密，下标n代表当前准备解密的字符串
    /* 这里使用的是字符串数组来存入，当传入很多字符串后，即使解密完后，仍然占用空间,且存在上限问题 */
    /* 可以通过模拟循环，设i的上限为x，当i = x时，先判断n>0？如果是，则i循环，从0开始 */
    /* 如果不是，则通知手机端等待。这里还需要一个flag标志，当i循环后，置1，当flag = 1时 */
    /* i就不能大于n,当n = x后，flag置0。 这样来节省空间 */
    /* 目前没时间实现 */
    JFrame jf = null;
    private String[] ciphertext;
    private String flag;// 输出时，标注第几条消息（i转换为字符串的结果）
    private Encrypt encrypt;
    private Decrypt decrypt;
    private Playfairalgorithm playfairalgorithm;
    private ServerSocket server;
    private Socket socket = null;
    private String cell = "01011011";
    private String t;
    private String f;
    private String randomnum;
    private Random random;
    private CDH Client;

    public Socket_Android() {
        jf = new JFrame("Socket_Android");
        jf.setSize(WIDTH, HEIGHT);
        jf.setResizable(false);
        jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {

                jf.dispose();
            }
        });
        t = "23";
        f = "5";
        encrypt = new Encrypt();
        decrypt = new Decrypt();
        playfairalgorithm = new Playfairalgorithm();
        ciphertext = new String[100];
        JPanel contentPane = new JPanel();// 放入frame中的中间容器，之后的panels均放在该容器下
        jf.setContentPane(contentPane);
        contentPane.setLayout(null);// 尝试绝对布局

        JLabel recvlabel = new JLabel("接收消息：");
        recvlabel.setFont(new Font("TimesRoman", Font.PLAIN, 17));// 设置字体风格、大小等
        recvlabel.setBounds(30, 30, 100, 25);
        contentPane.add(recvlabel);

        JLabel portlabel = new JLabel("Port:");
        portlabel.setFont(new Font("TimesRoman", Font.PLAIN, 17));// 设置字体风格、大小等
        portlabel.setBounds(80, 400, 50, 30);
        contentPane.add(portlabel);

        JLabel msglabel = new JLabel("消息:");
        msglabel.setFont(new Font("TimesRoman", Font.PLAIN, 17));// 设置字体风格、大小等
        msglabel.setBounds(30, 530, 100, 30);
        contentPane.add(msglabel);

        JLabel keylabel = new JLabel("秘钥:");
        keylabel.setFont(new Font("TimesRoman", Font.PLAIN, 17));// 设置字体风格、大小等
        keylabel.setBounds(30, 600, 100, 30);
        contentPane.add(keylabel);

        JTextArea showtext = new JTextArea(); // 显示从android接受数据
        showtext.setBackground(null);
        showtext.setLineWrap(true);// 自动换行
        showtext.setEditable(false);
        showtext.setFont(new Font("TimesRoman", Font.PLAIN, 17));// 设置字体风格、大小等
        JScrollPane jScrollPane1 = new JScrollPane(showtext);
        jScrollPane1.setBounds(30, 60, 535, 300);
        contentPane.add(jScrollPane1);

        JTextField porttext = new JTextField();// 输入端口号
        porttext.setFont(new Font("TimesRoman", Font.PLAIN, 17));// 设置字体风格、大小等
        porttext.setBounds(130, 400, 100, 30);
        porttext.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

                } else {
                    e.consume();
                }
            }
        });
        contentPane.add(porttext);

        JButton smbbtn = new JButton("发送");
        smbbtn.setFont(new Font("TimesRoman", Font.PLAIN, 17));// 设置字体风格、大小等
        smbbtn.setBounds(100, 650, 80, 30);
        contentPane.add(smbbtn);

        JButton decipherbtn = new JButton("解密");
        decipherbtn.setFont(new Font("TimesRoman", Font.PLAIN, 17));// 设置字体风格、大小等
        decipherbtn.setBounds(240, 650, 100, 30);
        contentPane.add(decipherbtn);

        JButton backbtn = new JButton("返回");
        backbtn.setFont(new Font("TimesRoman", Font.PLAIN, 17));// 设置字体风格、大小等
        backbtn.setBounds(400, 650, 80, 30);
        contentPane.add(backbtn);

        JButton getkey = new JButton("获得秘钥");
        getkey.setFont(new Font("TimesRoman", Font.PLAIN, 17));// 设置字体风格、大小等
        getkey.setBounds(210, 700, 160, 30);
        getkey.setVisible(false);
        contentPane.add(getkey);

        JTextField msginput = new JTextField();// 输入消息
        JTextField keyinput = new JTextField();// 输入秘钥

        JComboBox cipherbox = new JComboBox();
        cipherbox.addItem("Autocipher");
        cipherbox.addItem("CA");
        cipherbox.addItem("Caesar");
        cipherbox.addItem("ColumnPermutation");
        cipherbox.addItem("DES");
        cipherbox.addItem("DH");
        cipherbox.addItem("Keyword");
        cipherbox.addItem("Permutation");
        cipherbox.addItem("Playfair");
        cipherbox.addItem("Vigenere");
        cipherbox.setBounds(200, 450, 200, 25);
        contentPane.add(cipherbox);
        cipherbox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                msginput.setText("");
                keyinput.setText("");
                if (e.getItem().equals("DH")) {
                    msglabel.setText("随机数:");
                    decipherbtn.setText("随机数");
                    getkey.setVisible(true);
                    msginput.setEditable(false);
                    keyinput.setEditable(false);
                } else {
                    msglabel.setText("消息:");
                    decipherbtn.setText("解密");
                    getkey.setVisible(false);
                    msginput.setEditable(true);
                    keyinput.setEditable(true);
                }
                showtext.setText("");
            }
        });

        msginput.setFont(new Font("TimesRoman", Font.PLAIN, 17));// 设置字体风格、大小等
        msginput.setBounds(90, 530, 480, 30);
        msginput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!cipherbox.getSelectedItem().toString().equals("CA")) {
                    if (!(Character.isLowerCase(c) || Character.isUpperCase(c)))
                        e.consume();
                } else {
                    if (c - '0' < 0 || c - '1' > 0)
                        e.consume();
                }
            }
        });
        contentPane.add(msginput);

        keyinput.setFont(new Font("TimesRoman", Font.PLAIN, 17));// 设置字体风格、大小等
        keyinput.setBounds(90, 600, 480, 30);
        keyinput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!cipherbox.getSelectedItem().toString().equals("CA")) {
                    if (!(Character.isLowerCase(c) || Character.isUpperCase(c)))
                        e.consume();
                } else {
                    if (c - '0' < 0 || c - '9' > 0)
                        e.consume();
                }
            }

        });
        contentPane.add(keyinput);

        JButton connectbtn = new JButton("创建");
        connectbtn.setFont(new Font("TimesRoman", Font.PLAIN, 17));// 设置字体风格、大小等
        connectbtn.setBounds(400, 400, 100, 25);
        contentPane.add(connectbtn);

        connectbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (porttext.getText().equals("")) {
                    JOptionPane.showMessageDialog(contentPane, "请输入端口号!");
                } else {
                    try {
                        server = new ServerSocket(Integer.valueOf(porttext.getText()));
                        System.out.println("服务器创建出成功！等待连接..." + porttext.getText());
                        // 在这里弹出等待连接的提示框
                        JOptionPane jOptionPane = new JOptionPane();
                        JOptionPane.showMessageDialog(contentPane, "等待连接中...");
                        socket = server.accept();
                        while (jOptionPane.isShowing()) {

                        }
                        JOptionPane.showMessageDialog(contentPane, "连接成功!");
                        System.out.println("连接成功!");
                        Thread thread = new Thread(new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    out = new PrintWriter(
                                            new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                                    while (true) {
                                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                        mString = in.readLine();
                                        if (mString != null) {
                                            System.out.println(mString);
                                            if (cipherbox.getSelectedItem().equals("DH")) {
                                                showtext.setText(mString);
                                                mString = null;
                                                continue;
                                            } else {
                                                ciphertext[i] = mString;
                                                i++;
                                                flag = String.valueOf(i);
                                                showtext.append("密文" + flag + ": " + mString + "\n");
                                                mString = null;
                                                continue;
                                            }
                                        }
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread.start();
                    } catch (IOException e1) {
                        System.out.println("创建服务器错误!");
                        e1.printStackTrace();
                    }
                }
            }
        });

        smbbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (msginput.getText().equals("")) {
                    if (cipherbox.getSelectedItem().equals("DH")) {
                        JOptionPane.showMessageDialog(contentPane, "请先生成随机数!");
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "请输入明文!");
                    }
                } else if (socket != null && socket.isConnected() && !socket.isClosed()) {
                    msg = msginput.getText();
                    /* 这里要加密，根据combox选择来选择加密算法 */
                    switch (cipherbox.getSelectedIndex()) {
                        case 0:
                            msg = encrypt.Autocipher(msg, keyinput.getText());
                            break;
                        case 1:
                            if (msg.length() < 8) {
                                JOptionPane.showMessageDialog(contentPane, "消息长度不能小于8!");
                            } else {
                                msg = encrypt.CA(msg, keyinput.getText(), cell);
                            }
                            break;
                        case 2:
                            msg = encrypt.Caesar(msg, keyinput.getText());
                            break;
                        case 3:
                            msg = encrypt.ColumnPermutationcipher(msg, keyinput.getText());
                            break;
                        case 4:
                            msg = encrypt.DES(msg, keyinput.getText());
                            break;
                        case 5:
                            Client = new CDH(t, f, showtext.getText(), msginput.getText());
                            msg = Client.B.toString();// 发送回去
                            System.out.println("此时应该得到秘钥K：" + Client.K.toString());
                            System.out.println("并应该发送：" + Client.B.toString());
                            break;
                        case 6:
                            msg = encrypt.Keyword(msg, keyinput.getText());
                            break;
                        case 7:
                            msg = encrypt.Permutationcipher(msg, keyinput.getText());
                            break;

                        case 8:
                            char replace = 'x';
                            char[] msg1 = playfairalgorithm.encode(msg.replaceAll(" ", "").toCharArray(),
                                    keyinput.getText().replaceAll(" ", "").toCharArray(), replace);
                            msg = new String(msg1);
                            break;

                        case 9:
                            msg = encrypt.Vigenere(msg, keyinput.getText());
                            break;
                        default:
                            break;
                    }

                    out.println(msg);
                    System.out.println("点击发送" + msg);
                } else {
                    JOptionPane.showMessageDialog(contentPane, "请确定是否连接!");
                }

            }

        });

        decipherbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                /* 这里要解密，根据combox选择来选择加密算法 */
                // ciphertext[]中，第n个解密，并放回去。
                if (keyinput.getText().equals("") && !cipherbox.getSelectedItem().equals("DH")) {
                    JOptionPane.showMessageDialog(contentPane, "请输入秘钥!");
                } else {
                    if (n == i && !cipherbox.getSelectedItem().equals("DH")) {
                        JOptionPane.showMessageDialog(contentPane, "无消息，请等待...");
                    } else {
                        switch (cipherbox.getSelectedIndex()) {
                            case 0:
                                ciphertext[n] = decrypt.Autocipher(ciphertext[n], keyinput.getText());
                                break;
                            case 1:
                                ciphertext[n] = decrypt.CA(ciphertext[n], keyinput.getText(), cell);
                                break;
                            case 2:
                                ciphertext[n] = decrypt.Caesar(ciphertext[n], keyinput.getText());
                                break;
                            case 3:
                                ciphertext[n] = decrypt.ColumnPermutationcipher(ciphertext[n],
                                        keyinput.getText());
                                break;
                            case 4:
                                ciphertext[n] = decrypt.DES(ciphertext[n], keyinput.getText());
                                break;
                            case 5:
                                random = new Random();
                                randomnum = String.valueOf(random.nextInt(20) + 1);
                                msginput.setText(randomnum);
                                break;
                            case 6:
                                ciphertext[n] = decrypt.Keyword(ciphertext[n], keyinput.getText());
                                break;
                            case 7:
                                ciphertext[n] = decrypt.Permutationcipher(ciphertext[n], keyinput.getText());
                                break;

                            case 8:
                                char[] msg1 = playfairalgorithm.decode(ciphertext[n].toCharArray(),
                                        keyinput.getText().replaceAll(" ", "").toCharArray());
                                ciphertext[n] = new String(msg1);
                                break;

                            case 9:
                                ciphertext[n] = decrypt.Vigenere(ciphertext[n], keyinput.getText());
                                break;
                            default:
                                break;
                        }
                        if (!cipherbox.getSelectedItem().equals("DH")) {
                            n++;
                            flag = String.valueOf(n);
                            showtext.append("明文" + flag + ": " + ciphertext[n - 1] + "\n");
                        }
                    }
                }
            }
        });

        getkey.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (showtext.getText().equals("") || msginput.getText().equals("")) {
                    JOptionPane.showMessageDialog(contentPane, "请完成前面的步骤");
                } else {
                    keyinput.setText(Client.K.toString());// 最后得到的秘钥
                }
            }
        });
        backbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (socket != null) {
                        if (!socket.isClosed()) {
                            socket.close();
                            server.close();
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                jf.dispose();
            }
        });

        jf.setVisible(true);
    }
}
