package org.lyk.entities;

import java.util.*;

import org.lyk.interfaces.IList;

public class List<T> implements IList<T>
{
	class Node<N>
	{
		private N data;
		private Node<N> next;
		
		public Node(N data)
		{
			if(null == data)
				return ;
			
			this.data = data;
		}
		
		public void add(N data)
		{
			if(null == this.next)
			{
				this.next = new Node<N>(data);
				List.this.count++;
			}
			else
			{
				this.next.add(data);
			}
		}

		public void remove(Node<N> previous, T data)
		{
			if(this.data.equals(data))
			{
				previous.next = this.next;
				this.next = null;
				List.this.count--;
			}
			else if(null != this.next )
			{
				this.next.remove(this,data);
			}
			else
			{
				return;
			}
		}

		public void remove(Node<N> previous, Integer index)
		{
			if(List.this.foot++ == index)
			{
				Node<N> needToRemove = this;
				previous.next = this.next;
				needToRemove.next = null;
				List.this.count--;
			}
			else if( null != this.next )
			{
				this.next.remove(this, index);
			}
			else
			{
				return;
			}
		}

		public boolean contains(T data)
		{
			if(this.data.equals(data))
				return true;
			else if(null != this.next)
				return this.next.contains(data);
			else
				return false;
		}

		public N get(Integer index)
		{
			if(List.this.foot++ == index)
			{
				return this.data;
			}
			else if( null != this.next)
				return this.next.get(index);
			else
				return null;
		}

		public void replace(Integer index, N newData)
		{
			if(List.this.foot++ == index)
				this.data = newData;
			else if(null != this.next)
				this.next.replace(index, newData);
			else
				return;
		}

		public void replace(N oldData, N newData)
		{
			if(this.data.equals(oldData))
				this.data = newData;
			else if(null != this.next )
				this.next.replace(oldData, newData);
			else
				return;
		}
	}
	
	//=====================================
	
	
	private int count = 0;
	private int foot = 0;
	private Node<T> root;
	
	@Override
	public boolean isEmpty()
	{
		if(this.count == 0 && null == root)
			return true;
		else
			return false;
	}

	@Override
	public void add(T data)
	{
		if(null == data)
			return ;
		
		if(this.isEmpty())
		{
			this.root = new Node<T>(data);
			this.count++;
		}
		else
			this.root.add(data);
	}

	@Override
	public void removeFirst(T data)
	{
		if(null == data)
			return ;
		if(this.isEmpty())
			return;
		
		if(this.root.equals(data))
		{
			Node<T> needToRemove = this.root;
			this.root = this.root.next;
			this.count--;
		}
		else
		{
			this.root.remove(this.root,data);
		}
	}

	@Override
	public void removeAll(T data)
	{
		if(null == data && this.isEmpty())
			return;
		
		while(this.contains(data))
		{
			this.removeFirst(data);
		}
	}

	@Override
	public void removeFirt(Integer index)
	{
		if(null == index || index < 0 || index >= this.count || this.isEmpty())
			return ;
		
		if(0 == index)
		{
			Node<T> needToRemove = this.root;
			this.root = this.root.next;
			needToRemove.next = null;
			this.count--;
		}
		else
		{
			this.foot = 1;
			this.root.next.remove(this.root,index);
		}
	}

	@Override
	public boolean contains(T data)
	{
		if(null == data)
			return false;
		
		if(this.isEmpty())
			return false;
		
		return this.root.contains(data);
	}

	@Override
	public T get(Integer index)
	{
		if(null == index || index < 0 || index >= this.count || this.isEmpty())
			return null;
		
		this.foot = 0;
		return this.root.get(index);
	}

	@Override
	public void replace(Integer index, T newData)
	{
		if(null == index || index < 0 || index >= this.count || this.isEmpty())
			return;
		
		this.foot = 0;
		this.root.replace(index,newData);
	}

	@Override
	public void replace(T oldData, T newData)
	{
		if(this.isEmpty() || null == oldData || null == newData)
			return;
		
		this.root.replace(oldData, newData);
	}

	@Override
	public Object[] toArray()
	{
		if(this.isEmpty())
			return null;
		
		Object[] retVal = new Object[this.count];
		for(int i = 0; i < this.count; i++)
			retVal[i] = this.get(i);
		return retVal;
	}

	@Override
	public Integer size()
	{
		return this.count;
	}

	@Override
	public void replaceAll(T oldData, T newData)
	{
		// TODO Auto-generated method stub
		
	}	
}
