package com.sanjay.smtp.GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class adminInterface {

	private JFrame frame;

		public adminInterface() {
		initialize();
	}

	/** 
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 1264, 555);
		frame.setContentPane(new JLabel(new ImageIcon("C:\\img1.jpg")));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("Admin Panel");
		lblNewLabel.setForeground(SystemColor.activeCaptionBorder);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(695, 309, 184, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnViewUser = new JButton("Click");
		btnViewUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					viewUser u1=new viewUser(1);
					u1.userwindow();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnViewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnViewUser.setBounds(888, 391, 89, 23);
		frame.getContentPane().add(btnViewUser);
		
		JLabel lblNewLabel_1 = new JLabel("View Registered User:");
		lblNewLabel_1.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(626, 391, 206, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnUpdateProfile = new JButton("Click");
		btnUpdateProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateProfile p=new updateProfile("admin");
				
			}
		});
		btnUpdateProfile.setBounds(888, 448, 89, 23);
		frame.getContentPane().add(btnUpdateProfile);
		
		JLabel lblNewLabel_1_1 = new JLabel("Update Admin Profile:");
		lblNewLabel_1_1.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(626, 448, 206, 23);
		frame.getContentPane().add(lblNewLabel_1_1);
	}
}
