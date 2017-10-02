package org.lyk.service;

import java.util.List;

import org.lyk.vo.Dept;

public interface IDeptService
{
	public List<Dept> list()throws Exception;
	public boolean updateTitleByDid(Dept dept) throws Exception;
}
