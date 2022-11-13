package com.edusys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.edusys.utils.jdbcHelper;

public class ThongKeDAO {
	public List<Object[]>getNguoiHoc(){
		List<Object[]> List = new ArrayList<>();
		try {
			ResultSet rs;
			try {
				String sql = " {call sp_ThongKeNguoiHoc} ";
				rs = jdbcHelper.executeQuerry(sql);
				while(rs.next()) {
					Object[] model = {
							rs.getInt("Nam"),
							rs.getInt("soLuong"),
							rs.getDate("dauTien"),
							rs.getDate("cuoiCung")
					};
					List.add(model);
				}
				rs.getStatement().getConnection().close();
			} catch (Exception e1) {
				// TODO: handle exception
				throw new RuntimeException();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException();
		}
		return List;
	}
	
	public List<Object[]>getBangDiem(Integer maKH){
		List<Object[]> List = new ArrayList<>();
		try {
			ResultSet rs;
			try {
				String sql = " {call sp_BangDiem (?)} ";
				rs = jdbcHelper.executeQuerry(sql, maKH);
				while(rs.next()) {
					double diem = rs.getDouble("Diem");
					String xepLoai = "Xuất Sắc";
					if (diem<0) {
						xepLoai ="chưa nhập";
					}
					else if(diem < 3) {
						xepLoai ="Kém";
					}
					else if(diem<5) {
						xepLoai ="Yếu";
					}
					else if (diem < 6.5) {
						xepLoai ="Trung Bình";
					}
					else if (diem < 7.5) {
						xepLoai ="Khá";
					}else if (diem < 9) {
						xepLoai ="Giỏi";
					}
					Object[] model = {
							rs.getString("maNH"),
							rs.getString("hoTen"),
							diem,
							xepLoai
					};
					List.add(model);
				}
				rs.getStatement().getConnection().close();
			} catch (Exception e1) {
				// TODO: handle exception
				throw new RuntimeException();
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
		return List;
		
	}
	public List<Object[]>getDiemChuyenDe(){
		List<Object[]> List = new ArrayList<>();
		try {
			ResultSet rs;
			try {
				String sql = " {call sp_ThongKeDiem} ";
				rs = jdbcHelper.executeQuerry(sql);
				while(rs.next()) {
					
					Object[] model = {
							rs.getString("ChuyenDe"),
							rs.getInt("soHV"),
							rs.getFloat("thapNhat"),
							rs.getFloat("caoNhat"),
							rs.getFloat("trungBinh")
					};
					List.add(model);
				}
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				// TODO: handle exception
				throw new RuntimeException();
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
		return List;
	}
	public List<Object[]>getDoanhThu(int Nam){
		List<Object[]> List = new ArrayList<>();
		try {
			ResultSet rs;
			try {
				String sql = " {call sp_ThongKeDoanhThu(?)} ";
				rs = jdbcHelper.executeQuerry(sql, Nam);
				while(rs.next()) {
					
					Object[] model = {
							rs.getString("ChuyenDe"),
							rs.getInt("soKH"),
							rs.getInt("soHV"),
							rs.getFloat("DoanhThu"),
							rs.getFloat("thapNhat"),
							rs.getFloat("caoNhat"),
							rs.getFloat("trungBinh")
					};
					List.add(model);
					
				}
				rs.getStatement().getConnection().close();
			} catch (Exception e1) {
				// TODO: handle exception
				throw new RuntimeException();
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
		return List;
	}
}
