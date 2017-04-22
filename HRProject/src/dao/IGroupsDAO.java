package dao;

import java.sql.SQLException;
import java.util.List;

import vo.Groups;

public interface IGroupsDAO extends IDAO<Integer, Groups>
{
	public List<Groups> findAllByRole(Integer rid)throws SQLException;
}
