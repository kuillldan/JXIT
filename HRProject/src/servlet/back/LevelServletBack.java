package servlet.back;

import java.util.List;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.jsp.tagext.TryCatchFinally;

import factories.ServiceBackFactory;
import pages.LevelPages;
import utils.AbstractServlet;
import utils.General;
import vo.Level;


@WebServlet("/pages/back/admin/level/LevelServletBack/*")
public class LevelServletBack extends AbstractServlet
{
	private String insertValidation = "level.title|level.losal|level.hisal";
	private String updateValidation = "level.title|level.losal|level.hisal|level.levid";
	private Level level = new Level();
	public Level getLevel()
	{
		return level;
	}
	
	public String insertPre()
	{
		return LevelPages.insertJSP;
	}
	
	public String insert()
	{
		try
		{
			if(ServiceBackFactory.getILevelServiceBackInstance().insert(this.level))
			{
				return super.insertSuccessfull(LevelPages.insertJSP);
			}
			else
			{
				return super.insertFailed(LevelPages.insertJSP);
			}
		} catch (Exception e)
		{
			return super.setSystemError(e);
		}
	}
	
	public String delete()
	{
		try
		{
			Set<Integer> ids = General.splitIntegers(request.getParameter("ids"));
			if(ServiceBackFactory.getILevelServiceBackInstance().delete(ids))
			{
				return super.deleteSuccessfull(LevelPages.listURL);
			}
			else
			{
				return super.deleteFailed(LevelPages.listURL);
			}
		} catch (Exception e)
		{
			return super.setSystemError(e);
		}
	}
	
	public String update()
	{
		try
		{ 
			if(ServiceBackFactory.getILevelServiceBackInstance().update(this.level))
				return super.updateSuccessfull(LevelPages.listURL);
			else
				return super.updateFailed(LevelPages.listURL);
		} catch (Exception e)
		{
			return super.setSystemError(e);
		}
	}
	
	
	public String list()
	{
		try
		{
			List<Level> allLevels = ServiceBackFactory.getILevelServiceBackInstance().list(); 
			request.setAttribute("allLevels", allLevels);
			return  LevelPages.listJSP;
		} catch (Exception e)
		{
			return super.setSystemError(e);
		}
	}
 
	@Override
	protected String getUploadFolder()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getTitle()
	{
		return "工资级别";
	}

	@Override
	protected String getColumns()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getColumn()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
