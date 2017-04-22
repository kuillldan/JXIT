package dao;

import java.sql.SQLException;

import vo.Jobs;

public interface IJobsDAO extends IDAO<Integer, Jobs>
{
	public boolean findByTitle(String title) throws SQLException;
	public boolean findByTitleAndId(String title, Integer jid)  throws SQLException;
}
