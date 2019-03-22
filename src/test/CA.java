package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;

public class CA extends JDialog implements ActionListener {
    static final int WIDTH = 600;// 宽度
    static final int HEIGHT = 800;// 高度

    String plaintext = "";// 保存及时加密用户输入的明文
    String ciphertext = "";// 保存及时加密用户输入的密文
    String key = "";// 保存及时加密用户输入的密钥

    String fileplaintext = "";
    String fileciphertext = "";
    String filekey = "";

    String encryptionaddress = "";// 加密之后的文件地址
    String decryptionaddress = "";// 解密之后的文件地址
    String plaintextaddress = "";
    String ciphertextaddress = "";
    String keyaddress = "";

    String cell = "10101001";

    JFileChooser fileChooser = null;
    JFrame jf = null;
    JLabel fileplaintextlabel = null;
    JTextField fileplaintextinput = null;
    JTextField fileciphertextinput = null;
    JTextField filekeyinput = null;
    boolean fileplaintextlegal = false;// 如果获取的内容符合要求，则为true
    boolean fileciphertextlegal = false;// 如果获取的内容符合要求，则为true
    boolean filekeylegal = false;// 如果获取的内容符合要求，则为true

    public CA() {
        jf = new JFrame("CA密码");
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

        contentPane.setLayout(null);// 尝试绝对布局
        JTabbedPane tabbedPane1 = new JTabbedPane();// 及时加密tab
        JTabbedPane tabbedPane2 = new JTabbedPane();// 文件加密tab
        tabbedPane1.setBounds(5, 0, 590, 350);
        tabbedPane2.setBounds(5, 400, 590, 350);
        JPanel panel1 = new JPanel();// 及时加密
        JPanel panel2 = new JPanel();// 文件加密

        JPanel panel3 = new JPanel();// 及时加密中west区域布局
        JPanel panel4 = new JPanel();// 及时加密中Center区域布局
        JPanel panel5 = new JPanel();// 及时加密中south区域布局

        JPanel panel6 = new JPanel();// 文件加密中west区域布局
        JPanel panel7 = new JPanel();// 文件加密中Center区域布局
        JPanel panel8 = new JPanel();// 文件加密中East区域布局
        JPanel panel9 = new JPanel();// 文件加密中South区域布局
        JPanel panel = new JPanel();

        JSeparator jSeparator = new JSeparator();
        jSeparator.setOrientation(JSeparator.HORIZONTAL);
        /* 及时加密区域布局部分 */
        panel1.setLayout(new BorderLayout());
        panel3.setLayout(new GridLayout(3, 1));
        panel4.setLayout(new GridLayout(3, 1));
        panel5.setLayout(new GridLayout(1, 4));

        /* 问价加密区域布局部分 */
        panel2.setLayout(null);
        panel6.setLayout(new GridLayout(3, 1));
        panel7.setLayout(new GridLayout(3, 1));
        panel8.setLayout(new GridLayout(3, 1));
        panel9.setLayout(new GridLayout(1, 4));

        /* 设置tab标签 */
        tabbedPane1.addTab("及时加密", panel1);
        tabbedPane1.setEnabledAt(0, true);
        tabbedPane1.setTitleAt(0, "及时加密:");

        tabbedPane2.addTab("文件加密", panel2);
        tabbedPane2.setEnabledAt(0, true);
        tabbedPane2.setTitleAt(0, "文件加密:");

        contentPane.add(tabbedPane1);
        // contentPane.add(jSeparator);
        contentPane.add(tabbedPane2);

        /* 及时加密布局 */
        panel1.add(panel3, "West");
        panel1.add(panel4, "Center");
        panel1.add(panel5, "South");

        /* 及时加密明文部分 */
        JLabel plaintextlabel = new JLabel("明文: ");// 明文标签
        panel3.add(plaintextlabel, "West");

        JTextArea plaintextinput = new JTextArea();// 明文输入框
        plaintextinput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(c == '1' || c == '0'))
                    e.consume();
            }
        });// 设置只能输入0和1
        plaintextinput.setBackground(null);
        plaintextinput.setLineWrap(true);// 自动换行
        plaintextinput.setEditable(false);
        plaintextinput.setFont(new Font("TimesRoman", Font.PLAIN, 17));// 设置字体风格、大小等
        JScrollPane jScrollPane1 = new JScrollPane(plaintextinput);
        panel4.add(jScrollPane1);

        /* 及时加密密文部分 */
        JLabel ciphertextlabel = new JLabel("密文: ");
        panel3.add(ciphertextlabel);

        JTextArea ciphertextinput = new JTextArea();
        ciphertextinput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(c == '1' || c == '0'))
                    e.consume();
            }
        });// 设置只能输入0和1
        ciphertextinput.setBackground(null);
        ciphertextinput.setLineWrap(true);
        ciphertextinput.setEditable(false);
        ciphertextinput.setFont(new Font("TimesRoman", Font.PLAIN, 17));// 设置字体风格、大小等
        JScrollPane jScrollPane2 = new JScrollPane(ciphertextinput);
        panel4.add(jScrollPane2);

        /* 及时加密密钥部分 */
        JLabel keytextlabel = new JLabel("密钥: ");
        panel3.add(keytextlabel);

        JTextArea keyinput = new JTextArea();
        keyinput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7'
                        || c == '8' || c == '9'))
                    e.consume();
            }
        });// 设置只能输入数字
        keyinput.setBackground(null);
        keyinput.setLineWrap(true);
        keyinput.setEditable(false);
        keyinput.setFont(new Font("TimesRoman", Font.PLAIN, 17));// 设置字体风格、大小等
        JScrollPane jScrollPane3 = new JScrollPane(keyinput);
        panel4.add(jScrollPane3);

        /* 加解密部分 */
        JRadioButton encryption = new JRadioButton("加密");
        panel5.add(encryption);

        JRadioButton decryption = new JRadioButton("解密");
        panel5.add(decryption);

        ButtonGroup bg = new ButtonGroup();
        bg.add(decryption);
        bg.add(encryption);

        JButton ok1 = new JButton("确定");
        panel5.add(ok1);
        JButton back = new JButton("返回");
        panel5.add(back);

        /* 选择加密 */
        encryption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plaintextinput.setEditable(true);
                plaintextinput.setBackground(Color.white);
                plaintextinput.setText("");
                ciphertextinput.setEditable(false);
                ciphertextinput.setBackground(null);
                ciphertextinput.setText("");
                keyinput.setEditable(true);
                keyinput.setBackground(Color.white);
                keyinput.setText("");
            }
        });

        /* 选择解密 */
        decryption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plaintextinput.setEditable(false);
                plaintextinput.setBackground(null);
                plaintextinput.setText("");
                ciphertextinput.setEditable(true);
                ciphertextinput.setBackground(Color.white);
                ciphertextinput.setText("");
                keyinput.setEditable(true);
                keyinput.setBackground(Color.white);
                keyinput.setText("");
            }
        });

        /* 点击第一个确定发生的事件 */
        ok1.addActionListener(new ActionListener() { // 确定事件处理
            public void actionPerformed(ActionEvent Event) {
                plaintext = plaintextinput.getText();
                ciphertext = ciphertextinput.getText();
                key = keyinput.getText();
                if (encryption.isSelected()) {
                    if (plaintext.equals("") && key.equals("")) {
                        JOptionPane.showMessageDialog(jf, "未输入明文和密钥");
                    } else if (plaintext.equals("") && !key.equals("")) {
                        JOptionPane.showMessageDialog(jf, "未输入明文");
                    } else if (!plaintext.equals("") && key.equals("")) {
                        JOptionPane.showMessageDialog(jf, "未输入密钥");
                    } else if (255 < Integer.parseInt(key) || Integer.parseInt(key) < 0) {
                        JOptionPane.showMessageDialog(jf, "密钥大小不符合");
                    } else
                        // 执行加密算法
                        ciphertextinput.setText(new Encrypt().CA(plaintext, key, cell));
                } else if (decryption.isSelected()) {
                    if (ciphertext.equals("") && key.equals("")) {
                        JOptionPane.showMessageDialog(jf, "未输入密文和密钥");
                    } else if (ciphertext.equals("") && !key.equals("")) {
                        JOptionPane.showMessageDialog(jf, "未输入密文");
                    } else if (!ciphertext.equals("") && key.equals("")) {
                        JOptionPane.showMessageDialog(jf, "未输入密钥");
                    } else if (255 < Integer.parseInt(key) || Integer.parseInt(key) < 0) {
                        JOptionPane.showMessageDialog(jf, "密钥大小不符合");
                    } else
                        // 执行解密算法
                        plaintextinput.setText(new Decrypt().CA(ciphertext, key, cell));
                }
            }
        });

        /* 第一个返回按钮 */
        back.addActionListener(new ActionListener() { // 返回事件处理
            public void actionPerformed(ActionEvent Event) {
                jf.dispose();
            }
        });

        /* 文件加密布局 */

        /* 改为绝对布局 */
        /* 文件加密明文部分 */
        JLabel fileplaintextlabel = new JLabel("明文路径:");
        fileplaintextlabel.setFont(new Font("宋体", Font.BOLD, 13));
        fileplaintextlabel.setBounds(0, 60, 65, 30);
        panel2.add(fileplaintextlabel);

        fileplaintextinput = new JTextField(50);
        fileplaintextinput.setText(null);
        fileplaintextinput.setBounds(70, 60, 400, 30);
        fileplaintextinput.setEditable(false);
        panel2.add(fileplaintextinput);

        /* 文件加密密文部分 */
        JLabel fileciphertextlabel = new JLabel("密文路径:");
        fileciphertextlabel.setFont(new Font("宋体", Font.BOLD, 13));
        fileciphertextlabel.setBounds(0, 120, 65, 30);
        panel2.add(fileciphertextlabel);

        fileciphertextinput = new JTextField(50);
        fileciphertextinput.setText(null);
        fileciphertextinput.setBounds(70, 120, 400, 30);
        fileciphertextinput.setEditable(false);
        panel2.add(fileciphertextinput);

        /* 文件加密密钥部分 */
        JLabel filekeytextlabel = new JLabel("密钥路径:");
        filekeytextlabel.setFont(new Font("宋体", Font.BOLD, 13));
        filekeytextlabel.setBounds(0, 180, 65, 30);
        panel2.add(filekeytextlabel);

        filekeyinput = new JTextField(20);
        filekeyinput.setText(null);
        filekeyinput.setBounds(70, 180, 400, 30);
        filekeyinput.setEditable(false);
        panel2.add(filekeyinput);

        /* 打开明文文件按钮 */
        JButton openplain = new JButton("打开明文文件");
        openplain.setBounds(470, 60, 115, 30);
        openplain.setEnabled(false);
        openplain.addActionListener(this);

        /* 打开密文文件按钮 */
        JButton opencipher = new JButton("打开密文文件");
        opencipher.setBounds(470, 120, 115, 30);
        opencipher.setEnabled(false);
        opencipher.addActionListener(this);

        /* 打开密钥文件按钮 */
        JButton openkey = new JButton("打开密钥文件");
        openkey.setBounds(470, 180, 115, 30);
        openkey.setEnabled(false);
        openkey.addActionListener(this);

        panel2.add(openplain);
        panel2.add(opencipher);
        panel2.add(openkey);

        /* 文件选取器 */
        fileChooser = new JFileChooser("D:\\");
        contentPane.add(panel, BorderLayout.SOUTH);

        /* 文件加密按钮 */
        JRadioButton fileencryption = new JRadioButton("加密");
        fileencryption.setBounds(50, 240, 70, 30);
        panel2.add(fileencryption);

        /* 文件解密按钮 */
        JRadioButton filedecryption = new JRadioButton("解密");
        filedecryption.setBounds(150, 240, 70, 30);
        panel2.add(filedecryption);

        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(filedecryption);
        bg2.add(fileencryption);

        JButton ok2 = new JButton("确定");
        ok2.setBounds(330, 240, 100, 30);
        panel2.add(ok2);
        JButton back2 = new JButton("返回");
        back2.setBounds(450, 240, 100, 30);
        panel2.add(back2);

        /* 点击加密按钮 */
        fileencryption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openplain.setEnabled(true);
                opencipher.setEnabled(true);
                openkey.setEnabled(true);
                fileplaintextinput.setText("");
                fileciphertextinput.setText("");
                filekeyinput.setText("");
                openplain.setText("打开明文文件");
                opencipher.setText("密文保存路径");
                openkey.setText("打开密钥文件");
            }
        });

        /* 点击解密按钮 */
        filedecryption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openplain.setEnabled(true);
                opencipher.setEnabled(true);
                openkey.setEnabled(true);
                fileplaintextinput.setText("");
                fileciphertextinput.setText("");
                filekeyinput.setText("");
                openplain.setText("明文保存路径");
                opencipher.setText("打开密文文件");
                openkey.setText("打开密钥文件");
            }
        });

        /* 点击第二个确定 */
        ok2.addActionListener(new ActionListener() { // 确定事件处理
            public void actionPerformed(ActionEvent Event) {
                if (fileencryption.isSelected()) {
                    // 判断用户是否选择完
                    if (fileplaintextinput.getText().equals("") && fileciphertextinput.getText().equals("")
                            && filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "未选择");
                    } else if (!fileplaintextinput.getText().equals("") && fileciphertextinput.getText().equals("")
                            && filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "未选择密文保存路径和密钥");
                    } else if (fileplaintextinput.getText().equals("") && !fileciphertextinput.getText().equals("")
                            && filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "未选择明文和密钥");
                    } else if (fileplaintextinput.getText().equals("") && fileciphertextinput.getText().equals("")
                            && !filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "未选择明文和密文保存路径");
                    } else if (!fileplaintextinput.getText().equals("") && !fileciphertextinput.getText().equals("")
                            && filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "未选择密钥");
                    } else if (!fileplaintextinput.getText().equals("") && fileciphertextinput.getText().equals("")
                            && !filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "未选择密文保存路径");
                    } else if (fileplaintextinput.getText().equals("") && !fileciphertextinput.getText().equals("")
                            && !filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "未选择明文");
                    } else if (!fileplaintextinput.getText().equals("") && !fileciphertextinput.getText().equals("")
                            && !filekeyinput.getText().equals("")) {
                        FileCA p = new FileCA(filekey, cell);
                        File f1 = new File(plaintextaddress);
                        File f2 = new File(ciphertextaddress);
                        p.filecryp(f1, f2);
                        JOptionPane.showMessageDialog(jf, "创建成功");
                    }
                } else if (filedecryption.isSelected()) {
                    // 判断用户是否选择完
                    if (fileplaintextinput.getText().equals("") && fileciphertextinput.getText().equals("")
                            && filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "未选择");
                    } else if (!fileplaintextinput.getText().equals("") && fileciphertextinput.getText().equals("")
                            && filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "未选择密文和密钥");
                    } else if (fileplaintextinput.getText().equals("") && !fileciphertextinput.getText().equals("")
                            && filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "未选择明文保存路径和密钥");
                    } else if (fileplaintextinput.getText().equals("") && fileciphertextinput.getText().equals("")
                            && !filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "未选择明文保存路径和密文");
                    } else if (!fileplaintextinput.getText().equals("") && !fileciphertextinput.getText().equals("")
                            && filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "未选择密钥");
                    } else if (!fileplaintextinput.getText().equals("") && fileciphertextinput.getText().equals("")
                            && !filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "未选择密文");
                    } else if (fileplaintextinput.getText().equals("") && !fileciphertextinput.getText().equals("")
                            && !filekeyinput.getText().equals("")) {
                        JOptionPane.showMessageDialog(jf, "未选择明文保存路径");
                    } else if (!fileplaintextinput.getText().equals("") && !fileciphertextinput.getText().equals("")
                            && !filekeyinput.getText().equals("")) {
                        FileCA p = new FileCA(filekey, cell);
                        File f1 = new File(plaintextaddress);
                        File f2 = new File(ciphertextaddress);
                        p.filecryp(f2, f1);
                        JOptionPane.showMessageDialog(jf, "创建成功");
                    }
                    // 执行解密算法
                } else
                    JOptionPane.showMessageDialog(jf, "请选择加密还是解密!");
            }

        });

        /* 第二个返回 */
        back2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent Event) {
                jf.dispose();
            }
        });
    }

    /* 文件选取器相关 */
    public void actionPerformed(ActionEvent e) {
        File file = null;
        int result = -1;
        if (e.getActionCommand().equals("打开明文文件")) {
            fileChooser.setApproveButtonText("确定");
            fileChooser.setDialogTitle("打开文件");
            result = fileChooser.showOpenDialog(jf);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                fileplaintextinput.setText(file.getAbsolutePath());
                plaintextaddress = file.getAbsolutePath();
                try {
                    FileInputStream in = new FileInputStream(file);
                    byte[] byt = new byte[1024];
                    int len = in.read(byt);
                    fileplaintext = new String(byt, 0, len);
                } catch (Exception e2) {
                    // 异常处理
                }
            } else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(jf, "未选择");
            }
        } else if (e.getActionCommand().equals("打开密文文件")) {
            fileChooser.setApproveButtonText("确定");
            fileChooser.setDialogTitle("打开文件");
            result = fileChooser.showOpenDialog(jf);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                fileciphertextinput.setText(file.getAbsolutePath());
                ciphertextaddress = file.getAbsolutePath();
                try {
                    FileInputStream in = new FileInputStream(file);
                    byte[] byt = new byte[1024];
                    int len = in.read(byt);
                    fileciphertext = new String(byt, 0, len);
                } catch (Exception e2) {
                    // 异常处理
                }
            } else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(jf, "未选择");
            }
        } else if (e.getActionCommand().equals("打开密钥文件")) {
            fileChooser.setApproveButtonText("确定");
            fileChooser.setDialogTitle("打开文件");
            result = fileChooser.showOpenDialog(jf);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                filekeyinput.setText(file.getAbsolutePath());
                keyaddress = file.getAbsolutePath();
                try {
                    FileInputStream in = new FileInputStream(file);
                    byte[] byt = new byte[1024];
                    int len = in.read(byt);
                    filekey = new String(byt, 0, len);
                } catch (Exception e2) {
                    // 异常处理
                }
            } else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(jf, "未选择");
            }
        } else if (e.getActionCommand().equals("密文保存路径")) {
            fileChooser.setApproveButtonText("确定");
            fileChooser.setDialogTitle("打开文件");
            result = fileChooser.showOpenDialog(jf);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                // 获取路径，并且保存
                encryptionaddress = file.getAbsolutePath();
                ciphertextaddress = file.getAbsolutePath();
                fileciphertextinput.setText(encryptionaddress);
            } else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(jf, "未选择");
            }
        } else if (e.getActionCommand().equals("明文保存路径")) {
            fileChooser.setApproveButtonText("确定");
            fileChooser.setDialogTitle("打开文件");
            result = fileChooser.showOpenDialog(jf);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                // 获取路径并且保存
                decryptionaddress = file.getAbsolutePath();
                plaintextaddress = file.getAbsolutePath();
                fileplaintextinput.setText(decryptionaddress);
            } else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(jf, "未选择");
            }
        }
    }

}
