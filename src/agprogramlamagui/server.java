/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agprogramlamagui;

/**
 *
 * @author yucel
 */
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author yucel
 */
public class server {

    public static void main(String[] args) throws IOException {
        String clientGelen;
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        String parola = "";
        String hash = "";
        int random = (int) (Math.random() * 20000); 
        //System.out.println("random :" + random);
        int num = 0;
        File file = new File("C://wordList/wordListServer.txt");
        try {
            Scanner read = new Scanner(file);
            while (read.hasNextLine()) {

                if (num == random) {
                    parola = read.nextLine();
                    break;
                } else {
                    read.nextLine();
                    num++;
                }
            }
            read.close();
            // System.out.println("parola :"+parola);
            server ns = new server();
            hash = ns.md5(parola);
            System.out.println("Hash   :" + hash);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int sayi;
        try {
            serverSocket = new ServerSocket(7750);
        } catch (Exception e) {
            System.out.println("Port Hatası!");
        }
        System.out.println("SERVER BAGLANTI İÇİN HAZIR...");
        clientSocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out.println(hash);
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();

        String clientGelen2;
        ServerSocket serverSocket2 = null;
        Socket clientSocket2 = null;
        try {
            serverSocket2 = new ServerSocket(7751);
        } catch (Exception e) {
            System.out.println("Port Hatası!7751");
        }
       // System.out.println("SERVER BAGLANTI İÇİN HAZIR...22");
        clientSocket2 = serverSocket2.accept();
        PrintWriter out2 = new PrintWriter(clientSocket2.getOutputStream(), true);
        BufferedReader in2 = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));
        while (true) {
            clientGelen2 = in2.readLine();
            if (!(clientGelen2 == null || clientGelen2.equals(""))) {

                if (parola.equals(clientGelen2)) {
                    System.out.println("Sifreniz Dogru");
                } else {
                    System.out.println("Sifreniz Yanlis!!! : "+clientGelen2);
                }
            }
        }
    }

    private String md5(String s) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(), 0, s.length());
            BigInteger i = new BigInteger(1, m.digest());
            return String.format("%1$032x", i);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    void main() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
