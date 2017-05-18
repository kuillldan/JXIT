package exceptions;

@SuppressWarnings("serial")
public class NotSupportedFileTypeException extends Exception
{
	public NotSupportedFileTypeException()
	{
		super();
	}
	
	public NotSupportedFileTypeException(String msg)
	{
		super(msg);
	}
}
