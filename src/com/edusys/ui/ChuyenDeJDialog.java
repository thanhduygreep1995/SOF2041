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
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import com.edusys.entity.ChuyenDe;
import com.edusys.utils.Auth;
import com.edusys.utils.DataValidation;
import com.edusys.utils.XImage;
import com.edusys.utils.msgBox;



import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class ChuyenDeJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtMaChuyenDe;
	private JTextField txtHocPhi;
	ButtonGroup bgr = new ButtonGroup();
	private JTextField txtChuyenDe;
	private JTextField txtThoiLuong;
	private JLabel lblPicture;
	private AbstractButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDel;
	private JButton btnNew;
	private JButton btnFirst;
	private JButton btnPrev;
	private JButton btnNext;
	private JButton btnLast;
	DefaultTableModel tableModel;
	public static String dataImage = "/com/edusys/icon/ongvangfpoly.png";
	public URL path = ChuyenDeJDialog.class.getResource(dataImage);
	
	private JTextArea txtMoTaChuyenDe;
	int current = -1 ;
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
	// chon Hinh
	public void uploadImg() {
		try {
			
			JFileChooser JFC = new JFileChooser("D:\\FPTpolytechnic\\SOF2041\\EduSys\\src\\com\\edusys\\icon");
			int returnVal = JFC.showOpenDialog(lblPicture);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
			
				
				BufferedImage img; // dÃ¹ng Ä‘á»ƒ lÆ°u áº£nh
				File file = JFC.getSelectedFile();
                img = ImageIO.read(file);
                String dataImage1 = "/com/edusys/icon/" + file.getName();
//                ChuyenDe cd = getForm();
                
                
//                path = file.getClass().getResource(dataImage);
                path = ChuyenDeJDialog.class.getResource(dataImage1);
                setImage(path);
                

			}
			
			
			} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
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
	//setText
	public void Display(int i) {
		ChuyenDeDAO Dao = new ChuyenDeDAO();
		List<ChuyenDe> ds = Dao.selectAll();
		ChuyenDe cd = ds.get(i);
		txtMaChuyenDe.setText(cd.getMaCD());
		txtChuyenDe.setText(cd.getTenCD());
		txtHocPhi.setText(String.format("%.2f", cd.getHocPhi()));
		txtMoTaChuyenDe.setText(cd.getMoTa());
		txtThoiLuong.setText(String.format("%d", cd.getThoiLuong()));
	}
	public void Display(ChuyenDe cd) {

		txtMaChuyenDe.setText(cd.getMaCD());
		txtChuyenDe.setText(cd.getTenCD());
		txtHocPhi.setText(String.format("%.2f", cd.getHocPhi()));
		txtMoTaChuyenDe.setText(cd.getMoTa());
		txtThoiLuong.setText(String.format("%d", cd.getThoiLuong()));
		path = ChuyenDeJDialog.class.getResource(cd.getHinh());
		setImage(path);
	}
	//get form tu danh sach
	public ChuyenDe getForm() {
		ChuyenDe cd = new ChuyenDe();
		cd.setMaCD(txtMaChuyenDe.getText().trim());
		cd.setTenCD(txtChuyenDe.getText().trim());
		cd.setHocPhi(Float.parseFloat(txtHocPhi.getText().trim()));
		cd.setMoTa(txtMoTaChuyenDe.getText().trim());
		cd.setHinh(dataImage);
		return cd;
	}
	// load data to table
	private void loadDataTable() {
		ChuyenDeDAO Dao = new ChuyenDeDAO();
		List<ChuyenDe> ds = Dao.selectAll();
		tableModel.setRowCount(0);
		for(ChuyenDe cd:ds) {
			tableModel.addRow(new Object[] {
					cd.getMaCD(),cd.getTenCD(),cd.getThoiLuong(),cd.getHocPhi(),cd.getHinh()
			});
		}
		tableModel.fireTableDataChanged();
	}
	// xoa chuyen de
	public void Delete() {
		StringBuilder sb = new StringBuilder();
		DataValidation.validateEmp(txtMaChuyenDe, sb, "Cần Nhập Mã Chuyên Đề");
		if(sb.length()>0) {
			msgBox.showErrorDialog(null, sb.toString());
			return;
		}
		ChuyenDeDAO Dao = new ChuyenDeDAO();
		ChuyenDe cd = Dao.selectById(txtMaChuyenDe.getText().trim());
		if(msgBox.showConfirmDialog(null, "Bạn Có Muốn Xoá Chuyên Đề Này Không Không")) {
			Dao.delete(txtMaChuyenDe.getText().trim());
			msgBox.alert(this, "Chuyên Đề Đã Được Xóa");
			loadDataTable();
		}else {
			msgBox.alert(this, "Chuyên Đề Chưa Được Xóa");
		}
	}
	// update chuyen de
	public void Update() {
		StringBuilder sb = new StringBuilder();
		DataValidation.validateEmp(txtChuyenDe, sb, "Cần Nhập Tên Chuyên Đề");
		DataValidation.validateEmp(txtMaChuyenDe, sb, "Cần Nhập Chuyên Đề");
		DataValidation.validateNumber(txtHocPhi, sb, "Cần Nhập Học Phí");
		DataValidation.validateNumber(txtThoiLuong, sb, "Cần Nhập thời lượng");
		DataValidation.validateEmp(txtMoTaChuyenDe, sb, "Cần Ghi Rõ Mô Tả Chuyên Đề");
		if(sb.length()>0) {
			msgBox.showErrorDialog(null, sb.toString());
			return;
		}
		ChuyenDeDAO Dao = new ChuyenDeDAO();
		ChuyenDe cd = getForm();
		if(msgBox.showConfirmDialog(this, "Bạn Có Muốn Cập Nhật Chuyên Đề Này Không Không")) {
			Dao.update(cd);
			msgBox.alert(this, "Chuyên Đề Đã Được Cập Nhật");
			loadDataTable();
		}else {
			msgBox.alert(this, "Chuyên Đề Chưa Được Cập Nhật");
		}
	}
	// insert chuyen de
	public void Insert() {
		StringBuilder sb = new StringBuilder();
		DataValidation.validateEmp(txtChuyenDe, sb, "Cần Nhập Tên Chuyên Đề");
		DataValidation.validateEmp(txtMaChuyenDe, sb, "Cần Nhập Mã Chuyên Đề");
		DataValidation.validateNumber(txtHocPhi, sb, "Cần Nhập Học Phí");
		DataValidation.validateNumber(txtThoiLuong, sb, "Cần Nhập thời lượng");
		DataValidation.validateEmp(txtMoTaChuyenDe, sb, "Cần Ghi Rõ Mô Tả Chuyên Đề");
		
		if(sb.length()>0) {
			msgBox.showErrorDialog(null, sb.toString());
			return;
		}
		ChuyenDeDAO Dao = new ChuyenDeDAO();
		ChuyenDe cd = getForm();
		if(msgBox.showConfirmDialog(this, "Bạn Có Muốn Thêm Chuyên Đề Này Không Không")) {
			Dao.insert(cd);
			msgBox.alert(this, "Chuyên Đề Đã Được Thêm");
			loadDataTable();
		}else {
			msgBox.alert(this, "Chuyên Đề Chưa Được Thêm");
		}
	}
	// empty form
	public void New() {
		txtChuyenDe.setText("");
		txtHocPhi.setText("");
		txtMaChuyenDe.setText("");
		txtMoTaChuyenDe.setText("");
		txtThoiLuong.setText("");
		lblPicture.setIcon(null);
	}
	// xu ly su kien click bang
	public void clickDataTableToForm() {
		int row = table.getSelectedRow();
		
		if(row>=0) {
			String id = (String) table.getValueAt(row, 0);
			ChuyenDeDAO Dao = new ChuyenDeDAO();
			ChuyenDe cd = Dao.selectById(id);
			Display(cd);
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
			ChuyenDeJDialog dialog = new ChuyenDeJDialog();
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
	public ChuyenDeJDialog() {
		setIconImage(XImage.getAppIcon());
		setTitle("Quản Lý Chuyên Đề");
		setBounds(100, 100, 578, 467);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBorder(null);
			tabbedPane.setBounds(0, 34, 561, 394);
			contentPanel.add(tabbedPane);
			
			JPanel pnlCapNhat = new JPanel();
			tabbedPane.addTab("Cập Nhật", null, pnlCapNhat, null);
			pnlCapNhat.setLayout(null);
			
			JLabel lblChuyenDe = new JLabel("Mã Chuyên Đề");
			lblChuyenDe.setBounds(191, 3, 106, 22);
			pnlCapNhat.add(lblChuyenDe);
			
			txtMaChuyenDe = new JTextField();
			txtMaChuyenDe.setBounds(191, 28, 359, 20);
			pnlCapNhat.add(txtMaChuyenDe);
			txtMaChuyenDe.setColumns(10);
			
			JLabel lblTenChuyenDe = new JLabel("Tên Chuyên Đề");
			lblTenChuyenDe.setBounds(191, 55, 106, 22);
			pnlCapNhat.add(lblTenChuyenDe);
			
			JLabel lblThoiLuong = new JLabel("Thời Lượng(giờ)");
			lblThoiLuong.setBounds(191, 112, 118, 22);
			pnlCapNhat.add(lblThoiLuong);
			
			JLabel lblHocPhi = new JLabel("Học Phí");
			lblHocPhi.setBounds(191, 162, 106, 22);
			pnlCapNhat.add(lblHocPhi);
			
			txtHocPhi = new JTextField();
			txtHocPhi.setBounds(191, 187, 359, 20);
			pnlCapNhat.add(txtHocPhi);
			txtHocPhi.setColumns(10);
			
			JLabel lblMoTa = new JLabel("Mô Tả Chuyên Đề");
			lblMoTa.setBounds(5, 211, 118, 14);
			pnlCapNhat.add(lblMoTa);
			
			btnAdd = new JButton("Thêm");
			btnAdd.setBounds(4, 340, 66, 23);
			pnlCapNhat.add(btnAdd);
			
			btnUpdate = new JButton("Sửa");
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
			btnLast.setBounds(488, 340, 60, 23);
			pnlCapNhat.add(btnLast);
			
			txtChuyenDe = new JTextField();
			txtChuyenDe.setColumns(10);
			txtChuyenDe.setBounds(191, 81, 359, 20);
			pnlCapNhat.add(txtChuyenDe);
			
			txtThoiLuong = new JTextField();
			txtThoiLuong.setColumns(10);
			txtThoiLuong.setBounds(191, 137, 359, 20);
			pnlCapNhat.add(txtThoiLuong);
			
			lblPicture = new JLabel("");
			
			lblPicture.setBounds(5, 7, 176, 199);
			pnlCapNhat.add(lblPicture);
			
			txtMoTaChuyenDe = new JTextArea();
			txtMoTaChuyenDe.setBounds(5, 225, 545, 104);
			pnlCapNhat.add(txtMoTaChuyenDe);
			
			setImage(path);
			
			JPanel pnlDanhSach = new JPanel();
			tabbedPane.addTab("Danh Sách", null, pnlDanhSach, null);
			pnlDanhSach.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 554, 366);
			pnlDanhSach.add(scrollPane);
			
			table = new JTable();
			table.setModel(tableModel = new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
					{null, null, null, null, null},
				},
				new String[] {
					"M\u00E3 Chuy\u00EAn \u0110\u1EC1", "T\u00EAn Chuy\u00EAn \u0110\u1EC1", "Th\u1EDDi L\u01B0\u1EE3ng(Gi\u1EDD)", "H\u1ECDc Ph\u00ED", "H\u00ECnh"
				}
			));
			table.getColumnModel().getColumn(0).setPreferredWidth(104);
			table.getColumnModel().getColumn(1).setPreferredWidth(93);
			table.getColumnModel().getColumn(2).setPreferredWidth(95);
			table.getColumnModel().getColumn(4).setPreferredWidth(116);
			scrollPane.setViewportView(table);
		}
		
		JLabel lblNewLabel = new JLabel("Quản Lý Chuyên Đề");
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
		
		lblPicture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				uploadImg();
			}
		});
		Role();
		loadDataTable();
	}



}
