package dao;

import java.sql.SQLException; 

import vo.Level;

public interface ILevelDAO extends IDAO<Integer, Level>
{
	public boolean findByTitle(String title) throws SQLException;
	public boolean findByTitleAndId(String title, Integer levid) throws SQLException;
}
