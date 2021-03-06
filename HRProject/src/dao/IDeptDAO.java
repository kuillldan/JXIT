package dao;

import java.sql.SQLException;

import vo.Dept;

public interface IDeptDAO extends IDAO<Integer, Dept>
{
	/**
	 * 用于插入的时候对dname的重复检查
	 * 根据部门名称返回部门数据
	 * 如果找到相应的部门信息，则返回true 否则false
	 * @param dname
	 * @return
	 * @throws SQLException
	 */
	public boolean findByDname(String dname) throws SQLException;
	
	
	/**
	 * 用于更新的时候进行dname的重复检查
	 * 检查的时候需要排除掉要更新的部门id的记录
	 * 如果找到相应的部门信息，则返回true 否则false
	 * @param dname
	 * @param did
	 * @return
	 * @throws SQLException
	 */
	public boolean findByDnameAndId(String dname, Integer did) throws SQLException;
}
