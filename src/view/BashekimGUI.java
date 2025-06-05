package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Helper.*;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class BashekimGUI extends JFrame {

	clinic clinic = new clinic();
	static Bashekim bashekim = new Bashekim();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JPasswordField fld_dPass;
	private JTextField fld_doctorID;
	private JTextField fld_Tcno;
	private JTable table_doctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTable table_clinic;
	private JTextField fld_clinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JTable table_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public BashekimGUI(Bashekim bashekim) throws SQLException {
		setForeground(Color.WHITE);

		// doktor model
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC NO";
		colDoctorName[3] = "Şifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}

		// clinic model
		clinicModel = new DefaultTableModel();
		Object[] colclinic = new Object[2];
		colclinic[0] = "ID";
		colclinic[1] = "Polikinlik Adı";
		clinicModel.setColumnIdentifiers(colclinic);
		clinicData = new Object[2];
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);

		}
		
		//worker model
		
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0]="ID";
		colWorker[1]="Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];

		setTitle("Hastane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 813, 511);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, Sayın " + bashekim.getName());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setBackground(Color.YELLOW);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNewLabel.setBounds(216, 15, 332, 51);
		w_pane.add(lblNewLabel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.GRAY);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 10));
		tabbedPane.setBounds(28, 61, 750, 382);
		w_pane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Doktor Yönetimi", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(505, 10, 71, 23);
		panel.add(lblNewLabel_1);

		fld_dName = new JTextField();
		fld_dName.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 18));
		fld_dName.setForeground(Color.BLACK);
		fld_dName.setBackground(Color.WHITE);
		fld_dName.setBounds(505, 43, 196, 32);
		panel.add(fld_dName);
		fld_dName.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("T.C No");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(505, 76, 71, 23);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Şifre\r\n");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(505, 143, 71, 23);
		panel.add(lblNewLabel_1_2);

		fld_dPass = new JPasswordField();
		fld_dPass.setFont(new Font("Tahoma", Font.BOLD, 18));
		fld_dPass.setForeground(Color.BLACK);
		fld_dPass.setBackground(Color.WHITE);
		fld_dPass.setBounds(505, 176, 196, 32);
		panel.add(fld_dPass);

		JButton btn_addDoctor = new JButton("Ekle");
		btn_addDoctor.setBackground(Color.BLUE);
		btn_addDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_dName.getText().length() == 0 || fld_dPass.getText().length() == 0
						|| fld_Tcno.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = bashekim.addDoctor(fld_Tcno.getText(), fld_dPass.getText(),
								fld_dName.getText());
						if (control) {
							Helper.showMsg("success");
							fld_dName.setText(null);
							fld_Tcno.setText(null);
							fld_dPass.setText(null);
							updateDoctorModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_addDoctor.setBounds(505, 218, 196, 32);
		panel.add(btn_addDoctor);

		JLabel lblNewLabel_1_3 = new JLabel("Kullanıcı ID");
		lblNewLabel_1_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(505, 247, 90, 23);
		panel.add(lblNewLabel_1_3);

		fld_doctorID = new JTextField();
		fld_doctorID.setFont(new Font("Tahoma", Font.BOLD, 18));
		fld_doctorID.setForeground(Color.BLACK);
		fld_doctorID.setBackground(Color.WHITE);
		fld_doctorID.setBounds(505, 272, 196, 23);
		panel.add(fld_doctorID);
		fld_doctorID.setColumns(10);

		fld_Tcno = new JTextField();
		fld_Tcno.setFont(new Font("Tahoma", Font.BOLD, 18));
		fld_Tcno.setForeground(Color.BLACK);
		fld_Tcno.setBackground(Color.WHITE);
		fld_Tcno.setColumns(10);
		fld_Tcno.setBounds(505, 101, 196, 32);
		panel.add(fld_Tcno);

		JButton btn_delDoctor = new JButton("Sil");
		btn_delDoctor.setBackground(Color.RED);
		btn_delDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorID.getText().length() == 0) {
					Helper.showMsg("Lütfen Geçerli Bir Doktor Seçiniz");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_doctorID.getText());
						try {
							boolean control = bashekim.deleteDoctor(selectID);
							if (control) {
								Helper.showMsg("Success");
								fld_doctorID.setText(null);
								updateDoctorModel();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_delDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_delDoctor.setBounds(505, 307, 196, 32);
		panel.add(btn_delDoctor);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 10, 486, 329);
		panel.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		table_doctor.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		w_scrollDoctor.setViewportView(table_doctor);
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_doctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {

				}
			}
		});

		table_doctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectTcno = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectPass = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();

					try {
						boolean control = bashekim.uptadeDoctor(selectID, selectTcno, selectPass, selectName);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}

		});

		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Polikinlikler", null, w_clinic, null);
		w_clinic.setLayout(null);

		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(10, 10, 245, 329);
		w_clinic.add(w_scrollClinic);

		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				clinic selectClinic = clinic.getFetch(selID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateclinicModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				});
			}
		});

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure"))
					;
				int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				try {
					if (clinic.deleteClinic(selID)) {
						Helper.showMsg("success");
						updateclinicModel();
					} else {
						Helper.showMsg("Error");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		table_clinic = new JTable(clinicModel);
		table_clinic.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		w_scrollClinic.setViewportView(table_clinic);

		JLabel lblNewLabel_1_4 = new JLabel("Polikinlik Adı");
		lblNewLabel_1_4.setBackground(Color.RED);
		lblNewLabel_1_4.setForeground(Color.BLACK);
		lblNewLabel_1_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_4.setBounds(265, 57, 97, 23);
		w_clinic.add(lblNewLabel_1_4);

		fld_clinicName = new JTextField();
		fld_clinicName.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 18));
		fld_clinicName.setForeground(Color.BLACK);
		fld_clinicName.setBackground(Color.WHITE);
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(265, 90, 183, 32);
		w_clinic.add(fld_clinicName);

		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.setForeground(Color.BLACK);
		btn_addClinic.setBackground(Color.BLUE);
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_clinicName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (clinic.addClinic(fld_clinicName.getText())) {
							Helper.showMsg("success");
							fld_clinicName.setText(null);
							updateclinicModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addClinic.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_addClinic.setBounds(265, 132, 183, 32);
		w_clinic.add(btn_addClinic);

		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(458, 11, 259, 329);
		w_clinic.add(w_scrollWorker);
		
		table_worker = new JTable();
		table_worker.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		w_scrollWorker.setViewportView(table_worker);

		JComboBox select_doctor = new JComboBox();
		select_doctor.setBackground(Color.BLACK);
		select_doctor.setForeground(Color.LIGHT_GRAY);
		select_doctor.setBounds(265, 174, 183, 32);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			select_doctor.addItem(
					new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));
		}
		select_doctor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());
		});
		w_clinic.add(select_doctor);

		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.setForeground(Color.BLACK);
		btn_addWorker.setBackground(Color.BLUE);
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					Item doctorItem = (Item) select_doctor.getSelectedItem();
					try {
						boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicID);
						if (control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for(int i=0;i< bashekim.getClinicDoctorList(selClinicID).size();i++) {
								workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
								workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
								workerModel.addRow(workerData);
							}
							table_worker.setModel(workerModel);

						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen Bir Polikinlik Seçiniz");
				}
			}
		});
		btn_addWorker.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_addWorker.setBounds(265, 216, 183, 32);
		w_clinic.add(btn_addWorker);
		
		JButton btn_workerSelect = new JButton("Doktorları Görüntüle");
		btn_workerSelect.setForeground(Color.WHITE);
		btn_workerSelect.setBackground(Color.BLUE);
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if(selRow >=0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					
					try {
						for(int i=0;i< bashekim.getClinicDoctorList(selClinicID).size();i++) {
							workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
							workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);
				}else {
					Helper.showMsg("Lütfen Bir Polikinlik Seçiniz");
				}
			}
		});
		btn_workerSelect.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_workerSelect.setBounds(265, 258, 183, 32);
		w_clinic.add(btn_workerSelect);
		
		JButton btnkYap = new JButton("Çıkış Yap");
		btnkYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnkYap.setForeground(Color.DARK_GRAY);
		btnkYap.setBackground(Color.RED);
		btnkYap.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnkYap.setBounds(656, 27, 122, 39);
		w_pane.add(btnkYap);
	}

	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
	}

	public void updateclinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);

		}

	}
}
