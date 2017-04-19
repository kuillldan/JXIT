package service.front.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

import dbc.DatabaseConnection;
import enums.AdminType;
import factories.DAOFactory;
import service.front.IAdminServiceFront;
import vo.Action;
import vo.Admin;
import vo.Adminlogs;
import vo.Groups;
import vo.Role;

public class AdminServiceFrontImpl implements IAdminServiceFront
{
	private DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public Map<String, Object> login(Admin admin) throws Exception
	{
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			Admin result = DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).findLogin(admin);
			boolean flag = result != null;
			map.put("flag", flag);
			map.put("admin", result);

			if (flag == true)
			{
				// 登录成功
				//记录登录日志
				Adminlogs adminlogs = new Adminlogs();
				adminlogs.setAdmin(result);
				adminlogs.setLogindate(new Date(System.currentTimeMillis()));
				DAOFactory.getIAdminlogsDAOInstance(this.dbc.getConnection()).doCreate(adminlogs);
				
				//更新最后登录日期
				DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).doUpdateLastDate(result.getAid(), new Date(System.currentTimeMillis()));
				
				if (AdminType.BACK_ADMIN.ordinal() == result.getType())
				{
					// 前台管理员
					Role role = result.getRole();
					Integer rid = role.getRid();
					List<Groups> allGroups = DAOFactory.getIGroupsDAOInstance(this.dbc.getConnection()).findAllByRole(
							rid); 
					role.setAllGroups(allGroups);
					
					for (Groups groups : allGroups)
					{
						Integer gid = groups.getGid();
						List<Action> allActions = DAOFactory.getIActionDAOInstance(this.dbc.getConnection())
								.findAllByGroups(gid);
						groups.setAllActions(allActions);
					}
				}
			}

			return map;
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

	@Override
	public boolean updatePassword(String aid, String oldPassword, String newPassword) throws Exception
	{
		try
		{
			Admin admin = new Admin();
			admin.setAid(aid);
			admin.setPassword(oldPassword);
			if(DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).findLogin(admin) != null)
			{
				//旧密码正确
			}
			else
			{
				//旧密码不正确
			}
			return false;
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

}
