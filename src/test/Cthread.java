package test;

import javax.swing.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

//import java.net.SocketException;

public class Cthread extends Thread {
    static final int BUFSIZE = 1024;
    Socket client = null;
    String send = "";
    JTextArea ta = new JTextArea();
    String message = null;
    String recvMessage = null;
    int recvSize = 0;
    String randomnumber = "";
    boolean isFirsttime = true;
    private int Port = -1;
    private ServerSocket servSocket = null;
    private byte[] buf;

    public void setPort(int Port) {
        this.Port = Port;
    }

    public void setServerSocket(ServerSocket servSocket) {
        this.servSocket = servSocket;
    }

    public void setBuf(byte[] buf) {
        this.buf = buf;
    }

    public void setTextArea(JTextArea ta) {
        this.ta = ta;
    }

    public String getMessage() {
        return recvMessage;
    }

    public int getRecvSize() {
        return recvSize;
    }

    public void setRandomnumber(String randomnumber) {
        this.randomnumber = randomnumber;
    }


    public void run() {
        while (true) {
            try {
                recvSize = 0;
                client = servSocket.accept();
                // ���ӽ������
                ta.append("�������!");
                ta.append("\n");
                SocketAddress clientAddress = client.getRemoteSocketAddress();
                InputStream inputstream = client.getInputStream();
                OutputStream outputstream = client.getOutputStream(); // ��ȡ�ͻ���������


                SDH server = new SDH("23", "5", randomnumber);
                byte[] rannumbyt = server.A.toString().getBytes();
                System.out.println("g^a=" + randomnumber);
                outputstream.write(rannumbyt);
                System.out.println("�ѷ���");

                //inputstream.read(buf);
                //server.setK(client.B.toString());


                while ((recvSize = inputstream.read(buf)) != -1) {
                    if (isFirsttime) {
                        recvMessage = new String(buf);
                        //buf = null;
                        //buf = new byte[BUFSIZE];
                        recvMessage = recvMessage.substring(0, recvSize);
                        server.setK(recvMessage);
                        ta.append("ͨ��DHЭ�̵���Կ��" + server.K.toString() + "\n");
                        ta.selectAll();
                        outputstream.write(buf, 0, recvSize);        //��������ԭ��Ϣ����
                        recvMessage = null;
                        isFirsttime = false;
                        buf = null;
                        buf = new byte[BUFSIZE];
                    } else {
                        recvMessage = new String(buf);
                        //buf = null;
                        //buf = new byte[BUFSIZE];
                        recvMessage = recvMessage.substring(0, recvSize);
                        ta.append("���" + clientAddress + "����Ϣ:" + recvMessage + "\n");
                        ta.selectAll();
                        outputstream.write(buf, 0, recvSize);        //��������ԭ��Ϣ����
                    }
                }
                ta.append("��ͻ���" + clientAddress + "�Ͽ�");
                ta.append("\n");

            } catch (Exception e) {
            }
        }
        // System.out.println("����");
    }
}
