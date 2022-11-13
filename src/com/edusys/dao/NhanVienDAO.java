/**
 * 
 */
package com.edusys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.edusys.entity.NhanVien;
import com.edusys.utils.jdbcHelper;

/**
 * @author Admin
 *
 */
public class NhanVienDAO extends EduSysDAO<NhanVien, String > {
	String insert_sql = " INSERT INTO NhanVien (maNV, matKhau, hoTen, vaiTro) VALUES (?, ?, ?, ?) ";
	String update_sql = " UPDATE NhanVien SET matKhau = ?, hoTen = ?, vaiTro = ? WHERE maNV = ? ";
	String delete_sql = " DELETE FROM NhanVien WHERE maNV = ? ";
	String selectAll_sql = " SELECT * FROM NhanVien ";
	String selectByID_sql = " SELECT * FROM NhanVien WHERE maNV = ? ";
	@Override
	public void insert(NhanVien entity) {
		// TODO Auto-generated method stub
		try {
			jdbcHelper.executeUpdate(insert_sql, entity.getMaNV(), entity.getMatKhau(), entity.getHoTen(),entity.isVaiTro());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(NhanVien entity) {
		// TODO Auto-generated method stub
		try {
			jdbcHelper.executeUpdate(update_sql, entity.getMatKhau(), entity.getHoTen(),entity.isVaiTro(), entity.getMaNV());
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
	public List<NhanVien> selectAll() {
		// TODO Auto-generated method stub
		return selectBySql(selectAll_sql);
	}

	@Override
	public NhanVien selectById(String key) {
		// TODO Auto-generated method stub
		List<NhanVien> List = selectBySql(selectByID_sql, key);
		if(List.isEmpty()) {
			return null;
		}
		return List.get(0);
	}

	@Override
	protected List<NhanVien> selectBySql(String sql, Object... args) {
		// TODO Auto-generated method stub
		List<NhanVien> List = new ArrayList<>();
		try {
			ResultSet rs = jdbcHelper.executeQuerry(sql, args);
			
			while(rs.next()) {
				NhanVien entity = new NhanVien();
				entity.setMaNV(rs.getString(1));
				entity.setMatKhau(rs.getString(2));
				entity.setHoTen(rs.getString(3));
				entity.setVaiTro(rs.getBoolean(4));
				List.add(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return List;
	}

}
