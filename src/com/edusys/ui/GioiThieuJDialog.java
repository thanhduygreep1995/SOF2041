package com.edusys.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.edusys.utils.XImage;
import java.awt.Font;
import javax.swing.JTextPane;

public class GioiThieuJDialog extends JDialog {

	public JLabel lbLogo = new JLabel("");
	public static URL path = ChaoJDialog.class.getResource("/com/edusys/icon/FPT_Polytechnic.png");
	private final JPanel contentPanel = new JPanel();
	int i = 0, num = 0;
	public ImageIcon resizeImage(URL Path) {
		// TODO Auto-generated method stub
		ImageIcon myImage = new ImageIcon(Path);
        Image img = myImage.getImage();
        Image newimg = img.getScaledInstance(lbLogo.getWidth(), lbLogo.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newimg);
        return image;
	}

	public void setImage(URL Path) {
		// TODO Auto-generated method stub
		
		try {
			lbLogo.setIcon(resizeImage(Path));
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
			GioiThieuJDialog dialog = new GioiThieuJDialog();
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
	public GioiThieuJDialog() {
		setUndecorated(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(XImage.getAppIcon());
		setTitle("Hệ Thống Quản Lý Đào Tạo EduSys");
		setBounds(100, 100, 923, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		lbLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lbLogo.setIcon(new ImageIcon());
		lbLogo.setBounds(10, 11, 887, 417);
		setImage(path);
//		ImageIcon imageIcon1 = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(lblPicture.getWidth(), lblPicture.getHeight(), Image.SCALE_SMOOTH)); //100, 100 add your own size
//		lblPicture.setIcon(imageIcon1);
//		lblPicture.setIcon(resizeImage(path));

		
		contentPanel.add(lbLogo);
		
		JTextPane txtIntro = new JTextPane();
		txtIntro.setBackground(new Color(240, 240, 240));
		txtIntro.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtIntro.setText("PolyPro là dự án mẫu. Mục tiêu chính là huấn luyện sinh viên quy trình thực hiện dự án.\r\n\r\nMục tiêu của dự án này là để rèn luyện kỹ năng IO (CDIO) tức yêu cầu sinh viên không cần phải thu thập phân tích mà chỉ thực hiện và vận hành một phần mềm chuẩn bị cho các dự án sau này. các kỹ năng CD(trong CDIO) sẽ được huấn luyện ở dự án 1 và dự án 2\r\n\r\nYêu cầu về môi trường:\r\n1. Hệ Điều Hành Bất Kỳ\r\n2. JDK 1.8 trở lên\r\n3. SQL Server 2008 trở lên");
		txtIntro.setEditable(false);
		txtIntro.setBounds(10, 439, 887, 138);
		contentPanel.add(txtIntro);
		setLocationRelativeTo(null);
//		Time();
//		LoginJDialog jdL= new LoginJDialog();
//		jdL.setVisible(true);
//		jdL.setLocationRelativeTo(null);
		
		
		
	}
}
