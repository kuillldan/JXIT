package DAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import vo.Action;
import DAO.AbstractDAOImpl;
import DAO.IActionDAO;

public class ActionDAOImpl extends AbstractDAOImpl implements IActionDAO
{

	public ActionDAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean doCreate(Action vo) throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Action vo) throws SQLException
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
	public Action findById(Integer id) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> findAll() throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
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
	public List<Action> findAllByGroups(Integer gid) throws Exception
	{
		throws new Exception();
		return null;
	}

}
