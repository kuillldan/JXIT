package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultHandler
{
	public Object getResult(ResultSet rs) throws SQLException;
}
