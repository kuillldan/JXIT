package org.lyk.enums;

public enum ActionIDEnum
{
	// 增加管理员(pre) /pages/admin/addPre.action
	ADMIN_ADD_PRE(1),
	// 增加管理员(add) /pages/admin/add.action
	ADMIN_ADD(2),
	// 管理员列表 /pages/admin/list.action
	ADMIN_LIST(3),
	// 部门列表 /pages/dept/list.action
	DEPT_LIST(4),
	// 查看部门权限 /pages/groups/list.action
	GROUPS_LIST(6),
	// 部门修改 /pages/dept/edit.action
	DEPT_EDIT(7),
	// 11 | 4 | 增加员工 | /pages/emp/addPre.action
	EMP_ADD_PRE(11),
	// 12 | 4 | 增加员工 | /pages/emp/add.action
	EMP_ADD(12),
	// 13 4 员工列表 /pages/emp/list.action 1
	EMP_LIST(13);

	private Integer actid;

	private ActionIDEnum(Integer actid)
	{
		this.actid = actid;
	}

	public Integer getValue()
	{
		return actid;
	}
}
