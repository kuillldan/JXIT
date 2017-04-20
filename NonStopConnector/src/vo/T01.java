package vo;

public class T01
{
	private Integer empnum;
	private String first_name;
	private String last_name;
	private Integer teamnum;
	public Integer getEmpnum()
	{
		return empnum;
	}
	public void setEmpnum(Integer empnum)
	{
		this.empnum = empnum;
	}
	public String getFirst_name()
	{
		return first_name;
	}
	public void setFirst_name(String first_name)
	{
		this.first_name = first_name;
	}
	public String getLast_name()
	{
		return last_name;
	}
	public void setLast_name(String last_name)
	{
		this.last_name = last_name;
	}
	public Integer getTeamnum()
	{
		return teamnum;
	}
	public void setTeamnum(Integer teamnum)
	{
		this.teamnum = teamnum;
	}
	@Override
	public String toString()
	{
		return "T01 [empnum=" + empnum + ", first_name=" + first_name + ", last_name=" + last_name + ", teamnum="
				+ teamnum + "]";
	}
	
	
}
