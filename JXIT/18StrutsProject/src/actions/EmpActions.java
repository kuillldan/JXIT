package actions;

import java.util.List;
import java.util.Set;

import vo.Emp;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class EmpActions extends ActionSupport
{
	private Set<String> nids ;
	public Set<String> getNids()
	{
		return nids;
	}
	public void setNids(Set<String> nids)
	{
		this.nids = nids;
	}
	
	private Set<Integer> ids;
	public Set<Integer> getIds()
	{
		return ids;
	}
	public void setIds(Set<Integer> ids)
	{
		this.ids = ids;
	}
	
	public void delete()
	{
		System.out.println("要删除的员工编号（int）:" + this.ids);
		System.out.println("要删除的员工编号（String）:" + this.nids);
	}
}
