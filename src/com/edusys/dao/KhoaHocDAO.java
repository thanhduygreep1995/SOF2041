package com.edusys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.edusys.entity.ChuyenDe;
import com.edusys.entity.KhoaHoc;
import com.edusys.entity.NhanVien;
import com.edusys.utils.jdbcHelper;

public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer> {
	String insert_sql = " INSERT INTO KhoaHoc(maCD, hocPhi, thoiLuong, ngayKG, ghiChu, maNV, ngayTao) VALUES (?, ?, ?, ?, ?, ?, ?) ";
	String update_sql = " UPDATE KhoaHoc SET maCD =?, hocPhi =?, thoiLuong =?, ngayKG =?, ghiChu =?, maNV =?, ngayTao =? WHERE maKH =? ";
	String delete_sql = " DELETE FROM KhoaHoc WHERE maKH = ? ";
	String selectAll_sql = " SELECT * FROM KhoaHoc ";
	String selectByID_sql = " SELECT * FROM KhoaHoc WHERE maKH = ? ";
	List<KhoaHoc> List;
	List<ChuyenDe> List1;
	@Override
	public void insert(KhoaHoc entity) {
		// TODO Auto-generated method stub
		try {
			jdbcHelper.executeUpdate(insert_sql, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong()
					,entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void update(KhoaHoc entity) {
		// TODO Auto-generated method stub
		try {
			jdbcHelper.executeUpdate(update_sql, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong()
					,entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao(),entity.getMaKH());
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
	public List<KhoaHoc> selectAll() {
		// TODO Auto-generated method stub
		return selectBySql(selectAll_sql);
	}
	@Override
	public KhoaHoc selectById(Integer key) {
		// TODO Auto-generated method stub
		List<KhoaHoc> List = selectBySql(selectByID_sql, key);
		if(List.isEmpty()) {
			return null;
		}
		return List.get(0);
	}
	@Override
	protected List<KhoaHoc> selectBySql(String sql, Object... args) {
		// TODO Auto-generated method stub
		List<KhoaHoc> List = new ArrayList<>();
		try {
			ResultSet rs = jdbcHelper.executeQuerry(sql, args);
			while(rs.next()) {
				KhoaHoc entity = new KhoaHoc();
				entity.setMaKH(rs.getInt("maKH"));
				entity.setMaCD(rs.getString("maCD"));
				entity.setHocPhi(rs.getFloat("hocPhi"));
				entity.setThoiLuong(rs.getInt("thoiLuong"));
				entity.setNgayKG(rs.getDate("ngayKG"));
				entity.setGhiChu(rs.getString("ghiChu"));
				entity.setMaNV(rs.getString("maNV"));
				entity.setNgayTao(rs.getDate("ngayTao"));
				List.add(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return List;
	}
	public List<KhoaHoc> selectByChuyenDe(String maCD){
		String sql = " Select * From KhoaHoc Where maCD = ? ";
		return selectBySql(sql, maCD);
	}
	public List<Integer> selectYears(){
		String sql = " Select DISTINCT year(ngayKG) as Year From KhoaHoc  ORDER BY Year DESC ";
		List<Integer> ds = new ArrayList<>();
		try {
			ResultSet rs = jdbcHelper.executeQuerry(sql);
			while(rs.next()) {
				ds.add(rs.getInt(1));
			}
			rs.getStatement().getConnection().close();
			return ds;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
	}
}
