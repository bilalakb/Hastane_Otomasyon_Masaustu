package view;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastatc;
	private JTextField fld_doctorTc;
	private JButton btn_hastaLogin;
	private JButton btn_doctorLogin;
	private JPasswordField fld_doctorPass;
	private DBConnection conn = new DBConnection();
	private JPasswordField fld_hastaPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 415);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("hastane logosu.png")));
		lbl_logo.setBounds(206, 10, 75, 55);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Sopalı Devlet Hastanesine Hoşgeldiniz");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel.setBounds(88, 62, 312, 32);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setFont(new Font("Tahoma", Font.BOLD, 10));
		w_tabpane.setBackground(Color.LIGHT_GRAY);
		w_tabpane.setBounds(38, 106, 411, 228);
		w_pane.add(w_tabpane);
		
		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(Color.LIGHT_GRAY);
		w_tabpane.addTab("Hasta Girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel lblKimlikNumaranz = new JLabel("T.C. Kimlik No:\r\n");
		lblKimlikNumaranz.setBackground(Color.BLACK);
		lblKimlikNumaranz.setForeground(Color.BLACK);
		lblKimlikNumaranz.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblKimlikNumaranz.setBounds(10, 20, 117, 41);
		w_hastaLogin.add(lblKimlikNumaranz);
		
		JLabel lblNewLabel_1_1 = new JLabel("Şifre:\r\n\r\n");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(10, 77, 49, 41);
		w_hastaLogin.add(lblNewLabel_1_1);
		
		fld_hastatc = new JTextField();
		fld_hastatc.setForeground(Color.BLACK);
		fld_hastatc.setBackground(Color.WHITE);
		fld_hastatc.setFont(new Font("Tahoma", Font.BOLD, 18));
		fld_hastatc.setBounds(163, 20, 226, 36);
		w_hastaLogin.add(fld_hastatc);
		fld_hastatc.setColumns(10);
		
		JButton btn_register = new JButton("Kayıt Ol\r\n");
		btn_register.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_register.setForeground(Color.BLACK);
		btn_register.setBackground(Color.BLUE);
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setBounds(10, 141, 166, 50);
		w_hastaLogin.add(btn_register);
		
		btn_hastaLogin = new JButton("Giriş\r\n");
		btn_hastaLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_hastaLogin.setForeground(Color.BLACK);
		btn_hastaLogin.setBackground(Color.BLUE);
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_hastatc.getText().length()==0 || fld_hastaPass.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					boolean key = true;
					try {
						Connection con = conn.connDb(); 
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_hastatc.getText().equals(rs.getString("tcno")) && fld_hastaPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword("password");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key = false;
								}
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(key) {
						Helper.showMsg("Böyle Bir Hasta Bulunamadı Lütfen Kayıt Olunuz! ");
					}
				}
				
				
			}
		});
		btn_hastaLogin.setBounds(223, 141, 166, 50);
		w_hastaLogin.add(btn_hastaLogin);
		
		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setForeground(Color.BLACK);
		fld_hastaPass.setBackground(Color.WHITE);
		fld_hastaPass.setBounds(163, 77, 226, 36);
		w_hastaLogin.add(fld_hastaPass);
		
		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Girişi", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);
		
		JPanel w_hastaLogin_1 = new JPanel();
		w_hastaLogin_1.setLayout(null);
		w_hastaLogin_1.setBackground(Color.LIGHT_GRAY);
		w_hastaLogin_1.setBounds(0, 0, 458, 202);
		w_doctorLogin.add(w_hastaLogin_1);
		
		JLabel lblKimlikNumaranz_1 = new JLabel("T.C. Kimlik No:\r\n");
		lblKimlikNumaranz_1.setForeground(Color.BLACK);
		lblKimlikNumaranz_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblKimlikNumaranz_1.setBounds(15, 25, 118, 41);
		w_hastaLogin_1.add(lblKimlikNumaranz_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Şifre:\r\n\r\n");
		lblNewLabel_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_1_1_1.setBounds(16, 84, 49, 41);
		w_hastaLogin_1.add(lblNewLabel_1_1_1);
		
		fld_doctorTc = new JTextField();
		fld_doctorTc.setForeground(Color.BLACK);
		fld_doctorTc.setBackground(Color.WHITE);
		fld_doctorTc.setFont(new Font("Tahoma", Font.BOLD, 18));
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(161, 25, 226, 36);
		w_hastaLogin_1.add(fld_doctorTc);
		
		btn_doctorLogin = new JButton("Giriş\r\n");
		btn_doctorLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_doctorLogin.setForeground(Color.BLACK);
		btn_doctorLogin.setBackground(Color.BLUE);
		btn_doctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(fld_doctorTc.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					 Helper.showMsg("fill");
				 }else {
					try {
						Connection con = conn.connDb(); 
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_doctorTc.getText().equals(rs.getString("tcno")) && fld_doctorPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("bashekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword("password");
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}
								if(rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
									
								}
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
				 
			}
		});
		btn_doctorLogin.setBounds(15, 135, 372, 57);
		w_hastaLogin_1.add(btn_doctorLogin);
		
		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setFont(new Font("Tahoma", Font.BOLD, 10));
		fld_doctorPass.setForeground(Color.BLACK);
		fld_doctorPass.setBackground(Color.WHITE);
		fld_doctorPass.setBounds(161, 84, 226, 36);
		w_hastaLogin_1.add(fld_doctorPass);
	}
}
