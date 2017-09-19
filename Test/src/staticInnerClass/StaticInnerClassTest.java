package staticInnerClass;

import java.util.Arrays;
import java.util.Random;

public class StaticInnerClassTest
{
	public static void main(String[] args)
	{
		Double[] values = new Double[20];
		Random rand = new Random();
		for(int i = 0; i < values.length; i++)
		{
			values[i] = rand.nextDouble() * 100;
		}
		
		System.out.println(Arrays.toString(values));
		ArrayAlg.Pair pair = ArrayAlg.minmax(values);
		System.out.println("max:" + pair.getSecond());
		System.out.println("min:" + pair.getFirst());
	}
}


class ArrayAlg
{
	public static class Pair
	{
		private Double first;
		private Double second;
		public Pair(Double first, Double second)
		{
			super();
			this.first = first;
			this.second = second;
		}
		public Pair()
		{
			super();
		}
		public Double getFirst()
		{
			return first;
		}
		public Double getSecond()
		{
			return second;
		} 
	}
	
	public static ArrayAlg.Pair minmax(Double[] values)
	{
		Double min = Double.POSITIVE_INFINITY;
		Double max = Double.NEGATIVE_INFINITY;
		
		for(Double v : values)
		{
			if(min > v)
				min = v;
			if(max < v)
				max = v;
		}
		return new ArrayAlg.Pair(min, max);
	}
}
