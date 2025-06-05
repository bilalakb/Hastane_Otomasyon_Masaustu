package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.clinic;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class UpdateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_clinicName;
	private static clinic Clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(Clinic);
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
	public UpdateClinicGUI(clinic Clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 150);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_4 = new JLabel("Polikinlik Adı");
		lblNewLabel_1_4.setForeground(Color.BLACK);
		lblNewLabel_1_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_4.setBounds(3, 0, 97, 23);
		contentPane.add(lblNewLabel_1_4);
		
		fld_clinicName = new JTextField();
		fld_clinicName.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 15));
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(0, 25, 201, 32);
		fld_clinicName.setText(Clinic.getName());
		contentPane.add(fld_clinicName);
		
		JButton btn_updateClinic = new JButton("Düzenle");
		btn_updateClinic.setBackground(Color.BLUE);
		btn_updateClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					try {
						Clinic.uptadeClinic(Clinic.getId(),fld_clinicName.getText());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_updateClinic.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_updateClinic.setBounds(0, 67, 201, 32);
		contentPane.add(btn_updateClinic);
	}

}
