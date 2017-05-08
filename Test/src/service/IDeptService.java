package service;

import java.util.List;
import java.util.Set;

import vo.Dept;

public interface IDeptService
{
	public Dept show(Integer did) throws Exception;
	public List<Dept> list() throws Exception;
}
