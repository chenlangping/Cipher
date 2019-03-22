package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TCPclient {

    public static void main(String[] args) {

        String server = "127.0.0.1"; // Ŀ���������ַ
        String sendMessage = "hellow world!!"; // ������Ϣ����
        System.out.println("Send Message: " + sendMessage);
        byte[] message = sendMessage.getBytes();
        int serverPort = Integer.parseInt("1025"); // Ŀ��������˿�
        System.out.println("Server port: " + serverPort);
        Socket socket = null;
        try {
            socket = new Socket(server, serverPort); // �����ͻ����׽��֣����ӷ�����
            System.out.println("Connect to server......");
            InputStream inputstream = socket.getInputStream();
            OutputStream outputstream = socket.getOutputStream(); // ��ȡ������������
            outputstream.write(message); // д����Ϣ
            int RcvBytes = 0;
            int rcvbyte;
            while (RcvBytes < message.length) {
                rcvbyte = inputstream.read(message, RcvBytes, message.length - RcvBytes);
                if (rcvbyte == -1) {
                    throw new SocketException("Connection closed prematurely");
                }
                RcvBytes += rcvbyte;
            }
            // ע�⣬ ��ʱmessage��Ϊbyte[], ��Ҫת����String
            System.out.println("Receive: " + new String(message));
        } catch (UnknownHostException error) {
            error.printStackTrace();//
        } catch (IOException error) {
            error.printStackTrace();//
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                    System.out.println("����");
                }
            } catch (IOException error) {
                error.printStackTrace();
            }

        }
    }

}