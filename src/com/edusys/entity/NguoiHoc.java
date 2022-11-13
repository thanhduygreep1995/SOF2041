/**
 * 
 */
package com.edusys.entity;

import java.util.Date;

/**
 * @author Admin
 *
 */
public class NguoiHoc {
	private String maNH , hoTen, dienThoai, Email, ghiChu, maNV;
	private Date ngaySinh, ngayDK;
	boolean gioiTinh;
	
	public String getMaNH() {
		return maNH;
	}
	public void setMaNH(String maNH) {
		this.maNH = maNH;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getDienThoai() {
		return dienThoai;
	}
	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public Date getNgayDK() {
		return ngayDK;
	}
	public void setNgayDK(Date ngayDK) {
		this.ngayDK = ngayDK;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	
	public NguoiHoc(String maNH, String hoTen, String dienThoai, String email, String ghiChu, String maNV,
			Date ngaySinh, Date ngayDK, boolean gioiTinh) {
		super();
		this.maNH = maNH;
		this.hoTen = hoTen;
		this.dienThoai = dienThoai;
		Email = email;
		this.ghiChu = ghiChu;
		this.maNV = maNV;
		this.ngaySinh = ngaySinh;
		this.ngayDK = ngayDK;
		this.gioiTinh = gioiTinh;
	}
	/**
	 * 
	 */
	public NguoiHoc() {
		// TODO Auto-generated constructor stub
	}

}
