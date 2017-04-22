package vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Jobs implements Serializable
{
	private Integer jid;
	private String title;
	private String note;
	public Integer getJid()
	{
		return jid;
	}
	public void setJid(Integer jid)
	{
		this.jid = jid;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getNote()
	{
		return note;
	}
	public void setNote(String note)
	{
		this.note = note;
	}
}
