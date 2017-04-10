package org.lyk.interfaces;

public interface IList<T>
{
	
	/**
	 * 判断该List是否为空
	 * @return List为空则返回ture,否则返回false
	 */
	public boolean isEmpty();
	/**
	 * 返回当前链表长度
	 * @return
	 */
	public Integer size();
	
	/**
	 * 用equals方法判断链表是否包含特定的元素
	 * @param data
	 * @return
	 */
	public boolean contains(T data);
	
	
	/**
	 * 增加一个元素
	 * @param data
	 */
	public void add(T data);
	/**
	 * 移除一个元素，如果有多个元素，则只会移除第一个
	 * @param data
	 */
	public void removeFirst(T data);
	/**
	 * 移除所有data元素
	 * @param data
	 */
	public void removeAll(T data);
	/**
	 * 移除位置为index的元素，如果下标越界，则不会移除任何元素
	 * @param index
	 */
	public void removeFirt(Integer index);
	
	/**
	 * 获取指定index的元素
	 * @param index
	 * @return
	 */
	public T get(Integer index);
	/**
	 * 将指定index位置的元素替换为newData
	 * @param index
	 * @param newData
	 */
	public void replace(Integer index, T newData);
	/**
	 * 将第一个oldData替换为newData
	 * @param oldData
	 * @param newData
	 */
	public void replace(T oldData, T newData);
	
	
	
	/**
	 * 将链表中所有oldData替换
	 * @param oldData
	 * @param newData
	 */
	public void replaceAll(T oldData, T newData);
	
	/**
	 * 将链表转换为数组
	 * @return
	 */
	public Object[] toArray();
	
}