package com.edusys.utils;

import java.awt.Component;

import javax.swing.JOptionPane;

public class msgBox {
	public static void alert(Component parent, String message) {
		JOptionPane.showMessageDialog(parent, message, "Hệ Thống Quản Lý Đào Tạo", JOptionPane.INFORMATION_MESSAGE);
	}
	public static boolean showConfirmDialog(Component parent,String message) {
		int Choose = JOptionPane.showConfirmDialog(parent,message,"Hệ Thống Quản Lý Đào Tạo", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		return Choose == JOptionPane.YES_OPTION;
	}
	public static String prompt(Component parent, String message) {
		return JOptionPane.showInputDialog(parent, message, "Hệ Thống Quản Lý Đào Tạo", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void showErrorDialog(Component Com,String Content) {
		JOptionPane.showMessageDialog(Com, Content, "Hệ Thống Quản Lý Đào Tạo", JOptionPane.ERROR_MESSAGE);
	}
}
