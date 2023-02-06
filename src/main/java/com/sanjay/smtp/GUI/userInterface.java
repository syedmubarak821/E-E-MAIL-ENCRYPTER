package com.sanjay.smtp.GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class userInterface extends JFrame implements  ActionListener{

	private JFrame frame;
	private JTextField username;
	private JPasswordField userPassword;

	/**
	 * Launch the application.


	/**
	 * Create the application.
	 */
	public userInterface() {
		initialize();
	}

	public void setTrue() {
		this.frame.setVisible(true);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(255, 255, 240));
		frame.setBounds(100, 100, 1264, 555);
		frame.setContentPane(new JLabel(new ImageIcon("C:\\img1.jpg")));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome To Mail Encrypter");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(587, 196, 395, 43);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sign In To Your Account");
		lblNewLabel_1.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(659, 250, 195, 31);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("User Name:");
		lblNewLabel_2.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(621, 316, 99, 22);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password:");
		lblNewLabel_2_1.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(621, 361, 99, 22);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		username = new JTextField();
		username.setBounds(730, 316, 167, 20);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		userPassword = new JPasswordField();
		userPassword.setBounds(730, 365, 167, 20);
		frame.getContentPane().add(userPassword);
		
		JButton signin = new JButton("Sign In");
		signin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				user u=new user();
				Encryption encrypt=new Encryption();
				try {
					String pass=userPassword.getText();
					u.validate_user(username.getText(),pass, frame);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
		});
		signin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		signin.setBounds(707, 411, 108, 31);
		frame.getContentPane().add(signin);
		signin.addActionListener(null);
		
		JButton btnNotAlreadyRegistered = new JButton("Click To Register!");
		btnNotAlreadyRegistered.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Registerinterface n=new Registerinterface(frame);
				
			}
		});
		btnNotAlreadyRegistered.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNotAlreadyRegistered.setBounds(659, 462, 213, 43);
		frame.getContentPane().add(btnNotAlreadyRegistered);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
