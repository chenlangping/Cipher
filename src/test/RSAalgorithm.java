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
            // 保存公钥  
            FileOutputStream f1 = new FileOutputStream("pubkey.dat");
            ObjectOutputStream b1 = new ObjectOutputStream(f1);
            b1.writeObject(pbkey);
            // 保存私钥  
            FileOutputStream f2 = new FileOutputStream("privatekey.dat");
            ObjectOutputStream b2 = new ObjectOutputStream(f2);
            b2.writeObject(prkey);
        } catch (Exception e) {
        }
    }

    public static String encrypt(String plaintext) throws Exception {

        // 获取公钥及参数e,n  
        String s = plaintext;
        FileInputStream f = new FileInputStream("pubkey.dat");  //获取公钥
        ObjectInputStream b = new ObjectInputStream(f);  //获取公钥
        RSAPublicKey pbk = (RSAPublicKey) b.readObject();  //获取公钥
        BigInteger e = pbk.getPublicExponent();
        BigInteger n = pbk.getModulus();
        // System.out.println("e= " + e);
        //System.out.println("n= " + n);  
        // 获取明文m  
        byte[] ptext = s.getBytes(StandardCharsets.UTF_8);
        BigInteger m = new BigInteger(ptext);
        // 计算密文c  
        BigInteger c = m.modPow(e, n);
        // System.out.println("c= " + c);
        // 保存密文  
        String cs = c.toString();
        BufferedWriter out =
                new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream("encrypt.dat")));
        out.write(cs, 0, cs.length());
        out.close();
        return cs;
    }

    public static String decrypt(String ciphertext) throws Exception {
        //读取密文
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(new FileInputStream("encrypt.dat")));
        String ctext = in.readLine();

        BigInteger c = new BigInteger(ctext);
        // 读取私钥  
        FileInputStream f = new FileInputStream("privatekey.dat");
        ObjectInputStream b = new ObjectInputStream(f);
        RSAPrivateKey prk = (RSAPrivateKey) b.readObject();
        BigInteger d = prk.getPrivateExponent();
        // 获取私钥参数及解密  
        BigInteger n = prk.getModulus();
        //System.out.println("d= " + d);  
        //System.out.println("n= " + n);  
        BigInteger m = c.modPow(d, n);
        // 显示解密结果  
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
