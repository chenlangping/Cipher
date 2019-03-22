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

    static final int WIDTH = 600;// ���
    static final int HEIGHT = 800;// �߶�
    static final int BUFSIZE = 1024;
    // private static final int BUFSIZE = 64; //�ŵ��߳���ȥ����

    String plaintext = "";// ���漰ʱ�����û����������
    String ciphertext = "";// ���漰ʱ�����û����������
    String key = "";// ���漰ʱ�����û��������Կ

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
        jf = new JFrame("˫���ͻ���");
        jf.setSize(WIDTH, HEIGHT);
        jf.setResizable(false);// ���ɵ�����С
        jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                jf.dispose();
            }
        });// ���ùر�

        fileChooser = new JFileChooser("D:\\");

        JPanel contentPane = new JPanel();// ����frame�е��м�������֮���panels�����ڸ�������
        contentPane.setLayout(null);
        jf.setContentPane(contentPane);

        JTextArea ta1 = new JTextArea(10, 15);
        ta1.setBackground(null);
        ta1.setLineWrap(true);
        ta1.setEditable(false);
        ta1.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        JScrollPane jScrollPane3 = new JScrollPane(ta1);
        jScrollPane3.setBounds(30, 20, 535, 250);
        contentPane.add(jScrollPane3);

        JLabel jl1 = new JLabel("�˿ں�:");
        jl1.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        jl1.setBounds(390, 300, 70, 40);
        contentPane.add(jl1);

        JTextField tf1 = new JTextField("8000", 5);
        tf1.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        tf1.setBounds(470, 300, 100, 40);
        contentPane.add(tf1);

        JLabel jl2 = new JLabel("Ŀ��ip��ַ:");
        jl2.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        jl2.setBounds(20, 300, 160, 40);
        contentPane.add(jl2);

        JTextField tf2 = new JTextField("127.0.0.1", 50);
        tf2.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        tf2.setBounds(130, 300, 160, 40);
        contentPane.add(tf2);

        JButton b1 = new JButton("��������");
        b1.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        b1.setBounds(250, 370, 130, 45);
        contentPane.add(b1);

        JButton b2 = new JButton("�Ͽ�����");
        b2.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        b2.setBounds(410, 370, 130, 45);
        contentPane.add(b2);

        JLabel jl3 = new JLabel("��Ϣ:");
        jl3.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        jl3.setBounds(20, 550, 50, 40);
        contentPane.add(jl3);

        JTextField tf3 = new JTextField(50);
        tf3.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        tf3.setBounds(70, 550, 310, 40);
        contentPane.add(tf3);

        JLabel jl4 = new JLabel("��Կ:");
        jl4.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        jl4.setBounds(20, 610, 50, 40);
        contentPane.add(jl4);

        JTextField tf4 = new JTextField(50);
        tf4.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        tf4.setBounds(70, 610, 310, 40);
        contentPane.add(tf4);

        JButton b3 = new JButton("����");
        b3.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        b3.setBounds(300, 680, 80, 45);
        contentPane.add(b3);

        JButton b4 = new JButton("����");
        b4.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        b4.setBounds(410, 680, 80, 45);
        contentPane.add(b4);

        JButton b5 = new JButton("�����ļ�");
        b5.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        b5.setBounds(410, 555, 125, 30);
        contentPane.add(b5);

        JButton b6 = new JButton("��Կ�ļ�");
        b6.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        b6.setBounds(410, 615, 125, 30);
        contentPane.add(b6);

        JLabel jl5 = new JLabel("�����㷨:");
        jl5.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        jl5.setBounds(170, 460, 100, 35);
        contentPane.add(jl5);

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("������");
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

        b1.addActionListener(new ActionListener() { // ��������ȷ���¼�����
            public void actionPerformed(ActionEvent Event) {
                server = tf2.getText();// Ŀ���������ַ
                int serverPort = Integer.parseInt(tf1.getText()); // Ŀ��������˿�
                if (socket == null) {
                    try {
                        socket = new Socket(server, serverPort); // �����ͻ����׽��֣����ӷ�����
                        inputstream = socket.getInputStream();
                        outputstream = socket.getOutputStream();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(jf, "���ӷ�������");
                    }
                } else
                    JOptionPane.showMessageDialog(jf, "���ӷ�������");

                try {
                    inputstream.read(rannum);
                    CDH client = new CDH("23", "5", new String(rannum), Integer.toString((int) (Math.random() * 100)));
                    outputstream.write(client.B.toString().getBytes());
                    ta1.append("���ӳɹ���\n" + "ͨ��DHЭ�̵õ�����Կ��" + client.K.toString() + "\n");
                    ta1.selectAll();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        b2.addActionListener(new ActionListener() { // �Ͽ������¼�����
            public void actionPerformed(ActionEvent Event) {
                if (socket == null) {
                    JOptionPane.showMessageDialog(jf, "δ��������");
                } else
                    try {
                        socket.close();
                        socket = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        });

        b3.addActionListener(new ActionListener() { // �����¼�����
            public void actionPerformed(ActionEvent Event) {
                send = tf3.getText();
                if (comboBox.getSelectedItem().toString().equals("������")) {
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
                        JOptionPane.showMessageDialog(jf, "������������!");
                        cansendmessage = false;
                    } else if (Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "��Կ��������!");
                        cansendmessage = false;
                    } else if (!Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "���ĺ���Կ��������!");
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
                        JOptionPane.showMessageDialog(jf, "������������!");
                        cansendmessage = false;
                    } else if (Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "��Կ��������!");
                        cansendmessage = false;
                    } else if (!Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "���ĺ���Կ��������!");
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
                        JOptionPane.showMessageDialog(jf, "���Ļ���Կ��������!");
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
                        JOptionPane.showMessageDialog(jf, "������������!");
                        cansendmessage = false;
                    } else if (Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "��Կ��������!");
                        cansendmessage = false;
                    } else if (!Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "���ĺ���Կ��������!");
                        cansendmessage = false;
                    } else {
                        JOptionPane.showMessageDialog(jf, "��Կ���Ȳ��ܴ�������!");
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
                        JOptionPane.showMessageDialog(jf, "δ��⵽����!");
                        cansendmessage = false;
                    } else if (key.length() != 7) {
                        JOptionPane.showMessageDialog(jf, "��Կ���Ȳ�����Ҫ��!");
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
                        JOptionPane.showMessageDialog(jf, "������������!");
                        cansendmessage = false;
                    } else if (Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "��Կ��������!");
                        cansendmessage = false;
                    } else if (!Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "���ĺ���Կ��������!");
                        cansendmessage = false;
                    } else {
                        JOptionPane.showMessageDialog(jf, "��Կ���Ȳ��ܴ�������!");
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
                        JOptionPane.showMessageDialog(jf, "������������!");
                        cansendmessage = false;
                    } else if (Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "��Կ��������!");
                        cansendmessage = false;
                    } else if (!Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "���ĺ���Կ��������!");
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
                        JOptionPane.showMessageDialog(jf, "������������!");
                        cansendmessage = false;
                    } else if (Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "��Կ��������!");
                        cansendmessage = false;
                    } else if (!Algorithm.isOnlyletter(plaintext) && !Algorithm.isOnlyletter(key)) {
                        JOptionPane.showMessageDialog(jf, "���ĺ���Կ��������!");
                        cansendmessage = false;
                    }
                }
                if (cansendmessage) {

                    try {

                        // ��ȡ������������
                        outputstream.write(message); // д����Ϣ
                        ta1.append("��Ϣ����");
                        ta1.append(new String(message));
                        ta1.append("���ѳɹ�����!\n");
                        cansendmessage = false;

                    } catch (Exception e) {
                        // �쳣����
                    }
                }

            }
        });


        b4.addActionListener(new ActionListener() { // �����¼�����
            public void actionPerformed(ActionEvent Event) {
                jf.dispose();
            }
        });


        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = null;
                int result = -1;
                if (e.getActionCommand().equals("�����ļ�")) {
                    fileChooser.setApproveButtonText("ȷ��");
                    fileChooser.setDialogTitle("���ļ�");
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
                            // �쳣����
                        }
                    } else if (result == JFileChooser.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(jf, "δѡ��");
                    }
                }
            }
        });

        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = null;
                int result = -1;
                if (e.getActionCommand().equals("��Կ�ļ�")) {
                    fileChooser.setApproveButtonText("ȷ��");
                    fileChooser.setDialogTitle("���ļ�");
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
                            // �쳣����
                        }
                    } else if (result == JFileChooser.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(jf, "δѡ��");
                    }
                }
            }
        });

        jf.setVisible(true);// ���ÿɼ�
    }

}
