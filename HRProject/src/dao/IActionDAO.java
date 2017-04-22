package dao;

import java.util.List;

import vo.Action;
import vo.Groups;

public interface IActionDAO extends IDAO<Integer, Action>
{
	/**
	 * 根据权限组id查询出对应的所有的Action
	 * @param gid
	 * @return
	 * @throws Exception
	 */
	public List<Action> findAllByGroups(Integer gid) throws Exception;
}
