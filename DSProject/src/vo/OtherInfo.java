package vo;

import java.util.Arrays;

public class OtherInfo
{
	private String details;
	private String description;
	
	private Integer[] locs;
	
	
 	
	public Integer[] getLocs()
	{
		return locs;
	}
	public void setLocs(Integer[] locs)
	{
		this.locs = locs;
	}
	public String getDetails()
	{
		return details;
	}
	public void setDetails(String details)
	{
		this.details = details;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	@Override
	public String toString()
	{
		return "OtherInfo [details=" + details + ", description=" + description + ", locs="
				+ Arrays.toString(locs) + "]";
	}
	
	
}
