package com.sanjay.smtp.GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class Interface {

	private JFrame frame;
	private String username;
	/**
	 * Launch the application.
	 
	/**
	 * Create the application.
	 */
	public Interface(String uname) {
		username=uname;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() { 
		frame = new JFrame();
		frame.setBounds(100, 100, 1264, 555);
		frame.setContentPane(new JLabel(new ImageIcon("C:\\img1.jpg")));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Well Come To mail Encrypter");
		lblNewLabel.setForeground(SystemColor.activeCaptionBorder);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(557, 251, 477, 64);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Send Mail");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SenderFrame(username);
			}
		});
		btnNewButton.setBounds(617, 356, 110, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Recieve Mail");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ReceiverFrame(username);
			}
		});
		btnNewButton_1.setBounds(802, 356, 110, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update Profile");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateProfile p1=new updateProfile(username);
			}
		});
		btnNewButton_2.setBounds(700, 411, 117, 23);
		frame.getContentPane().add(btnNewButton_2);
	}
}
