package vo;

import java.util.Date;
import java.util.List;

public class Orders
{
	private Integer oid;
	private String mid;
	private String name;
	private String phone;
	private String address;
	private Date credate;
	private Double pay;
	 
	private List<Details> allDetails ;

	
	public Integer getOid()
	{
		return oid;
	}

	public void setOid(Integer oid)
	{
		this.oid = oid;
	}

	public String getMid()
	{
		return mid;
	}

	public void setMid(String mid)
	{
		this.mid = mid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public Date getCredate()
	{
		return credate;
	}

	public void setCredate(Date credate)
	{
		this.credate = credate;
	}

	public Double getPay()
	{
		return pay;
	}

	public void setPay(Double pay)
	{
		this.pay = pay;
	}

	public List<Details> getAllDetails()
	{
		return allDetails;
	}

	public void setAllDetails(List<Details> allDetails)
	{
		this.allDetails = allDetails;
	}

	@Override
	public String toString()
	{
		return "Orders [mid=" + mid + ", name=" + name + ", phone=" + phone + ", address=" + address
				+ ", credate=" + credate + ", pay=" + pay + ", allDetails=" + allDetails + "]";
	}
	
	
}
