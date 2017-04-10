package vo;

import java.util.Date;
 

@SuppressWarnings("serial")
public class Member implements java.io.Serializable
{
	private String mid;
	private String password;
	private String name;
	private String phone;
	private Date regdate;
	private String photo;
	private Integer status;
	private String code;
	private String address ;
	
	 
	
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	public Integer getStatus()
	{
		return status;
	}
	public void setStatus(Integer status)
	{
		this.status = status;
	}
	public String getMid()
	{
		return mid;
	}
	public void setMid(String mid)
	{
		this.mid = mid;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
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
	public Date getRegdate()
	{
		return regdate;
	}
	public void setRegdate(Date regdate)
	{
		this.regdate = regdate;
	}
	public String getPhoto()
	{
		return photo;
	}
	public void setPhoto(String photo)
	{
		this.photo = photo;
	}
	@Override
	public String toString()
	{
		return "Member [mid=" + mid + ", password=" + password + ", name=" + name + ", phone=" + phone
				+ ", regdate=" + regdate + ", photo=" + photo + ", status=" + status + "]";
	}
	
	
	
}
