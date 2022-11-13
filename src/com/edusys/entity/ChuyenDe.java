/**
 * 
 */
package com.edusys.entity;

/**
 * @author Admin
 *
 */
public class ChuyenDe {
	private String maCD,tenCD, Hinh, moTa;
	private float hocPhi;
	private int thoiLuong;
	

	public String getMaCD() {
		return maCD;
	}


	public void setMaCD(String maCD) {
		this.maCD = maCD;
	}


	public String getTenCD() {
		return tenCD;
	}


	public void setTenCD(String tenCD) {
		this.tenCD = tenCD;
	}


	public String getHinh() {
		return Hinh;
	}


	public void setHinh(String hinh) {
		Hinh = hinh;
	}


	public String getMoTa() {
		return moTa;
	}


	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}


	public float getHocPhi() {
		return hocPhi;
	}


	public void setHocPhi(float hocPhi) {
		this.hocPhi = hocPhi;
	}


	public int getThoiLuong() {
		return thoiLuong;
	}


	public void setThoiLuong(int thoiLuong) {
		this.thoiLuong = thoiLuong;
	}


	public ChuyenDe(String maCD, String tenCD, String hinh, String moTa, float hocPhi, int thoiLuong) {
		super();
		this.maCD = maCD;
		this.tenCD = tenCD;
		Hinh = hinh;
		this.moTa = moTa;
		this.hocPhi = hocPhi;
		this.thoiLuong = thoiLuong;
	}


	/**
	 * 
	 */
	public ChuyenDe() {
		// TODO Auto-generated constructor stub
	}

}
