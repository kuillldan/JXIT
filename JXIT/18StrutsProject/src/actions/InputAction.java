package actions;

import java.util.Arrays;
import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class InputAction extends ActionSupport
{
	private int[] intArray;
	public void setIntArray(int[] intArray)
	{
		this.intArray = intArray;
	}
	
	private String[] StringArray;
	public void setStringArray(String[] stringArray)
	{
		StringArray = stringArray;
	}
	
	private double[] doubleArray;
	public void setDoubleArray(double[] doubleArray)
	{
		this.doubleArray = doubleArray;
	}
	
	private Set<Integer> IntegerSet;
	public void setIntegerSet(Set<Integer> integerSet)
	{
		IntegerSet = integerSet;
	}
	
	public String intArray()
	{
		System.out.println("+++++++++++++++++++");
		System.out.println(Arrays.toString(this.intArray));
		System.out.println("+++++++++++++++++++");
		return "good";
	}
	
	public String StringArray()
	{
		System.out.println("+++++++++++++++++++");
		System.out.println(Arrays.toString(this.StringArray));
		System.out.println("+++++++++++++++++++");
		return "good";
	}
	
	public String doubleArray()
	{
		System.out.println("+++++++++++++++++++");
		System.out.println(Arrays.toString(this.doubleArray));
		System.out.println("+++++++++++++++++++");
		return "good";
	}
	
	public String IntegerSet()
	{
		System.out.println("+++++++++++++++++++");
		System.out.println(this.IntegerSet);
		System.out.println("+++++++++++++++++++");
		return "good";
	}
}
