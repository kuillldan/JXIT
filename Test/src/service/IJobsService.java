package service;

import java.util.List;

import vo.Jobs;

public interface IJobsService
{
	public List<Jobs> list() throws Exception;
	public boolean insert(Jobs jobs)throws Exception;
}
