package org.lyk.service;

import java.util.List;

import org.lyk.vo.Emp;
import org.lyk.vo.Level;

public interface IAdminService
{
	public boolean add(Emp admin) throws Exception;
	public List<Level> addPre() throws Exception;
}
