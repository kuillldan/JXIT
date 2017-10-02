package org.lyk.helper;

import javax.servlet.http.HttpServletRequest;

import org.lyk.utils.StringUtils;

public class SplitHandler
{
	private String column;
	private String keyWord;
	private Integer currentPage = 1;
	private Integer lineSize = 10;

	public SplitHandler(HttpServletRequest request)
	{
		this.setColumn(request.getParameter("col"));
		this.setCurrentPage(request.getParameter("cp"));
		this.setKeyWord(request.getParameter("kw"));
		this.setLineSize(request.getParameter("ls"));
	}
	
	public String getColumn()
	{
		return column;
	}

	public void setColumn(String column)
	{
		if (StringUtils.isEmpty(column))
		{
			this.column = "";
			return;
		}

		this.column = column;
	}

	public String getKeyWord()
	{
		return keyWord;
	}

	public void setKeyWord(String keyWord)
	{
		if(StringUtils.isEmpty(keyWord))
		{
			this.keyWord = "";
			return;
		}
		
		this.keyWord = keyWord;
	}

	public Integer getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(String currentPage)
	{
		if (StringUtils.isEmpty(currentPage))
			return;

		try
		{
			this.currentPage = Integer.parseInt(currentPage);
		} catch (Exception e)
		{

		}
	}

	public Integer getLineSize()
	{
		return lineSize;
	}

	public void setLineSize(String lineSize)
	{
		if (StringUtils.isEmpty(lineSize))
			return;

		try
		{
			this.lineSize = Integer.parseInt("lineSize");
		} catch (Exception e)
		{
		}
	}

}
