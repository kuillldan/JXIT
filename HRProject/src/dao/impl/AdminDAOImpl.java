package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.management.relation.Role;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

import com.mysql.jdbc.PreparedStatement;

import dao.AbstractDAOImpl;
import dao.IAdminDAO;
import dao.IDAO;
import enums.AdminType;
import utils.General;
import vo.Admin;

public class AdminDAOImpl extends AbstractDAOImpl implements IAdminDAO
{
	public AdminDAOImpl(Connection conn)
	{
		super(conn);
	}

	@Override
	public boolean doCreate(Admin vo) throws SQLException
	{
		return false;
	}

	@Override
	public boolean doUpdate(Admin vo) throws SQLException
	{
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<String> ids) throws SQLException
	{
		return false;
	}

	@Override
	public Admin findById(String id) throws SQLException
	{
		Admin admin = null;
		// String sql = " SELECT aid,rid, ";
		return admin;
	}

	@Override
	public List<Admin> findAll() throws SQLException
	{
		return null;
	}

	@Override
	public List<Admin> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws SQLException
	{
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws SQLException
	{
		return null;
	}

	@Override
	public Admin findFrontLogin(Admin vo) throws SQLException
	{
		Admin admin = null;
		String sql = " SELECT aid,rid,type,lastdate,flag FROM Admin WHERE aid = ? AND password = ? AND type=" + AdminType.FRONT_ADMIN.ordinal() + " ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getAid());
		this.ps.setString(2, vo.getPassword());
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
		{
			admin = new Admin();
			admin.setAid(rs.getString("aid"));
			vo.Role role = new vo.Role();
			role.setRid(rs.getInt("rid"));
			admin.setRole(role);
			admin.setType(rs.getInt("type"));
			admin.setLastdate(rs.getTimestamp("lastdate"));
			admin.setFlag(rs.getInt("flag"));
		} 
		return admin;
	}

	@Override
	public boolean doUpdateLastDate(String aid, Date date) throws SQLException
	{
		// TODO Auto-generated method stub
		String sql = " UPDATE admin SET lastdate=? WHERE aid=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setTimestamp(1, General.getCurrentSqlDate());
		this.ps.setString(2, aid);
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doUpdatePassword(String aid, String password) throws SQLException
	{
		String sql = " UPDATE admin SET password = ? WHERE aid = ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, password);
		this.ps.setString(2, aid);
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public Admin findBackLogin(Admin vo) throws SQLException
	{
		Admin admin = null;
		String sql = " SELECT aid,rid,type,lastdate,flag FROM Admin WHERE aid = ? AND password = ? AND type=" + AdminType.BACK_ADMIN.ordinal() + " ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getAid());
		this.ps.setString(2, vo.getPassword());
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
		{
			admin = new Admin();
			admin.setAid(rs.getString("aid"));
			vo.Role role = new vo.Role();
			role.setRid(rs.getInt("rid"));
			admin.setRole(role);
			admin.setType(rs.getInt("type"));
			admin.setLastdate(rs.getTimestamp("lastdate"));
			admin.setFlag(rs.getInt("flag"));
		} 
		return admin;
	}
}
