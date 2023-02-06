package com.sanjay.smtp;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.xml.bind.DatatypeConverter;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Encryption {
	private static final String ALGORITHM = "AES";
	private static byte[] keyValue;
	private static final String RSA = "RSA";

	public Encryption(){
	}
	public Encryption(byte[] kv) {
		keyValue = kv;
	}
	public  Key generateKey() throws Exception {
	    Key key = new SecretKeySpec(keyValue, ALGORITHM);
//	    System.out.println(key);
	    return key;
	} 
	public String encrypt(String valueToEnc, Key key) throws Exception {
		 
	       Cipher cipher = Cipher.getInstance(ALGORITHM);
	       cipher.init(Cipher.ENCRYPT_MODE, key);
	 
	       byte[] encValue = cipher.doFinal(valueToEnc.getBytes());
	       byte[] encryptedByteValue = new Base64().encode(encValue);
//	       System.out.println("Encrypted Value :: " + new String(encryptedByteValue));
	 
	       return new String(encryptedByteValue);
	   }
		public  String decrypt(String encryptedValue, Key key) throws Exception {
	       // Key key = generateKey();
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, key);
	        byte[] decodedBytes = new Base64().decode(encryptedValue.getBytes());
	 
	        byte[] enctVal = cipher.doFinal(decodedBytes);
	        
//	        System.out.println("Decrypted Value :: " + new String(enctVal));
	        return new String(enctVal);
	    }
		
		
		public static byte[ ] getSHA( String input ) throws NoSuchAlgorithmException  
	    {   
	        // Static getInstance method is called with hashing SHA  
	        // Make sure you pass "SHA-256" without any spaces   
	        // and just a '-' in between properly   
	        MessageDigest md = MessageDigest.getInstance( "SHA-256" ) ;   
	    
	        // To caculate message digest of an input  
	        // digest( ) method is called  
	        // which returns an array of bytes  
	        return md.digest( input.getBytes( StandardCharsets.UTF_8 ) ) ;   
	    }  
		
		
	    public static String toHexString( byte[ ] hash )  
	    {  
	        // For converting byte array into signum representation   
	        BigInteger number = new BigInteger( 1, hash ) ;   
	        // For converting message digest into hex value   
	        StringBuilder hexString = new StringBuilder( number.toString( 16 ) ) ;   
	    
	        // Pad with leading zeros  
	        while ( hexString.length( ) < 32 )   
	        {   
	            hexString.insert( 0,  " 0 " ) ;   
	        }   
	        return hexString.toString( ) ;   
	    }
	    
	    
	    // Generating public and private keys
	    // using RSA algorithm.
	    public static KeyPair generateRSAKkeyPair() throws Exception{
	    	SecureRandom secureRandom = new SecureRandom();
	    	
	    	KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
	    	
	    	keyPairGenerator.initialize(2048, secureRandom);
	    	
	    	return keyPairGenerator.generateKeyPair();
	    }
	 // Encryption function which converts
	    // the plainText into a cipherText
	    // using private Key.
	    public static byte[] do_RSAEncryption(String plainText,PublicKey publicKey) throws Exception{
	        Cipher cipher = Cipher.getInstance(RSA);
	 
	        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	 
	        return cipher.doFinal(plainText.getBytes());
	    }
	    // Decryption function which converts
	    // the ciphertext back to the
	    // original plaintext.
	    public static String do_RSADecryption(byte[] cipherText,PrivateKey privateKey)throws Exception{
	        Cipher cipher = Cipher.getInstance(RSA);
	 
	        cipher.init(Cipher.DECRYPT_MODE,privateKey);
	        byte[] result = cipher.doFinal(cipherText);
	 
	        return new String(result);
	    }
	    
}
