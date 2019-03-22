package test;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSAalgorithm {

    public static void generateKey() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair kp = kpg.genKeyPair();
            PublicKey pbkey = kp.getPublic();
            PrivateKey prkey = kp.getPrivate();
            // ���湫Կ  
            FileOutputStream f1 = new FileOutputStream("pubkey.dat");
            ObjectOutputStream b1 = new ObjectOutputStream(f1);
            b1.writeObject(pbkey);
            // ����˽Կ  
            FileOutputStream f2 = new FileOutputStream("privatekey.dat");
            ObjectOutputStream b2 = new ObjectOutputStream(f2);
            b2.writeObject(prkey);
        } catch (Exception e) {
        }
    }

    public static String encrypt(String plaintext) throws Exception {

        // ��ȡ��Կ������e,n  
        String s = plaintext;
        FileInputStream f = new FileInputStream("pubkey.dat");  //��ȡ��Կ
        ObjectInputStream b = new ObjectInputStream(f);  //��ȡ��Կ
        RSAPublicKey pbk = (RSAPublicKey) b.readObject();  //��ȡ��Կ
        BigInteger e = pbk.getPublicExponent();
        BigInteger n = pbk.getModulus();
        // System.out.println("e= " + e);
        //System.out.println("n= " + n);  
        // ��ȡ����m  
        byte[] ptext = s.getBytes(StandardCharsets.UTF_8);
        BigInteger m = new BigInteger(ptext);
        // ��������c  
        BigInteger c = m.modPow(e, n);
        // System.out.println("c= " + c);
        // ��������  
        String cs = c.toString();
        BufferedWriter out =
                new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream("encrypt.dat")));
        out.write(cs, 0, cs.length());
        out.close();
        return cs;
    }

    public static String decrypt(String ciphertext) throws Exception {
        //��ȡ����
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(new FileInputStream("encrypt.dat")));
        String ctext = in.readLine();

        BigInteger c = new BigInteger(ctext);
        // ��ȡ˽Կ  
        FileInputStream f = new FileInputStream("privatekey.dat");
        ObjectInputStream b = new ObjectInputStream(f);
        RSAPrivateKey prk = (RSAPrivateKey) b.readObject();
        BigInteger d = prk.getPrivateExponent();
        // ��ȡ˽Կ����������  
        BigInteger n = prk.getModulus();
        //System.out.println("d= " + d);  
        //System.out.println("n= " + n);  
        BigInteger m = c.modPow(d, n);
        // ��ʾ���ܽ��  
        //System.out.println("m= " + m);  
        byte[] mt = m.toByteArray();
        String plaintext = new String(mt);
        //   System.out.println(plaintext);
        return plaintext;

    }  
  
   /* public static void main(String args[]) {  
       try {  
    	   String s = "Hello World!"; 
            generateKey();  
            System.out.println( encrypt(s));  
            String text=new String(s);
           System.out.println(decrypt(text)) ;  
        } catch (Exception e) {  
            System.out.println(e.toString());  
        }  
    }  */

}  
