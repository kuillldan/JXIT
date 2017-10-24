package org.lyk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.lyk.vo.Dept;
import org.omg.CORBA.PUBLIC_MEMBER;

@Mapper
public interface IDeptDAO
{
	public List<Dept> findAll() throws Exception;
	public boolean doCreate(Dept dept) throws Exception;
}
