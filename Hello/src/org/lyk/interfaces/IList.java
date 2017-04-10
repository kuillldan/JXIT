package org.lyk.interfaces;

public interface IList<T>
{
	public boolean isEmpty();
	public void add(T data);
	public void remove(T data);
	public void removeAll(T data);
	public void remove(Integer index);
	public boolean contains(T data);
	public T get(Integer index);
	public void replace(Integer index, T newData) throws Exception;
	public void replace(T oldData, T newData);
	public Object[] toArray();
	public Integer size();
}