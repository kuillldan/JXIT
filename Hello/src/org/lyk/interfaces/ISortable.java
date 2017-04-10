package org.lyk.interfaces;

public interface ISortable<T extends Comparable<T>>
{
	/**
	 * 冒泡排序
	 * @param array
	 */
	void bubbleSort(T[] array);
	
	
	
	/**
	 * 选择排序
	 * @param array 待排序数组
	 */
	void selectionSort(T[] array);
	
	
	
	/**
	 * 插入排序。对整个传入数据进行插入排序。
	 * @param array 待排序数组
	 */
	void insertionSort(T[] array);
	
	
	
	/**
	 * 插入排序。对传入的数据进行部分排序。（指定待排序元素起点和终点）
	 * @param array 待排序数组
	 * @param start 待排序数组的起点
	 * @param length 从起点开始，对接下来的length个元素进行排序
	 */
	void insertionSort(T[] array, int start, int length);
	
	
	/**
	 * 希尔排序
	 * @param array 待排序数组
	 */
	void shellSort(T[] array);
	
	
	
	/**
	 * 快速排序 
	 * @param array 待排序数组
	 */
	void quickSort(T[] array);
}
