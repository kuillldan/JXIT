package vo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Member entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "member", catalog = "hedb")
public class Member implements java.io.Serializable
{

	// Fields

	private MemberId id;
	private Integer age;

	// Constructors

	/** default constructor */
	public Member()
	{
	}

	/** minimal constructor */
	public Member(MemberId id)
	{
		this.id = id;
	}

	/** full constructor */
	public Member(MemberId id, Integer age)
	{
		this.id = id;
		this.age = age;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides(
	{ @AttributeOverride(name = "mid", column = @Column(name = "mid", nullable = false)),
			@AttributeOverride(name = "name", column = @Column(name = "name", nullable = false, length = 50)) })
	public MemberId getId()
	{
		return this.id;
	}

	public void setId(MemberId id)
	{
		this.id = id;
	}

	@Column(name = "age")
	public Integer getAge()
	{
		return this.age;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

}