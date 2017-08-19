package dao;

import java.sql.SQLException;

import vo.Emp;

public interface IEmpDAO extends IDAO<Integer,Emp>
{
	public void doRemoveByDeptno(Integer deptno) throws SQLException;
}
