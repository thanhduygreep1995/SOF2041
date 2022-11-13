package com.edusys.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.edusys.dao.KhoaHocDAO;
import com.edusys.dao.ThongKeDAO;
import com.edusys.entity.KhoaHoc;
import com.edusys.utils.Auth;
import com.edusys.utils.XImage;
import com.edusys.utils.msgBox;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ThongKeJDialog extends JDialog {
	private JTable tblBangDiem;
	private JTable tblNguoiHoc;
	private JTable tblChuyenDe;
	private JTable tblDoanhThu;
	private JTabbedPane tabs;
	ThongKeDAO tkDao = new ThongKeDAO();
	KhoaHocDAO khDao = new KhoaHocDAO();
	DefaultTableModel tableModelBD,tableModelNH,tableModelCD,tableModelDT;
	private JComboBox cboKhoaHoc;
	private JComboBox cboNam;
	List<KhoaHoc> dsKhoaHoc = new ArrayList<KhoaHoc>();
	private JPanel pnlDoanhThu;
	private JPanel pnlChuyenDe;
	private JPanel pnlNguoiHoc;
	private JPanel pnlBangDiem1;
	
	public void accessRight(int index) {
		this.selectTab(index);
		if(!Auth.isManager()) {
			tabs.remove(3);
		}
	}
	// fill combobox
	//cboKhoaHoc
	public void fillComboBoxKhoaHoc() {
		// TODO Auto-generated method stub
		cboKhoaHoc.removeAllItems();
		KhoaHocDAO khDao = new KhoaHocDAO();
		dsKhoaHoc = khDao.selectAll();
		for(KhoaHoc kh: dsKhoaHoc) {
			cboKhoaHoc.addItem(kh.getMaCD()+" ("+kh.getNgayKG()+")");
		}		
	}
	//cboNam
	public void fillComboBoxNam() {
		cboNam.removeAllItems();
		List<Integer> ds = khDao.selectYears();
		for(Integer year: ds) {
			cboNam.addItem(year);
		}
	}
	
	// Load data to table
	// table bang diem
	public void loadDataToTableBD() {
		tableModelBD.setRowCount(0);
		List<Object[]> dsThongKe = null;

		String maCD =String.valueOf(cboKhoaHoc.getSelectedItem()).split(" ")[0] ;	
		dsKhoaHoc = khDao.selectByChuyenDe(maCD.trim());
		for(KhoaHoc kh: dsKhoaHoc) {
			dsThongKe= tkDao.getBangDiem(kh.getMaKH());

		}
		for(Object[] Row: dsThongKe) {
			double Diem = (double) Row[2];
			tableModelBD.addRow(new Object[] {
				Row[0], Row[1], Diem, Row[3]
			});	
		}
		tableModelBD.fireTableDataChanged();	
	}
	// table bang Nguoi Hoc
	public void loadDataToTableNH() {
		tableModelNH.setRowCount(0);
		List<Object[]> dsThongKe = tkDao.getNguoiHoc();
		for(Object[] row: dsThongKe) {
			tableModelNH.addRow(row);
		}
	}
	
	// table bang chuyen de
	public void loadDataToTableCD() {
		tableModelCD.setRowCount(0);
		List<Object[]> dsThongKe = tkDao.getDiemChuyenDe();
		for(Object[] row: dsThongKe) {
			tableModelCD.addRow(row);
		}
	}
	// table bang doanh thu
	public void loadDataToTableDT() {
		tableModelDT.setRowCount(0);
		int Year = (int) cboNam.getSelectedItem();
		List<Object[]> ds = tkDao.getDoanhThu(Year);
		for (Object[] Row: ds) {
			tableModelDT.addRow(Row);
		}
	}
	public void selectTab(int Index) {
		tabs.setSelectedIndex(Index);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ThongKeJDialog dialog = new ThongKeJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ThongKeJDialog() {
		setIconImage(XImage.getAppIcon());
		setTitle("EduSys - Tổng Hợp Và Thống Kê");
		setBounds(100, 100, 627, 297);
		getContentPane().setLayout(null);
		
		tabs = new JTabbedPane(JTabbedPane.TOP);
		tabs.setBounds(10, 48, 591, 212);
		getContentPane().add(tabs);
		
		pnlBangDiem1 = new JPanel();
		tabs.addTab("Bảng Điểm", null, pnlBangDiem1, null);
		pnlBangDiem1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Khóa Học");
		lblNewLabel_1.setBounds(10, 11, 62, 14);
		pnlBangDiem1.add(lblNewLabel_1);
		
		cboKhoaHoc = new JComboBox();
		
		cboKhoaHoc.setBounds(82, 7, 494, 22);
		pnlBangDiem1.add(cboKhoaHoc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 40, 586, 144);
		pnlBangDiem1.add(scrollPane);
		
		tblBangDiem = new JTable();
		tblBangDiem.setModel( tableModelBD = new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"M\u00E3 Ng\u01B0\u1EDDi H\u1ECDc", "H\u1ECD V\u00E0 T\u00EAn", "\u0110i\u1EC3m", "X\u1EBFp Lo\u1EA1i"
			}
		));
		tblBangDiem.getColumnModel().getColumn(0).setPreferredWidth(89);
		scrollPane.setViewportView(tblBangDiem);
		
		pnlNguoiHoc = new JPanel();
		tabs.addTab("Người Học", null, pnlNguoiHoc, null);
		pnlNguoiHoc.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 586, 184);
		pnlNguoiHoc.add(scrollPane_1);
		
		tblNguoiHoc = new JTable();
		tblNguoiHoc.setModel(tableModelNH =new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"N\u0103m", "S\u1ED1 Ng\u01B0\u1EDDi H\u1ECDc", "\u0110\u0103ng K\u00FD S\u1EDBm Nh\u1EA5t", "\u0110\u0103ng K\u00FD Mu\u1ED9n Nh\u1EA5t"
			}
		));
		tblNguoiHoc.getColumnModel().getColumn(1).setPreferredWidth(88);
		tblNguoiHoc.getColumnModel().getColumn(2).setPreferredWidth(119);
		tblNguoiHoc.getColumnModel().getColumn(3).setPreferredWidth(124);
		scrollPane_1.setViewportView(tblNguoiHoc);
		
		pnlChuyenDe = new JPanel();
		tabs.addTab("Chuyên Đề", null, pnlChuyenDe, null);
		pnlChuyenDe.setLayout(null);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(0, 0, 586, 184);
		pnlChuyenDe.add(scrollPane2);
		
		tblChuyenDe = new JTable();
		tblChuyenDe.setModel(tableModelCD= new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Chuy\u00EAn \u0110\u1EC1", "S\u1ED1 L\u01B0\u1EE3ng H\u1ECDc Vi\u00EAn", "\u0110i\u1EC3m TN", "\u0110i\u1EC3m CN", "\u0110i\u1EC3m TB"
			}
		));
		tblChuyenDe.getColumnModel().getColumn(1).setPreferredWidth(120);
		scrollPane2.setViewportView(tblChuyenDe);
		
		pnlDoanhThu = new JPanel();
		tabs.addTab("Doanh Thu", null, pnlDoanhThu, null);
		pnlDoanhThu.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 28, 586, 156);
		pnlDoanhThu.add(scrollPane_2);
		
		tblDoanhThu = new JTable();
		tblDoanhThu.setModel(tableModelDT=new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Chuy\u00EAn \u0110\u1EC1", "S\u1ED1 Kh\u00F3a H\u1ECDc", "S\u1ED1 H\u1ECDc Vi\u00EAn", "Doanh Thu H\u1EB1ng N\u0103m", "H\u1ECDc Ph\u00ED TN", "H\u1ECDc Phi CN", "H\u1ECDc Ph\u00ED TB"
			}
		));
		tblDoanhThu.getColumnModel().getColumn(3).setPreferredWidth(122);
		tblDoanhThu.getColumnModel().getColumn(4).setPreferredWidth(107);
		scrollPane_2.setViewportView(tblDoanhThu);
		
		JLabel lblNewLabel_2 = new JLabel("Năm");
		lblNewLabel_2.setBounds(9, 7, 46, 14);
		pnlDoanhThu.add(lblNewLabel_2);
		
		cboNam = new JComboBox();
		cboNam.setBounds(67, 2, 476, 22);
		pnlDoanhThu.add(cboNam);
		
		JLabel lblNewLabel = new JLabel("Tổng Hợp Thống Kê");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 233, 26);
		getContentPane().add(lblNewLabel);
		//Action Listener
		fillComboBoxKhoaHoc();
		fillComboBoxNam();
		cboKhoaHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDataToTableBD();
			}
		});
		cboNam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDataToTableDT();
			}
		});
		loadDataToTableBD();
		loadDataToTableNH();
		loadDataToTableCD();
		loadDataToTableDT();
		accessRight(0);
	}
}
