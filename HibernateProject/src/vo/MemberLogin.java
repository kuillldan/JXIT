package vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CascadeType;

/**
 * MemberLogin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "member_login", catalog = "hedb")
public class MemberLogin implements java.io.Serializable
{

	// Fields

	private String mid;
	private String password;
	private MemberDetails memberDetails;

	// Constructors

	/** default constructor */
	public MemberLogin()
	{
	}

	/** minimal constructor */
	public MemberLogin(String mid)
	{
		this.mid = mid;
	}

	/** full constructor */
	public MemberLogin(String mid, String password, MemberDetails memberDetails)
	{
		this.mid = mid;
		this.password = password;
		this.memberDetails = memberDetails;
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

	@Column(name = "password", length = 32)
	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "memberLogin")
	public MemberDetails getMemberDetails()
	{
		return this.memberDetails;
	}

	public void setMemberDetails(MemberDetails memberDetails)
	{
		this.memberDetails = memberDetails;
	}

}