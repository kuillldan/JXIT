package org.lyk.service;

import java.util.List;

import org.lyk.vo.Dept;

public interface IDeptService
{
	public boolean insert(Dept dept) throws Exception;
	public List<Dept> list()throws Exception;
}
