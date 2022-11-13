/**
 * 
 */
package com.edusys.entity;

/**
 * @author Admin
 *
 */
public class NhanVien {
	private String maNV,matKhau,hoTen;
	private boolean vaiTro;
	
	 public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public boolean isVaiTro() {
		return vaiTro;
	}
	public void setVaiTro(boolean vaiTro) {
		this.vaiTro = vaiTro;
	}
	
	public NhanVien(String maNV, String matKhau, String hoTen, boolean vaiTro) {
		super();
		this.maNV = maNV;
		this.matKhau = matKhau;
		this.hoTen = hoTen;
		this.vaiTro = vaiTro;
	}

	/**
	 * 
	 */
	public NhanVien() {
		// TODO Auto-generated constructor stub
	}

}
