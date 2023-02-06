package com.sanjay.smtp.GUI;



import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
// import java.security.cert.Extension;
import java.util.Properties;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sanjay.smtp.Encryption;

public class App 
{
	static Scanner scn = new Scanner(System.in);
	private Cipher cipher;
	byte[] EncryptedMessages;
	int id;
	// https://docs.oracle.com/javase/8/docs/api/java/security/spec/X509EncodedKeySpec.html
	public PublicKey getPublicKeyDB(String email) throws Exception {
		
		//here will be the querry to fetch the public key byte data
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("JDBC:mysql:///user","root","");
		PreparedStatement stmt=con.prepareStatement("select * from users where email=?"); 
		stmt.setString(1, email);
		ResultSet rs=stmt.executeQuery();
		if(rs.next()) {
			id=rs.getInt(9);
		}
		stmt=con.prepareStatement("select * from keystore where id=?"); 
		stmt.setInt(1, id);
		rs=stmt.executeQuery();
		byte[] keyBytes = null;
		if(rs.next()) {
			keyBytes = rs.getBytes(2);
		}
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		con.close();
		return kf.generatePublic(spec);
	}

	public void encryptFile(byte[] input, PublicKey key) 
		throws IOException, GeneralSecurityException {
//		cipher=new Cipher();
		cipher = Cipher.getInstance("RSA"); // changed
		System.out.println("Hi");
		System.out.println("key is "+key+" and "+Cipher.ENCRYPT_MODE);
		this.cipher.init(Cipher.ENCRYPT_MODE, key);
		EncryptedMessages = this.cipher.doFinal(input);
		//writeToFile(output, this.cipher.doFinal(input)); to be stored in the mail database
	}


	public static int sendEmail(String message, String subject, String to, final String from,final String password,String ourkeyvalue)throws Exception,IOException {
		Properties properties = System.getProperties();
		
		properties.put("mail.smtp.host", "mail.sbros.pk");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.tls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		

//		 Session Instantiation
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from,password);
			}
		});
		 
		// Compose message
		MimeMessage mail = new MimeMessage(session);
		
		mail.setFrom(from);
		mail.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		mail.setSubject(subject);	
		
		
		if (ourkeyvalue.length()!=16) {
			System.out.println("Length of UKey should be equal to 16 ");
			return -1;
		}

		
		Encryption enc = new Encryption(ourkeyvalue.getBytes());
		Key key = enc.generateKey();
		
		String encryptedMessage = enc.encrypt(message, key);
//		char[] a = encryptedMessage.toCharArray();
//		StringBuffer result = new StringBuffer();

//		int s =4;
//		for (int i=0; i<ourkeyvalue.length(); i++){
//			if ((ourkeyvalue.charAt(i)>='A' && ourkeyvalue.charAt(i)<='Z') || (ourkeyvalue.charAt(i)>='a' && ourkeyvalue.charAt(i)<='z')){
//				if (Character.isUpperCase(ourkeyvalue.charAt(i))){
//					char ch = (char)(((int)ourkeyvalue.charAt(i) + s - 65) % 26 + 65);
//					result.append(ch);
//				}
//				else
//				{
//					char ch = (char)(((int)ourkeyvalue.charAt(i) + s - 97) % 26 + 97);
//					result.append(ch);
//				}
//			}else {
//				result.append(ourkeyvalue.charAt(i));
//			}
//        }
//		char[] kvarray = result.toString().toCharArray();
//		
//		char[] temp = new char[encryptedMessage.length()+ourkeyvalue.length()];
//		int i=0;
//		int k=0;
//		for (i=0;i<encryptedMessage.length()/2;i++) {
//			temp[k++]=a[i];
//		}
//		int z=i;
//		for (int j=0;j<ourkeyvalue.length();j++) {
//			temp[k++]=kvarray[j];
//		}
//		for (int l=z;l<encryptedMessage.length();l++) {
//			temp[k++]=a[l];
//		}
		String mailtext = String.copyValueOf(encryptedMessage.toCharArray());
		System.out.print("Encrypted message : ");
		System.out.println(mailtext);
		// Set text
		mail.setText(mailtext);
		
		// Send message
		try{
			Transport.send(mail);
			System.out.println("Sent message succesfully.");
			App a=new App();
			PublicKey p=a.getPublicKeyDB(to);
			System.out.println("PublicKey: "+p);
			a.encryptFile(ourkeyvalue.getBytes(),p);
			Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("JDBC:mysql:///user","root","");
	        String quer="update email set symmetrickey=? where email=? ;";
	        PreparedStatement ps=con.prepareStatement(quer);
	        System.out.println("before");

	        ps.setBytes(1, a.EncryptedMessages);
	        ps.setString(2, to);
	        System.out.println("after");
	        ps.executeUpdate();
			return 1;
		}catch(Exception e){
			System.out.println("Unsuccesfull"+e);
			return 0;
		}

	}
}
