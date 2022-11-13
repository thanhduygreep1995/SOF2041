package com.edusys.entity;

//import java.sql.Date;
import java.util.Date;
public class KhoaHoc {
	private int maKH ,thoiLuong;
	private String maCD , ghiChu, maNV ;
	private float hocPhi; 	
	private Date ngayKG, ngayTao;
	
	
	public int getMaKH() {
		return maKH;
	}


	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}


	public int getThoiLuong() {
		return thoiLuong;
	}


	public void setThoiLuong(int thoiLuong) {
		this.thoiLuong = thoiLuong;
	}


	public String getMaCD() {
		return maCD;
	}


	public void setMaCD(String maCD) {
		this.maCD = maCD;
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


	public float getHocPhi() {
		return hocPhi;
	}


	public void setHocPhi(float hocPhi) {
		this.hocPhi = hocPhi;
	}


	public Date getNgayKG() {
		return ngayKG;
	}


	public void setNgayKG(Date ngayKG) {
		this.ngayKG = ngayKG;
	}


	public Date getNgayTao() {
		return ngayTao;
	}


	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}


	public KhoaHoc(int maKH, int thoiLuong, String maCD, String ghiChu, String maNV, float hocPhi, Date ngayKG,
			Date ngayTao) {
		super();
		this.maKH = maKH;
		this.thoiLuong = thoiLuong;
		this.maCD = maCD;
		this.ghiChu = ghiChu;
		this.maNV = maNV;
		this.hocPhi = hocPhi;
		this.ngayKG = ngayKG;
		this.ngayTao = ngayTao;
	}


	public KhoaHoc() {
		// TODO Auto-generated constructor stub
	}

}
