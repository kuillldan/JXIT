package dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParameterSetter
{
	public void setParameter(PreparedStatement ps) throws SQLException;
}
