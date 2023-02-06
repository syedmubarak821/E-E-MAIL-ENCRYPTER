package com.sanjay.smtp.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.SystemColor;


public class updateProfile {

	private JFrame frame;
	private JTextField txtemail;
	private JTextField txtage;
	private JTextField txtcnic;
	private JTextField txtdob;
	private JTextField txtaddress;
	private JLabel lblNewLabel;
	private JLabel pass;
	private JTextField txtpassword;
	private String username="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateProfile window = new updateProfile();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public updateProfile() {
		initialize();
	}
	public updateProfile(String uname) {
		username = uname;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.setBounds(100, 100, 1425, 755);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel(new ImageIcon("C:\\img1.jpg"));
		label.setForeground(SystemColor.inactiveCaptionBorder);
		frame.setContentPane(label);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		
		JLabel lblname = new JLabel("Email:");
		lblname.setForeground(SystemColor.inactiveCaptionBorder);
		lblname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblname.setBounds(650, 326, 147, 17);
		frame.getContentPane().add(lblname);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(807, 326, 132, 20);
		frame.getContentPane().add(txtemail);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setForeground(SystemColor.inactiveCaptionBorder);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAge.setBounds(650, 354, 147, 29);
		frame.getContentPane().add(lblAge);
		
		txtage = new JTextField();
		txtage.setColumns(10);
		txtage.setBounds(807, 366, 132, 20);
		frame.getContentPane().add(txtage);
		
		txtcnic = new JTextField();
		txtcnic.setColumns(10);
		txtcnic.setBounds(807, 397, 132, 20);
		frame.getContentPane().add(txtcnic);
		
		txtdob = new JTextField();
		txtdob.setColumns(10);
		txtdob.setBounds(807, 428, 132, 20);
		frame.getContentPane().add(txtdob);
		
		txtaddress = new JTextField();
		txtaddress.setColumns(10);
		txtaddress.setBounds(807, 459, 132, 20);
		frame.getContentPane().add(txtaddress);
		
		JLabel lbladdress = new JLabel("Address:");
		lbladdress.setForeground(SystemColor.inactiveCaptionBorder);
		lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbladdress.setBounds(650, 459, 147, 17);
		frame.getContentPane().add(lbladdress);
		
		JLabel Dob = new JLabel("Date Of Birth:");
		Dob.setForeground(SystemColor.inactiveCaptionBorder);
		Dob.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Dob.setBounds(650, 428, 147, 17);
		frame.getContentPane().add(Dob);
		
		JLabel lblCNIC = new JLabel("CNIC:");
		lblCNIC.setForeground(SystemColor.inactiveCaptionBorder);
		lblCNIC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCNIC.setBounds(650, 394, 147, 20);
		frame.getContentPane().add(lblCNIC);
		
		lblNewLabel = new JLabel("Update Profile");
		lblNewLabel.setForeground(SystemColor.activeCaptionBorder);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(660, 231, 205, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				user u=new user();
				Encryption encrypt=new Encryption();
				try {
					String email=txtemail.getText();
					String age=txtage.getText();
					String cnic=txtcnic.getText();
					String dateofbirth=txtdob.getText();
					String address=txtaddress.getText();
					String password=txtpassword.getText();
					password = encrypt.toHexString(encrypt.getSHA(password));
					String quer="Select * from users where username=?";
					Class.forName("com.mysql.cj.jdbc.Driver");
 
					Connection con = DriverManager.getConnection("JDBC:mysql:///user","root","");
					if(con!=null) {
						PreparedStatement ps= con.prepareStatement(quer);
						ps.setString(1, username);
						ResultSet result=ps.executeQuery();
						
						if(result.next()) {
							if(email.isEmpty())
								email=result.getString(3);
							if(age.isEmpty()) {
								int c = (result.getInt(4));
								age=Integer.toString(c);
							}
							if(cnic.isEmpty())
								cnic=result.getString(5);
							if(dateofbirth.isEmpty())
								dateofbirth=result.getString(6);			
							if(address.isEmpty())
								address=result.getString(7);
							if(password.isEmpty())
								password=result.getString(2);
							con.close();
							u.update_user(username,email,age,cnic,dateofbirth,address,password);
						}
						
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(739, 533, 126, 29);
		frame.getContentPane().add(btnNewButton);
		
		pass = new JLabel("Password:");
		pass.setForeground(SystemColor.inactiveCaptionBorder);
		pass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pass.setBounds(650, 491, 147, 17);
		frame.getContentPane().add(pass);
		
		txtpassword = new JTextField();
		txtpassword.setColumns(10);
		txtpassword.setBounds(807, 491, 132, 20);
		frame.getContentPane().add(txtpassword);
	}
	  
}
