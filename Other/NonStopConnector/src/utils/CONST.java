package utils;

public class CONST
{
	public static final String errorPage = "/pages/common/error.jsp";
	public static final String noPhoto = "nophoto.jpg";
	public static final String forwardPage = "/pages/common/forward.jsp";
	
	public enum DATATYPE
	{
		Integer("Integer"),
		String("String"),
		Int("int"),
		DATE("Date"),
		Double("Double"),
		IntArray("int[]"),
		IntegerArray("Integer[]"),
		DoubleArray("Double[]"),
		doubleArray("double[]"),
		StringArray("String[]");
		
		
		private String realType;
		private DATATYPE(String realType)
		{
			this.realType = realType;
		}
		public String getRealType()
		{
			return realType;
		}
	}
}
