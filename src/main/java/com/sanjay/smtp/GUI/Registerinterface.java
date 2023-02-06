package com.sanjay.smtp.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Registerinterface {

	private JFrame frmUserRegistration;
	private JTextField username;
	private JTextField email;
	private JTextField password;
	private JTextField Address;
	private JTextField CNIC;
	private JTextField age;
	private JTextField dob;
	private JTextField Emailpassword;
	private JLabel label;
	
	public Registerinterface(JFrame frame) {
		frame.setVisible(false);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUserRegistration = new JFrame();
		frmUserRegistration.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmUserRegistration.setTitle("User Registration\r\n");
		frmUserRegistration.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmUserRegistration.setBounds(100, 100, 1425, 755);

		frmUserRegistration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUserRegistration.getContentPane().setLayout(null);
		
		label = new JLabel(new ImageIcon("C:\\img1.jpg"));
		label.setBackground(SystemColor.info);
		frmUserRegistration.setContentPane(label);
		frmUserRegistration.setExtendedState(JFrame.MAXIMIZED_BOTH);

		
		JLabel lblNewLabel = new JLabel("Enter Details To Create Account");
		lblNewLabel.setForeground(SystemColor.activeCaptionBorder);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(530, 168, 431, 99);
		frmUserRegistration.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User Name:");
		lblNewLabel_1.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(553, 268, 120, 22);
		frmUserRegistration.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email:");
		lblNewLabel_1_1.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(553, 301, 66, 22);
		frmUserRegistration.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Password:");
		lblNewLabel_1_2.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(553, 465, 102, 22);
		frmUserRegistration.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_4 = new JLabel("Email Password:");
		lblNewLabel_1_2_4.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_1_2_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2_4.setBounds(553, 510, 102, 22);
		frmUserRegistration.getContentPane().add(lblNewLabel_1_2_4);
		
		username = new JTextField();
		username.setBounds(699, 272, 174, 20);
		frmUserRegistration.getContentPane().add(username);
		username.setColumns(10);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(699, 305, 174, 20);
		frmUserRegistration.getContentPane().add(email);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(699, 467, 174, 20);
		frmUserRegistration.getContentPane().add(password);

		Emailpassword = new JTextField();
		Emailpassword.setColumns(10);
		Emailpassword.setBounds(699, 510, 174, 20);
		frmUserRegistration.getContentPane().add(Emailpassword);
		
		JLabel lblNewLabel_2 = new JLabel("* Please Make Sure the Password is same as of the email");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(564, 560, 420, 22);
		frmUserRegistration.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("else the mail won't be sent");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(574, 590, 199, 14);
		frmUserRegistration.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				user u=new user();
				Encryption encrypt=new Encryption();
				try {
					//key is is registered
					String pass=password.getText();
					pass = encrypt.toHexString(encrypt.getSHA(pass));
					u.register(username.getText(),pass,email.getText(),Integer.parseInt(age.getText()),CNIC.getText(),dob.getText(),Address.getText(),Emailpassword.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(666, 630, 107, 31);
		frmUserRegistration.getContentPane().add(btnNewButton);
		
		JButton btnAlreadyRegistered = new JButton("Already Registered?");
		btnAlreadyRegistered.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmUserRegistration.setVisible(false);
				userInterface u = new userInterface();
				u.setTrue();
			}
		});
		btnAlreadyRegistered.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAlreadyRegistered.setBounds(611, 660, 233, 41);
		frmUserRegistration.getContentPane().add(btnAlreadyRegistered);
		
		Address = new JTextField();
		Address.setColumns(10);
		Address.setBounds(699, 404, 174, 20);
		frmUserRegistration.getContentPane().add(Address);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Address:");
		lblNewLabel_1_2_1.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2_1.setBounds(553, 402, 102, 22);
		frmUserRegistration.getContentPane().add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("CNIC:");
		lblNewLabel_1_1_1.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(553, 367, 66, 22);
		frmUserRegistration.getContentPane().add(lblNewLabel_1_1_1);
		
		CNIC = new JTextField();
		CNIC.setColumns(10);
		CNIC.setBounds(699, 371, 174, 20);
		frmUserRegistration.getContentPane().add(CNIC);
		
		age = new JTextField();
		age.setColumns(10);
		age.setBounds(699, 338, 174, 20);
		frmUserRegistration.getContentPane().add(age);
		
		JLabel lblNewLabel_1_3 = new JLabel("Age:");
		lblNewLabel_1_3.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(553, 334, 120, 22);
		frmUserRegistration.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Date Of Birth:");
		lblNewLabel_1_2_1_1.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2_1_1.setBounds(553, 435, 102, 22);
		frmUserRegistration.getContentPane().add(lblNewLabel_1_2_1_1);
		
		dob = new JTextField();
		dob.setColumns(10);
		dob.setBounds(699, 437, 174, 20);
		frmUserRegistration.getContentPane().add(dob);
		frmUserRegistration.setVisible(true);
	}
	public JFrame getFrmUserRegistration() {
		return frmUserRegistration;
	}
}
