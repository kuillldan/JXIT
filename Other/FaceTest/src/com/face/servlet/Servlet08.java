package com.face.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.face.util.SqlGenerateUtil;
import com.face.util.JDBCUtil;

/**
 * Servlet implementation class ConnTest
 */
public class Servlet08 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// servletの行列番号
	private static final int SERVLET_NO = 8;

	private static final Logger logger = Logger.getLogger(Servlet08.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet08() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("resource")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean israndom = Boolean.valueOf(ResourceBundle.getBundle("app").getString("jmeter.random")).booleanValue();

		Connection conn = new JDBCUtil().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			String sql = null;
			// 検索条件
			String[] colInd = ResourceBundle.getBundle("app").getString("select.colind").split(",");
			String[] colVal = ResourceBundle.getBundle("app").getString("select.colval").split(",");
			String[] o_colInd = ResourceBundle.getBundle("app").getString("other.colind").split(",");
			String[] o_colVal = ResourceBundle.getBundle("app").getString("other.colval").split(",");
			// selectの実行回数
			int max_exec = Integer.valueOf(ResourceBundle.getBundle("app").getString("select.number"));
			// selectのcolumnタイプ数
			int col_type = Integer.valueOf(ResourceBundle.getBundle("app").getString("select.coltype"));

			if (col_type < 0
					|| col_type > Integer.valueOf(ResourceBundle.getBundle("app").getString("table.maxcolumnnum"))) {
				Exception e = new Exception("select.coltypeが許容範囲外の値が設定されています");
				throw e;
			}

			// SELECT
			for (int i = 1; i <= max_exec; i++) {

				for (int n = 0; n < col_type; n++) {
					if (israndom) {
						sql = SqlGenerateUtil.getSelect(SERVLET_NO, i, n, colInd, colVal);
					} else {
						sql = SqlGenerateUtil.getSelect(i, SERVLET_NO, n, colInd, colVal);
					}

					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					rs.last();
					logger.info("SELECT : " + sql);
					logger.info("count : " + rs.getRow());
				}
			}

			// INSERT
			// 黙認値はcolumn名と同じです
			sql = SqlGenerateUtil.getInsert(SERVLET_NO);
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			logger.info("INSERT : " + sql);

			// UPDATE
			sql = SqlGenerateUtil.getUpdate(SERVLET_NO, o_colInd, o_colVal);
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			logger.info("UPDATE : " + sql);

			// DELETE
			sql = SqlGenerateUtil.getDelete(SERVLET_NO, o_colInd, o_colVal);
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			logger.info("DELETE : " + sql);

			response.getWriter().write("{\"result\":\"success\",\"servlet\":\"servlet08\"}");

			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
