package factory;

import java.sql.Connection;

import vo.Jobs;
import dao.IDeptDAO;
import dao.IJobs;
import dao.impl.DeptDAOImpl;
import dao.impl.JobsDAOImpl;

public class DAOFactory
{
	public static IDeptDAO getIDeptDAOInstance(Connection conn)
	{
		return new DeptDAOImpl(conn);
	}
	
	public static IJobs getIJobsDAOInstance(Connection conn)
	{
		return new JobsDAOImpl(conn);
	}
}
