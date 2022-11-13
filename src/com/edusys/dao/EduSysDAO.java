/**
 * 
 */
package com.edusys.dao;

import java.util.List;

import com.edusys.entity.ChuyenDe;
import com.edusys.entity.NhanVien;

/**
 * @author Admin
 *
 */
public abstract class EduSysDAO<E,K> {
	abstract public void insert(E entity);
	abstract public void update(E entity);
	abstract public void delete(K key);
	abstract public List<E>selectAll();
	abstract public E selectById(K key);
	abstract protected List<E> selectBySql(String sql, Object...args);
}
