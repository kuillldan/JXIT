package org.lyk.services;

import java.util.List;

import org.lyk.vo.Dept;

public interface IDeptService
{
	public List<Dept> list() throws Exception;
	public boolean doCreate(List<Dept> allDepts) throws Exception;
}
