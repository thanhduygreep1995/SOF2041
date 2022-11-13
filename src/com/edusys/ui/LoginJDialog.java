package com.edusys.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.edusys.dao.NhanVienDAO;
import com.edusys.entity.NhanVien;
import com.edusys.utils.Auth;
import com.edusys.utils.DataValidation;
import com.edusys.utils.XImage;
import com.edusys.utils.msgBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.WindowConstants;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JPasswordField;
public class LoginJDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JLabel lblPicture = new JLabel("");
	public static URL path = ChaoJDialog.class.getResource("/com/edusys/icon/ongvangfpoly.png");
	private JTextField txtLogin;
	private JButton btnLogin;
	private JPasswordField txtPass;
	//xu ly dang nhap
	public void accessRight() {
		// TODO Auto-generated method stub
		String Login = txtLogin .getText().trim();
		String Pass = String.valueOf(txtPass.getPassword());
		NhanVienDAO dao = new NhanVienDAO();
		
		NhanVien nv = dao.selectById(Login);
		StringBuilder sb = new StringBuilder();
		DataValidation.validateEmp(txtLogin, sb, "Cần Nhập UserName");
		DataValidation.validateEmp(txtPass, sb, "Cần Nhập Pass");
		if(sb.length()>0) {
			msgBox.showErrorDialog(null, sb.toString());
			return;
		}
		if(nv!=null) {
			if(nv.getMatKhau().equals(Pass)) {
				Auth.user = nv;
				msgBox.alert(this, "Đăng Nhập Thành Công" + "\n Chúc Bạn " + nv.getHoTen() + " Một Ngày Tốt Lành");
				this.dispose();
				EduSysJFrame frame = new EduSysJFrame();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				
			}else {
				msgBox.alert(this, "Đăng Nhập Thất Bại Do Sai Mật Khẩu\n Vui Lòng Thử Lại");
			}
		}else {
			msgBox.alert(this, "Đăng Nhập Thất Bại Do Sai Mật Khẩu\n Vui Lòng Thử Lại");
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

	public void setImage(URL Path) {
		// TODO Auto-generated method stub
		
		try {
			lblPicture.setIcon(resizeImage(Path));
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginJDialog dialog = new LoginJDialog();
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
	public LoginJDialog() {
		setIconImage(XImage.getAppIcon());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setTitle("EduSys Đăng Nhập Hệ Thống");
		setBounds(100, 100, 374, 212);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
//		lblPicture.setIcon(new ImageIcon("D:\\FPTpolytechnic\\SOF2041\\Hinh\\ongvangfpoly.png"));
		
		lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
		lblPicture.setBounds(0, 0, 139, 173);
		setImage(path);
		contentPanel.add(lblPicture);
		
		JLabel lblLogin = new JLabel("Tên Đăng Nhập:");
		lblLogin.setBounds(150, 12, 101, 14);
		contentPanel.add(lblLogin);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(149, 32, 200, 20);
		contentPanel.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblPass = new JLabel("Mật Khẩu:");
		lblPass.setBounds(149, 69, 82, 14);
		contentPanel.add(lblPass);
		
		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setIcon(new ImageIcon(LoginJDialog.class.getResource("/com/edusys/icon/Log out.png")));
		btnExit.setBounds(251, 113, 97, 56);
		
		btnExit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExit.setVerticalTextPosition(SwingConstants.BOTTOM);
		contentPanel.add(btnExit);
		
		btnLogin = new JButton("Đăng Nhập");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accessRight();
			}
		});
		btnLogin.setIcon(new ImageIcon(LoginJDialog.class.getResource("/com/edusys/icon/Key.png")));
		btnLogin.setBounds(149, 113, 97, 56);
		btnLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogin.setVerticalTextPosition(SwingConstants.BOTTOM);
		contentPanel.add(btnLogin);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(149, 83, 200, 20);
		contentPanel.add(txtPass);
		
	}
}
