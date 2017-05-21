package vo;

/**
 * News entity. @author MyEclipse Persistence Tools
 */

public class News implements java.io.Serializable
{

	// Fields

	private Integer nid;
	private String title;
	private String content;

	// Constructors

	/** default constructor */
	public News()
	{
	}

	/** minimal constructor */
	public News(String title)
	{
		this.title = title;
	}

	/** full constructor */
	public News(String title, String content)
	{
		this.title = title;
		this.content = content;
	}

	// Property accessors

	public Integer getNid()
	{
		return this.nid;
	}

	public void setNid(Integer nid)
	{
		this.nid = nid;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return this.content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	@Override
	public String toString()
	{
		return "News [nid=" + nid + ", title=" + title + ", content=" + content + "]";
	}
}