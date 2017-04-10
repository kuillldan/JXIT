package org.lyk.entities;

public class KMP
{
	public boolean strContains_KMP(char[] source, char[] target)
	{
		if (null == target || null == source || 0 == target.length || 0 == target.length)
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
			int[] next = this.getNext(target);
			int currentSourceIndex = 0;
			int currentTargetIndex = 0;
			// todo
			while (currentTargetIndex < targetLength)
			{
				if (currentSourceIndex >= sourceLength)
					return false;

				if (source[currentSourceIndex] == target[currentTargetIndex])
				{
					if (currentTargetIndex == targetLength - 1)
						return true;
					else
					{
						currentSourceIndex++;
						currentTargetIndex++;
						continue;
					}
				} else
				{
					if(currentTargetIndex == 0)
					{
						currentSourceIndex++;
					}
					currentTargetIndex = next[currentTargetIndex];
				}
			}

			return false;
		}
	}

	private int[] getNext(char[] target)
	{
		int targetLength = target.length;
		int[] next = new int[targetLength];
		if(targetLength > 2)
		{
			for(int i = 2; i < targetLength; i++)
			{
				int count = 0;
				//前面有几个重复，该位置的值就为几
				// i = 5
				// 0 1 2 3 4
				for(int j = 0; j < i-1;j++)
				{
					boolean flag = true;
					for(int k = 0; k <= j; k++)
					{
						if(target[k] != target[i-1-j+k])
						{
							flag = false;
						}
					}
					if(true == flag )
					{
						count = j+1;
					}
				}
				next[i] = count;
			}
		}
			
		return next;
	}

	public static boolean strContains_KMP1(String _source, String _target)
	{
		boolean retVal = false;
		if (null == _source || null == _target || "".equals(_source) || "".equals(_target))
			return false;
		char[] source = _source.toCharArray();
		char[] target = _target.toCharArray();
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
			int[] next = getNext1(target);
			int currentSourceIndex = 0;
			int currentTargetIndex = 0;
			while (currentTargetIndex < targetLength)
			{
				int k = 0;
				boolean flag = true;
				for (int i = currentTargetIndex; i < targetLength; i++)
				{
					if ((currentSourceIndex + k) >= sourceLength)
					{
						return false;
					}

					if (target[i] == source[currentSourceIndex + k])
					{
						k++;
						continue;
					} else
					{
						flag = false;
						if (i == 0)
						{
							currentSourceIndex++;
						} else
						{
							currentSourceIndex = currentSourceIndex + k;
						}
						currentTargetIndex = next[currentTargetIndex];
						break;
					}
				}
				if (flag == true)
					return true;
			}
			return true;
		}
	}

	public static int[] getNext1(char[] target)
	{
		int targetLength = target.length;
		int[] next = new int[targetLength];

		if (targetLength <= 2)
		{
			for (int i = 0; i < targetLength; i++)
			{
				next[i] = 0;
			}
			return next;
		} else
		{
			next[0] = 0;
			next[1] = 0;
			for (int i = 2; i < targetLength; i++)
			{
				int count = 0;
				for (int j = 0; j < i - 1; j++)
				{
					boolean flag = true;
					for (int k = 0; k <= j; k++)
					{
						if (target[k] != target[i - 1 - j + k])
							flag = false;
					}
					if (flag == true)
						count = j + 1;
				}
				next[i] = count;
			}
			return next;
		}
	}

	public static boolean strContain_BF1(String _source, String _target)
	{
		boolean retVal = false;
		if (null == _source || null == _target || "".equals(_source) || "".equals(_target))
			return false;
		char[] source = _source.toCharArray();
		char[] target = _target.toCharArray();
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
					if (target[j] != source[i + j])
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
}
