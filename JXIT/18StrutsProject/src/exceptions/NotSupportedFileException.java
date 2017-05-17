package exceptions;

@SuppressWarnings("serial")
public class NotSupportedFileException extends Exception
{
	public NotSupportedFileException(String msg)
	{
		super(msg);
	}
}
