package com.sanjay.smtp.GUI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class PublicPrivatekeys{

	private KeyPairGenerator keyGen;
	private KeyPair pair;
	private PrivateKey privateKey;
	private PublicKey publicKey;
	private static Connection con;
	
	public PublicPrivatekeys(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException, SQLException {
			this.keyGen = KeyPairGenerator.getInstance("RSA");
			this.keyGen.initialize(keylength);
		
	}

	public void createKeys() {
		this.pair = this.keyGen.generateKeyPair();
		this.privateKey = pair.getPrivate();
		this.publicKey = pair.getPublic();
	}

	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public PublicKey getPublicKey() {
		return this.publicKey;
	}

	
		
	public int generateKeys() throws SQLException, NoSuchProviderException, ClassNotFoundException {
		int id=0;
		this.createKeys();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("JDBC:mysql:///user","root","");
		PreparedStatement stmt=con.prepareStatement("insert into Keystore(public_key,private_key) values(?,?)",Statement.RETURN_GENERATED_KEYS);
		stmt.setBytes(1,this.getPublicKey().getEncoded());
		stmt.setBytes(2,this.getPrivateKey().getEncoded());
		int result = stmt.executeUpdate();
		if(1==result) {
			System.out.println("Success");
		}
		System.out.println(this.getPublicKey().getEncoded());
		ResultSet rs=stmt.getGeneratedKeys();
		if(rs.next()){
		id=rs.getInt(1);
		}
		con.close();
		System.out.println(id);
		return id;

	}

}
