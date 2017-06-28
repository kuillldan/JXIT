package vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MemberId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class MemberId implements java.io.Serializable
{

	// Fields

	private Integer mid;
	private String name;

	// Constructors

	/** default constructor */
	public MemberId()
	{
	}

	/** full constructor */
	public MemberId(Integer mid, String name)
	{
		this.mid = mid;
		this.name = name;
	}

	// Property accessors

	@Column(name = "mid", nullable = false)
	public Integer getMid()
	{
		return this.mid;
	}

	public void setMid(Integer mid)
	{
		this.mid = mid;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean equals(Object other)
	{
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MemberId))
			return false;
		MemberId castOther = (MemberId) other;

		return ((this.getMid() == castOther.getMid()) || (this.getMid() != null && castOther.getMid() != null && this
				.getMid().equals(castOther.getMid())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null && castOther.getName() != null && this
						.getName().equals(castOther.getName())));
	}

	public int hashCode()
	{
		int result = 17;

		result = 37 * result + (getMid() == null ? 0 : this.getMid().hashCode());
		result = 37 * result + (getName() == null ? 0 : this.getName().hashCode());
		return result;
	}

}