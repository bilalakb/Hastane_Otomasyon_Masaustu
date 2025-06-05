package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Doctor;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoctorGUI extends JFrame {

	private JPanel w_pane;
	private static Doctor doctor = new Doctor();
	private JTable table_whour;
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DoctorGUI(Doctor doctor) throws SQLException {
		setBackground(Color.WHITE);
		
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0]="ID";
		colWhour[1]="Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		for(int i =0; i<doctor.getWhourList(doctor.getId()).size();i++) {
			
			whourData[0]= doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1]= doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
		
		
		
		setTitle("Hastane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 512);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, Sayın " + doctor.getName());
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 26, 285, 41);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış \r\n");
		btnNewButton.setBackground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnNewButton.setBounds(604, 26, 122, 41);
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBackground(Color.LIGHT_GRAY);
		w_tab.setBounds(10, 77, 716, 376);
		w_pane.add(w_tab);
		
		JPanel w_whour = new JPanel();
		w_whour.setBackground(Color.LIGHT_GRAY);
		w_tab.addTab("Çalışma Saatleri", null, w_whour, null);
		w_whour.setLayout(null);
		
		JDateChooser select_date = new JDateChooser();
		select_date.getCalendarButton().setBackground(Color.RED);
		select_date.setBackground(Color.BLACK);
		select_date.setBounds(161, 10, 130, 27);
		w_whour.add(select_date);
		
		JComboBox select_time = new JComboBox();
		select_time.setBackground(Color.ORANGE);
		select_time.setFont(new Font("Tahoma", Font.BOLD, 10));
		select_time.setModel(new DefaultComboBoxModel(new String[] {"10:00", "10:30", "11:00", "12:00", "12:30", "13:30", "14:00", "14:30", "15:00", "15:30"}));
		select_time.setBounds(301, 10, 58, 27);
		w_whour.add(select_time);
		
		JButton btn_addWhour = new JButton("Ekle");
		btn_addWhour.setBackground(Color.BLUE);
		btn_addWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";
				try {
					 date = sdf.format(select_date.getDate());
				} catch(Exception e2){
					
				}
				if(date.length()==0) {
					Helper.showMsg("Lütfen Geçerli Bir Tarih Giriniz !");
				}else {
					String time = " " + select_time.getSelectedItem().toString() +":00";
					String selectDate = date + time;
					try {
						boolean control = doctor.addWhour(doctor.getId(),doctor.getName(), selectDate);
						if(control) {
							Helper.showMsg("success");
							updateWhourModel(doctor);
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
		btn_addWhour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_addWhour.setBounds(369, 10, 69, 27);
		w_whour.add(btn_addWhour);
		
		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(0, 47, 691, 292);
		w_whour.add(w_scrollWhour);
		
		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);
		
		JButton btn_deleteWhour = new JButton("Sil");
		btn_deleteWhour.setBackground(Color.RED);
		btn_deleteWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if(selRow >=0) {
					String  selectRow = table_whour.getModel().getValueAt(selRow, 0).toString();
					int selID = Integer.parseInt(selectRow);
					boolean control;
					try {
						control = doctor.deleteWhour(selID);
						if(control) {
							Helper.showMsg("success");
							updateWhourModel(doctor);
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else {
					Helper.showMsg("Lütfen Bir Tarih Seçiniz! ");
				}
			}
		});
		btn_deleteWhour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_deleteWhour.setBounds(448, 10, 69, 27);
		w_whour.add(btn_deleteWhour);
	}
	
	public void updateWhourModel(Doctor doctor) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
	for(int i =0; i<doctor.getWhourList(doctor.getId()).size();i++) {
			whourData[0]= doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1]= doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
	}
}
