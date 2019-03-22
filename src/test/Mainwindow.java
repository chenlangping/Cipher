package test;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.CremeSkin;
import org.jvnet.substance.theme.SubstanceTerracottaTheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mainwindow extends JFrame {
    static final int WIDTH = 700;
    static final int HEIGHT = 800;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new SubstanceLookAndFeel());
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            SubstanceLookAndFeel.setCurrentTheme(new SubstanceTerracottaTheme());
            SubstanceLookAndFeel.setSkin(new CremeSkin());
            // SubstanceLookAndFeel.setCurrentButtonShaper(new
            // ClassicButtonShaper());
            // SubstanceLookAndFeel.setCurrentWatermark(new
            // SubstanceBubblesWatermark());
            // SubstanceLookAndFeel.setCurrentBorderPainter(new
            // StandardBorderPainter());
            // SubstanceLookAndFeel.setCurrentGradientPainter(new
            // StandardGradientPainter());
            // SubstanceLookAndFeel.setCurrentTitlePainter(new
            // FlatTitePainter());
        } catch (Exception e) {
            System.err.println("Something went wrong!");
        }
        new Mainwindow().CreateJFrame("加解密程序");
    }

    public void CreateJFrame(String title) {

        JFrame jf = new JFrame(title);
        jf.setSize(WIDTH, HEIGHT);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();// 让程序框显示再屏幕中间
        Dimension dimension = toolkit.getScreenSize();
        int width = dimension.width;
        int height = dimension.height;
        int x = (width - WIDTH) / 2;
        int y = (height - HEIGHT) / 2;
        jf.setLocation(x, y);
        jf.setResizable(false);
        JPanel panel = new JPanel();
        /********** 添加图片 *************/
        JLabel jlpic = new JLabel();
        ImageIcon icon = new ImageIcon("picture/picture1.jpg");
        icon.setImage(
                icon.getImage().getScaledInstance(icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT));
        System.out.println(icon.getIconHeight() + " " + icon.getIconWidth());
        jlpic.setBounds(0, 0, 700, 800);
        jlpic.setHorizontalAlignment(0);
        jlpic.setIcon(icon);

        jf.setContentPane(panel);
        JTabbedPane tabbedPane1 = new JTabbedPane();
        JTabbedPane tabbedPane2 = new JTabbedPane();
        JTabbedPane tabbedPane3 = new JTabbedPane();
        JTabbedPane tabbedPane4 = new JTabbedPane();
        JTabbedPane tabbedPane5 = new JTabbedPane();
        JTabbedPane tabbedPane6 = new JTabbedPane();
        JTabbedPane tabbedPane7 = new JTabbedPane();
        JTabbedPane tabbedPane8 = new JTabbedPane();
        JTabbedPane tabbedPane9 = new JTabbedPane();
        JPanel panel1 = new JPanel();// 单表
        JPanel panel2 = new JPanel();// 多表
        JPanel panel3 = new JPanel();// 多图
        JPanel panel4 = new JPanel();// 置换
        JPanel panel5 = new JPanel();// 流密码
        JPanel panel6 = new JPanel();// 分块
        JPanel panel7 = new JPanel();// 公钥
        JPanel panel8 = new JPanel();// DH and 双机
        JPanel panel9 = new JPanel();// Android

        panel1.setLayout(null);// 单表
        panel2.setLayout(null);// 多表
        panel3.setLayout(null);// 多图
        panel4.setLayout(null);// 置换
        panel5.setLayout(null);// 流密码
        panel6.setLayout(null);// 分块
        panel7.setLayout(null);// 公钥
        panel8.setLayout(null);// DH and 双机
        panel9.setLayout(null);// DH and 双机
        panel.setLayout(null);

        tabbedPane1.addTab("单表密码:", panel1);
        tabbedPane1.setEnabledAt(0, true);
        tabbedPane1.setTitleAt(0, "单表密码:");
        tabbedPane1.setBounds(10, 30, 300, 100);

        tabbedPane2.addTab("多表密码:", panel2);
        tabbedPane2.setEnabledAt(0, true);
        tabbedPane2.setTitleAt(0, "多表密码:");
        tabbedPane2.setBounds(380, 30, 300, 100);

        tabbedPane3.addTab("多图密码:", panel3);
        tabbedPane3.setEnabledAt(0, true);
        tabbedPane3.setTitleAt(0, "多图密码:");
        tabbedPane3.setBounds(160, 175, 150, 100);

        tabbedPane4.addTab("置换密码:", panel4);
        tabbedPane4.setEnabledAt(0, true);
        tabbedPane4.setTitleAt(0, "置换密码:");
        tabbedPane4.setBounds(10, 465, 300, 100);

        tabbedPane5.addTab("流密码:", panel5);
        tabbedPane5.setEnabledAt(0, true);
        tabbedPane5.setTitleAt(0, "流密码:");
        tabbedPane5.setBounds(380, 175, 150, 100);

        tabbedPane6.addTab("分块密码:", panel6);
        tabbedPane6.setEnabledAt(0, true);
        tabbedPane6.setTitleAt(0, "分块密码:");
        tabbedPane6.setBounds(160, 320, 150, 100);

        tabbedPane7.addTab("公钥密码:", panel7);
        tabbedPane7.setEnabledAt(0, true);
        tabbedPane7.setTitleAt(0, "公钥密码:");
        tabbedPane7.setBounds(380, 320, 150, 100);

        tabbedPane8.addTab(" 双机:", panel8);
        tabbedPane8.setEnabledAt(0, true);
        tabbedPane8.setTitleAt(0, "双机:");
        tabbedPane8.setBounds(380, 465, 300, 100);

        tabbedPane9.addTab(" Android通信:", panel9);
        tabbedPane9.setEnabledAt(0, true);
        tabbedPane9.setTitleAt(0, "Android通信机:");
        tabbedPane9.setBounds(280, 610, 130, 100);

        JButton b1 = new JButton("凯撒密码");// 单表
        JButton b2 = new JButton("关键字密码");
        JButton b3 = new JButton("Vigenere");// 多表
        JButton b4 = new JButton("Autokey");
        JButton b5 = new JButton("Playfair");// 多图
        JButton b6 = new JButton("置换密码");// 置换
        JButton b7 = new JButton("列置换密码");
        JButton b8 = new JButton("CA");// 流密码
        JButton b9 = new JButton("DES");// 分块
        JButton b10 = new JButton("RSA");// 公钥
        JButton b11 = new JButton("双机服务器");
        JButton b12 = new JButton("双机客户端");
        JButton b13 = new JButton("Android端通信");

        b1.setBounds(25, 15, 100, 40);
        b2.setBounds(170, 15, 100, 40);
        b3.setBounds(25, 15, 100, 40);
        b4.setBounds(170, 15, 100, 40);
        b5.setBounds(22, 15, 100, 40);
        b6.setBounds(25, 15, 100, 40);
        b7.setBounds(170, 15, 100, 40);
        b8.setBounds(22, 15, 100, 40);
        b9.setBounds(22, 15, 100, 40);
        b10.setBounds(22, 15, 100, 40);
        b11.setBounds(25, 15, 100, 40);
        b12.setBounds(170, 15, 100, 40);
        b13.setBounds(10, 10, 100, 40);

        panel1.add(b1);
        panel1.add(b2);
        panel2.add(b3);
        panel2.add(b4);
        panel3.add(b5);
        panel4.add(b6);
        panel4.add(b7);
        panel5.add(b8);
        panel6.add(b9);
        panel7.add(b10);
        panel8.add(b11);
        panel8.add(b12);
        panel9.add(b13);

        panel.add(tabbedPane1);
        panel.add(tabbedPane2);
        panel.add(tabbedPane3);
        panel.add(tabbedPane4);
        panel.add(tabbedPane5);
        panel.add(tabbedPane6);
        panel.add(tabbedPane7);
        panel.add(tabbedPane8);
        panel.add(tabbedPane9);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent Event) {
                new Caesar();
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent Event) {
                new Keyword();
            }
        });

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent Event) {
                new Vigenere();
            }
        });

        b4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Autocipher();
            }
        });
        b5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Playfair();
            }
        });

        b6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Permutation();
            }
        });

        b7.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new ColumnPermutationcipher();
            }
        });
        b8.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new CA();
            }
        });
        b9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent Event) {
                new DES();
            }
        });
        b10.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new RSA();
            }
        });
        b11.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Server();
            }
        });

        b12.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Client();
            }
        });
        b13.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Socket_Android();
            }
        });
        // panel.add(jlpic);
        jf.setVisible(true);

    }
}
