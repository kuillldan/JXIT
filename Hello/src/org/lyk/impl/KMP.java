package org.lyk.impl;

import org.lyk.interfaces.IKMP;

public class KMP<T> implements IKMP<T>
{
	public boolean comtains(T[] source, T[] target) throws Exception
	{
		if(source == null || target == null)
		{
			throw new Exception("比较目标不能为空");
		}
		 
		int sourceLength = source.length;
		int targetLength = target.length;
		if(targetLength > sourceLength)
			//目标串长达大于源串，肯定不存在，直接返回false
			return false;
		else if(targetLength == sourceLength)
		{
			//两个串长度相等，则挨个比较，若发现任何一个元素不相等，则直接返回false，若所有元素均相等个，则返回true
			return this.compair(source, target);
		}
		else
		{//源串长度大于目标串长度，用KMP算法比较
			
			//分析目标串的重复情况，生成next数组
			int[] next = this.getNext(target);
			int currentSourceIndex = 0;
			int currentTargetIndex = 0;
			
			//如果目标串没有到末尾，则继续循环，如果已经到目标串末尾，则说明找到
			while(currentTargetIndex < targetLength)
			{
				//如果源串下标越界，说明查找结束，乜没有找到结果，返回false
				if(currentSourceIndex >= sourceLength)
					return false;
				
				if(source[currentSourceIndex].equals(target[currentTargetIndex]))
				{
					//两个元素相等，目标串和源串下标向后各移动一位
					currentSourceIndex++;
					currentTargetIndex++;
				}
				else
				{
					
					//目标串的第一个元素就与源串不相等，则源串向后移动一个位置继续比较
					if(currentTargetIndex == 0)
					{
						currentSourceIndex++;
					}
					
					currentTargetIndex = next[currentTargetIndex];
				}
			}
			return true;
		}
	}
	
	public boolean strContain_BF(T[] source, T[] target)
    {
        boolean retVal = false;
        if (null == source || null == target )
            return false; 
        
        int sourceLength = source.length;
        int targetLength = target.length;
        if (targetLength > sourceLength)
            return false;
        else if (targetLength == sourceLength)
        {
            for (int i = 0; i < sourceLength; i++)
            {
                if (source[i] != target[i])
                    return false;
            }
            return true;
        } else
        {
            for (int i = 0; i <= sourceLength - targetLength; i++)
            {
                boolean flag = true;
                for (int j = 0; j < targetLength; j++)
                {
                    if (!target[j].equals(source[i + j]))
                    {
                        flag = false;
                        break;
                    }
                }
                if (flag == true)
                    return true;
            }
            return false;
        }
    }
	
	private int[] getNext(T[] target)
	{
		int[] next = new int[target.length];
		if(next.length > 2)
		{
			for(int i = 2; i<target.length; i++)
			{
				int count = 0;
				for(int j = 0;j < i-1; j++)
				{
					boolean flag = true;
					for(int k = 0; k <= j; k++)
					{
						if(target[k] != target[i-1-j+k])
						{
							flag = false;
							break;
						}
					}
					if(flag == true)
					{
						count = j+1;
					}
				}
				next[i] = count;
			}
		}
		return next;
	} 
	
	private boolean compair(T[] source, T[]target)
	{
		for(int i = 0; i < source.length; i++)
		{
			if(!source[i].equals(target))
				return false;
		}
		return true;
	}
}
