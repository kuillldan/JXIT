package DAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import vo.Groups;
import DAO.AbstractDAOImpl;
import DAO.IGroupsDAO;

public class GroupsDAOImpl extends AbstractDAOImpl implements IGroupsDAO
{

	public GroupsDAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean doCreate(Groups vo) throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Groups vo) throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Groups findById(Integer id) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Groups> findAll() throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Groups> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Groups> findAllByRole(Integer rid) throws SQLException
	{
		throws new Exception();
		// TODO Auto-generated method stub
		return null;
	}

}
