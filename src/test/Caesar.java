package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Caesar extends JDialog implements ActionListener {
    static final int WIDTH = 600;// ���
    static final int HEIGHT = 800;// �߶�

    String plaintext = "";// ���漰ʱ�����û����������
    String ciphertext = "";// ���漰ʱ�����û����������
    String key = "";// ���漰ʱ�����û��������Կ

    String fileplaintext = "";
    String fileciphertext = "";
    String filekey = "";

    String encryptionaddress = "";// ����֮����ļ���ַ
    String decryptionaddress = "";// ����֮����ļ���ַ

    JFileChooser fileChooser = null;
    JFrame jf = null;
    JLabel fileplaintextlabel = null;
    JTextField fileplaintextinput = null;
    JTextField fileciphertextinput = null;
    JTextField filekeyinput = null;
    char[] alpht = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    boolean fileplaintextlegal = false;// �����ȡ�����ݷ���Ҫ����Ϊtrue
    boolean fileciphertextlegal = false;// �����ȡ�����ݷ���Ҫ����Ϊtrue
    boolean filekeylegal = false;// �����ȡ�����ݷ���Ҫ����Ϊtrue

    public Caesar() {
        jf = new JFrame("��������");
        jf.setSize(WIDTH, HEIGHT);
        jf.setResizable(false);// ���ɵ�����С
        jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                jf.dispose();
            }
        });// ���ùر�
        jf.setVisible(true);// ���ÿɼ�
        JPanel contentPane = new JPanel();// ����frame�е��м�������֮���panels�����ڸ�������
        jf.setContentPane(contentPane);

        contentPane.setLayout(null);// ���Ծ��Բ���
        JTabbedPane tabbedPane1 = new JTabbedPane();// ��ʱ����tab
        JTabbedPane tabbedPane2 = new JTabbedPane();// �ļ�����tab
        tabbedPane1.setBounds(5, 0, 590, 350);
        tabbedPane2.setBounds(5, 400, 590, 350);
        JPanel panel1 = new JPanel();// ��ʱ����
        JPanel panel2 = new JPanel();// �ļ�����

        JPanel panel3 = new JPanel();// ��ʱ������west���򲼾�
        JPanel panel4 = new JPanel();// ��ʱ������Center���򲼾�
        JPanel panel5 = new JPanel();// ��ʱ������south���򲼾�

        JPanel panel6 = new JPanel();// �ļ�������west���򲼾�
        JPanel panel7 = new JPanel();// �ļ�������Center���򲼾�
        JPanel panel8 = new JPanel();// �ļ�������East���򲼾�
        JPanel panel9 = new JPanel();// �ļ�������South���򲼾�
        JPanel panel = new JPanel();

        JSeparator jSeparator = new JSeparator();
        jSeparator.setOrientation(JSeparator.HORIZONTAL);
        /* ��ʱ�������򲼾ֲ��� */
        panel1.setLayout(new BorderLayout());
        panel3.setLayout(new GridLayout(3, 1));
        panel4.setLayout(new GridLayout(3, 1));
        panel5.setLayout(new GridLayout(1, 4));

        /* �������򲼾ֲ��� */
        panel2.setLayout(null);
        panel6.setLayout(new GridLayout(3, 1));
        panel7.setLayout(new GridLayout(3, 1));
        panel8.setLayout(new GridLayout(3, 1));
        panel9.setLayout(new GridLayout(1, 4));

        /* ����tab��ǩ */
        tabbedPane1.addTab("��ʱ����", panel1);
        tabbedPane1.setEnabledAt(0, true);
        tabbedPane1.setTitleAt(0, "��ʱ����:");

        tabbedPane2.addTab("�ļ�����", panel2);
        tabbedPane2.setEnabledAt(0, true);
        tabbedPane2.setTitleAt(0, "�ļ�����:");

        contentPane.add(tabbedPane1);
        // contentPane.add(jSeparator);
        contentPane.add(tabbedPane2);

        /* ��ʱ���ܲ��� */
        panel1.add(panel3, "West");
        panel1.add(panel4, "Center");
        panel1.add(panel5, "South");

        /* ��ʱ�������Ĳ��� */
        JLabel plaintextlabel = new JLabel("����: ");// ���ı�ǩ
        panel3.add(plaintextlabel, "West");

        JTextArea plaintextinput = new JTextArea();// ���������
        plaintextinput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isLowerCase(c) || Character.isUpperCase(c)))
                    e.consume();
            }
        });// ����ֻ�������Сд
        plaintextinput.setBackground(null);
        plaintextinput.setLineWrap(true);// �Զ�����
        plaintextinput.setEditable(false);
        plaintextinput.setFont(new Font("TimesRoman", Font.PLAIN, 17));// ���������񡢴�С��
        JScrollPane jScrollPane1 = new JScrollPane(plaintextinput);
        panel4.add(jScrollPane1);

        /* ��ʱ�������Ĳ��� */
        JLabel ciphertextlabel = new JLabel("����: ");
        panel3.add(ciphertextlabel);

        JTextArea ciphertextinput = new JTextArea();
        ciphertextinput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isLowerCase(c) || Character.isUpperCase(c)))
                    e.consume();
            }
        });// ����ֻ�������Сд
        ciphertextinput.setBackground(null);
        ciphertextinput.setLineWrap(true);
        ciphertextinput.setEditable(false);
        ciphertextinput.setFont(new Font("TimesRoman", Font.PLAIN, 17));// ���������񡢴�С��
        JScrollPane jScrollPane2 = new JScrollPane(ciphertextinput);
        panel4.add(jScrollPane2);

        /* ��ʱ������Կ���� */
        JLabel keytextlabel = new JLabel("��Կ: ");
        panel3.add(keytextlabel);

        /*
         * JTextArea keyinput = new JTextArea(); keyinput.setBackground(null);
         * keyinput.setLineWrap(true); keyinput.setEditable(false);
         * keyinput.setFont(new Font("TimesRoman", Font.PLAIN, 17));//���������񡢴�С��
         * JScrollPane jScrollPane3 = new JScrollPane(keyinput);
         * panel4.add(jScrollPane3);
         */

        JComboBox comboBox = new JComboBox();
        for (int i = 0; i < 26; i++) {
            comboBox.addItem(alpht[i]);
        }
        panel4.add(comboBox);

        /* �ӽ��ܲ��� */
        JRadioButton encryption = new JRadioButton("����");
        panel5.add(encryption);

        JRadioButton decryption = new JRadioButton("����");
        panel5.add(decryption);

        ButtonGroup bg = new ButtonGroup();
        bg.add(decryption);
        bg.add(encryption);

        JButton ok1 = new JButton("ȷ��");
        panel5.add(ok1);
        JButton back = new JButton("����");
        panel5.add(back);

        /* ѡ����� */
        encryption.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                plaintextinput.setEditable(true);
                plaintextinput.setBackground(Color.white);
                plaintextinput.setText("");
                ciphertextinput.setEditable(false);
                ciphertextinput.setBackground(null);
                ciphertextinput.setText("");
                // keyinput.setEditable(true);
                // keyinput.setBackground(Color.white);
                // keyinput.setText("");
            }
        });

        /* ѡ����� */
        decryption.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                plaintextinput.setEditable(false);
                plaintextinput.setBackground(null);
                plaintextinput.setText("");
                ciphertextinput.setEditable(true);
                ciphertextinput.setBackground(Color.white);
                ciphertextinput.setText("");
                // keyinput.setEditable(true);
                // keyinput.setBackground(Color.white);
                // keyinput.setText("");
            }
        });

        /* �����һ��ȷ���������¼� */
        ok1.addActionListener(new ActionListener() { // ȷ���¼�����
            public void actionPerformed(ActionEvent Event) {
                plaintext = plaintextinput.getText();
                ciphertext = ciphertextinput.getText();
                key = comboBox.getSelectedItem().toString();
                if (encryption.isSelected() == true) {
                    // ִ�м����㷨
                    if (plaintext.equals("")) {
                        JOptionPane.showMessageDialog(jf, "δ��������");
                    } else
                        ciphertextinput.setText(new Encrypt().Caesar(plaintext, key));
                } else if (decryption.isSelected() == true) {
                    // ִ�н����㷨
                    if (ciphertext.equals("")) {
                        JOptionPane.showMessageDialog(jf, "δ��������");
                    } else
                        plaintextinput.setText(new Decrypt().Caesar(ciphertext, key));
                }
            }
        });

        /* ��һ�����ذ�ť */
        back.addActionListener(new ActionListener() { // �����¼�����
            public void actionPerformed(ActionEvent Event) {
                jf.dispose();
            }
        });

        /* �ļ����ܲ��� */

        /* ��Ϊ���Բ��� */
        /* �ļ��������Ĳ��� */
        JLabel fileplaintextlabel = new JLabel("����·��:");
        fileplaintextlabel.setFont(new Font("����", Font.BOLD, 13));
        fileplaintextlabel.setBounds(0, 60, 65, 30);
        panel2.add(fileplaintextlabel);

        fileplaintextinput = new JTextField(50);
        fileplaintextinput.setText(null);
        fileplaintextinput.setBounds(70, 60, 400, 30);
        fileplaintextinput.setEditable(false);
        panel2.add(fileplaintextinput);

        /* �ļ��������Ĳ��� */
        JLabel fileciphertextlabel = new JLabel("����·��:");
        fileciphertextlabel.setFont(new Font("����", Font.BOLD, 13));
        fileciphertextlabel.setBounds(0, 120, 65, 30);
        panel2.add(fileciphertextlabel);

        fileciphertextinput = new JTextField(50);
        fileciphertextinput.setText(null);
        fileciphertextinput.setBounds(70, 120, 400, 30);
        fileciphertextinput.setEditable(false);
        panel2.add(fileciphertextinput);

        /* �ļ�������Կ���� */
        JLabel filekeytextlabel = new JLabel("��Կ·��:");
        filekeytextlabel.setFont(new Font("����", Font.BOLD, 13));
        filekeytextlabel.setBounds(0, 180, 65, 30);
        panel2.add(filekeytextlabel);

        filekeyinput = new JTextField(20);
        filekeyinput.setText(null);
        filekeyinput.setBounds(70, 180, 400, 30);
        filekeyinput.setEditable(false);
        panel2.add(filekeyinput);

        /* �������ļ���ť */
        JButton openplain = new JButton("�������ļ�");
        openplain.setBounds(470, 60, 115, 30);
        openplain.setEnabled(false);
        openplain.addActionListener(this);

        /* �������ļ���ť */
        JButton opencipher = new JButton("�������ļ�");
        opencipher.setBounds(470, 120, 115, 30);
        opencipher.setEnabled(false);
        opencipher.addActionListener(this);

        /* ����Կ�ļ���ť */
        JButton openkey = new JButton("����Կ�ļ�");
        openkey.setBounds(470, 180, 115, 30);
        openkey.setEnabled(false);
        openkey.addActionListener(this);

        panel2.add(openplain);
        panel2.add(opencipher);
        panel2.add(openkey);

        /* �ļ�ѡȡ�� */
        fileChooser = new JFileChooser("D:\\");
        contentPane.add(panel, BorderLayout.SOUTH);

        /* �ļ����ܰ�ť */
        JRadioButton fileencryption = new JRadioButton("����");
        fileencryption.setBounds(50, 240, 70, 30);
        panel2.add(fileencryption);

        /* �ļ����ܰ�ť */
        JRadioButton filedecryption = new JRadioButton("����");
        filedecryption.setBounds(150, 240, 70, 30);
        panel2.add(filedecryption);

        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(filedecryption);
        bg2.add(fileencryption);

        JButton ok2 = new JButton("ȷ��");
        ok2.setBounds(330, 240, 100, 30);
        panel2.add(ok2);
        JButton back2 = new JButton("����");
        back2.setBounds(450, 240, 100, 30);
        panel2.add(back2);

        /* ������ܰ�ť */
        fileencryption.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                openplain.setEnabled(true);
                opencipher.setEnabled(true);
                openkey.setEnabled(true);
                fileplaintextinput.setText("");
                fileciphertextinput.setText("");
                filekeyinput.setText("");
                openplain.setText("�������ļ�");
                opencipher.setText("���ı���·��");
                openkey.setText("����Կ�ļ�");
            }
        });

        /* ������ܰ�ť */
        filedecryption.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                openplain.setEnabled(true);
                opencipher.setEnabled(true);
                openkey.setEnabled(true);
                fileplaintextinput.setText("");
                fileciphertextinput.setText("");
                filekeyinput.setText("");
                openplain.setText("���ı���·��");
                opencipher.setText("�������ļ�");
                openkey.setText("����Կ�ļ�");
            }
        });

        /* ����ڶ���ȷ�� */
        ok2.addActionListener(new ActionListener() { // ȷ���¼�����
            public void actionPerformed(ActionEvent Event) {
                if (fileencryption.isSelected() == true) {
                    // �ж��û��Ƿ�ѡ����
                    if (fileplaintextinput.getText().equals("") && fileciphertextinput.getText().equals("")
                            && filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "δѡ��");
                    } else if (!fileplaintextinput.getText().equals("") && fileciphertextinput.getText().equals("")
                            && filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "δѡ�����ı���·������Կ");
                    } else if (fileplaintextinput.getText().equals("") && !fileciphertextinput.getText().equals("")
                            && filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "δѡ�����ĺ���Կ");
                    } else if (fileplaintextinput.getText().equals("") && fileciphertextinput.getText().equals("")
                            && !filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "δѡ�����ĺ����ı���·��");
                    } else if (!fileplaintextinput.getText().equals("") && !fileciphertextinput.getText().equals("")
                            && filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "δѡ����Կ");
                    } else if (!fileplaintextinput.getText().equals("") && fileciphertextinput.getText().equals("")
                            && !filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "δѡ�����ı���·��");
                    } else if (fileplaintextinput.getText().equals("") && !fileciphertextinput.getText().equals("")
                            && !filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "δѡ������");
                    } else if (!fileplaintextinput.getText().equals("") && !fileciphertextinput.getText().equals("")
                            && !filekeyinput.getText().equals("")) {
                        fileplaintextlegal = Algorithm.isOnlyletter(fileplaintext);

                        filekeylegal = Algorithm.isOnlyletter(filekey);

                        if (fileplaintextlegal && filekeylegal) {
                            // ִ�м����㷨
                            fileciphertext = new Encrypt().Caesar(fileplaintext, filekey);
                            File file = new File(encryptionaddress);
                            try {
                                FileOutputStream out = new FileOutputStream(file);
                                byte[] byt = fileciphertext.getBytes();
                                out.write(byt);
                            } catch (Exception e) {
                                // �쳣����
                            }
                            JOptionPane.showMessageDialog(jf, "�����ɹ�");
                        } else if (!fileplaintextlegal && filekeylegal) {
                            JOptionPane.showMessageDialog(jf, "�������м�⵽�Ƿ��ַ���");
                        } else if (fileplaintextlegal && !filekeylegal) {
                            JOptionPane.showMessageDialog(jf, "����Կ�м�⵽�Ƿ��ַ���");
                        } else
                            JOptionPane.showMessageDialog(jf, "�����ĺ���Կ�ж���⵽�Ƿ��ַ���");

                    }

                } else if (filedecryption.isSelected() == true) {
                    // �ж��û��Ƿ�ѡ����
                    if (fileplaintextinput.getText().equals("") && fileciphertextinput.getText().equals("")
                            && filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "δѡ��");
                    } else if (!fileplaintextinput.getText().equals("") && fileciphertextinput.getText().equals("")
                            && filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "δѡ�����ĺ���Կ");
                    } else if (fileplaintextinput.getText().equals("") && !fileciphertextinput.getText().equals("")
                            && filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "δѡ�����ı���·������Կ");
                    } else if (fileplaintextinput.getText().equals("") && fileciphertextinput.getText().equals("")
                            && !filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "δѡ�����ı���·��������");
                    } else if (!fileplaintextinput.getText().equals("") && !fileciphertextinput.getText().equals("")
                            && filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "δѡ����Կ");
                    } else if (!fileplaintextinput.getText().equals("") && fileciphertextinput.getText().equals("")
                            && !filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "δѡ������");
                    } else if (fileplaintextinput.getText().equals("") && !fileciphertextinput.getText().equals("")
                            && !filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "δѡ�����ı���·��");
                    } else if (!fileplaintextinput.getText().equals("") && !fileciphertextinput.getText().equals("")
                            && !filekeyinput.getText().equals("")) {
                        fileciphertextlegal = Algorithm.isOnlyletter(fileciphertext);

                        filekeylegal = Algorithm.isOnlyletter(filekey);
                        if (fileciphertextlegal && filekeylegal) {
                            fileplaintext = new Decrypt().Caesar(fileciphertext, filekey);
                            File file = new File(decryptionaddress);
                            try {
                                FileOutputStream out = new FileOutputStream(file);
                                byte[] byt = fileplaintext.getBytes();
                                out.write(byt);
                            } catch (Exception e) {
                                // �쳣����
                            }
                            JOptionPane.showMessageDialog(jf, "�����ɹ�");
                        } else if (!fileciphertextlegal && filekeylegal) {
                            JOptionPane.showMessageDialog(jf, "�������м�⵽�Ƿ��ַ���");
                        } else if (fileciphertextlegal && !filekeylegal) {
                            JOptionPane.showMessageDialog(jf, "����Կ�м�⵽�Ƿ��ַ���");
                        } else
                            JOptionPane.showMessageDialog(jf, "�����ĺ���Կ�ж���⵽�Ƿ��ַ���");

                    }
                    // ִ�н����㷨

                } else
                    JOptionPane.showMessageDialog(jf, "��ѡ����ܻ��ǽ���!");
            }

        });

        /* �ڶ������� */
        back2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent Event) {
                jf.dispose();
            }
        });
    }

    /* �ļ�ѡȡ����� */
    public void actionPerformed(ActionEvent e) {
        File file = null;
        int result = -1;
        if (e.getActionCommand().equals("�������ļ�")) {
            fileChooser.setApproveButtonText("ȷ��");
            fileChooser.setDialogTitle("���ļ�");
            result = fileChooser.showOpenDialog(jf);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                fileplaintextinput.setText(file.getAbsolutePath());
                try {
                    FileInputStream in = new FileInputStream(file);
                    byte[] byt = new byte[1024];
                    int len = in.read(byt);
                    fileplaintext = new String(byt, 0, len);
                } catch (Exception e2) {
                    // �쳣����
                }
            } else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(jf, "δѡ��");
            }
        } else if (e.getActionCommand().equals("�������ļ�")) {
            fileChooser.setApproveButtonText("ȷ��");
            fileChooser.setDialogTitle("���ļ�");
            result = fileChooser.showOpenDialog(jf);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                fileciphertextinput.setText(file.getAbsolutePath());
                try {
                    FileInputStream in = new FileInputStream(file);
                    byte[] byt = new byte[1024];
                    int len = in.read(byt);
                    fileciphertext = new String(byt, 0, len);
                } catch (Exception e2) {
                    // �쳣����
                }
            } else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(jf, "δѡ��");
            }
        } else if (e.getActionCommand().equals("����Կ�ļ�")) {
            fileChooser.setApproveButtonText("ȷ��");
            fileChooser.setDialogTitle("���ļ�");
            result = fileChooser.showOpenDialog(jf);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                filekeyinput.setText(file.getAbsolutePath());
                try {
                    FileInputStream in = new FileInputStream(file);
                    byte[] byt = new byte[1024];
                    int len = in.read(byt);
                    filekey = new String(byt, 0, len);
                } catch (Exception e2) {
                    // �쳣����
                }
            } else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(jf, "δѡ��");
            }
        } else if (e.getActionCommand().equals("���ı���·��")) {
            fileChooser.setApproveButtonText("ȷ��");
            fileChooser.setDialogTitle("���ļ�");
            result = fileChooser.showOpenDialog(jf);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                // ��ȡ·�������ұ���
                encryptionaddress = file.getAbsolutePath();
                fileciphertextinput.setText(encryptionaddress);
            } else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(jf, "δѡ��");
            }
        } else if (e.getActionCommand().equals("���ı���·��")) {
            fileChooser.setApproveButtonText("ȷ��");
            fileChooser.setDialogTitle("���ļ�");
            result = fileChooser.showOpenDialog(jf);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                // ��ȡ·�����ұ���
                decryptionaddress = file.getAbsolutePath();
                fileplaintextinput.setText(decryptionaddress);
            } else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(jf, "δѡ��");
            }
        }
    }

}
