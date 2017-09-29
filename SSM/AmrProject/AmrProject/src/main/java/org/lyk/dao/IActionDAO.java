package org.lyk.dao;

import java.util.List;

import org.lyk.vo.Action;

public interface IActionDAO extends IDAO<Integer, Action>
{
	public List<Action> findAllByGroups(Integer gid) throws Exception;
}
