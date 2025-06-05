package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;
import Model.user;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private Hasta hasta = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterGUI() {
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBackground(Color.LIGHT_GRAY);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 10, 71, 23);
		w_pane.add(lblNewLabel_1);
		
		fld_name = new JTextField();
		fld_name.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 18));
		fld_name.setForeground(Color.BLACK);
		fld_name.setColumns(10);
		fld_name.setBounds(10, 43, 266, 32);
		w_pane.add(fld_name);
		
		fld_tcno = new JTextField();
		fld_tcno.setFont(new Font("Tahoma", Font.BOLD, 18));
		fld_tcno.setForeground(Color.BLACK);
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 123, 266, 32);
		w_pane.add(fld_tcno);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Şifre");
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(10, 166, 45, 23);
		w_pane.add(lblNewLabel_1_1_1);
		
		fld_pass = new JPasswordField();
		fld_pass.setForeground(Color.BLACK);
		fld_pass.setFont(new Font("Tahoma", Font.PLAIN, 10));
		fld_pass.setBounds(10, 199, 266, 32);
		w_pane.add(fld_pass);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.setBackground(Color.BLUE);
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_tcno.getText().length()==0 || fld_pass.getText().length()==0 || fld_name.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					try {
						boolean control = hasta.register(fld_tcno.getText(),fld_pass.getText(),fld_name.getText());
						if(control) {
							Helper.showMsg("success");
							LoginGUI login = new LoginGUI();
							login.setVisible(true);
							dispose();
						}else {
							Helper.showMsg("error");
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_register.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_register.setBounds(10, 241, 111, 32);
		w_pane.add(btn_register);
		
		JButton btn__backto = new JButton("Geri Dön");
		btn__backto.setBackground(Color.BLUE);
		btn__backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn__backto.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn__backto.setBounds(155, 241, 121, 32);
		w_pane.add(btn__backto);
		
		JLabel lblNewLabel_1_1 = new JLabel("T.C NO");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 90, 71, 23);
		w_pane.add(lblNewLabel_1_1);
	}
}
