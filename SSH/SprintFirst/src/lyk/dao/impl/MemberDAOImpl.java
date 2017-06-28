package lyk.dao.impl;

import org.springframework.stereotype.Component;

import lyk.dao.IMemberDAO;


@Component
public class MemberDAOImpl implements IMemberDAO
{

	@Override
	public boolean doCreate() throws Exception
	{
		System.out.println("【DAO数据层】执行Member插入操作");
		  
		return true;
	}

}
