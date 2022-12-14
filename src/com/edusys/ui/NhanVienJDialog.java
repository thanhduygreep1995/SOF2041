package com.edusys.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.edusys.dao.ChuyenDeDAO;
import com.edusys.dao.NhanVienDAO;
import com.edusys.entity.ChuyenDe;
import com.edusys.entity.NhanVien;
import com.edusys.utils.Auth;
import com.edusys.utils.DataValidation;
import com.edusys.utils.XImage;
import com.edusys.utils.msgBox;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;

public class NhanVienJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtMaNhanVien;
	private JTextField txtHovaTen;
	private JPasswordField txtPass;
	private JPasswordField txtXacNhanPass;
	ButtonGroup bgr = new ButtonGroup();
	private JRadioButton rdoTruongPhong;
	private JRadioButton rdoNhanVien;
	DefaultTableModel tableModel;
	int current = -1 ;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDel;
	private JButton btnNew;
	private JButton btnFirst;
	private JButton btnPrev;
	private JButton btnNext;
	private JButton btnLast;
	// set text len form
	public void setForm(NhanVien nv) {
		txtMaNhanVien.setText(nv.getMaNV());
		txtHovaTen.setText(nv.getHoTen());
		txtPass.setText(nv.getMatKhau());
		txtXacNhanPass.setText(nv.getMatKhau());
		rdoTruongPhong.setSelected(nv.isVaiTro());
		rdoNhanVien.setSelected(!nv.isVaiTro());
	}
	// get text tu nhan vien
	public NhanVien getForm() {
		NhanVien nv = new NhanVien();
		nv.setMaNV(txtMaNhanVien.getText().trim());
		nv.setHoTen(txtHovaTen.getText().trim());
		nv.setMatKhau(String.format("%s", txtPass.getText().trim()));
		nv.setVaiTro(rdoTruongPhong.isSelected());
		return nv;
	}
	// load data len bang
	public void loadDataToTable() {
		try {
			NhanVienDAO Dao = new NhanVienDAO();
			List<NhanVien> ds = Dao.selectAll();
			tableModel.setRowCount(0);
			for(NhanVien nv : ds) {
				tableModel.addRow(new Object[] {
						nv.getMaNV(), nv.getMatKhau(), nv.getHoTen(), 
						nv.isVaiTro()? "Tr?????ng Ph??ng" : "Nh??n Vi??n"
				});
			}
			tableModel.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
			msgBox.alert(this, "L???i Truy V???n D??? Li???u");
		}
	}
	//setText theo thu tu
	public void Display(int i) {
		NhanVienDAO Dao = new NhanVienDAO();
		List<NhanVien> ds = Dao.selectAll();
		NhanVien nv = ds.get(i);
		txtMaNhanVien.setText(nv.getMaNV());
		txtHovaTen.setText(nv.getHoTen());
		txtPass.setText(nv.getMatKhau());
		txtXacNhanPass.setText(nv.getMatKhau());
		rdoTruongPhong.setSelected(nv.isVaiTro());
		rdoNhanVien.setSelected(!nv.isVaiTro());
	}
	// nut dau danh sach
		private void firstButton() {
			// TODO Auto-generated method stub
			current = 0;
			table.setRowSelectionInterval(current, current);
			Display(current);
		}
		// nut lui
		private void prevButton() {
			// TODO Auto-generated method stub
			current--;
			ChuyenDeDAO Dao = new ChuyenDeDAO();
			List<ChuyenDe> ds = Dao.selectAll();
			if(current<0) {
				current = ds.size() - 1;
			}
			table.setRowSelectionInterval(current, current);
			Display(current);
		}
		//nut toi
		private void nextButton() {
			current++;
			ChuyenDeDAO Dao = new ChuyenDeDAO();
			List<ChuyenDe> ds = Dao.selectAll();
			if(current>= ds.size()) {
				current = 0;
			}
			table.setRowSelectionInterval(current, current);
			Display(current);
		}
		// nut cuoi danh sach
		private void lastButton() {
			current=tableModel.getRowCount()-1;
			table.setRowSelectionInterval(current, current);
			Display(current);
		}
		// xoa nhan vien
		public void Delete() {
			StringBuilder sb = new StringBuilder();
			DataValidation.validateEmp(txtMaNhanVien, sb, "C???n Nh???p M?? Nh??n Vi??n");
			if(sb.length()>0) {
				msgBox.showErrorDialog(null, sb.toString());
				return;
			}
			NhanVienDAO Dao = new NhanVienDAO();
			NhanVien nv = Dao.selectById(txtMaNhanVien.getText().trim());
			if(!nv.getMaNV().equals(txtMaNhanVien.getText().trim())) {
				msgBox.alert(this, "Nh??n Vi??n N??y Ch??a ???????c Th??m V??o Danh S??ch");
				return;
			}
			if(msgBox.showConfirmDialog(null, "B???n C?? Mu???n Xo?? Nh??n Vi??n N??y Kh??ng Kh??ng")) {
				Dao.delete(txtMaNhanVien.getText().trim());
				msgBox.alert(this, "Nh??n Vi??n ???? ???????c X??a");
				loadDataToTable();
			}else {
				msgBox.alert(this, "Nh??n Vi??n Ch??a ???????c X??a");
			}
		}
		// update chuyen de
		public void Update() {
			StringBuilder sb = new StringBuilder();
			DataValidation.validateEmp(txtHovaTen, sb, "C???n Nh???p T??n Chuy??n ?????");
			DataValidation.validateEmp(txtMaNhanVien, sb, "C???n Nh???p M?? Nh??n Vi??n");
			DataValidation.validateEmp(txtXacNhanPass, sb, "C???n X??c Nh???n Pass");
			DataValidation.validateEmp(txtPass, sb, "C???n Nh???p M???t Kh???u");
			DataValidation.validateRole(bgr, sb, "C???n ch???n ch???c danh");
			if(sb.length()>0) {
				msgBox.showErrorDialog(null, sb.toString());
				return;
			}
			NhanVienDAO Dao = new NhanVienDAO();
			NhanVien nv = getForm();
			
			
			if(!nv.getMaNV().equals(txtMaNhanVien.getText().trim())) {
				msgBox.alert(this, "Nh??n Vi??n N??y Ch??a ???????c Th??m V??o Danh S??ch");
				return;
			}
			if(msgBox.showConfirmDialog(this, "B???n C?? Mu???n C???p Nh???t Nh??n Vi??n N??y Kh??ng Kh??ng")) {
				Dao.update(nv);
				msgBox.alert(this, "Nh??n Vi??n ???? ???????c C???p Nh???t");
				loadDataToTable();
			}else {
				msgBox.alert(this, "Nh??n Vi??n Ch??a ???????c C???p Nh???t");
			}
		}
		// insert chuyen de
		public void Insert() {
			StringBuilder sb = new StringBuilder();
			DataValidation.validateEmp(txtHovaTen, sb, "C???n Nh???p T??n Chuy??n ?????");
			DataValidation.validateEmp(txtMaNhanVien, sb, "C???n Nh???p M?? Nh??n Vi??n");
			DataValidation.validateEmp(txtXacNhanPass, sb, "C???n X??c Nh???n Pass");
			DataValidation.validateEmp(txtPass, sb, "C???n Nh???p M???t Kh???u");
			DataValidation.validateRole(bgr, sb, "C???n ch???n ch???c danh");
			if(sb.length()>0) {
				msgBox.showErrorDialog(null, sb.toString());
				return;
			}
			NhanVienDAO Dao = new NhanVienDAO();
			NhanVien nv = getForm();
			String mk2 = new String(txtXacNhanPass.getPassword());
			if(!mk2.equals(nv.getMatKhau())) {
				msgBox.alert(this, "X??c Nh???n M???t Kh???u Sai");
				return;
			}
			if(msgBox.showConfirmDialog(this, "B???n C?? Mu???n Th??m Nh??n Vi??n N??y Kh??ng Kh??ng")) {
				Dao.insert(nv);
				msgBox.alert(this, "Nh??n Vi??n ???? ???????c Th??m");
				loadDataToTable();
			}else {
				msgBox.alert(this, "Nh??n Vi??n Ch??a ???????c Th??m");
			}
		}
		//empty form
		public void New() {
			txtMaNhanVien.setText("");
			txtHovaTen.setText("");
			txtPass.setText("");
			txtXacNhanPass.setText("");
			bgr.clearSelection();
		}
		// xu ly su kien click bang
		public void clickDataTableToForm() {
			int row = table.getSelectedRow();
			
			if(row>=0) {
				String id = (String) table.getValueAt(row, 0);
				NhanVienDAO Dao = new NhanVienDAO();
				NhanVien nv = Dao.selectById(id);
				setForm(nv);
			}
		}
		//an/hien nut doi voi tung vai tro
		public void Role() {
			if(!Auth.isManager()) {
				btnDel.setEnabled(false);
				btnFirst.setEnabled(false);
				btnPrev.setEnabled(false);
				btnNext.setEnabled(false);
				btnLast.setEnabled(false);
			}else {
				btnDel.setEnabled(true);
				btnFirst.setEnabled(true);
				btnPrev.setEnabled(true);
				btnNext.setEnabled(true);
				btnLast.setEnabled(true);
			}
		}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NhanVienJDialog dialog = new NhanVienJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NhanVienJDialog() {
		setIconImage(XImage.getAppIcon());
		setTitle("Qu???n L?? Nh??n Vi??n");
		setBounds(100, 100, 578, 392);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBorder(null);
			tabbedPane.setBounds(0, 34, 561, 319);
			contentPanel.add(tabbedPane);
			
			JPanel pnlCapNhat = new JPanel();
			tabbedPane.addTab("C???p Nh???t", null, pnlCapNhat, null);
			pnlCapNhat.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("M?? Nh??n Vi??n");
			lblNewLabel_1.setBounds(5, 6, 106, 22);
			pnlCapNhat.add(lblNewLabel_1);
			
			txtMaNhanVien = new JTextField();
			txtMaNhanVien.setBounds(5, 28, 539, 20);
			pnlCapNhat.add(txtMaNhanVien);
			txtMaNhanVien.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("M???t Kh???u");
			lblNewLabel_2.setBounds(5, 56, 76, 22);
			pnlCapNhat.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("X??c Nh???n M???t Kh???u");
			lblNewLabel_3.setBounds(5, 112, 118, 22);
			pnlCapNhat.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("H??? v?? T??n");
			lblNewLabel_4.setBounds(5, 168, 106, 22);
			pnlCapNhat.add(lblNewLabel_4);
			
			txtHovaTen = new JTextField();
			txtHovaTen.setBounds(5, 187, 539, 20);
			pnlCapNhat.add(txtHovaTen);
			txtHovaTen.setColumns(10);
			
			JLabel lblNewLabel_5 = new JLabel("Vai Tr??");
			lblNewLabel_5.setBounds(5, 211, 58, 14);
			pnlCapNhat.add(lblNewLabel_5);
			
			rdoTruongPhong = new JRadioButton("Tr?????ng Ph??ng");
			rdoTruongPhong.setBounds(5, 228, 109, 23);
			pnlCapNhat.add(rdoTruongPhong);
			
			rdoNhanVien = new JRadioButton("Nh??n Vi??n");
			rdoNhanVien.setBounds(116, 228, 109, 23);
			pnlCapNhat.add(rdoNhanVien);
			
			bgr.add(rdoNhanVien);
			bgr.add(rdoTruongPhong);
			
			btnAdd = new JButton("Th??m");
			btnAdd.setBounds(5, 258, 66, 23);
			pnlCapNhat.add(btnAdd);
			
			btnUpdate = new JButton("S???a");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnUpdate.setBounds(76, 257, 60, 23);
			pnlCapNhat.add(btnUpdate);
			
			btnDel = new JButton("X??a");
			btnDel.setBounds(141, 257, 60, 23);
			pnlCapNhat.add(btnDel);
			
			btnNew = new JButton("M???i");
			btnNew.setBounds(206, 257, 60, 23);
			pnlCapNhat.add(btnNew);
			
			btnFirst = new JButton("|<");
			btnFirst.setBounds(290, 258, 60, 23);
			pnlCapNhat.add(btnFirst);
			
			btnPrev = new JButton("<<");
			btnPrev.setBounds(355, 258, 60, 23);
			pnlCapNhat.add(btnPrev);
			
			btnNext = new JButton(">>");
			btnNext.setBounds(420, 258, 60, 23);
			pnlCapNhat.add(btnNext);
			
			btnLast = new JButton(">|");
			btnLast.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnLast.setBounds(489, 258, 60, 23);
			pnlCapNhat.add(btnLast);
			
			txtPass = new JPasswordField();
			txtPass.setBounds(5, 81, 539, 20);
			pnlCapNhat.add(txtPass);
			
			txtXacNhanPass = new JPasswordField();
			txtXacNhanPass.setBounds(5, 137, 539, 20);
			pnlCapNhat.add(txtXacNhanPass);
			
			JPanel pnlDanhSach = new JPanel();
			tabbedPane.addTab("Danh S??ch", null, pnlDanhSach, null);
			pnlDanhSach.setLayout(null);
		
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 554, 289);
			pnlDanhSach.add(scrollPane);
			
			table = new JTable();
			table.setModel(tableModel = new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
					{null, null, null, null},
				},
				new String[] {
					"M\u00E3 NV", "M\u1EADt Kh\u1EA9u", "H\u1ECD v\u00E0 T\u00EAn", "Vai Tr\u00F2"
				}
			));
			scrollPane.setViewportView(table);
		}
		
		JLabel lblNewLabel = new JLabel("Qu???n L?? Nh??n Vi??n Qu???n Tr???");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(3, 5, 274, 28);
		contentPanel.add(lblNewLabel);
		
		//action listener
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insert();
			}
		});
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Delete();
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update();
			}
		});
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				New();
			}
		});
		
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstButton();
			}
		});
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevButton();
			}
		});
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextButton();
			}
		});
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastButton();
			}
		});
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				clickDataTableToForm();
			}
		});
		
		Role();
		loadDataToTable();
	}
}
