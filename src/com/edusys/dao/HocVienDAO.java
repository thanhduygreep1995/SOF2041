package com.edusys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.edusys.entity.HocVien;
import com.edusys.entity.NhanVien;
import com.edusys.utils.jdbcHelper;

public class HocVienDAO extends EduSysDAO<HocVien, Integer> {
	String insert_sql = " INSERT INTO HocVien (maKH, maNH, Diem) VALUES (?, ?, ?) ";
	String update_sql = " UPDATE HocVien SET Diem = ? WHERE maHV = ? ";
	String delete_sql = " DELETE FROM HocVien WHERE maHV = ? ";
	String selectAll_sql = " SELECT * FROM HocVien ";
	String selectByID_sql = " SELECT * FROM HocVien WHERE maHV = ? ";
	
	@Override
	public void insert(HocVien entity) {
		// TODO Auto-generated method stub
		try {
			jdbcHelper.executeUpdate(insert_sql, entity.getMaKH(), entity.getMaNH(), entity.getDiem());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(HocVien entity) {
		// TODO Auto-generated method stub
		try {
			jdbcHelper.executeUpdate(update_sql, entity.getDiem(), entity.getMaHV());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer key) {
		// TODO Auto-generated method stub
		try {
			jdbcHelper.executeUpdate(delete_sql,key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<HocVien> selectAll() {
		// TODO Auto-generated method stub
		return selectBySql(selectAll_sql);
	}

	@Override
	public HocVien selectById(Integer key) {
		// TODO Auto-generated method stub
		List<HocVien> List = selectBySql(selectByID_sql, key);
//		if(List.isEmpty()) {
//			return null;
//		}
		return List.get(0);
	}

	@Override
	protected List<HocVien> selectBySql(String sql, Object... args) {
		// TODO Auto-generated method stub
		List<HocVien> List = new ArrayList<>();
		try {
			ResultSet rs = jdbcHelper.executeQuerry(sql, args);
			while(rs.next()) {
				HocVien entity = new HocVien();
				entity.setMaHV(rs.getInt("maHV"));
				entity.setMaKH(rs.getInt("maKH"));
				entity.setMaNH(rs.getString("maNH"));
				entity.setDiem(rs.getFloat("Diem"));
				List.add(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return List;
	}
	public List<HocVien> selectByKhoaHoc(int maKH){
		String sql = " SELECT * FROM HocVien Where maKH=? ";
		return this.selectBySql(sql, maKH);
	}
}
