package lyk.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * MemberDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "member_details", catalog = "hedb")
public class MemberDetails implements java.io.Serializable
{

	// Fields

	private String mid;
	private MemberLogin memberLogin;
	private String name;
	private String email;
	private String phone;

	// Constructors

	/** default constructor */
	public MemberDetails()
	{
	}

	/** minimal constructor */
	public MemberDetails(String mid, MemberLogin memberLogin)
	{
		this.mid = mid;
		this.memberLogin = memberLogin;
	}

	/** full constructor */
	public MemberDetails(String mid, MemberLogin memberLogin, String name, String email, String phone)
	{
		this.mid = mid;
		this.memberLogin = memberLogin;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	// Property accessors
	@Id
	@Column(name = "mid", unique = true, nullable = false, length = 50)
	public String getMid()
	{
		return this.mid;
	}

	public void setMid(String mid)
	{
		this.mid = mid;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public MemberLogin getMemberLogin()
	{
		return this.memberLogin;
	}

	public void setMemberLogin(MemberLogin memberLogin)
	{
		this.memberLogin = memberLogin;
	}

	@Column(name = "name", length = 50)
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Column(name = "email", length = 50)
	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Column(name = "phone", length = 50)
	public String getPhone()
	{
		return this.phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	@Override
	public String toString()
	{
		return "MemberDetails [name=" + name + ", email=" + email + ", phone=" + phone + "]";
	} 
}