package com.edusys.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.InputEvent;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JSplitPane;
import javax.swing.JSlider;
import javax.swing.JScrollBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.DebugGraphics;
import javax.swing.border.BevelBorder;
import java.awt.ComponentOrientation;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.xml.stream.events.StartDocument;

import com.edusys.utils.Auth;
import com.edusys.utils.XImage;
import com.edusys.utils.msgBox;

public class EduSysJFrame extends JFrame {
	public static URL path = ChaoJDialog.class.getResource("/com/edusys/icon/ongvangfpoly.png");
	private JPanel contentPane;
	private JLabel lblTime;
	private JLabel lblPicture;
	private JMenuItem mniDangNhap;
	private JMenuItem mniDangXuat;
	private JMenuItem mniDoiMatKhau;
	private JMenuItem mniKetThuc;
	private JMenuItem mniNguoiHoc;
	private JMenuItem mniChuyenDe;
	private JMenuItem mniKhoaHoc;
	private JMenuItem mniNhanVien;
	private JMenuItem mniNguoiHocTungNam;
	private JMenuItem mniBangDiemKhoa;
	private JMenuItem mniDiemTungKhoaHoc;
	private JMenuItem mniDoanhThuTungChuyenDe;
	private JMenuItem mniHuongDanSuDung;
	private JButton btnLogOut;
	private JButton btnEnd;
	private JButton btnChuyenDe;
	private JButton btnNguoiHoc;
	private JButton btnKhoaHoc;
	private JButton btnHuongDan;
	private JLabel lblTrangThai;
	private JMenuItem mniHocVien;
	private JButton btnHocVien;
	
	// chinh lblTime chay dong ho
	public void Timer() {

		new javax.swing.Timer(100, new ActionListener() {
			SimpleDateFormat SDF = new SimpleDateFormat("hh:mm:ss a");
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lblTime.setText(SDF.format(new Date()));
			}
		}).start();
	}
	
	
	// mo giao dien nhan vien
	public void openNhanVien() {
		if(Auth.isLogin()) {
			NhanVienJDialog frame = new NhanVienJDialog();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			
		}
		else {
			msgBox.alert(this, "Vui L??ng ????ng Nh???p");
		}
	}
	
	//mo giao dien khoa hoc
	public void openKhoaHoc() {
		if(Auth.isLogin()) {
			KhoaHocJDialog frame = new KhoaHocJDialog();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			
		}
		else {
			msgBox.alert(this, "Vui L??ng ????ng Nh???p");
		}
	}
	// mo giao dien chuyen de
	public void openChuyenDe() {
		if(Auth.isLogin()) {
			ChuyenDeJDialog frame = new ChuyenDeJDialog();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			
		}
		else {
			msgBox.alert(this, "Vui L??ng ????ng Nh???p");
		}
	}
	// mo giao dien doi mat khau
	public void openDoiMatKhau() {
		if(Auth.isLogin()) {
			DoiMatKhauJDialog frame = new DoiMatKhauJDialog();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.dispose();
		}
		else {
			msgBox.alert(this, "Vui L??ng ????ng Nh???p");
		}
	}
	// mo giao dien hoc vien
	public void openHocVien() {
		if(Auth.isLogin()) {
			HocVienJDialog frame = new HocVienJDialog();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			
		}
		else {
			msgBox.alert(this, "Vui L??ng ????ng Nh???p");
		}
	}
	// mo giao dien nguoi hoc
	public void openNguoiHoc() {
		if(Auth.isLogin()) {
			NguoiHocJDialog frame = new NguoiHocJDialog();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			
		}
		else {
			msgBox.alert(this, "Vui L??ng ????ng Nh???p");
		}
	}
	// gioi thieu
	public void openGioiThieu() {
		GioiThieuJDialog frame = new GioiThieuJDialog();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	//dang nhap
	public void openLogin() {
		LoginJDialog login =new LoginJDialog();
		login.setVisible(true);
		login.setLocationRelativeTo(null);
		this.dispose();
	}
	// mo giao dien thong ke
	public void openThongKe(int index) {
		if(Auth.isLogin()) {
			if (index==3 && !Auth.isManager()) {
				msgBox.alert(this, "B???n kh??ng c?? quy???n xem th??ng tin doanh thu");
			} else {
				ThongKeJDialog tkWin = new ThongKeJDialog();
				tkWin.setVisible(true);
				tkWin.selectTab(index);
			}
		}
		else {
			msgBox.alert(this, "Vui L??ng ????ng Nh???p");
		}
	}
	// dang xuat
	public void dangXuat() {
		Auth.clear();
		LoginJDialog login = new LoginJDialog();
		this.dispose();
		login.setVisible(true);
		login.setLocationRelativeTo(null);
	}
	// ket thuc
	public void ketThuc() {
		if(msgBox.showConfirmDialog(this,"B???n mu???n k???t th??c l??m vi???c")) {
			System.exit(0);
		}
	}
	// doi mat khau
	
	// huong dan
	public void openHuongDan() {
		try {
			Desktop.getDesktop().browse(new File("help/index.html").toURI());
		} catch (Exception e) {
			// TODO: handle exception
			msgBox.alert(this, "Kh??ng t??m th???y file h?????ng d???n");
		}
	}
	
	public ImageIcon resizeImage(URL Path) {
		// TODO Auto-generated method stub
		ImageIcon myImage = new ImageIcon(Path);
        Image img = myImage.getImage();
        Image newimg = img.getScaledInstance(lblPicture.getWidth(), lblPicture.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newimg);
        return image;
	}
	public ImageIcon resizeImage1(URL Path) {
		// TODO Auto-generated method stub
		ImageIcon myImage = new ImageIcon(Path);
        Image img = myImage.getImage();
        Image newimg = img.getScaledInstance(250, 300, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newimg);
        return image;
	}
	public void setImage(URL Path) {
		// TODO Auto-generated method stub
		
		try {
			lblPicture.setIcon(resizeImage1(Path));
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}
	


	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					EduSysJFrame frame = new EduSysJFrame();
//					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public EduSysJFrame() {
		
//		setAlwaysOnTop(true);
//		setVisible(true);
		setTitle("H??? Th???ng Qu???n L?? ????o T???o EduSys");
		setIconImage(XImage.getAppIcon());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 437);
		contentPane = new JPanel();
		contentPane.setVerifyInputWhenFocusTarget(false);
		contentPane.setLocation(new Point(50, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 633, 22);
		menuBar.setToolTipText("");
		contentPane.add(menuBar);
		
		JMenu mnuHeThong = new JMenu("H??? Th???ng");
		menuBar.add(mnuHeThong);
		
		mniDangNhap = new JMenuItem("????ng Nh???p");
		mniDangNhap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
		mniDangNhap.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Key.png")));
		mnuHeThong.add(mniDangNhap);
		
		mniDangXuat = new JMenuItem("????ng Xu???t");
		mniDangXuat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		mniDangXuat.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Exit.png")));
		mnuHeThong.add(mniDangXuat);
		
		JSeparator separator = new JSeparator();
		mnuHeThong.add(separator);
		
		mniDoiMatKhau = new JMenuItem("?????i M???t Kh???u");
		mniDoiMatKhau.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Refresh.png")));
		mnuHeThong.add(mniDoiMatKhau);
		
		JSeparator separator_1 = new JSeparator();
		mnuHeThong.add(separator_1);
		
		mniKetThuc = new JMenuItem("K???t Th??c");
		mniKetThuc.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Stop.png")));
		mniKetThuc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0));
		mnuHeThong.add(mniKetThuc);
		
		JMenu mnuQuanLy = new JMenu("Qu???n L??");
		menuBar.add(mnuQuanLy);
		
		mniNguoiHoc = new JMenuItem("Ng?????i H???c");
		mniNguoiHoc.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Conference.png")));
		mnuQuanLy.add(mniNguoiHoc);
		
		mniChuyenDe = new JMenuItem("Chuy??n ?????");
		mniChuyenDe.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Lists.png")));
		mnuQuanLy.add(mniChuyenDe);
		
		mniKhoaHoc = new JMenuItem("Kh??a H???c");
		mniKhoaHoc.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Certificate.png")));
		mnuQuanLy.add(mniKhoaHoc);
		
		mniNhanVien = new JMenuItem("Nh??n Vi??n");
		mniNhanVien.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/User group.png")));
		mnuQuanLy.add(mniNhanVien);
		
		mniHocVien = new JMenuItem("H???c Vi??n");
		mniHocVien.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/User.png")));
		mnuQuanLy.add(mniHocVien);
		
		JMenu mnuThongKe = new JMenu("Th???ng K??");
		menuBar.add(mnuThongKe);
		
		mniNguoiHocTungNam = new JMenuItem("Ng?????i H???c T???ng N??m");
		mniNguoiHocTungNam.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Clien list.png")));
		mnuThongKe.add(mniNguoiHocTungNam);
		
		JSeparator separator_2 = new JSeparator();
		mnuThongKe.add(separator_2);
		
		mniBangDiemKhoa = new JMenuItem("B???ng ??i???m Kh??a");
		mniBangDiemKhoa.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Card file.png")));
		mnuThongKe.add(mniBangDiemKhoa);
		
		mniDiemTungKhoaHoc = new JMenuItem("??i???m T???ng Kh??a H???c");
		mniDiemTungKhoaHoc.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Bar chart.png")));
		mnuThongKe.add(mniDiemTungKhoaHoc);
		
		JSeparator separator_3 = new JSeparator();
		mnuThongKe.add(separator_3);
		
		mniDoanhThuTungChuyenDe = new JMenuItem("Doanh Thu T???ng Chuy??n ?????");
		mniDoanhThuTungChuyenDe.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Dollar.png")));
		mnuThongKe.add(mniDoanhThuTungChuyenDe);
		
		JMenu mnuTroGiup = new JMenu("Tr??? Gi??p");
		menuBar.add(mnuTroGiup);
		
		mniHuongDanSuDung = new JMenuItem("H?????ng D???n S??? D???ng");
		mniHuongDanSuDung.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mniHuongDanSuDung.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Globe.png")));
		mnuTroGiup.add(mniHuongDanSuDung);
		
		JSeparator separator_4 = new JSeparator();
		mnuTroGiup.add(separator_4);
		
		JMenuItem mniGioiThieuSanPham = new JMenuItem("Gi???i Thi???u S???n Ph???m");
		mniGioiThieuSanPham.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Brick house.png")));
		mnuTroGiup.add(mniGioiThieuSanPham);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		toolBar.setOpaque(false);
		toolBar.setRollover(true);
		toolBar.setBounds(2, 21, 631, 68);
		contentPane.add(toolBar);
		
		JSeparator separator_5 = new JSeparator();
		
//		JSeparator separator_13 = new JSeparator();
//		separator_13.setMaximumSize(new Dimension(5, 0));
//		toolBar.add(separator_13);
//		
		btnLogOut = new JButton("????ng Xu???t");
		btnLogOut.setBorder(null);
		btnLogOut.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		btnLogOut.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogOut.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnLogOut.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Log out.png")));
		toolBar.add(btnLogOut);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setMaximumSize(new Dimension(5, 0));
		separator_8.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_8);
		
		btnEnd = new JButton("K???t Th??c");
		btnEnd.setBorder(null);
		btnEnd.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		btnEnd.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Stop.png")));
		btnEnd.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEnd.setVerticalTextPosition(SwingConstants.BOTTOM);
		toolBar.add(btnEnd);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setMaximumSize(new Dimension(5, 0));
		toolBar.add(separator_10);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(Color.LIGHT_GRAY);
		separator_6.setBackground(Color.LIGHT_GRAY);
		separator_6.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		separator_6.setBorder(null);
		separator_6.setMinimumSize(new Dimension(5, 0));
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setMaximumSize(new Dimension(5, 1000));
		toolBar.add(separator_6);
		
		btnChuyenDe = new JButton("Chuy??n ?????");
		btnChuyenDe.setHorizontalTextPosition(SwingConstants.CENTER);
		btnChuyenDe.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnChuyenDe.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Lists.png")));
		btnChuyenDe.setBorder(null);
		btnChuyenDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toolBar.add(btnChuyenDe);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setMaximumSize(new Dimension(5, 0));
		toolBar.add(separator_9);
		
		btnNguoiHoc = new JButton("Ng?????i H???c");
		btnNguoiHoc.setBorder(null);
		btnNguoiHoc.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/User group.png")));
		btnNguoiHoc.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNguoiHoc.setVerticalTextPosition(SwingConstants.BOTTOM);
		toolBar.add(btnNguoiHoc);
		
		JSeparator separator_11 = new JSeparator();
		separator_11.setMaximumSize(new Dimension(5, 0));
		toolBar.add(separator_11);
		
		btnKhoaHoc = new JButton("Kh??a H???c");
		btnKhoaHoc.setBorder(null);
		btnKhoaHoc.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Certificate.png")));
		btnKhoaHoc.setHorizontalTextPosition(SwingConstants.CENTER);
		btnKhoaHoc.setVerticalTextPosition(SwingConstants.BOTTOM);
		toolBar.add(btnKhoaHoc);
		
		JSeparator separator_12 = new JSeparator();
		separator_12.setMaximumSize(new Dimension(5, 0));
		toolBar.add(separator_12);
		
		btnHocVien = new JButton("H???c Vi??n");
		btnHocVien.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnHocVien.setHorizontalTextPosition(SwingConstants.CENTER);
		btnHocVien.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/User.png")));
		toolBar.add(btnHocVien);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setForeground(Color.LIGHT_GRAY);
		separator_7.setBackground(Color.LIGHT_GRAY);
		separator_7.setMaximumSize(new Dimension(5, 1000));
		separator_7.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_7);
		
		
		btnHuongDan = new JButton("H?????ng D???n");
		btnHuongDan.setBorder(null);
		btnHuongDan.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Globe.png")));
		btnHuongDan.setHorizontalTextPosition(SwingConstants.CENTER);
		btnHuongDan.setVerticalTextPosition(SwingConstants.BOTTOM);
		toolBar.add(btnHuongDan);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(0, 370, 633, 28);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblTrangThai = new JLabel("H??? Qu???n L?? ????o T???o");
		lblTrangThai.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Info.png")));
		lblTrangThai.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblTrangThai,BorderLayout.WEST);
		
		lblTime = new JLabel("");
		lblTime.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Alarm.png")));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTime,BorderLayout.EAST);
		
		lblPicture = new JLabel("");
		lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
		lblPicture.setHorizontalTextPosition(SwingConstants.CENTER);
//		lblPicture.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/ongvangfpoly.png")));
		lblPicture.setBounds(0, 90, 633, 281);
		setImage(path);
		contentPane.add(lblPicture);
		Timer();
		// Action listener
		// chuyen de
		btnChuyenDe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openChuyenDe();
			}
		});
		mniChuyenDe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openChuyenDe();
			}
		});
		// dang xuat
		btnLogOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dangXuat();
			}
		});
		mniDangXuat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dangXuat();
			}
		});
		//ket thuc
		btnEnd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ketThuc();
			}
		});
		mniKetThuc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ketThuc();
			}
		});
		//nguoi hoc
		btnNguoiHoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openNguoiHoc();
			}
		});
		mniNguoiHoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openNguoiHoc();
			}
		});
		//khoa hoc
		btnKhoaHoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openKhoaHoc();
			}
		});
		mniKhoaHoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openKhoaHoc();
			}
		});
		// Hoc vien
		btnHocVien.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openHocVien();
			}
		});
		mniHocVien.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openHocVien();
			}
		});
		// NhanVien
		mniNhanVien.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openNhanVien();
			}
		});
		//dang nhap
		mniDangNhap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openLogin();
			}
		});
		// nguoi hoc tung nam
		mniNguoiHocTungNam.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openThongKe(1);
			}
		});
		// bang diem
		mniBangDiemKhoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openThongKe(0);
			}
		});
		// diem chuyen de - diem tung khoa hoc
		mniDiemTungKhoaHoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openThongKe(2);
			}
		});
		// doanh thu
		mniDoanhThuTungChuyenDe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openThongKe(3);
			}
		});
		// gioi thieu
		mniGioiThieuSanPham.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openGioiThieu();
			}
		});
		// huong dan
		mniHuongDanSuDung.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openHuongDan();;
			}
		});
		btnHuongDan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openHuongDan();;
			}
		});
		// doi mat khau
		mniDoiMatKhau.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openDoiMatKhau();
			}
		});
	}
}
