package com.sanjay.smtp.GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class viewUser extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField age;
	private JTextField dob;
	private JTextField cnic;
	private JTextField Address;
	private int i=0;
	private Boolean check=true;
	List<String> NAME=new ArrayList<String>(30);
	List<String> EMAIL=new ArrayList<String>(30);
	List<Integer> AGE=new ArrayList<Integer>(30);
	List<String> CNIC=new ArrayList<String>(30);
	List<String> DOB=new ArrayList<String>(30);
	List<String> ADDRESS=new ArrayList<String>(30);
	ListIterator<String> a;
	ListIterator<Integer> b;
	ListIterator<String> c;
	ListIterator<String> d;
	ListIterator<String> addre;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public viewUser(int i) {
		
	}
	public viewUser() throws Exception {
		get_data();
		JFrame frame = new JFrame();
		frame.setVisible(true);
		setBounds(100, 100, 416, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 374, 294);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("User", null, panel, null);
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("User");
		lblNewLabel.setBounds(10, 11, 81, 27);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(10, 49, 110, 14);
		panel.add(lblname);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(10, 77, 110, 14);
		panel.add(lblAge);
		
		JLabel lblCNIC = new JLabel("CNIC");
		lblCNIC.setBounds(10, 105, 110, 14);
		panel.add(lblCNIC);
		
		JLabel address = new JLabel("Address");
		address.setBounds(10, 161, 110, 14);
		panel.add(address);
		
		JLabel Dob = new JLabel("Date Of Birth");
		Dob.setBounds(10, 133, 110, 14);
		panel.add(Dob);
		
		Address = new JTextField();
		Address.setBounds(167, 158, 132, 20);
		panel.add(Address);
		Address.setEditable(false);
		Address.setColumns(10);
		
		dob = new JTextField();
		dob.setBounds(167, 130, 132, 20);
		panel.add(dob);
		dob.setEditable(false);
		dob.setColumns(10);
		
		cnic = new JTextField();
		cnic.setBounds(167, 102, 132, 20);
		panel.add(cnic);
		cnic.setEditable(false);
		cnic.setColumns(10);
		
		age = new JTextField();
		age.setBounds(167, 74, 132, 20);
		panel.add(age);
		age.setEditable(false);
		age.setColumns(10);
		
		name = new JTextField();
		name.setBounds(167, 46, 132, 20);
		panel.add(name);
		name.setEditable(false);
		name.setColumns(10);
		
		JButton btnshownext = new JButton("Show Next");
		btnshownext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(a.hasNext()) {
					name.setText(a.next());
					age.setText((b.next()).toString());
					cnic.setText((c.next()).toString());
					dob.setText((d.next()));
					Address.setText(addre.next());
				}
				System.out.println(a.hasNext());
			}
		});
		btnshownext.setBounds(167, 198, 132, 23);
		panel.add(btnshownext);
		
		JButton btnShowPrevious = new JButton("Show Previous");
		btnShowPrevious.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(a.hasPrevious()) {
					name.setText(a.previous());
					age.setText((b.previous()).toString());
					cnic.setText((c.previous()).toString());
					dob.setText((d.previous()));
					Address.setText(addre.previous());
				}}
		});
		btnShowPrevious.setBounds(10, 198, 132, 23);
		panel.add(btnShowPrevious);
		
	}
	void get_data() throws Exception{
		String quer="Select * from users";
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("JDBC:mysql:///user","root","");
		if(con != null) {
			System.out.println("Connection Successful ");
			Statement stm=con.createStatement();
			ResultSet res=stm.executeQuery(quer);
			while (res.next()) {
				NAME.add(res.getString(1));
				EMAIL.add(res.getString(3));
				AGE.add(Integer.parseInt(res.getString(4)));
				CNIC.add(res.getString(5));
				DOB.add(res.getString(6));
				ADDRESS.add(res.getString(7));
				System.out.print(NAME);
			}
			a=NAME.listIterator();
			b=AGE.listIterator();
			c=CNIC.listIterator();
			d=DOB.listIterator();
			addre=ADDRESS.listIterator();

			
		}
		else {
			System.out.println("Connection Unsuccessful");
			System.exit(0);
		}
	}
	
	public void userwindow() throws Exception{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewUser frame = new viewUser();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
