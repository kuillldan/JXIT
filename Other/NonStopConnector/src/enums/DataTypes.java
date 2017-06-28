package enums;

public enum DataTypes
{
	CHAR("CHARACTER"),NUMERIC("NUMERIC");
	
	private String realType;
	
	private DataTypes(String realType)
	{
		this.realType = realType;
	}

	public String getRealType()
	{
		return realType;
	}

	public void setRealType(String realType)
	{
		this.realType = realType;
	}
}
