package com.sanjay.smtp.GUI;


import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceiverFrame extends JFrame implements ActionListener{
    JTextArea message;
    JButton button;
    JTextField email,subject;
    JPasswordField Password;
    JLabel Lmail,LPass,Lmessage,Lsubject;
    String username;
    public ReceiverFrame(String uname){
        username=uname;
        this.getContentPane().setBackground(new Color(229,204,255));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TMS");
        this.setLayout(new FlowLayout());


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

        // Lmessage = new JLabel();
        // Lmessage.setText("Message ");
        // message  = new JTextField();
        // message.setPreferredSize(new Dimension(350,40));
        // this.add(Lmessage);
        // this.add(message);

        button = new JButton("Receive");
        button.addActionListener((ActionListener) this);
        this.add(button);
        this.setResizable(false);
        this.setSize(390, 500);
        this.setVisible(true);

        // this.pack();
    }
    
 
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button){
            user u=new user();
        	CheckingMail mail=new CheckingMail();
        	mail=u.mailreciever(username);
        	message.setText(mail.body());
            subject.setText(mail.subject());
        	}
        }
}
    
    
