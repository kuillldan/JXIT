package test;

import org.junit.Test;

import vo.Dept;

public class DeptServiceTest
{
	@Test
	public void deptInsert()
	{
		Dept dept = new Dept();
		dept.setDeptno(88);
		dept.setDname("SSIT");
		dept.setLoc("CHONGQING");
	}
}
