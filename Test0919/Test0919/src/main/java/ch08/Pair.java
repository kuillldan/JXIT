package ch08;

public class Pair<T>
{
	private T first;
	private T second;
	public Pair()
	{
		// TODO Auto-generated constructor stub
	}
	public Pair(T first, T second)
	{
		super();
		this.first = first;
		this.second = second;
	}
	public T getFirst()
	{
		return first;
	}
	public void setFirst(T first)
	{
		this.first = first;
	}
	public T getSecond()
	{
		return second;
	}
	public void setSecond(T second)
	{
		this.second = second;
	} 
	
	public static <T> void show(T a, T b)
	{
		System.out.println(a.getClass());
		System.out.println(b.getClass());
		System.out.println(a + "," + b);
	}
}
