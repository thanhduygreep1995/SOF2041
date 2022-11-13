package com.edusys.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.edusys.dao.NhanVienDAO;
import com.edusys.utils.Auth;
import com.edusys.utils.XImage;
import com.edusys.utils.msgBox;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class DoiMatKhauJDialog extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JTextField txtMaNV;
	private JPasswordField txtMatKhauCu;
	private JPasswordField txtMatKhauMoi;
	private JPasswordField txtXacNhanMatKhau;
	NhanVienDAO DAO = new NhanVienDAO();
	private JLabel lblDoiMatKhauMoi;
	private JLabel lblTenDangNhap;
	private JLabel lblMatKhauHienTai;
	private JLabel lblXacNhanDoiMatKhau;
	private JButton btnDongY;
	private JButton btnHuyBo;
	
	private void doiMatKhau() {
		String maNV = txtMaNV.getText();
		String matKhau = new String(txtMatKhauCu.getPassword());
		String matKhauMoi = new String(txtMatKhauMoi.getPassword());
		String xacNhanMK = new String(txtXacNhanMatKhau.getPassword());
		if(!maNV.equalsIgnoreCase(Auth.user.getMaNV())) {
			msgBox.alert(this, "Sai Tên Đăng Nhập");
		}else if(!matKhau.equalsIgnoreCase(Auth.user.getMatKhau())) {
			msgBox.alert(this, "Sai Tên Mật Khẩu");
		}else if(!matKhauMoi.equals(xacNhanMK)) {
			msgBox.alert(this, "Xác Nhận Mật Khẩu Sai");
		}else {
			Auth.user.setMatKhau(matKhauMoi);
			DAO.update(Auth.user);
			msgBox.alert(this, "Đổi Mật Khẩu Thành Công");
		}
	}
	private void huyBo() {
		this.dispose();
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DoiMatKhauJDialog dialog = new DoiMatKhauJDialog();
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
	public DoiMatKhauJDialog() {
		setTitle("EduSys - Đổi Mật Khẩu");
		setIconImage(XImage.getAppIcon());
		setBounds(100, 100, 450, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Đổi Mật Khẩu");
			lblNewLabel.setForeground(new Color(0, 128, 64));
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(10, 11, 119, 25);
			contentPanel.add(lblNewLabel);
		}
		{
			lblTenDangNhap = new JLabel("Tên Đăng Nhập");
			lblTenDangNhap.setBounds(10, 47, 100, 14);
			contentPanel.add(lblTenDangNhap);
		}
		{
			lblMatKhauHienTai = new JLabel("Mật Khẩu Hiện Tại");
			lblMatKhauHienTai.setBounds(238, 47, 119, 14);
			contentPanel.add(lblMatKhauHienTai);
		}
		
		txtMaNV = new JTextField();
		txtMaNV.setBounds(10, 65, 175, 20);
		contentPanel.add(txtMaNV);
		txtMaNV.setColumns(10);
		
		lblDoiMatKhauMoi = new JLabel("Đổi Mật Khẩu Mới");
		lblDoiMatKhauMoi.setBounds(10, 96, 119, 14);
		contentPanel.add(lblDoiMatKhauMoi);
		
		lblXacNhanDoiMatKhau = new JLabel("Xác Nhận Đổi Mật Khẩu");
		lblXacNhanDoiMatKhau.setBounds(238, 96, 145, 14);
		contentPanel.add(lblXacNhanDoiMatKhau);
		
		txtMatKhauCu = new JPasswordField();
		txtMatKhauCu.setBounds(238, 65, 175, 20);
		contentPanel.add(txtMatKhauCu);
		
		txtMatKhauMoi = new JPasswordField();
		txtMatKhauMoi.setBounds(10, 117, 175, 20);
		contentPanel.add(txtMatKhauMoi);
		
		txtXacNhanMatKhau = new JPasswordField();
		txtXacNhanMatKhau.setBounds(238, 117, 175, 20);
		contentPanel.add(txtXacNhanMatKhau);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnDongY = new JButton("Đồng Ý");
				btnDongY.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						doiMatKhau();
					}
				});
				btnDongY.setIcon(new ImageIcon(DoiMatKhauJDialog.class.getResource("/com/edusys/icon/Refresh.png")));
				btnDongY.setActionCommand("OK");
				buttonPane.add(btnDongY);
				getRootPane().setDefaultButton(btnDongY);
			}
			{
				btnHuyBo = new JButton("Hủy Bỏ");
				btnHuyBo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						huyBo();
					}
				});
				btnHuyBo.setIcon(new ImageIcon(DoiMatKhauJDialog.class.getResource("/com/edusys/icon/No.png")));
				btnHuyBo.setActionCommand("Cancel");
				buttonPane.add(btnHuyBo);
			}
		}
	}
}
