package org.lyk.service;

import java.util.List;

import org.lyk.vo.Dept;

public interface IDeptService
{
	public List<Dept> list(Integer actid)throws Exception;
	public boolean updateTitleByDid(Integer actid,Dept dept) throws Exception;
}
