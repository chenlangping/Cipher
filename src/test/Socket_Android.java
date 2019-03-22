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
    int i = 0;// ���ܵ������ķ����ַ������飬i��Ϊ�±�
    int n = 0;// ��Ϊ���յ����ַ������ܲ����������ܣ��±�n����ǰ׼�����ܵ��ַ���
    /* ����ʹ�õ����ַ������������룬������ܶ��ַ����󣬼�ʹ���������Ȼռ�ÿռ�,�Ҵ����������� */
    /* ����ͨ��ģ��ѭ������i������Ϊx����i = xʱ�����ж�n>0������ǣ���iѭ������0��ʼ */
    /* ������ǣ���֪ͨ�ֻ��˵ȴ������ﻹ��Ҫһ��flag��־����iѭ������1����flag = 1ʱ */
    /* i�Ͳ��ܴ���n,��n = x��flag��0�� ��������ʡ�ռ� */
    /* Ŀǰûʱ��ʵ�� */
    JFrame jf = null;
    private String[] ciphertext;
    private String flag;// ���ʱ����ע�ڼ�����Ϣ��iת��Ϊ�ַ����Ľ����
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
        JPanel contentPane = new JPanel();// ����frame�е��м�������֮���panels�����ڸ�������
        jf.setContentPane(contentPane);
        contentPane.setLayout(null);// ���Ծ��Բ���

        JLabel recvlabel = new JLabel("������Ϣ��");
        recvlabel.setFont(new Font("TimesRoman", Font.PLAIN, 17));// ���������񡢴�С��
        recvlabel.setBounds(30, 30, 100, 25);
        contentPane.add(recvlabel);

        JLabel portlabel = new JLabel("Port:");
        portlabel.setFont(new Font("TimesRoman", Font.PLAIN, 17));// ���������񡢴�С��
        portlabel.setBounds(80, 400, 50, 30);
        contentPane.add(portlabel);

        JLabel msglabel = new JLabel("��Ϣ:");
        msglabel.setFont(new Font("TimesRoman", Font.PLAIN, 17));// ���������񡢴�С��
        msglabel.setBounds(30, 530, 100, 30);
        contentPane.add(msglabel);

        JLabel keylabel = new JLabel("��Կ:");
        keylabel.setFont(new Font("TimesRoman", Font.PLAIN, 17));// ���������񡢴�С��
        keylabel.setBounds(30, 600, 100, 30);
        contentPane.add(keylabel);

        JTextArea showtext = new JTextArea(); // ��ʾ��android��������
        showtext.setBackground(null);
        showtext.setLineWrap(true);// �Զ�����
        showtext.setEditable(false);
        showtext.setFont(new Font("TimesRoman", Font.PLAIN, 17));// ���������񡢴�С��
        JScrollPane jScrollPane1 = new JScrollPane(showtext);
        jScrollPane1.setBounds(30, 60, 535, 300);
        contentPane.add(jScrollPane1);

        JTextField porttext = new JTextField();// ����˿ں�
        porttext.setFont(new Font("TimesRoman", Font.PLAIN, 17));// ���������񡢴�С��
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

        JButton smbbtn = new JButton("����");
        smbbtn.setFont(new Font("TimesRoman", Font.PLAIN, 17));// ���������񡢴�С��
        smbbtn.setBounds(100, 650, 80, 30);
        contentPane.add(smbbtn);

        JButton decipherbtn = new JButton("����");
        decipherbtn.setFont(new Font("TimesRoman", Font.PLAIN, 17));// ���������񡢴�С��
        decipherbtn.setBounds(240, 650, 100, 30);
        contentPane.add(decipherbtn);

        JButton backbtn = new JButton("����");
        backbtn.setFont(new Font("TimesRoman", Font.PLAIN, 17));// ���������񡢴�С��
        backbtn.setBounds(400, 650, 80, 30);
        contentPane.add(backbtn);

        JButton getkey = new JButton("�����Կ");
        getkey.setFont(new Font("TimesRoman", Font.PLAIN, 17));// ���������񡢴�С��
        getkey.setBounds(210, 700, 160, 30);
        getkey.setVisible(false);
        contentPane.add(getkey);

        JTextField msginput = new JTextField();// ������Ϣ
        JTextField keyinput = new JTextField();// ������Կ

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
                    msglabel.setText("�����:");
                    decipherbtn.setText("�����");
                    getkey.setVisible(true);
                    msginput.setEditable(false);
                    keyinput.setEditable(false);
                } else {
                    msglabel.setText("��Ϣ:");
                    decipherbtn.setText("����");
                    getkey.setVisible(false);
                    msginput.setEditable(true);
                    keyinput.setEditable(true);
                }
                showtext.setText("");
            }
        });

        msginput.setFont(new Font("TimesRoman", Font.PLAIN, 17));// ���������񡢴�С��
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

        keyinput.setFont(new Font("TimesRoman", Font.PLAIN, 17));// ���������񡢴�С��
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

        JButton connectbtn = new JButton("����");
        connectbtn.setFont(new Font("TimesRoman", Font.PLAIN, 17));// ���������񡢴�С��
        connectbtn.setBounds(400, 400, 100, 25);
        contentPane.add(connectbtn);

        connectbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (porttext.getText().equals("")) {
                    JOptionPane.showMessageDialog(contentPane, "������˿ں�!");
                } else {
                    try {
                        server = new ServerSocket(Integer.valueOf(porttext.getText()));
                        System.out.println("�������������ɹ����ȴ�����..." + porttext.getText());
                        // �����ﵯ���ȴ����ӵ���ʾ��
                        JOptionPane jOptionPane = new JOptionPane();
                        JOptionPane.showMessageDialog(contentPane, "�ȴ�������...");
                        socket = server.accept();
                        while (jOptionPane.isShowing()) {

                        }
                        JOptionPane.showMessageDialog(contentPane, "���ӳɹ�!");
                        System.out.println("���ӳɹ�!");
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
                                                showtext.append("����" + flag + ": " + mString + "\n");
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
                        System.out.println("��������������!");
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
                        JOptionPane.showMessageDialog(contentPane, "�������������!");
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "����������!");
                    }
                } else if (socket != null && socket.isConnected() && !socket.isClosed()) {
                    msg = msginput.getText();
                    /* ����Ҫ���ܣ�����comboxѡ����ѡ������㷨 */
                    switch (cipherbox.getSelectedIndex()) {
                        case 0:
                            msg = encrypt.Autocipher(msg, keyinput.getText());
                            break;
                        case 1:
                            if (msg.length() < 8) {
                                JOptionPane.showMessageDialog(contentPane, "��Ϣ���Ȳ���С��8!");
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
                            msg = Client.B.toString();// ���ͻ�ȥ
                            System.out.println("��ʱӦ�õõ���ԿK��" + Client.K.toString());
                            System.out.println("��Ӧ�÷��ͣ�" + Client.B.toString());
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
                    System.out.println("�������" + msg);
                } else {
                    JOptionPane.showMessageDialog(contentPane, "��ȷ���Ƿ�����!");
                }

            }

        });

        decipherbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                /* ����Ҫ���ܣ�����comboxѡ����ѡ������㷨 */
                // ciphertext[]�У���n�����ܣ����Ż�ȥ��
                if (keyinput.getText().equals("") && !cipherbox.getSelectedItem().equals("DH")) {
                    JOptionPane.showMessageDialog(contentPane, "��������Կ!");
                } else {
                    if (n == i && !cipherbox.getSelectedItem().equals("DH")) {
                        JOptionPane.showMessageDialog(contentPane, "����Ϣ����ȴ�...");
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
                            showtext.append("����" + flag + ": " + ciphertext[n - 1] + "\n");
                        }
                    }
                }
            }
        });

        getkey.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (showtext.getText().equals("") || msginput.getText().equals("")) {
                    JOptionPane.showMessageDialog(contentPane, "�����ǰ��Ĳ���");
                } else {
                    keyinput.setText(Client.K.toString());// ���õ�����Կ
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
