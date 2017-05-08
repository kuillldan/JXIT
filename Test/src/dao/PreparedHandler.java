package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
 

public interface PreparedHandler
{
	public void setParameter(PreparedStatement ps) throws SQLException;
}
