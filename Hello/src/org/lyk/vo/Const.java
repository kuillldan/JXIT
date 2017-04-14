package org.lyk.vo;

public class Const
{
	public enum DATATYPE
	{
		Integer("Integer"),String("String"),Int("int"),DATE("Date"),Double("Double"),IntArray("int[]"),IntegerArray("Integer[]"),DoubleArray("Double[]"),doubleArray("double[]"),StringArray("String[]");
		
		
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
