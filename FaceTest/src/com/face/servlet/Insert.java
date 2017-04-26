package com.face.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.face.util.JDBCUtil;

/**
 * Servlet implementation class Insert
 */
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int maxsql = Integer.valueOf(ResourceBundle.getBundle("insert").getString("table.maxsql"));
		
		String[] tables = ResourceBundle.getBundle("insert").getString("table.index").split(",");
		
		boolean truncate = Boolean.valueOf(ResourceBundle.getBundle("insert").getString("table.truncate")).booleanValue();
		
		Connection conn = new JDBCUtil().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			StringBuffer sql = null;
			for(String table:tables){
				if(truncate){
					sql = new StringBuffer();
					sql.append("delete from " + table + ";");
					ps = conn.prepareStatement(sql.toString());
					ps.executeUpdate();
				}
			}
//			conn.setAutoCommit(false);
			for(String table:tables){
//				if(truncate){
//					sql = new StringBuffer();
//					sql.append("PURGEDATA " + table + ";");
//					ps = conn.prepareStatement(sql.toString());
//					ps.executeUpdate();
//				}
				
				int maxcol = Integer.valueOf(ResourceBundle.getBundle("insert").getString("table.maxcolumn."+table));
				
				Set<Integer> col_number = new HashSet<Integer>();
				for(String s: ResourceBundle.getBundle("insert").getString("table.incre."+table).split(",")){
					col_number.add(Integer.valueOf(s));
				}
				
				int tmaxsql = Integer.valueOf(ResourceBundle.getBundle("insert").getString("table.maxsql."+table));
				
				for(int i=1;i<=maxsql;i++){
					if(tmaxsql>0&&i>tmaxsql)
						break;
					
					sql = new StringBuffer();
					sql.append("INSERT INTO " + table);
					sql.append(" VALUES (");
					
					for(int n=1;n<=maxcol;n++){
						if(col_number.contains(n))
							sql.append(i);
						else
							sql.append("0");
						
						if(n<maxcol)
							sql.append(",");
					}
					
					sql.append(");");
					response.getWriter().println(sql.toString());
					ps = conn.prepareStatement(sql.toString());
					ps.executeUpdate();
				}
				
			}
			
//			conn.commit();
		} catch (SQLException e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
