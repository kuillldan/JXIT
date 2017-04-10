package vo;

public class Column
{
	private String columnName;
	private String dataType;
	private String length;
	private String isNull;
	
	

	public String getIsNull()
	{
		return isNull;
	}

	public void setIsNull(String isNull)
	{
		this.isNull = isNull;
	}

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	public String getLength()
	{
		return length;
	}

	public void setLength(String length)
	{
		this.length = length;
	}
}
