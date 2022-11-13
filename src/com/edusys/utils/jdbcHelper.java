/**
 * 
 */
package com.edusys.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Admin
 *
 */
public class jdbcHelper {
	private static String Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String User = "sa";
	private static String Pass = "123456";
	private static String URL = "jdbc:sqlserver://localhost:1433;databaseName=EduSys;encrypt=true;trustServerCertificate=true";
	/**
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * 
	 */
	static {
		
	}
//	public static Connection openConnection() throws Exception {
//		try {
//			Class.forName(Driver);
//			Connection con = DriverManager.getConnection(URL);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		 
//		return con;
//	}
	public static PreparedStatement getStmt (String sql, Object...args) throws Exception {
		PreparedStatement pstmt = null;
		try {
			Class.forName(Driver);
			Connection con = DriverManager.getConnection(URL,User,Pass);
			
			if (sql.trim().startsWith("{")) {
				pstmt = con.prepareCall(sql);
			}
			else {
				pstmt = con.prepareStatement(sql);
			}
			for (int i = 0; i<args.length;i++) {
				
				pstmt.setObject(i+1, args[i]);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pstmt;

	}
	
	public static int executeUpdate (String sql, Object...args) throws Exception {
		PreparedStatement stmt = getStmt(sql, args);
		try {
			return stmt.executeUpdate();
		} finally {
			stmt.getConnection().close();
		}
	}
	
	public static ResultSet executeQuerry (String sql, Object...args) throws Exception {
		
		try {
			PreparedStatement stmt = getStmt(sql, args);
			return stmt.executeQuery();
		}catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
	}
	
	public static Object value (String sql, Object...args) {
		try {
			ResultSet rs = executeQuerry(sql, args);
			if(rs.next()) {
				return rs.getObject(0);
			}
			rs.getStatement().getConnection().close();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
		return null;
	}
}
