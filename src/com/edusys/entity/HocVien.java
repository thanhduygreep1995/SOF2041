/**
 * 
 */
package com.edusys.entity;

/**
 * @author Admin
 *
 */
public class HocVien {
	private int maHV, maKH;
	private String maNH;
	private float Diem;
	
	
	public int getMaHV() {
		return maHV;
	}


	public void setMaHV(int maHV) {
		this.maHV = maHV;
	}


	public int getMaKH() {
		return maKH;
	}


	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}


	public String getMaNH() {
		return maNH;
	}


	public void setMaNH(String maNH) {
		this.maNH = maNH;
	}


	public float getDiem() {
		return Diem;
	}


	public void setDiem(float diem) {
		Diem = diem;
	}


	public HocVien(int maHV, int maKH, String maNH, float diem) {
		super();
		this.maHV = maHV;
		this.maKH = maKH;
		this.maNH = maNH;
		Diem = diem;
	}


	/**
	 * 
	 */
	public HocVien() {
		// TODO Auto-generated constructor stub
	}

}
