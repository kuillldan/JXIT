package interfaces;

public interface IDAO
{
	public static void show()
	{}
	
	default void print()
	{
		System.out.println("This is print");
	}
}
