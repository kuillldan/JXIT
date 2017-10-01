package org.lyk.service;

import java.util.List;

import org.lyk.vo.Action;

public interface IActionService
{
	public List<Action> listAllActionsByGroupsId(Integer gid) throws Exception;
}
