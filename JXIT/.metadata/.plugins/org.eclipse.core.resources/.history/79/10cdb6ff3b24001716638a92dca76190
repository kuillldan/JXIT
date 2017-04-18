package DAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.mysql.jdbc.PreparedStatement; 

import vo.Admin;
import DAO.AbstractDAOImpl;
import DAO.IAdminDAO;
import DAO.IDAO;

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
//		String sql = " SELECT aid,rid, ";
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
	public Admin findLogin(Admin vo) throws SQLException
	{
		Admin admin = null;
		String sql = " SELECT aid,rid,type,lastdate,flag FROM Admin WHERE aid = ? AND password = ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getAid());
		this.ps.setString(2, vo.getPassword());
		ResultSet rs = this.ps.executeQuery();
		if(rs.next())
		{
			admin = new Admin();
			admin.setAid(rs.getString("aid"));
			admin.setType(rs.getInt("type"));
			admin.setLastdate(rs.getDate("lastdate"));
			admin.setFlag(rs.getInt("flag"));
		}
		return admin;
	} 
}
