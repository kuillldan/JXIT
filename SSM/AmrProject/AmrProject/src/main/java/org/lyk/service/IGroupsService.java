package org.lyk.service;

import java.util.List;

import org.lyk.vo.Groups;

public interface IGroupsService
{
	public List<Groups> findAllByDept(Integer did) throws Exception;
}
