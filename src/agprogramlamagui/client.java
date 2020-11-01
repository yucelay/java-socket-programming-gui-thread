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
public class client {
   static String gelenHash;
     public static void main(String[] args) throws IOException {
                    int iteration=0;
              System.out.println("Server a baglanmak icin E tusuna");
              System.out.println("Servera HASH istegi gondermek icin H tusuna basın");
              System.out.print("Seciminiz : ");
              
             Scanner oku=new Scanner(System.in);
              try{
              
              String okunan=oku.next(); 
                  if ("H".equalsIgnoreCase(okunan)) {
                      System.out.println("seciminiz E");
                      islem();
                  }else if("E".equalsIgnoreCase(okunan)){
                      System.out.println("seciminiz H");
                     
                  }
              }catch(Exception e){
                 // System.out.println(e);
              }
              
    
            

                      
        File file=new File("C://wordList/wordListClient.txt");
          try {
          client cn=new client();
          Scanner read=new Scanner(file);
          while (read.hasNextLine()) {
              String temp=read.nextLine();
              String tempHash=cn.md5(temp);
              if (gelenHash.equals(tempHash)) {
                  System.out.println("parola "+iteration+ " denemede bulundu ");
                  System.out.println("HASH :"+tempHash+" = "+temp);
                  break;
              }
              System.out.println(gelenHash+" != "+tempHash);
              iteration++;
         } }catch(Exception e){
             // System.out.println(e);
          } 
                      
             
     
     }
 
     public static void islem() throws UnknownHostException, IOException {
          Socket socket = null;
          PrintWriter out = null;
          BufferedReader in = null;
          String deger;

          try {
               //* server 'a localhost ve 7755 portu üzerinden başlantı sağlanıyor *//
               socket = new Socket("localhost", 7750);
          } catch (Exception e) {
               System.out.println("Port Hatası!");
          }
          
          
          //* Server'a veri gönderimi için kullandığımız PrintWriter nesnesi oluşturduk *//
          out = new PrintWriter(socket.getOutputStream(), true);
 
          //* Server'dan gelen verileri tutan BufferedReader nesnesi oluşturulur *//
          in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
         //System.out.print("Client - Server'a hash isteği ");
 
          //* Gönderilecek sayının girişi yapılıyor *//
          BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
          
          
                gelenHash=in.readLine();
               System.out.println("Server'dan gelen HASH = " + gelenHash);
               /*
          if((deger = data.readLine()) != null) {
               //out.println(deger);
              // System.out.print("Client - Server'a gönderilecek saysı giriniz");
          }*/
          out.close();
          in.close();
          data.close();
          socket.close();
          
          

          
     }
     
     
     
     

      
      private String md5(String s) {
    try {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(s.getBytes(), 0, s.length());
        BigInteger i = new BigInteger(1,m.digest());
        return String.format("%1$032x", i);         
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
    return null;
}  
      
      
     
     
}
