package com.edusys.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.edusys.dao.ChuyenDeDAO;
import com.edusys.dao.KhoaHocDAO;
import com.edusys.dao.NguoiHocDAO;
import com.edusys.entity.ChuyenDe;
import com.edusys.entity.KhoaHoc;
import com.edusys.entity.NguoiHoc;
import com.edusys.utils.Auth;
import com.edusys.utils.DataValidation;
import com.edusys.utils.XImage;
import com.edusys.utils.msgBox;

import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;

public class KhoaHocJDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtKhaiGiang;
	ButtonGroup bgr = new ButtonGroup();
	DefaultTableModel tableModel;
	private JTextField txtThoiLuong;
	private JTextField txtNgayTao;
	private JTextField txtNguoiTao;
	private JTextField txtHocPhi;
	private JButton btnPrev;
	private JButton btnFirst;
	private JButton btnNext;
	private JButton btnLast;
	int current = -1 ;
	private JComboBox cboChuyenDe;
	private JLabel lblChuyenDe;
	private JTextArea txtMoTaChuyenDe;
	private JButton btnDel;
	ChuyenDeDAO Dao = new ChuyenDeDAO();
	List<KhoaHoc> ds = null;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnNew;
	public void Display(int i) {
		KhoaHocDAO khDao = new KhoaHocDAO();
		List<KhoaHoc> ds = khDao.selectAll();
		KhoaHoc kh = ds.get(i);
		txtHocPhi.setText(String.valueOf(kh.getHocPhi()));
		txtKhaiGiang.setText(String.valueOf (kh.getNgayKG()));
		txtNgayTao.setText(String.valueOf(kh.getNgayTao()));
		txtNguoiTao.setText(kh.getMaNV());
		txtThoiLuong.setText(String.valueOf(kh.getThoiLuong()));
		txtMoTaChuyenDe.setText(kh.getGhiChu());
		
		
		ChuyenDeDAO cdDao = new ChuyenDeDAO();
		ChuyenDe cd = cdDao.selectById(kh.getMaCD());
		cboChuyenDe.setSelectedItem(cd.getTenCD());
		lblChuyenDe.setText(cd.getTenCD());
	}
	
	//do du lieu len combobox
	public void fillComboBoxChuyenDe() {
		try {

			
			List<ChuyenDe> ds2 = Dao.selectAll();
			cboChuyenDe.removeAll();
			
			for(ChuyenDe cd : ds2) {
				cboChuyenDe.addItem(cd.getTenCD());
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
	}
	// load data to table
	private void loadDataTable() {
		KhoaHocDAO Dao = new KhoaHocDAO();
		List<KhoaHoc> ds1 = Dao.selectAll();
		tableModel.setRowCount(0);
		for(KhoaHoc kh : ds1) {
			tableModel.addRow(new Object[] {
					kh.getMaKH(), kh.getThoiLuong(), kh.getHocPhi(),kh.getNgayKG(),kh.getMaNV(),kh.getNgayTao()
			});
		}
		tableModel.fireTableDataChanged();
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
	// auto Text khi chon combobox
	private void chonChuyenDe() {
			String id = (String) cboChuyenDe.getSelectedItem();
			ChuyenDeDAO Dao = new ChuyenDeDAO();
			ChuyenDe cd = Dao.selectByName(id);
//			Date createDate = new Date();
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			txtHocPhi.setText(String.valueOf(cd.getHocPhi()));
			txtThoiLuong.setText(String.valueOf(cd.getThoiLuong()));
			lblChuyenDe.setText(cd.getTenCD());
			txtMoTaChuyenDe.setText(cd.getMoTa());
			KhoaHoc kh = new KhoaHoc();
			kh.setMaNV(Auth.user.getMaNV());
			txtNguoiTao.setText(kh.getMaNV());
//			txtNgayTao.setText(String.valueOf(formatter.format(createDate)));
	}
	public KhoaHoc getForm() throws ParseException {
		KhoaHoc kh = new KhoaHoc();
		
		String id = (String) cboChuyenDe.getSelectedItem();
		
		ChuyenDeDAO Dao = new ChuyenDeDAO();
		ChuyenDe cd = Dao.selectByName(id);
		
		Date Date = new SimpleDateFormat("yyyy-MM-dd").parse(txtKhaiGiang.getText().trim());
//		Date createDate = new SimpleDateFormat("yyyy-MM-dd").parse(txtNgayTao.getText().trim());
	
		try {
			
			kh.setThoiLuong(Integer.parseInt(txtThoiLuong.getText().trim()));
			
			kh.setMaCD(cd.getMaCD());
			kh.setGhiChu(txtMoTaChuyenDe.getText().trim());
			kh.setMaNV(txtNguoiTao.getText().trim());
			
			kh.setHocPhi(Float.parseFloat(txtHocPhi.getText().trim()));
			
			kh.setNgayKG(Date);
			kh.setNgayTao(new SimpleDateFormat("yyyy-MM-dd").parse(txtNgayTao.getText().trim()));
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return kh;
	}
	public KhoaHoc getForm1(int index) throws ParseException {
		KhoaHoc kh = new KhoaHoc();
		
		String id = (String) cboChuyenDe.getSelectedItem();
		
		ChuyenDeDAO Dao = new ChuyenDeDAO();
		ChuyenDe cd = Dao.selectByName(id);
		
		Date Date = new SimpleDateFormat("yyyy-MM-dd").parse(txtKhaiGiang.getText().trim());
//		Date createDate = new SimpleDateFormat("yyyy-MM-dd").parse(txtNgayTao.getText().trim());
	
	
		kh.setThoiLuong(Integer.parseInt(txtThoiLuong.getText().trim()));
		kh.setMaCD(cd.getMaCD());
		kh.setGhiChu(txtMoTaChuyenDe.getText().trim());
		kh.setMaNV(txtNguoiTao.getText().trim());
		kh.setHocPhi(Float.parseFloat(txtHocPhi.getText().trim()));
		
		kh.setNgayKG(Date);
		kh.setNgayTao(new SimpleDateFormat("yyyy-MM-dd").parse(txtNgayTao.getText().trim()));
		kh.setMaKH(index);

		return kh;
	}
	public void setForm(KhoaHoc kh,ChuyenDe cd) {
		txtHocPhi.setText(String.valueOf(kh.getHocPhi()));
		txtKhaiGiang.setText(String.valueOf (kh.getNgayKG()));
		txtNgayTao.setText(String.valueOf(kh.getNgayTao()));
		txtNguoiTao.setText(kh.getMaNV());
		txtThoiLuong.setText(String.valueOf(kh.getThoiLuong()));
		txtMoTaChuyenDe.setText(kh.getGhiChu());
		
		
		
		cboChuyenDe.setSelectedItem(cd.getTenCD());
		lblChuyenDe.setText(cd.getTenCD());
		
	}
	// xu ly su kien click bang
	public void clickDataTableToForm() {
		try {
			int row = table.getSelectedRow();
			
			if(row>=0) {
				
				int id = (int) table.getValueAt(row, 0);
				KhoaHocDAO khDao = new KhoaHocDAO();
				KhoaHoc kh = khDao.selectById(id);
				
				ChuyenDeDAO cdDao = new ChuyenDeDAO();
				ChuyenDe cd = cdDao.selectById(kh.getMaCD());

				setForm(kh,cd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	// xoa chuyen de
		public void Delete() {
			int row = table.getSelectedRow();
			if (row>=0) {
				int id = (int) table.getValueAt(row, 0);
				KhoaHocDAO Dao = new KhoaHocDAO();
				KhoaHoc kh = Dao.selectById(id);
				
				if(msgBox.showConfirmDialog(null, "Bạn Có Muốn Xoá Khóa Học Này Không Không")) {
					Dao.delete(kh.getMaKH());
					msgBox.alert(this, "Khóa học Đã Được Xóa");
					loadDataTable();
				}else {
					msgBox.alert(this, "Khóa Học Chưa Được Xóa");
				}
			}
			
		}
		// update chuyen de
		public void Update() throws ParseException {
			StringBuilder sb = new StringBuilder();
			DataValidation.validateEmp(txtNgayTao, sb, "Cần Nhập Ngày Tạo");
			DataValidation.validateYear(txtKhaiGiang, sb, "Cần Nhập Ngày Khai Giảng");
			DataValidation.validateYear(txtNgayTao, sb, "Cần Nhập Ngày Khai Giảng");
			DataValidation.validateNumber(txtHocPhi, sb, "Cần Nhập Học Phí");
			DataValidation.validateNumber(txtThoiLuong, sb, "Cần Nhập thời lượng");
			DataValidation.validateEmp(txtMoTaChuyenDe, sb, "Cần Ghi Rõ Mô Tả Chuyên Đề");
			if(sb.length()>0) {
				msgBox.showErrorDialog(null, sb.toString());
				return;
			}
			int row = table.getSelectedRow();
			if(row >=0) {
				int Index = (int) table.getValueAt(row, 0);
				KhoaHocDAO Dao = new KhoaHocDAO();
				KhoaHoc kh = getForm1(Index);
				
				if(msgBox.showConfirmDialog(this, "Bạn Có Muốn Cập Nhật Khóa Học Này Không Không")) {
					
					Dao.update(kh);
					msgBox.alert(this, "Khóa Học Đã Được Cập Nhật");
					loadDataTable();
				}else {
					msgBox.alert(this, "Khóa Học Chưa Được Cập Nhật");
				}
			}
			
		}
		// insert chuyen de
		public void Insert() {
			StringBuilder sb = new StringBuilder();
			DataValidation.validateEmp(txtNgayTao, sb, "Cần Nhập Ngày Tạo");
			DataValidation.validateYear(txtKhaiGiang, sb, "Cần Nhập Ngày Khai Giảng");
			DataValidation.validateNumber(txtHocPhi, sb, "Cần Nhập Học Phí");
			DataValidation.validateNumber(txtThoiLuong, sb, "Cần Nhập thời lượng");
			DataValidation.validateEmp(txtMoTaChuyenDe, sb, "Cần Ghi Rõ Mô Tả Chuyên Đề");
			DataValidation.validateYear(txtNgayTao, sb, "Cần Nhập Ngày Khai Giảng");
			if(sb.length()>0) {
				msgBox.showErrorDialog(null, sb.toString());
				return;
			}
			
			
			try {
				KhoaHocDAO Dao = new KhoaHocDAO();
				KhoaHoc kh = getForm();
				if(msgBox.showConfirmDialog(this, "Bạn Có Muốn Thêm Khóa Học Này Không")) {
					Dao.insert(kh);
					msgBox.alert(this, "Khóa Học Đã Được Thêm");
					loadDataTable();
				}else {
					msgBox.alert(this, "Khóa Học Chưa Được Thêm");
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		// empty form
		public void New() {
			txtNgayTao.setText("");
			txtHocPhi.setText("");
			txtNguoiTao.setText("");
			txtMoTaChuyenDe.setText("");
			txtThoiLuong.setText("");
			lblChuyenDe.setText("");
			txtKhaiGiang.setText("");
			cboChuyenDe.setSelectedItem(null);
		}
	
	//an/hien nut doi voi tung vai tro
	public void Role() {
		if(!Auth.isManager()) {
			btnDel.setEnabled(false);
			btnFirst.setEnabled(false);
			btnPrev.setEnabled(false);
			btnNext.setEnabled(false);
			btnLast.setEnabled(false);
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			KhoaHocJDialog dialog = new KhoaHocJDialog();
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
	public KhoaHocJDialog() {
		setIconImage(XImage.getAppIcon());
		setTitle("Quản Lý Khóa Học");
		setBounds(100, 100, 578, 502);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBorder(null);
			tabbedPane.setBounds(0, 70, 561, 404);
			contentPanel.add(tabbedPane);
			
			JPanel pnlCapNhat = new JPanel();
			tabbedPane.addTab("Cập Nhật", null, pnlCapNhat, null);
			pnlCapNhat.setLayout(null);
			
			JLabel lblKhaiGiang = new JLabel("Khai Giảng");
			lblKhaiGiang.setBounds(310, 7, 106, 22);
			pnlCapNhat.add(lblKhaiGiang);
			
			txtKhaiGiang = new JTextField();
			txtKhaiGiang.setBounds(310, 39, 240, 20);
			pnlCapNhat.add(txtKhaiGiang);
			txtKhaiGiang.setColumns(10);
			
			JLabel lblThoiLuong = new JLabel("Thời Lượng(giờ)");
			lblThoiLuong.setBounds(310, 78, 118, 22);
			pnlCapNhat.add(lblThoiLuong);
			
			JLabel lblNgayTao = new JLabel("Ngày Tạo");
			lblNgayTao.setBounds(310, 142, 106, 22);
			pnlCapNhat.add(lblNgayTao);
			
			JLabel lblMoTa = new JLabel("Ghi Chú");
			lblMoTa.setBounds(5, 211, 118, 14);
			pnlCapNhat.add(lblMoTa);
			
			btnAdd = new JButton("Thêm");
			btnAdd.setBounds(4, 340, 66, 23);
			pnlCapNhat.add(btnAdd);
			
			btnUpdate = new JButton("Sửa");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnUpdate.setBounds(75, 339, 60, 23);
			pnlCapNhat.add(btnUpdate);
			
			btnDel = new JButton("Xóa");
			btnDel.setBounds(140, 339, 60, 23);
			pnlCapNhat.add(btnDel);
			
			btnNew = new JButton("Mới");
			btnNew.setBounds(205, 339, 60, 23);
			pnlCapNhat.add(btnNew);
			
			btnFirst = new JButton("|<");
			btnFirst.setBounds(289, 340, 60, 23);
			pnlCapNhat.add(btnFirst);
			
			btnPrev = new JButton("<<");
			btnPrev.setBounds(354, 340, 60, 23);
			pnlCapNhat.add(btnPrev);
			
			btnNext = new JButton(">>");
			btnNext.setBounds(419, 340, 60, 23);
			pnlCapNhat.add(btnNext);
			
			btnLast = new JButton(">|");
			btnLast.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnLast.setBounds(488, 340, 60, 23);
			pnlCapNhat.add(btnLast);
			
			txtMoTaChuyenDe = new JTextArea();
			txtMoTaChuyenDe.setBounds(5, 225, 545, 104);
			pnlCapNhat.add(txtMoTaChuyenDe);
			
			txtThoiLuong = new JTextField();
			txtThoiLuong.setColumns(10);
			txtThoiLuong.setBounds(310, 111, 240, 20);
			pnlCapNhat.add(txtThoiLuong);
			
			txtNgayTao = new JTextField();
			txtNgayTao.setColumns(10);
			txtNgayTao.setBounds(310, 175, 240, 20);
			pnlCapNhat.add(txtNgayTao);
			
			JLabel lblNgiTo = new JLabel("Người Tạo");
			lblNgiTo.setBounds(5, 142, 106, 22);
			pnlCapNhat.add(lblNgiTo);
			
			txtNguoiTao = new JTextField();
			txtNguoiTao.setColumns(10);
			txtNguoiTao.setBounds(5, 175, 240, 20);
			pnlCapNhat.add(txtNguoiTao);
			
			JLabel lblHcPh = new JLabel("Học Phí");
			lblHcPh.setBounds(5, 78, 106, 22);
			pnlCapNhat.add(lblHcPh);
			
			txtHocPhi = new JTextField();
			txtHocPhi.setColumns(10);
			txtHocPhi.setBounds(5, 111, 240, 20);
			pnlCapNhat.add(txtHocPhi);
			
			JLabel lblNewLabel_1 = new JLabel("Chuyên Đề");
			lblNewLabel_1.setBounds(5, 11, 84, 14);
			pnlCapNhat.add(lblNewLabel_1);
			
			lblChuyenDe = new JLabel("Lập Trình Java Căn Bản");
			lblChuyenDe.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblChuyenDe.setForeground(Color.RED);
			lblChuyenDe.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			lblChuyenDe.setBounds(5, 39, 240, 25);
			pnlCapNhat.add(lblChuyenDe);
			
			
			
			JPanel pnlDanhSach = new JPanel();
			tabbedPane.addTab("Danh Sách", null, pnlDanhSach, null);
			pnlDanhSach.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 554, 366);
			pnlDanhSach.add(scrollPane);
			
			table = new JTable();
			table.setModel(tableModel = new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
				},
				new String[] {
					"M\u00E3 KH", "Th\u1EDDi L\u01B0\u1EE3ng(Gi\u1EDD)", "H\u1ECDc Ph\u00ED", "Khai Gi\u1EA3ng", "T\u1EA1o B\u1EDFi", "Ng\u00E0y T\u1EA1o"
				}
			));
			table.getColumnModel().getColumn(0).setPreferredWidth(104);
			table.getColumnModel().getColumn(1).setPreferredWidth(95);
			table.getColumnModel().getColumn(3).setPreferredWidth(93);
			table.getColumnModel().getColumn(4).setPreferredWidth(116);
			scrollPane.setViewportView(table);
		}
		
		JLabel lblNewLabel = new JLabel("Chuyên Đề");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(4, 3, 274, 22);
		contentPanel.add(lblNewLabel);
		
		JPanel pnlChuyenDe = new JPanel();
		pnlChuyenDe.setBounds(0, 27, 561, 41);
		contentPanel.add(pnlChuyenDe);
		pnlChuyenDe.setLayout(null);
		
		cboChuyenDe = new JComboBox();
		
		
		cboChuyenDe.setBounds(10, 11, 541, 22);
		pnlChuyenDe.add(cboChuyenDe);
		// action listener
		cboChuyenDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chonChuyenDe();
			}
		});
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
				try {
					Update();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		
	
		
//		Role();
		fillComboBoxChuyenDe();
		loadDataTable();
	}
}
