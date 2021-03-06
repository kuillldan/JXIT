package servlet.back;

import java.util.List;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import factories.ServiceBackFactory;
import pages.JobsPages;
import utils.AbstractServlet;
import utils.General;
import vo.Jobs;

@SuppressWarnings("serial")
@WebServlet("/pages/back/admin/jobs/JobsServletBack/*")
public class JobsServletBack  extends AbstractServlet
{
	private String insertValidation = "jobs.title";
	private String updateValidation = "jobs.title|jobs.jid";
	Jobs jobs = new Jobs();
	public Jobs getJobs()
	{
		return jobs;
	}
	
	public String insertPre()
	{
		return JobsPages.insertJSP;
	}
	
	public String insert()
	{
		try
		{
			if(ServiceBackFactory.getIJobsServiceBackInstance().insert(this.jobs))
			{
				return super.insertSuccessfull(JobsPages.insertJSP);
			}
			else
			{
				return super.insertFailed(JobsPages.insertJSP);
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
			if(ServiceBackFactory.getIJobsServiceBackInstance().delete(ids))
			{
				return super.deleteSuccessfull(JobsPages.listURL);
			}
			else
			{
				return super.deleteFailed(JobsPages.listURL);
			}
		} catch (Exception e)
		{
			return super.setSystemError(e);
		}
	}
	
	public String list()
	{
		try
		{
			List<Jobs> allJobs = ServiceBackFactory.getIJobsServiceBackInstance().list();
			request.setAttribute("allJobs", allJobs);
			return JobsPages.listJSP;
		} catch (Exception e)
		{
			return super.setSystemError(e);
		}
	}
	
	public String updatePre()
	{
		return null;
	}
	
	public String update()
	{
		try
		{
			if(ServiceBackFactory.getIJobsServiceBackInstance().update(jobs))
			{
				return super.updateSuccessfull(JobsPages.listURL);
			}
			else
			{
				return super.updateFailed(JobsPages.listURL);
			}
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
		return "职位";
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
