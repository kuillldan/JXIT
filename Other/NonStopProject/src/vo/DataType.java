package vo;

public enum DataType
{
	VARCHAR("VARCHAR"),INT("INT");
	
	private String realType;
	private DataType()
	{
		
	}
	DataType(String realType)
	{
		this.realType = realType;
	}
	public String getRealType()
	{
		return this.realType;
	}
}
