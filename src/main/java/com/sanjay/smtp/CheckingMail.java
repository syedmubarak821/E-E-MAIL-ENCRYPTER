package com.sanjay.smtp;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import com.sanjay.smtp.Encryption;

import java.util.*;
public class CheckingMail {
 
    /**
     * @param args
     */
    static String subjectm="";
    static String bodym="";
	private Cipher cipher;
	byte[] DecryptedKey;
	
	public PrivateKey getPrivateKeyDB(int id) throws Exception {
		//here will be the private key that will be fetch from databasae
		Class.forName("com.mysql.cj.jdbc.Driver");
		cipher=Cipher.getInstance("RSA");
		Connection con = DriverManager.getConnection("JDBC:mysql:///user","root","");
		PreparedStatement stmt=con.prepareStatement("select * from keystore where key_id=?"); 
		stmt.setInt(1, id);
		ResultSet rs=stmt.executeQuery();
		byte[] keyBytes = rs.getBytes(3);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		con.close();
		return kf.generatePrivate(spec);
	}
	
	public void decryptFile(byte[] input, PrivateKey key) 
			throws IOException, GeneralSecurityException {
			this.cipher.init(Cipher.DECRYPT_MODE, key);
			DecryptedKey = this.cipher.doFinal(input);
		}
    
    public static void receiveMail(String Email,String password) throws Exception {
        Scanner scn = new Scanner(System.in);
        Properties props = new Properties();
        		
        props.put("mail.smtp.host", "outlook.office365.com");
		props.put("mail.smtp.port", "993");
//        props.put("mail.smtp.port", "465");
		props.put("mail.smtp.tls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "993");
		
        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore("imaps");
        try{
            store.connect("outlook.office365.com", Email,password);
            System.out.println(store);
     
            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_WRITE); // Folder.READ_ONLY
            int messageCount = inbox.getMessageCount();
            System.out.println("Total Messages " + messageCount);
            int startMessage = messageCount - 5;
            int endMessage = messageCount;
    
            if (messageCount < 5) {
                startMessage = 0;
            }
    
            Message[] messages = inbox.getMessages(startMessage, endMessage);
            Message message = messages[messages.length-1];
            String content= message.getContent().toString();
            System.out.println("Hello"+content);
            System.out.println(message.getSubject());
            subjectm = message.getSubject();
//            int sizeOfByte = 16;
//            int emhalflength = (content.length()/2)-((sizeOfByte/2)+1);
//            String dkeyvalue = content.substring(emhalflength,emhalflength+sizeOfByte);
//            // System.out.println(dkeyvalue);
//            StringBuffer result = new StringBuffer();
//            int s=22;
//            for (int i=0; i<dkeyvalue.length(); i++){
//                if ((dkeyvalue.charAt(i)>='A' && dkeyvalue.charAt(i)<='Z') || (dkeyvalue.charAt(i)>='a' && dkeyvalue.charAt(i)<='z')){
//                    if (Character.isUpperCase(dkeyvalue.charAt(i))){
//                        char ch = (char)(((int)dkeyvalue.charAt(i) + s - 65) % 26 + 65);
//                        result.append(ch);
//                    }
//                    else
//                    {
//                        char ch = (char)(((int)dkeyvalue.charAt(i) + s - 97) % 26 + 97);
//                        result.append(ch);
//                    }
//                }else {
//                    result.append(dkeyvalue.charAt(i));
//                }
//            }
//            String resultkey = result.toString();
            // System.out.println(resultkey); .substring(0,emhalflength) + content.substring(emhalflength+sizeOfByte,content.length())
            String msg = content; 
            // System.out.println(msg);
            
            
            //fetch from db private key and symmtric key then decrypt it then send as par
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("JDBC:mysql:///user","root","");
            String quer="Select symmetrickey from email where email = ?";
            PreparedStatement ps=con.prepareStatement(quer);
            ps.setString(1, Email);
            ResultSet rs=ps.executeQuery();
            if(!rs.next()) {
            	System.out.println("User Doesnot Exist");
            }
            else {
            byte[] symmetricKey=rs.getBytes(1);
            CheckingMail c=new CheckingMail();
            quer="Select key_id from users where email = ?";
            ps=con.prepareStatement(quer);
            ps.setString(1, Email);
            rs=ps.executeQuery();
            int id=rs.getInt(1);
            System.out.println("ID: "+id);
            PrivateKey p=c.getPrivateKeyDB(rs.getInt(1));
            System.out.println("BeforeKey");
            c.decryptFile(symmetricKey, p);
            Encryption enc = new Encryption(c.DecryptedKey);
            Key key = enc.generateKey();
            String decryptedvalue = enc.decrypt(msg, key);
            System.out.print("Decrypted value is : ");
            System.out.println(decryptedvalue);
            bodym=decryptedvalue;
            inbox.close(true);
            System.out.println("Done....");
            store.close();
            scn.close();
            con.close();
            }
        }catch(Exception e){
            System.out.println("Invalid Inputs "+e );
        }
        }

		public static String subject(){
            if (subjectm==""){
                return "NULL";
            }else {
                return subjectm;
            }
        }
        public static String body(){
            if (bodym==""){
                return "NULL";
            }else {
                return bodym;
            }
        }
}