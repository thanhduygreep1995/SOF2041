package com.edusys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.edusys.entity.NguoiHoc;
import com.edusys.entity.NhanVien;
import com.edusys.utils.jdbcHelper;

public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String> {
	String insert_sql = " INSERT INTO NguoiHoc(maNH, hoTen, ngaySinh , gioiTinh, dienThoai, Email, ghiChu, maNV, ngayDK) "
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	String update_sql = " UPDATE NguoiHoc SET hoTen = ?, ngaySinh = ?, gioiTinh = ?, dienThoai = ?,"
			+ " Email = ?, ghiChu = ?, maNV = ?, ngayDK = ? WHERE maNH = ? ";
	String delete_sql = " DELETE FROM NguoiHoc WHERE maNH = ? ";
	String selectAll_sql = " SELECT * FROM NguoiHoc ";
	String selectByID_sql = " SELECT * FROM NguoiHoc WHERE maNH = ?";
	@Override
	public void insert(NguoiHoc entity) {
		// TODO Auto-generated method stub
		try {
			jdbcHelper.executeUpdate(insert_sql, entity.getMaNH(), entity.getHoTen(), entity.getNgaySinh(),
					entity.isGioiTinh(),entity.getDienThoai(),entity.getEmail(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayDK());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(NguoiHoc entity) {
		// TODO Auto-generated method stub
		try {
			jdbcHelper.executeUpdate(update_sql, entity.getHoTen(), entity.getNgaySinh(), entity.isGioiTinh()
					,entity.getDienThoai(),entity.getEmail(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayDK(), entity.getMaNH());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub
		try {
			jdbcHelper.executeUpdate(delete_sql,key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<NguoiHoc> selectAll() {
		// TODO Auto-generated method stub
		return selectBySql(selectAll_sql);
	}

	@Override
	public NguoiHoc selectById(String key) {
		// TODO Auto-generated method stub
		List<NguoiHoc> List = selectBySql(selectByID_sql, key);
		if(List.isEmpty()) {
			return null;
		}
		return List.get(0);
	}


	@Override
	protected List<NguoiHoc> selectBySql(String sql, Object... args) {
		// TODO Auto-generated method stub
		List<NguoiHoc> List = new ArrayList<>();
		try {
			ResultSet rs = jdbcHelper.executeQuerry(sql, args);
			while(rs.next()) {
				NguoiHoc entity = new NguoiHoc();
				entity.setMaNH(rs.getString("maNH"));
				entity.setHoTen(rs.getString("hoTen"));
				entity.setNgaySinh(rs.getDate("ngaySinh"));
				entity.setGioiTinh(rs.getBoolean("gioiTinh"));
				entity.setDienThoai(rs.getString("dienThoai"));
				entity.setEmail(rs.getString("Email"));
				entity.setGhiChu(rs.getString("ghiChu"));
				entity.setMaNV(rs.getString("maNV"));
				entity.setNgayDK(rs.getDate("ngayDK"));
				List.add(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return List;	
	}
	
	public List<NguoiHoc> selectByKeyword(String keyword){
		String sql = " SELECT * FROM NguoiHoc WHERE hoTen like ? ";
		return this.selectBySql(sql, "%" + keyword + "%");
	}
	
	public List<NguoiHoc> selectNotInCourse(int maKh, String keyWord){
		String sql = " Select * From NguoiHoc "
				+ " Where hoTen like ? And "
				+ " maNH not in (Select MaNH From HocVien Where maKH = ?) ";
		return this.selectBySql(sql, "%" + keyWord + "%", maKh);
	}
	
}
