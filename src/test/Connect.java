package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Connect {

    static final int WIDTH = 600;// ���
    static final int HEIGHT = 800;// �߶�
    static final int BUFSIZE = 64;
    // private static final int BUFSIZE = 64; //�ŵ��߳���ȥ����

    JFrame jf = null;
    int Port = -1;
    String send = "";
    String receive = "";
    Socket socket = null;

    public Connect() {
        jf = new JFrame("˫������");
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

        JLabel jl1 = new JLabel("�˿ں�:");
        contentPane.add(jl1);

        JTextField tf1 = new JTextField(5);
        contentPane.add(tf1);

        JButton b1 = new JButton("��������");
        contentPane.add(b1);

        JTextArea ta1 = new JTextArea(10, 15);
        contentPane.add(ta1);

        b1.addActionListener(new ActionListener() { // ȷ���¼�����
            public void actionPerformed(ActionEvent Event) {
                // �����������
                ta1.append("�ȴ�����........\n");
                int Port = 1025; // �������˿�
                ServerSocket servSocket = null;
                int recvSize = 0;
                byte[] buf = new byte[BUFSIZE];

                try {
                    servSocket = new ServerSocket(Port); // �����������׽���

                    Cthread connecthread = new Cthread();
                    connecthread.setPort(Port);
                    connecthread.setServerSocket(servSocket);
                    connecthread.setBuf(buf);
                    connecthread.setTextArea(ta1);
                    connecthread.start();

                } catch (IOException error) {
                    error.printStackTrace();
                }
            }

        });

        JLabel jl2 = new JLabel("Ŀ��������˿ں�:");
        contentPane.add(jl2);

        JTextField tf2 = new JTextField(5);
        contentPane.add(tf2);

        JLabel jl3 = new JLabel("Ŀ�������ip��ַ:");
        contentPane.add(jl3);

        JTextField tf3 = new JTextField(15);
        contentPane.add(tf3);

        JButton b2 = new JButton("��������");
        contentPane.add(b2);

        JButton b3 = new JButton("�Ͽ�����");
        contentPane.add(b3);

        b2.addActionListener(new ActionListener() { // ��������ȷ���¼�����
            public void actionPerformed(ActionEvent Event) {
                String server = "127.0.0.1"; // Ŀ���������ַ
                String sendMessage = "hellow world!!"; // ������Ϣ����
                // String server = args[0];
                // String sendMessage = args[1];
                System.out.println("Send Message: " + sendMessage);

                byte[] message = sendMessage.getBytes();
                int serverPort = Integer.parseInt("1025"); // Ŀ��������˿�

                System.out.println("Server port: " + serverPort);

                try {
                    socket = new Socket(server, serverPort); // �����ͻ����׽��֣����ӷ�����
                    /*
                     * System.out.println("Connect to server......");
                     * InputStream inputstream = socket.getInputStream();
                     * OutputStream outputstream =socket.getOutputStream();
                     * //��ȡ������������
                     *
                     * outputstream.write(message); //д����Ϣ
                     *
                     * int RcvBytes = 0; int rcvbyte;
                     *
                     * while(RcvBytes < message.length){ rcvbyte =
                     * inputstream.read(message, RcvBytes,message.length -
                     * RcvBytes); if(rcvbyte == -1){ throw new SocketException(
                     * "Connection closed prematurely"); } RcvBytes += rcvbyte;
                     * } //ע�⣬ ��ʱmessage��Ϊbyte[], ��Ҫת����String
                     * System.out.println("Receive: "+ new String(message));
                     */

                } catch (Exception e) {
                    // �쳣����
                }

            }

        });

        b2.addActionListener(new ActionListener() { // ȷ���¼�����
            public void actionPerformed(ActionEvent Event) {

            }
        });

        JTextArea ta2 = new JTextArea(10, 15);
        contentPane.add(ta2);

        JButton b4 = new JButton("����");
        contentPane.add(b4);

        b4.addActionListener(new ActionListener() { // ȷ���¼�����
            public void actionPerformed(ActionEvent Event) {
                // �������
                send = ta2.getText();
                System.out.println(send);
                byte[] message = send.getBytes();
                try {

                    InputStream inputstream = socket.getInputStream();
                    OutputStream outputstream = socket.getOutputStream(); // ��ȡ������������
                    outputstream.write(message); // д����Ϣ
                    /*
                     * int RcvBytes = 0; int rcvbyte; while(RcvBytes <
                     * message.length){ rcvbyte = inputstream.read(message,
                     * RcvBytes,message.length - RcvBytes); if(rcvbyte == -1){
                     * throw new SocketException("Connection closed prematurely"
                     * ); } RcvBytes += rcvbyte; }
                     */
                    // ע�⣬ ��ʱmessage��Ϊbyte[], ��Ҫת����String
                } catch (Exception e) {
                    // �쳣����
                }

            }
        });

    }
}