package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dao.AbstractDAOImpl;
import dao.IActionDAO;
import vo.Action;
import vo.Groups;

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
		List<Action> allActions = new ArrayList<Action>();
		String sql = " SELECT actid,gid,title,url FROM action WHERE gid = ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, gid);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			Action vo = new Action();
			vo.setActid(rs.getInt("actid"));
			Groups groups = new Groups();
			groups.setGid(rs.getInt("gid"));
			vo.setGroups(groups);
			vo.setTitle(rs.getString("title"));
			vo.setUrl(rs.getString("url"));
			allActions.add(vo);
		}
		return allActions;
	}

}
