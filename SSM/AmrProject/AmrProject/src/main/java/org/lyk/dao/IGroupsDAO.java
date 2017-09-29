package org.lyk.dao;

import java.util.List;

import org.lyk.vo.Groups;

public interface IGroupsDAO extends IDAO<Integer, Groups>
{
	public List<Groups> findAllByDept(Integer did) throws Exception;
}
