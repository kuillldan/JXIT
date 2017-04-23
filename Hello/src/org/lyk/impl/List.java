package org.lyk.impl;

import org.lyk.interfaces.IList;

public class List<T> implements IList<T> {

	private class Node
	{
		private T data;
		private Node next;
		
		
	}
	
	private Node root;
	private int count;
	private int foot;
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(T data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(T data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAll(T data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Integer index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(T data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T get(Integer index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void replace(Integer index, T newData) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replace(T oldData, T newData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return null;
	}

}
