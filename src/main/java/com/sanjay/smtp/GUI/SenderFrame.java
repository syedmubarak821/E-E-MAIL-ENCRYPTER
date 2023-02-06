package com.sanjay.smtp.GUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SenderFrame extends JFrame implements  ActionListener{
    JButton button;
    JTextArea message;
    JTextField to,key,subject;
    JLabel LTo,LFrom,LPass,Lmessage,LUKey,Lsubject;
    String username,password,email;
    public SenderFrame(String uname){
        this.getContentPane().setBackground(new Color(229,204,255));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TMS");
        username=uname;
        this.setLayout(new FlowLayout());
        LTo = new JLabel();
        LTo.setText("To ");
        to  = new JTextField();
        to.setPreferredSize(new Dimension(350,40));
        this.add(LTo);
        this.add(to);

        Lsubject = new JLabel();
        Lsubject.setText("Subject ");
        subject  = new JTextField();
        subject.setPreferredSize(new Dimension(350,40));
        this.add(Lsubject);
        this.add(subject);

        
        
        Lmessage = new JLabel();
        Lmessage.setText("Message ");
        message  = new JTextArea(10,30);
        JScrollPane scrollPane = new JScrollPane(message);
        this.add(Lmessage);
        this.add(message);
        this.add(scrollPane);

        LUKey = new JLabel();
        LUKey.setText("UKey ");
        key  = new JTextField();
        key.setPreferredSize(new Dimension(350,40));
        this.add(LUKey);
        this.add(key);

        

        button = new JButton("Send");
        button.addActionListener(this);
        this.add(button);
        
        this.setBackground(new Color(123,45,200));
        // this.setResizable(false);
        this.setMinimumSize(new Dimension(390,600));
        // this.setSize(390, 500);
        this.setVisible(true);

        // this.pack();
    }

    public void actionPerformed(ActionEvent e) {
        if (key.getText().length()!=16){
            JOptionPane.showMessageDialog(null, "Key should be of 16 byte length");
        }
        if (e.getSource()==button){
        	

				user u=new user();
				String mto = to.getText();
				String msubj = subject.getText();
				String mmessage = message.getText();
				String mkey = key.getText();
				try{
					u.send_mail(username,mto,msubj,mmessage,mkey);
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				
			} 

        }
    }   

