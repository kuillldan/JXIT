package DAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Set;

import vo.Admin;
import DAO.AbstractDAOImpl;
import DAO.IAdminDAO;

public class AdminDAOImpl extends AbstractDAOImpl implements IAdminDAO
{

	public AdminDAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 根据用户名和密码找到lastdate
	 */
	@Override
	public boolean findLogin(Admin vo) throws Exception
	{
		String sql = " SELECT lastdate FROM admin WHERE aid = ? AND password =? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getAid());
		this.ps.setString(2, vo.getPassword());
		ResultSet rs = this.ps.executeQuery();
		if(rs.next())
		{
			vo.setLastdate(rs.getDate("lastdate"));
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 根据aid设置当前时间为lastdate值
	 */
	@Override
	public boolean doUpdateLastdate(String aid) throws Exception
	{
		String sql = " UPDATE admin SET lastdate=? WHERE aid=? ";
		this.ps =  this.conn.prepareStatement(sql);
		this.ps.setDate(1, new java.sql.Date(new Date().getTime()));
		this.ps.setString(2, aid);
		return 1 ==this.ps.executeUpdate();
	}

	@Override
	public boolean doCreate(Admin vo) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Admin vo) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<String> ids) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Admin findById(String id) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> findAll() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

}
