package com.edusys.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.edusys.dao.ChuyenDeDAO;
import com.edusys.dao.NguoiHocDAO;
import com.edusys.dao.NhanVienDAO;
import com.edusys.entity.ChuyenDe;
import com.edusys.entity.NguoiHoc;
import com.edusys.entity.NhanVien;
import com.edusys.utils.Auth;
import com.edusys.utils.DataValidation;
import com.edusys.utils.XImage;
import com.edusys.utils.msgBox;

import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NguoiHocJDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtMaNguoiHoc;
	private JTextField txtHovaTen;
	ButtonGroup bgr = new ButtonGroup();
	private JTextField txtNgaySinh;
	private JTextField txtEmail;
	private JTextField txtDienThoai;
	private JTextField txtTimKiem;
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
	private JTextArea txtGhiChu;
	private JRadioButton rdoNam;
	private JRadioButton rdoNu;
	private JButton btnTimKiem;
	private JTabbedPane tabbedPane;
	boolean gioiTinh = true;
	// set text len form
	public void setForm(NguoiHoc nh) {
		txtMaNguoiHoc.setText(nh.getMaNH());
		txtHovaTen.setText(nh.getHoTen());
		txtDienThoai.setText(nh.getDienThoai());
		txtEmail.setText(nh.getEmail());
		
		Date Date = nh.getNgaySinh();
		DateFormat df = null;
		
		df = new SimpleDateFormat("yyyy-MM-dd");
		df.format(Date);
		txtNgaySinh.setText(df.format(Date));
		txtGhiChu.setText(nh.getGhiChu());
		if (nh.isGioiTinh()==true) {
			rdoNam.setSelected(gioiTinh);
		} else {
			rdoNu.setSelected(gioiTinh);
		}
//		rdoNam.setSelected(nh.isGioiTinh());
//	
//		rdoNu.setSelected(!nh.isGioiTinh());
	
	}
	public NguoiHoc getForm() {
		NguoiHoc nh = new NguoiHoc();
		try {
			if (rdoNam.isSelected()) {
				gioiTinh = true;
			} else {
				gioiTinh = false;
			}
			
			Date currentDate = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        formatter.format(currentDate);
			nh.setMaNH(txtMaNguoiHoc.getText().trim());
			nh.setDienThoai(txtDienThoai.getText().trim());
			nh.setEmail(txtEmail.getText().trim());
			nh.setGioiTinh(gioiTinh);
			nh.setNgaySinh(new SimpleDateFormat("yyyy-MM-dd").parse(txtNgaySinh.getText().trim()));
			nh.setGhiChu(txtGhiChu.getText().trim());
			nh.setNgayDK(currentDate);
			nh.setHoTen(txtHovaTen.getText().trim());
			nh.setMaNV(Auth.user.getMaNV());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nh;
	}
	// load data len bang
	public void loadDataToTable() {
		try {
			NguoiHocDAO Dao = new NguoiHocDAO();
			List<NguoiHoc> ds = Dao.selectAll();
			tableModel.setRowCount(0);
			for(NguoiHoc nh : ds) {
				tableModel.addRow(new Object[] {
						nh.getMaNH(),nh.getHoTen(),nh.isGioiTinh()?"Nam":"N???",nh.getNgaySinh()
						,nh.getDienThoai(),nh.getEmail(),nh.getMaNV(),
						nh.getNgayDK()
				});
			}
			tableModel.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			msgBox.alert(this, "L???i Truy V???n D??? Li???u");
			throw new RuntimeException();
//			System.out.println("" + e.getMessage());
		}
	}
	//tim kiem hien thong tin len bang
	public void findDataInTable() {
		try {
			NguoiHocDAO Dao = new NguoiHocDAO();
			List<NguoiHoc> ds = Dao.selectByKeyword(txtTimKiem.getText().trim());
			tableModel.setRowCount(0);
			for(NguoiHoc nh : ds) {
				tableModel.addRow(new Object[] {
						nh.getMaNH(),nh.getHoTen(),nh.getNgaySinh(),nh.isGioiTinh()?"Nam":"N???"
						,nh.getDienThoai(),nh.getEmail(),nh.getMaNV(),
						nh.getNgayDK()
				});
			}
			tableModel.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			msgBox.alert(this, "L???i Truy V???n D??? Li???u");
			throw new RuntimeException();
//			System.out.println("" + e.getMessage());
		}
	}

	//setText theo thu tu
	public void Display(int i) {
		NguoiHocDAO Dao = new NguoiHocDAO();
		List<NguoiHoc> ds = Dao.selectAll();
		NguoiHoc nh = ds.get(i);
		txtMaNguoiHoc.setText(nh.getMaNH());
		txtHovaTen.setText(nh.getHoTen());
		txtDienThoai.setText(nh.getDienThoai());
		txtEmail.setText(nh.getEmail());
		txtNgaySinh.setText(String.format("%t",nh.getNgaySinh()));
		txtGhiChu.setText(nh.getGhiChu());
		rdoNam.setSelected(nh.isGioiTinh());
		rdoNu.setSelected(!nh.isGioiTinh());
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
			DataValidation.validateEmp(txtMaNguoiHoc, sb, "C???n Nh???p M?? Ng?????i H???c");
			if(sb.length()>0) {
				msgBox.showErrorDialog(null, sb.toString());
				return;
			}
			NguoiHocDAO Dao = new NguoiHocDAO();
			NguoiHoc nh = Dao.selectById(txtMaNguoiHoc.getText().trim());
			if(!nh.getMaNH().equals(txtMaNguoiHoc.getText().trim())) {
				msgBox.alert(this, "Ng?????i H???c N??y Ch??a ???????c Th??m V??o Danh S??ch");
				return;
			}
			if(msgBox.showConfirmDialog(null, "B???n C?? Mu???n Xo?? Ng?????i H???c N??y Kh??ng Kh??ng")) {
				Dao.delete(txtMaNguoiHoc.getText().trim());
				msgBox.alert(this, "Ng?????i H???c ???? ???????c X??a");
				loadDataToTable();
			}else {
				msgBox.alert(this, "Ng?????i H???c Ch??a ???????c X??a");
			}
		}
		// update chuyen de
		public void Update() {
			StringBuilder sb = new StringBuilder();
			DataValidation.validateEmp(txtHovaTen, sb, "C???n Nh???p H??? V?? T??n");
			DataValidation.validateEmp(txtMaNguoiHoc, sb, "C???n Nh???p M?? Ng?????i H???c");
			DataValidation.validateEmp(txtDienThoai, sb, "C???n nh???p s??? ??i???n tho???i");
			DataValidation.validateEmp(txtEmail, sb, "C???n Nh???p Email");
//			DataValidation.validateEmp(txtNgaySinh, sb, "C???n Nh???p Ng??y Sinh");
			DataValidation.validateRole(bgr, sb, "C???n ch???n ch???c danh");
			DataValidation.validateEmail(txtEmail, sb, "C???n Nh???p ????ng ?????nh D???ng Email");
			DataValidation.validatePhoneNumber(txtDienThoai, sb, "S??? ??i???n Tho???i C???n Nh???p ????ng 10 Ch??? S???");
			DataValidation.validateYear(txtNgaySinh, sb, "C???n Nh???p ????ng ?????nh D???ng yyyy-MM-dd");
			if(sb.length()>0) {
				msgBox.showErrorDialog(null, sb.toString());
				return;
			}
			NguoiHocDAO Dao = new NguoiHocDAO();
//			NguoiHoc nh = Dao.selectById(txtMaNguoiHoc.getText().trim());
			NguoiHoc nh = getForm();
			
			if(!nh.getMaNH().equals(txtMaNguoiHoc.getText().trim())) {
				msgBox.alert(this, "Ng?????i H???c N??y Ch??a ???????c Th??m V??o Danh S??ch");
				return;
			}
			if(msgBox.showConfirmDialog(this, "B???n C?? Mu???n C???p Nh???t Ng?????i H???c N??y Kh??ng Kh??ng")) {
				Dao.update(nh);
				msgBox.alert(this, "Ng?????i H???c ???? ???????c C???p Nh???t");
				loadDataToTable();
			}else {
				msgBox.alert(this, "Ng?????i H???c Ch??a ???????c C???p Nh???t");
			}
		}
		// insert chuyen de
		public void Insert() {
			StringBuilder sb = new StringBuilder();
			DataValidation.validateEmp(txtHovaTen, sb, "C???n Nh???p H??? V?? T??n");
			DataValidation.validateEmp(txtMaNguoiHoc, sb, "C???n Nh???p M?? Ng?????i H???c");
			DataValidation.validateEmp(txtDienThoai, sb, "C???n nh???p s??? ??i???n tho???i");
			DataValidation.validateEmp(txtEmail, sb, "C???n Nh???p Email");
			DataValidation.validateYear(txtNgaySinh, sb, "C???n Nh???p Ng??y Sinh");
			DataValidation.validateRole(bgr, sb, "C???n ch???n ch???c danh");
			DataValidation.validateEmail(txtEmail, sb, "C???n Nh???p ????ng ?????nh D???ng Email");
			DataValidation.validatePhoneNumber(txtDienThoai, sb, "S??? ??i???n Tho???i C???n Nh???p ????ng 10 Ch??? S???");
			if(sb.length()>0) {
				msgBox.showErrorDialog(null, sb.toString());
				return;
			}
			NguoiHocDAO Dao = new NguoiHocDAO();
			NguoiHoc nh = getForm();
			
			if(msgBox.showConfirmDialog(this, "B???n C?? Mu???n Th??m Ng?????i H???c N??y Kh??ng Kh??ng")) {
				Dao.insert(nh);
				msgBox.alert(this, "Ng?????i H???c ???? ???????c Th??m");
				loadDataToTable();
			}else {
				msgBox.alert(this, "Ng?????i H???c Ch??a ???????c Th??m");
			}
		}
		//tim kiem
		public void Search() {
			StringBuilder sb = new StringBuilder();
			String timNguoiHoc= txtTimKiem.getText().trim();
			DataValidation.validateEmp(txtTimKiem, sb, "C???n Nh???p M?? Ng?????i H???c");
			if(sb.length()>0) {
				msgBox.showErrorDialog(null, sb.toString());
				return;
			}
			findDataInTable();
			
			
		}
		//empty form
		public void New() {
			txtMaNguoiHoc.setText("");
			txtHovaTen.setText("");
			txtMaNguoiHoc.setText("");
			txtHovaTen.setText("");
			txtDienThoai.setText("");
			txtEmail.setText("");
			txtNgaySinh.setText("");
			txtGhiChu.setText("");
			
			bgr.clearSelection();
			
			loadDataToTable();
			
		}
		// xu ly su kien click bang
		public void clickDataTableToForm() {
			int row = table.getSelectedRow();
			
			if(row>=0) {
				String id = (String) table.getValueAt(row, 0);
				NguoiHocDAO Dao = new NguoiHocDAO();
				NguoiHoc nh = Dao.selectById(id);
				setForm(nh);
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
			}
		}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NguoiHocJDialog dialog = new NguoiHocJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
//			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NguoiHocJDialog() {
		
		setIconImage(XImage.getAppIcon());
		setTitle("Qu???n L?? Ng?????i H???c");
		setBounds(100, 100, 578, 430);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBorder(null);
			tabbedPane.setBounds(0, 34, 561, 357);
			contentPanel.add(tabbedPane);
			
			JPanel pnlCapNhat = new JPanel();
			tabbedPane.addTab("C???p Nh???t", null, pnlCapNhat, null);
			pnlCapNhat.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("M?? Ng?????i H???c");
			lblNewLabel_1.setBounds(5, 6, 106, 22);
			pnlCapNhat.add(lblNewLabel_1);
			
			txtMaNguoiHoc = new JTextField();
			txtMaNguoiHoc.setBounds(5, 28, 539, 20);
			pnlCapNhat.add(txtMaNguoiHoc);
			txtMaNguoiHoc.setColumns(10);
			
			JLabel lblNewLabel_4 = new JLabel("H??? v?? T??n");
			lblNewLabel_4.setBounds(5, 59, 106, 22);
			pnlCapNhat.add(lblNewLabel_4);
			
			txtHovaTen = new JTextField();
			txtHovaTen.setBounds(5, 78, 539, 20);
			pnlCapNhat.add(txtHovaTen);
			txtHovaTen.setColumns(10);
			
			JLabel lblGender = new JLabel("Gi???i T??nh");
			lblGender.setBounds(5, 109, 58, 14);
			pnlCapNhat.add(lblGender);
			
			rdoNam = new JRadioButton("Nam");
			rdoNam.setBounds(5, 130, 66, 23);
			pnlCapNhat.add(rdoNam);
			
			rdoNu = new JRadioButton("N???");
			rdoNu.setBounds(73, 130, 66, 23);
			pnlCapNhat.add(rdoNu);
			
			bgr.add(rdoNu);
			bgr.add(rdoNam);
			
			btnAdd = new JButton("Th??m");
			btnAdd.setBounds(7, 299, 66, 23);
			pnlCapNhat.add(btnAdd);
			
			btnUpdate = new JButton("S???a");
			
			btnUpdate.setBounds(78, 298, 60, 23);
			pnlCapNhat.add(btnUpdate);
			
			btnDel = new JButton("X??a");
			btnDel.setBounds(143, 298, 60, 23);
			pnlCapNhat.add(btnDel);
			
			btnNew = new JButton("M???i");
			btnNew.setBounds(208, 298, 60, 23);
			pnlCapNhat.add(btnNew);
			
			btnFirst = new JButton("|<");
			btnFirst.setBounds(292, 299, 60, 23);
			pnlCapNhat.add(btnFirst);
			
			btnPrev = new JButton("<<");
			btnPrev.setBounds(357, 299, 60, 23);
			pnlCapNhat.add(btnPrev);
			
			btnNext = new JButton(">>");
			btnNext.setBounds(422, 299, 60, 23);
			pnlCapNhat.add(btnNext);
			
			btnLast = new JButton(">|");
			btnLast.setBounds(491, 299, 60, 23);
			pnlCapNhat.add(btnLast);
			
			JLabel lblNewLabel_2 = new JLabel("Ng??y Sinh");
			lblNewLabel_2.setBounds(290, 109, 76, 14);
			pnlCapNhat.add(lblNewLabel_2);
			
			txtNgaySinh = new JTextField();
			txtNgaySinh.setBounds(290, 131, 254, 20);
			pnlCapNhat.add(txtNgaySinh);
			txtNgaySinh.setColumns(10);
			
			JLabel lblNewLabel_2_1 = new JLabel("?????a Ch??? Email");
			lblNewLabel_2_1.setBounds(290, 163, 91, 14);
			pnlCapNhat.add(lblNewLabel_2_1);
			
			txtEmail = new JTextField();
			txtEmail.setColumns(10);
			txtEmail.setBounds(290, 188, 254, 20);
			pnlCapNhat.add(txtEmail);
			
			JLabel lblNewLabel_2_1_1 = new JLabel("??i???n Tho???i");
			lblNewLabel_2_1_1.setBounds(5, 163, 91, 14);
			pnlCapNhat.add(lblNewLabel_2_1_1);
			
			txtDienThoai = new JTextField();
			txtDienThoai.setColumns(10);
			txtDienThoai.setBounds(5, 188, 254, 20);
			pnlCapNhat.add(txtDienThoai);
			
			JLabel lblNewLabel_2_1_1_1 = new JLabel("Ghi Ch??");
			lblNewLabel_2_1_1_1.setBounds(5, 219, 91, 14);
			pnlCapNhat.add(lblNewLabel_2_1_1_1);
			
			txtGhiChu = new JTextArea();
			txtGhiChu.setBounds(5, 233, 539, 59);
			pnlCapNhat.add(txtGhiChu);
			
			JPanel pnlDanhSach = new JPanel();
			tabbedPane.addTab("Danh S??ch", null, pnlDanhSach, null);
			pnlDanhSach.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 86, 554, 243);
			pnlDanhSach.add(scrollPane);
			
			table = new JTable();
			table.setModel(tableModel = new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
				},
				new String[] {
					"M\u00E3 NH", "H\u1ECD v\u00E0 T\u00EAn", "Gi\u1EDBi T\u00EDnh", "Ng\u00E0y Sinh", "\u0110i\u1EC7n Tho\u1EA1i", "Email", "M\u00E3 NV", "Ng\u00E0y T\u1EA1o"
				}
			));
			scrollPane.setViewportView(table);
			
			JLabel lblNewLabel_3 = new JLabel("T??m Ki???m");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel_3.setBounds(4, 4, 90, 14);
			pnlDanhSach.add(lblNewLabel_3);
			
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel.setBounds(4, 21, 550, 59);
			pnlDanhSach.add(panel);
			panel.setLayout(null);
			
			txtTimKiem = new JTextField();
			txtTimKiem.setBounds(10, 20, 432, 20);
			panel.add(txtTimKiem);
			txtTimKiem.setColumns(10);
			
			btnTimKiem = new JButton("T??m ");
			btnTimKiem.setBounds(452, 19, 89, 23);
			panel.add(btnTimKiem);
		}
		
		JLabel lblNewLabel = new JLabel("Qu???n L?? Ng?????i H???c");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
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
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Search();
			}
		});
		loadDataToTable();
		Role();
		
	}

}
