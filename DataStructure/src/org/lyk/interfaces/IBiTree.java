package org.lyk.interfaces;

import java.util.List;

public interface IBiTree<T extends Comparable<T>>
{
	public void addWithOrder(T data);
	public void addFirst(T[] data,T endFlag);
	public Object[] toArrayFirst();
	public Object[] toArrayMiddle();
	public Object[] toArrayLast();
	public int size();
	public boolean isEmpty();
	public boolean contains(T data);
	public void delete(T data);
}
