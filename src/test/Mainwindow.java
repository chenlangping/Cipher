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
        new Mainwindow().CreateJFrame("�ӽ��ܳ���");
    }

    public void CreateJFrame(String title) {

        JFrame jf = new JFrame(title);
        jf.setSize(WIDTH, HEIGHT);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();// �ó������ʾ����Ļ�м�
        Dimension dimension = toolkit.getScreenSize();
        int width = dimension.width;
        int height = dimension.height;
        int x = (width - WIDTH) / 2;
        int y = (height - HEIGHT) / 2;
        jf.setLocation(x, y);
        jf.setResizable(false);
        JPanel panel = new JPanel();
        /********** ���ͼƬ *************/
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
        JPanel panel1 = new JPanel();// ����
        JPanel panel2 = new JPanel();// ���
        JPanel panel3 = new JPanel();// ��ͼ
        JPanel panel4 = new JPanel();// �û�
        JPanel panel5 = new JPanel();// ������
        JPanel panel6 = new JPanel();// �ֿ�
        JPanel panel7 = new JPanel();// ��Կ
        JPanel panel8 = new JPanel();// DH and ˫��
        JPanel panel9 = new JPanel();// Android

        panel1.setLayout(null);// ����
        panel2.setLayout(null);// ���
        panel3.setLayout(null);// ��ͼ
        panel4.setLayout(null);// �û�
        panel5.setLayout(null);// ������
        panel6.setLayout(null);// �ֿ�
        panel7.setLayout(null);// ��Կ
        panel8.setLayout(null);// DH and ˫��
        panel9.setLayout(null);// DH and ˫��
        panel.setLayout(null);

        tabbedPane1.addTab("��������:", panel1);
        tabbedPane1.setEnabledAt(0, true);
        tabbedPane1.setTitleAt(0, "��������:");
        tabbedPane1.setBounds(10, 30, 300, 100);

        tabbedPane2.addTab("�������:", panel2);
        tabbedPane2.setEnabledAt(0, true);
        tabbedPane2.setTitleAt(0, "�������:");
        tabbedPane2.setBounds(380, 30, 300, 100);

        tabbedPane3.addTab("��ͼ����:", panel3);
        tabbedPane3.setEnabledAt(0, true);
        tabbedPane3.setTitleAt(0, "��ͼ����:");
        tabbedPane3.setBounds(160, 175, 150, 100);

        tabbedPane4.addTab("�û�����:", panel4);
        tabbedPane4.setEnabledAt(0, true);
        tabbedPane4.setTitleAt(0, "�û�����:");
        tabbedPane4.setBounds(10, 465, 300, 100);

        tabbedPane5.addTab("������:", panel5);
        tabbedPane5.setEnabledAt(0, true);
        tabbedPane5.setTitleAt(0, "������:");
        tabbedPane5.setBounds(380, 175, 150, 100);

        tabbedPane6.addTab("�ֿ�����:", panel6);
        tabbedPane6.setEnabledAt(0, true);
        tabbedPane6.setTitleAt(0, "�ֿ�����:");
        tabbedPane6.setBounds(160, 320, 150, 100);

        tabbedPane7.addTab("��Կ����:", panel7);
        tabbedPane7.setEnabledAt(0, true);
        tabbedPane7.setTitleAt(0, "��Կ����:");
        tabbedPane7.setBounds(380, 320, 150, 100);

        tabbedPane8.addTab(" ˫��:", panel8);
        tabbedPane8.setEnabledAt(0, true);
        tabbedPane8.setTitleAt(0, "˫��:");
        tabbedPane8.setBounds(380, 465, 300, 100);

        tabbedPane9.addTab(" Androidͨ��:", panel9);
        tabbedPane9.setEnabledAt(0, true);
        tabbedPane9.setTitleAt(0, "Androidͨ�Ż�:");
        tabbedPane9.setBounds(280, 610, 130, 100);

        JButton b1 = new JButton("��������");// ����
        JButton b2 = new JButton("�ؼ�������");
        JButton b3 = new JButton("Vigenere");// ���
        JButton b4 = new JButton("Autokey");
        JButton b5 = new JButton("Playfair");// ��ͼ
        JButton b6 = new JButton("�û�����");// �û�
        JButton b7 = new JButton("���û�����");
        JButton b8 = new JButton("CA");// ������
        JButton b9 = new JButton("DES");// �ֿ�
        JButton b10 = new JButton("RSA");// ��Կ
        JButton b11 = new JButton("˫��������");
        JButton b12 = new JButton("˫���ͻ���");
        JButton b13 = new JButton("Android��ͨ��");

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
