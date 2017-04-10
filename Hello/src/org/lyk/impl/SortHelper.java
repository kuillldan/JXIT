package org.lyk.impl;

import org.lyk.interfaces.ISortable;

public class SortHelper<T extends Comparable<T>> implements ISortable<T>
{

	@Override
	public void bubbleSort(T[] array)
	{
		for(int i = array.length-1;i>0; i--)
		{
			for(int j = 0; j <i; j++)
			{
				if(array[j].compareTo(array[j+1]) > 0)
				{
					T temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
	}

	@Override
	public void selectionSort(T[] array)
	{
		for(int i = array.length-1; i>0; i--)
		{
			int max = 0;
			for(int j = 0; j <= i; j++)
			{
				if(array[max].compareTo(array[j]) < 0)
				{
					max = j;
				}
			}
			
			T temp = array[max];
			array[max] = array[i];
			array[i] = temp;
		}
	}

	@Override
	public void insertionSort(T[] array)
	{ 
		this.insertionSort(array,0,array.length);
	}

	
	@Override
	public void insertionSort(T[] array, int start, int length)
	{
		for(int i = start; i < start+length;i++)
		{
			for(int j = start; j < i; j++)
			{
				if(array[i].compareTo(array[j]) < 0)
				{
					T temp = array[i];
					for(int k = i; k > j; k--)
					{
						array[k] = array[k-1];
					}
					array[j] = temp;
				}
			}
		}
	}

	@Override
	public void shellSort(T[] array)
	{
		int gap = array.length/2;
		while(gap >=1)
		{
			for(int i = gap; i<array.length; i++)
			{
				this.insertionSort(array, i-gap, gap+1);
			}
			gap = gap/2;
		}
	}

	@Override
	public void quickSort(T[] array)
	{
		this.quickSortInternal(array, 0, array.length-1);
	}
	
	private void quickSortInternal(T[] array, int left, int right)
	{
		if(left >= right)
		{
			return;
		}
		
		int _left = left;
		int _right = right;
		
		boolean flag = false;
		while(left!=right)
		{
			if(flag == false)
			{
				//从右向左逼近
				if(array[right].compareTo(array[left]) < 0)
				{
					T temp = array[left];
					array[left] = array[right];
					array[right] = temp;
					//交换后 应该调换方向
					flag = !flag;
				}
				right--;
			}
			else
			{
				//从左向右逼近
				if(array[left].compareTo(array[right]) > 0)
				{
					T temp = array[left];
					array[left] = array[right];
					array[right] = temp;
					//交换后 应该调换方向
					flag = !flag;
				}
				left++;
			}
		}
		//递归
		this.quickSortInternal(array, _left, left-1);
		this.quickSortInternal(array, right+1, _right);
	}
}










