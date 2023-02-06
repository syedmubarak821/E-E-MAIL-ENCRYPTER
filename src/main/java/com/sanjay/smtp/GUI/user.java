package com.sanjay.smtp.GUI;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.sql.ResultSet;

@SuppressWarnings("unused")
public class user   {
	private String password="";
	private String email="";
	JFrame f;
	boolean check=true;
	public int validate_user(String uname,String pass,JFrame frame) throws Exception {
		password=pass;
		String quer="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		int i=0;
		Connection con = DriverManager.getConnection("JDBC:mysql:///user","root","");
		if(con != null) {
			System.out.println("Connection Successful ");
			quer="Select * from Users";
			Encryption e= new Encryption();
			password=e.toHexString(e.getSHA(password));
			System.out.println(i + "Time" + password);
			Statement stm=con.createStatement();
			ResultSet res=stm.executeQuery(quer);
			while (res.next()) {
				if(res.getString(1).equals(uname)) {
					if(!( res.getString(2).equals(password))) {
						f=new JFrame();
						JOptionPane.showMessageDialog(f, "Password Incorrect");
						check=false;
						break;
					}
				}
				if(res.getString(1).equals(uname) && res.getString(2).equals(password)) {
					String adm="admin";
					if(uname.equals("admin")&&password.equals("youcantguess")) {
						f=new JFrame();
						JOptionPane.showMessageDialog(f, "Howdy Admin");
						check=false;
						con.close();
						frame.setVisible(false);
						adminInterface admin = new adminInterface();
						break;
					}
						
					System.out.println("Welcome to System Mr/Ms " + uname);
					email=res.getString(4);
					Interface inter=new Interface(uname);
					frame.setVisible(false);
					check=false;
					con.close();
					break;
				}
			}
			if(check) {
				f=new JFrame();
				JOptionPane.showMessageDialog(f, "Please SignUp");
				
			}

		}
		else {
			System.out.println("Connection Unsuccessful");
			System.exit(0);
		}
		
		return 0;
		
	}
	//send_mail Starts
	public void send_mail(String username,String mto, String msubj, String mmessage, String mkey) throws Exception{
		String password = null,email = null;
		try {
			String quer="Select * from users where username=?";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("JDBC:mysql:///user","root","");
			if(con!=null) {
				PreparedStatement ps= con.prepareStatement(quer);
				ps.setString(1, username);
				ResultSet result=ps.executeQuery();
				if(result.next()) {
					password=result.getString(10);
					System.out.println(password);
					email=result.getString(3);
				}
				con.close();
			}
			else {
				System.exit(0);
			}
		}
		
		catch(Exception e) {
				System.out.println(e.getStackTrace());
		}
		char[] pass = password.toCharArray();
		StringBuffer mypass = new StringBuffer(); 
		
		for (int i=0;i<pass.length;i++){
            // System.out.print(pass[i]);
            mypass.append(pass[i]);
        }
        // System.out.println();
        String fmypass = mypass.toString();
        try {
            int status = App.sendEmail(mmessage, msubj, mto, email, fmypass, mkey);
            if (status==1){
                JOptionPane.showMessageDialog(null, "Succesfully Sent");
            }else if (status==0){
                JOptionPane.showMessageDialog(null, "UnSuccesfull");
            }else {
                return;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

	}
	public CheckingMail mailreciever(String username) {
		System.out.println(username);
		String quer="Select * from users where username=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("JDBC:mysql:///user","root","");
			if(con!=null) {
				PreparedStatement ps= con.prepareStatement(quer);
				ps.setString(1, username);
				ResultSet result=ps.executeQuery();
				if(result.next()) {
					password=result.getString(10);
					email=result.getString(3);
					System.out.println(password+email);
				}
				
			con.close();}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		char[] pass = password.toCharArray();
		
		CheckingMail c=new CheckingMail();
		StringBuffer mypass = new StringBuffer(); 
        for (int i=0;i<pass.length;i++){
            mypass.append(pass[i]);
        }
        String fmypass = mypass.toString();
        try {
            c.receiveMail(email,fmypass);
            return c;
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return c;

    }

	public void register(String name, String pass, String email, int age, String cnic, String dob, String address,String epass) throws Exception {
		// TODO Auto-generated method stub
		String quer="";
		Boolean check=true;
		final String INSERT_USER_QUERY = "INSERT INTO USERS(username,password,email,age,cnic,dateofbirth,address,key_id,epass) VALUES(?,?,?,?,?,?,?,?,?)";
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("JDBC:mysql:///user","root","");
		if(con != null) {
			System.out.println("Connection Successful ");
			quer="Select * from Users";
			Statement stm=con.createStatement();
			ResultSet res=stm.executeQuery(quer);
			while (res.next()) {
				if(res.getString(1).equals(name) || res.getString(3).equals(email)) {
					f=new JFrame();  
				    JOptionPane.showMessageDialog(f,"Email or UserName ALready Exsist."); 
					System.out.println("Email or UserName ALready Exsist");
				    check=false;
					break;
				}
				
			}
			if(check && ((!name.isEmpty()) || (!email.isEmpty()) || (!pass.isEmpty()))) {
				PreparedStatement ps=con.prepareStatement(INSERT_USER_QUERY);
				PublicPrivatekeys p=new PublicPrivatekeys(1024);
				int id=p.generateKeys();
				ps.setString(1, name);
				ps.setString(2, pass);
				ps.setString(3, email);	
				ps.setInt(4, age);
				ps.setString(5, cnic);
				ps.setString(6, dob);	
				ps.setString(7, address);
				ps.setInt(8, id);
				ps.setString(9, epass);
				int result = ps.executeUpdate();
				ps =con.prepareStatement("insert into email(email) values (?)");
				ps.setString(1,email);
				ps.executeUpdate();
				f=new JFrame();  
			    JOptionPane.showMessageDialog(f,"Hey "+name+" Your Account has been Registered");
				System.out.println("Registered");
			}
			else {
				f=new JFrame();  
			    JOptionPane.showMessageDialog(f,"Some/All Fields Are Empty");
			}
			con.close();
				
			
			
		}
		else {
			System.out.println("Connection Unsuccessful");
			System.exit(0);
		}

		
	}

	public void update_user(String username,String email, String age, String cnic, String dob, String address,String password) throws Exception {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		PreparedStatement ps = null;
		String quer="";
		Connection con = DriverManager.getConnection("JDBC:mysql:///user","root","");
		if(con != null) {
			System.out.println("Connection Successful ");
			
			quer="Update Users set email=?,password=?,age=?,cnic=?,dateofbirth=?,address=? where username=?";
			PreparedStatement UpdateUser= con.prepareStatement(quer);
			UpdateUser.setString(1, email);
			UpdateUser.setString(2, password);
			UpdateUser.setInt(3, Integer.parseInt(age));
			UpdateUser.setString(4, cnic);
			UpdateUser.setString(5, dob);
			UpdateUser.setString(6, address);
			UpdateUser.setString(7, username);

			int resultupdate=UpdateUser.executeUpdate();
			if(resultupdate==1) {
				f=new JFrame();
				JOptionPane.showMessageDialog(f, "Your Profile has been updated");
			}
				
		}
		else {
			System.out.println("Connection Unsuccessful");
			System.exit(0);
		}
		
		
	}

}

