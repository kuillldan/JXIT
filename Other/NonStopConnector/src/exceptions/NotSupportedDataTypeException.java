package exceptions;

@SuppressWarnings("serial")
public class NotSupportedDataTypeException extends Exception
{
	public NotSupportedDataTypeException()
	{
		super();
	}
	
	public NotSupportedDataTypeException(String msg)
	{
		super(msg);
	}
}
