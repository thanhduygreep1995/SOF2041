/**
 * 
 */
package com.edusys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.edusys.entity.ChuyenDe;
import com.edusys.entity.KhoaHoc;
import com.edusys.entity.NhanVien;
import com.edusys.utils.jdbcHelper;

/**
 * @author Admin
 *
 */
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String>{
	String insert_sql = " INSERT INTO ChuyenDe(maCD, tenCD, hocPhi, thoiLuong, Hinh, moTa) VALUES (?, ?, ?, ?, ?, ?) ";
	String update_sql = " UPDATE ChuyenDe SET tenCD = ?, hocPhi = ?, thoiLuong = ?, Hinh = ?, moTa = ? WHERE maCD = ? ";
	String delete_sql = " DELETE FROM ChuyenDe WHERE maCD = ? ";
	String selectAll_sql = " SELECT * FROM ChuyenDe ";
	String selectByID_sql = " SELECT * FROM ChuyenDe WHERE maCD = ? ";
	@Override
	public void insert(ChuyenDe entity) {
		// TODO Auto-generated method stub
		try {
			jdbcHelper.executeUpdate(insert_sql, entity.getMaCD(),entity.getTenCD(),
					entity.getHocPhi(),entity.getThoiLuong(),entity.getHinh(),entity.getMoTa());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void update(ChuyenDe entity) {
		// TODO Auto-generated method stub
		try {
			jdbcHelper.executeUpdate(update_sql,entity.getTenCD(), entity.getHocPhi(),entity.getThoiLuong()
					,entity.getHinh(),entity.getMoTa(), entity.getMaCD());
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
	public List<ChuyenDe> selectAll() {
		// TODO Auto-generated method stub
		return selectBySql(selectAll_sql);
	}
	@Override
	public ChuyenDe selectById(String key) {
		// TODO Auto-generated method stub
		List<ChuyenDe> List = selectBySql(selectByID_sql, key);
		if(List.isEmpty()) {
			return null;
		}
		return List.get(0);
	}
	@Override
	protected List<ChuyenDe> selectBySql(String sql, Object... args) {
		// TODO Auto-generated method stub
		List<ChuyenDe> List = new ArrayList<>();
		try {
			ResultSet rs = jdbcHelper.executeQuerry(sql, args);
			while(rs.next()) {
				ChuyenDe entity = new ChuyenDe();
				entity.setMaCD(rs.getString("maCD"));
				entity.setHocPhi(rs.getFloat("hocPhi"));
				entity.setTenCD(rs.getString("tenCD"));
				entity.setHinh(rs.getString("Hinh"));
				entity.setMoTa(rs.getString("moTa"));
				entity.setThoiLuong(rs.getInt("thoiLuong"));
				List.add(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return List;
	}
	public ChuyenDe selectByName(String keyword){
		String sql = " SELECT * FROM ChuyenDe WHERE tenCD like ? ";
		List<ChuyenDe> List = selectBySql(sql, "%" + keyword + "%");
		
		return List.get(0);
	}
	
}
