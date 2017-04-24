package utils;

import enums.DataTypes;
import exceptions.NotSupportedDataTypeException;

public class Column
{
	private String columnName;
	private String notNull ;
	private String dataType;
	private Integer length;
	
	public String getColumnName()
	{
		return columnName;
	}
	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}
	public String getNotNull()
	{
		return notNull;
	}
	public void setNotNull(String notNull)
	{
		this.notNull = notNull;
	}
	public String getDataType()
	{
		return dataType;
	}
	public void setDataType(String dataType) throws NotSupportedDataTypeException
	{
		if(DataTypes.CHAR.toString().equals(dataType))
		{
			this.dataType = DataTypes.CHAR.getRealType();
		}
		else if(DataTypes.NUMERIC.toString().equals(dataType))
		{
			this.dataType = DataTypes.NUMERIC.getRealType();
		}
		else
		{
			throw new NotSupportedDataTypeException();
		}
	}
	
	public Integer getLength()
	{
		return length;
	}
	public void setLength(String length)
	{
		this.length = Integer.parseInt(length);
	}
	@Override
	public String toString()
	{
		return "Column [columnName=" + columnName + ", notNull=" + notNull + ", dataType=" + dataType + ", length="
				+ length + "]";
	}
}
