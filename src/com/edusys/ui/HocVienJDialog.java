package com.edusys.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.edusys.dao.ChuyenDeDAO;
import com.edusys.dao.HocVienDAO;
import com.edusys.dao.KhoaHocDAO;
import com.edusys.dao.NguoiHocDAO;
import com.edusys.entity.ChuyenDe;
import com.edusys.entity.HocVien;
import com.edusys.entity.KhoaHoc;
import com.edusys.entity.NguoiHoc;
import com.edusys.utils.Auth;
import com.edusys.utils.XImage;
import com.edusys.utils.msgBox;

import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HocVienJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblHocVien;
	private JTable tblNguoiHoc;
	
	private JPanel pnlHocVien;
	private JTabbedPane Tabs;
	private JComboBox cboKhoaHoc;
	private JPanel pnlKhoaHoc;
	private JComboBox cboChuyenDe;
	private JPanel pnlChuyenDe;
	private Label lblKhoaHoc;
	private Label lblChuyenDe;
	DefaultTableModel tableModelHV, tableModelNH;
	int current = 0 ;
	private JPanel pnlNguoiHoc;
	private JButton btnDel;
	private JButton btnAdd;
	private JLabel lblTimKiem;
	private JPanel panel;
	private JTextField txtTimKiem;
	List<KhoaHoc> dsKhoaHoc;
	List<ChuyenDe> dsChuyenDe;
	List<HocVien> dsHocVien;
	List<NguoiHoc> dsNguoiHoc;
	
	
	//do du lieu len combobox
	public void fillComboBoxChuyenDe() {
		try {

			ChuyenDeDAO Dao = new ChuyenDeDAO();
			
			cboChuyenDe.removeAll();
			dsChuyenDe = Dao.selectAll();
			for(ChuyenDe cd : dsChuyenDe) {
				cboChuyenDe.addItem(cd.getTenCD());
				KhoaHoc kh = new KhoaHoc();
				kh.setMaCD(cd.getMaCD());
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage()+ "\ncomboxChuyenDe");
			
		}
	}
	public void fillComboBoxKhoaHoc() {
		// TODO Auto-generated method stub
		cboKhoaHoc.removeAllItems();
		KhoaHocDAO khDao = new KhoaHocDAO();
		int Index = cboChuyenDe.getSelectedIndex();
		if(Index >= 0) {
			dsKhoaHoc = khDao.selectByChuyenDe(dsChuyenDe.get(Index).getMaCD());
			for(KhoaHoc kh: dsKhoaHoc) {
				cboKhoaHoc.addItem(kh.getMaCD()+" ("+kh.getNgayKG()+")");
			}
			
		}		
	}
	//load data len bang tblHocVien
	public void loadDataToHocVien() {
		HocVienDAO hvDao = new HocVienDAO();
		KhoaHocDAO khDao = new KhoaHocDAO();
		KhoaHoc kh = new KhoaHoc();

		NguoiHocDAO nhDao = new NguoiHocDAO();
		ChuyenDe cd =new ChuyenDe();
		
		int Index =   cboKhoaHoc.getSelectedIndex();
		if (Index >=0) {
			kh = dsKhoaHoc.get(Index);

			dsKhoaHoc = khDao.selectByChuyenDe(dsKhoaHoc.get(Index).getMaCD());
			dsHocVien = hvDao.selectByKhoaHoc(kh.getMaKH());
			tableModelHV.setRowCount(0);
				
			for (int i =0;i<dsHocVien.size();i++) {
				
				HocVien hv = dsHocVien.get(i);
				String hoTen = nhDao.selectById(hv.getMaNH()).getHoTen();
				tableModelHV.addRow(new Object[] {
						i+1,hv.getMaHV(),hv.getMaNH(),hoTen,hv.getDiem()
				});		
			}
		}
		tableModelHV.fireTableDataChanged();
		
	}
	//load data len bang tblNguoiHoc
	public void loadDataToNguoiHoc() {
		KhoaHocDAO khDao = new KhoaHocDAO();
		KhoaHoc kh = new KhoaHoc();
		HocVienDAO hvDao = new HocVienDAO();
		
		String maCD =(String)cboKhoaHoc.getSelectedItem();
		kh.setMaCD(maCD);
//		System.out.println(kh.getMaCD());
		dsKhoaHoc = khDao.selectByChuyenDe(kh.getMaCD());
		
		
		NguoiHocDAO nhDao= new NguoiHocDAO();
		
		String keyWord = txtTimKiem.getText().trim();
		dsNguoiHoc = nhDao.selectNotInCourse(kh.getMaKH(), keyWord);
		
		tableModelNH.setRowCount(0);
		for(NguoiHoc nh:dsNguoiHoc) {
			tableModelNH.addRow(new Object[] {
					nh.getMaNH(),nh.getHoTen(), nh.isGioiTinh()? "Nam" : "Nữ", nh.getNgaySinh(),nh.getDienThoai(),nh.getEmail()
			});
		}
		tableModelNH.fireTableDataChanged();
	}
	//tim kiem data bang nguoi hoc
	public void findDataToNguoiHoc() {
		try {
			NguoiHocDAO nhDao = new NguoiHocDAO();
			dsNguoiHoc = nhDao.selectByKeyword(txtTimKiem.getText().trim());
			tableModelNH.setRowCount(0);
			for (NguoiHoc nh: dsNguoiHoc) {
				tableModelNH.addRow(new Object[] {
						nh.getMaNH(),nh.getHoTen(), nh.isGioiTinh()? "Nam" : "Nữ", nh.getNgaySinh(),nh.getDienThoai(),nh.getEmail()
				});
			}
			tableModelNH.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
			msgBox.alert(this, "Lỗi Truy Vấn Dữ Liệu");
			throw new RuntimeException();
		}
	}
	//them hoc vien
	public void addHocVien() {
		int index = tblNguoiHoc.getSelectedRow();
		int indexKH = cboKhoaHoc.getSelectedIndex();
		HocVienDAO hvDao = new HocVienDAO();
		HocVien hv = new HocVien();

		if(indexKH >= 0 && index >= 0) {
			hv.setDiem(0);
			hv.setMaKH(dsKhoaHoc.get(indexKH).getMaKH());
			hv.setMaNH(dsNguoiHoc.get(index).getMaNH());
			
			hvDao.insert(hv);
			msgBox.alert(contentPanel, "Thêm Thành Công");
		}
		this.loadDataToHocVien();
		this.Tabs.setSelectedIndex(0);
	}
	public void removeHocVien() {
		if(!Auth.isManager()) {
			msgBox.alert(this, "Bạn Không Có Quyền Xóa Học Viên");
		}
		else {
			HocVienDAO hvDao= new HocVienDAO();
			HocVien hv = new HocVien();
			int row = tblHocVien.getSelectedRow();
			hv.setMaHV(dsHocVien.get(row).getMaHV());
			System.out.println(hv.getMaHV());
			if(msgBox.showConfirmDialog(this, "Bạn Muốn Xóa Các Học Viên Được Chọn?")) {
				
				if(row>=0) {
					int maHV = (int) tblHocVien.getValueAt(row, 1);
					hvDao.delete(maHV);
					this.loadDataToHocVien();
				}	
			}
		}
	}
	public void updateDiem() {
		HocVienDAO hvDao= new HocVienDAO();
		int index = tblHocVien.getSelectedRow();
		int indexKH = cboKhoaHoc.getSelectedIndex();
		
		if(indexKH >= 0 && index >= 0) {
			int maHV = Integer.valueOf(""+tblHocVien.getValueAt(index, 1)) ;
//			System.out.println(maHV);
			HocVien hv = dsHocVien.get(index);
			
			float Diem= Float.valueOf(""+tblHocVien.getValueAt(index, 4)) ;
//			 System.out.println(Diem);
			
			hv.setDiem(Diem);
			hvDao.update(hv);
		
			msgBox.alert(this, "Cập Nhật Điểm Thành Công");
			this.loadDataToHocVien();
		}
//		for(int i =0; i <tblHocVien.getRowCount();i++) 
			
			
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HocVienJDialog dialog = new HocVienJDialog();
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
	public HocVienJDialog() {
		setTitle("EduSys - Quản Lý Học Viên");
		setIconImage(XImage.getAppIcon());
		setBounds(100, 100, 623, 358);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblChuyenDe = new Label("Chuyên Đề");
		lblChuyenDe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChuyenDe.setBounds(10, 10, 97, 22);
		contentPanel.add(lblChuyenDe);
		
		lblKhoaHoc = new Label("Khóa Học");
		lblKhoaHoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKhoaHoc.setBounds(316, 10, 97, 22);
		contentPanel.add(lblKhoaHoc);
		
		pnlChuyenDe = new JPanel();
		pnlChuyenDe.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlChuyenDe.setBounds(10, 38, 282, 44);
		contentPanel.add(pnlChuyenDe);
		pnlChuyenDe.setLayout(null);
		
		cboChuyenDe = new JComboBox();
		
		cboChuyenDe.setBounds(10, 11, 262, 22);
		pnlChuyenDe.add(cboChuyenDe);
		
		pnlKhoaHoc = new JPanel();
		pnlKhoaHoc.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlKhoaHoc.setLayout(null);
		pnlKhoaHoc.setBounds(316, 38, 282, 44);
		contentPanel.add(pnlKhoaHoc);
		
		cboKhoaHoc = new JComboBox();
		
		cboKhoaHoc.setBounds(10, 11, 262, 22);
		pnlKhoaHoc.add(cboKhoaHoc);
		
		Tabs = new JTabbedPane(JTabbedPane.TOP);
		Tabs.setBounds(10, 82, 588, 228);
		contentPanel.add(Tabs);
		
		pnlHocVien = new JPanel();
		Tabs.addTab("Học Viên", null, pnlHocVien, null);
		pnlHocVien.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 583, 173);
		pnlHocVien.add(scrollPane);
		
		tblHocVien = new JTable();
		
		tblHocVien.setModel(tableModelHV = new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"TT", "M\u00E3 HV", "M\u00E3 NH", "H\u1ECD T\u00EAn", "\u0110i\u1EC3m"
			}
		));
		scrollPane.setViewportView(tblHocVien);
		
		JButton btnUpdate = new JButton("Cập Nhật Điểm");
		btnUpdate.setBounds(434, 177, 139, 23);
		pnlHocVien.add(btnUpdate);
		
		btnDel = new JButton("Xóa khỏi khóa học");
		btnDel.setBounds(285, 177, 139, 23);
		pnlHocVien.add(btnDel);
		
		pnlNguoiHoc = new JPanel();
		Tabs.addTab("Người Học", null, pnlNguoiHoc, null);
		pnlNguoiHoc.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 57, 583, 119);
		pnlNguoiHoc.add(scrollPane_1);
		
		tblNguoiHoc = new JTable();
		tblNguoiHoc.setModel(tableModelNH=new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 NH", "H\u1ECD v\u00E0 T\u00EAn", "Gi\u1EDBi T\u00EDnh", "Ng\u00E0y Sinh", "\u0110i\u1EC7n Tho\u1EA1i", "Email"
			}
		));
		scrollPane_1.setViewportView(tblNguoiHoc);
		
		btnAdd = new JButton("Thêm Vào Khóa Học");
		btnAdd.setBounds(438, 177, 135, 23);
		pnlNguoiHoc.add(btnAdd);
		
		lblTimKiem = new JLabel("Tìm Kiếm");
		lblTimKiem.setBounds(7, 0, 100, 14);
		pnlNguoiHoc.add(lblTimKiem);
		
		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(7, 15, 566, 40);
		pnlNguoiHoc.add(panel);
		panel.setLayout(null);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(10, 11, 447, 20);
		panel.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		JButton btnTimKiem = new JButton("Tìm Kiếm");
		
		btnTimKiem.setBounds(467, 10, 89, 23);
		panel.add(btnTimKiem);
		//action listener
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addHocVien();
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateDiem();
			}
		});
		btnDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				removeHocVien();
			}
		});
		cboChuyenDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillComboBoxKhoaHoc();
			}
		});
//		tblHocVien.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				
//			}
//		});
		cboKhoaHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDataToHocVien();
			}
		});
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findDataToNguoiHoc();
			}
		});
		fillComboBoxChuyenDe();
		loadDataToNguoiHoc();
		fillComboBoxKhoaHoc();
	}
}
