package com.edusys.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.edusys.utils.XImage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Toolkit;


public class ChaoJDialog extends JDialog {
	public JLabel lbLogo = new JLabel("");
	public static URL path = ChaoJDialog.class.getResource("/com/edusys/icon/FPT_Polytechnic.png");
	private final JPanel contentPanel = new JPanel();
	private JProgressBar pgbChao;
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
	public void iterate() {
        while (i <= 100) {
            pgbChao.setValue(i);
            i = i + 1;
            try {
                Thread.sleep(20);
            } catch (Exception e) {
            }
        }   
	}
//	public boolean Time() {
//		javax.swing.Timer Time = null;
//		Time = new javax.swing.Timer(10, new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				int Value = pgbChao.getValue();
//				
//				if(Value <pgbChao.getMaximum()) {
//					pgbChao.setValue(Value+1);
//				}else {
//					ChaoJDialog.this.dispose();
//					
//				}
//			}
//		});
//		Time.start();
//
//		return true;
//	}
	

	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChaoJDialog dialog = new ChaoJDialog();
			
//			dialog.setVisible(true);
//			dialog.setLocationRelativeTo(null);
//			dialog.iterate();
//			dialog.dispose();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChaoJDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setIconImage(XImage.getAppIcon());
		setTitle("Hệ Thống Quản Lý Đào Tạo EduSys");
		setBounds(100, 100, 923, 497);
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
		
		pgbChao = new JProgressBar();
		pgbChao.setForeground(Color.ORANGE);
		pgbChao.setBounds(0, 434, 907, 25);
		pgbChao.setValue(0);
        pgbChao.setStringPainted(true);
        
        
		contentPanel.add(pgbChao);
		
		
		setVisible(true);
		setLocationRelativeTo(null);
		
		
//		LoginJDialog jdL= new LoginJDialog();
//		jdL.setVisible(true);
//		jdL.setLocationRelativeTo(null);
		iterate();
		dispose();
		LoginJDialog jdL = new LoginJDialog();
		jdL.setVisible(true);
		jdL.setLocationRelativeTo(null);
		
	}
}
