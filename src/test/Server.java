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

    static final int WIDTH = 600;// ���
    static final int HEIGHT = 800;// �߶�
    static final int BUFSIZE = 1024;
    // private static final int BUFSIZE = 64; //�ŵ��߳���ȥ����

    BigInteger primeA = new BigInteger("23");
    String plaintext = "";// ���漰ʱ�����û����������
    String ciphertext = "";// ���漰ʱ�����û����������
    String key = "";// ���漰ʱ�����û��������Կ

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
        jf = new JFrame("˫��������");
        jf.setSize(WIDTH, HEIGHT);
        jf.setResizable(false);// ���ɵ�����С
        jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                jf.dispose();
            }
        });// ���ùر�

        JPanel contentPane = new JPanel();// ����frame�е��м�������֮���panels�����ڸ�������
        contentPane.setLayout(null);
        jf.setContentPane(contentPane);

        JTextArea ta1 = new JTextArea(10, 15);
        //contentPane.add(ta1);
        ta1.setBackground(null);
        ta1.setLineWrap(true);
        ta1.setEditable(false);
        ta1.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        JScrollPane jScrollPane3 = new JScrollPane(ta1);
        jScrollPane3.setBounds(20, 20, 550, 300);
        contentPane.add(jScrollPane3);

        JLabel jl1 = new JLabel("�˿ں�:");
        jl1.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        jl1.setBounds(60, 370, 90, 40);
        contentPane.add(jl1);

        JTextField tf1 = new JTextField("8000", 5);
        tf1.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        tf1.setBounds(160, 370, 100, 40);
        contentPane.add(tf1);

        JButton b1 = new JButton("��������");
        b1.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        b1.setBounds(400, 370, 120, 40);
        contentPane.add(b1);

        JLabel jl5 = new JLabel("�����㷨:");
        jl5.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        jl5.setBounds(55, 480, 100, 40);
        contentPane.add(jl5);

        JLabel jl4 = new JLabel("��Կ:");
        jl4.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        jl4.setBounds(70, 550, 50, 40);
        contentPane.add(jl4);

        JTextField tf4 = new JTextField(50);
        tf4.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        tf4.setBounds(160, 550, 310, 40);
        contentPane.add(tf4);

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
        comboBox.setBounds(160, 480, 100, 40);
        contentPane.add(comboBox);

        JButton b3 = new JButton("����");
        b3.setFont(new Font("TimesRoman", Font.BOLD, 20));// ���������񡢴�С��
        b3.setBounds(420, 470, 80, 40);
        contentPane.add(b3);

        b1.addActionListener(new ActionListener() { // ���������¼�����
            public void actionPerformed(ActionEvent Event) {
                // �����������
                ta1.append("�ȴ�����........\n");
                int Port = Integer.parseInt(tf1.getText()); // �������˿�
                ServerSocket servSocket = null;
                int recvSize = 0;
                byte[] buf = new byte[BUFSIZE];

                try {
                    servSocket = new ServerSocket(Port); // �����������׽���
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

        b3.addActionListener(new ActionListener() { // ��������¼�����
            public void actionPerformed(ActionEvent Event) {
                receive = connecthread.getMessage();
                recvSize = connecthread.getRecvSize();
                receive = receive.substring(0, recvSize);
                ciphertext = receive;
                key = tf4.getText();
                //System.out.println("ciphertext= " + ciphertext + "  length = " + ciphertext.length());
                //System.out.println("key= " + key + "  length = " + key.length());
                if (comboBox.getSelectedItem().toString().equals("������")) {
                    ta1.append("�յ�����ϢΪ:" + receive);
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("Autokey")) {
                    ta1.append("���ܺ�Ϊ��");
                    ta1.append(new Decrypt().Autocipher(ciphertext, key));
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("Caesar")) {
                    ta1.append("���ܺ�Ϊ��");
                    ta1.append(new Decrypt().Caesar(ciphertext, key));
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("CA")) {
                    ta1.append("���ܺ�Ϊ��");
                    ta1.append(new Decrypt().CA(ciphertext, key, cell));
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("ColumnPermutation")) {
                    ta1.append("���ܺ�Ϊ��");
                    ta1.append(new Decrypt().ColumnPermutationcipher(ciphertext, key));
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("DES")) {
                    ta1.append("���ܺ�Ϊ��");
                    ta1.append(new Decrypt().DES(ciphertext, key));
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("Permutation")) {
                    ta1.append("���ܺ�Ϊ��");
                    ta1.append(new Decrypt().Permutationcipher(ciphertext, key));
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("Playfair")) {
                    ta1.append("���ܺ�Ϊ��");
                    char[] plaintext1 = new Playfairalgorithm().decode(ciphertext.toCharArray(),
                            key.toLowerCase().toCharArray());
                    ta1.append(new String(plaintext1));
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("Keyword")) {
                    ta1.append("���ܺ�Ϊ��");
                    ta1.append(new Decrypt().Keyword(ciphertext, key));
                    ta1.append("\n");
                } else if (comboBox.getSelectedItem().toString().equals("Vigenere")) {
                    ta1.append("���ܺ�Ϊ��");
                    ta1.append(new Decrypt().Vigenere(ciphertext, key));
                    ta1.append("\n");
                }
                // System.out.println(receive);
            }
        });


        jf.setVisible(true);// ���ÿɼ�
    }

}