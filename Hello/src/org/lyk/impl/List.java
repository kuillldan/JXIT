package org.lyk.impl;

import org.lyk.interfaces.IList;

public class List<T> implements IList<T>
{

	private class Node
	{
		private T data;
		private Node next;

		public Node(T data)
		{
			this.data = data;
		}

		private void add(T data)
		{
			if (null == this.next)
			{
				this.next = new Node(data);
				List.this.count++;
			} else
			{
				this.next.add(data);
			}
		}

		public void remove(Node previous, T data)
		{
			if (this.data.equals(data))
			{
				previous.next = this.next;
				this.next = null;
				List.this.count--;
			} else
			{
				if (null != this.next)
					this.next.remove(this, data);
			}
		}

		public void remove(Node previous, Integer index)
		{
			if (List.this.index++ == index)
			{
				previous.next = this.next;
				this.next = null;
				List.this.count--;
			} else
			{
				if (null != this.next)
					this.next.remove(this, index);
				else
					return;
			}
		}

		public boolean contains(T data)
		{
			if (this.data.equals(data))
				return true;
			else
			{
				if (null != this.next)
					return this.next.contains(data);
				else
					return false;
			}
		}

		public T get(Integer index)
		{
			if (List.this.index++ == index)
				return this.data;
			else
			{
				if (null != this.next)
					return this.next.get(index);
				else
					return null;
			}
		}

		public Node findByValue(T data)
		{
			if (this.data.equals(data))
				return this;
			else
			{
				if (null != this.next)
					return this.next.findByValue(data);
				else
					return null;
			}
		}

		public Node findByIndex(Integer index)
		{
			if (List.this.index++ == index)
				return this;
			else
			{
				if (null != this.next)
					return this.next.findByIndex(index);
				else
					return null;
			}
		}

	}

	private Node root;
	private Integer count;
	private Integer index;

	public List()
	{
		this.count = 0;
		this.index = 0;
	}

	@Override
	public boolean isEmpty()
	{
		if (0 == this.count && null == this.root)
			return true;
		else
			return false;
	}

	@Override
	public void add(T data)
	{
		if (this.isEmpty())
		{
			this.root = new Node(data);
			this.count++;
		} else
			this.root.add(data);
	}

	@Override
	public void remove(T data)
	{
		if (this.isEmpty())
		{
			return;
		}

		if (data.equals(this.root.data))
		{
			Node nodeDel = this.root;
			this.root = this.root.next;
			nodeDel.next = null;
			this.count--;
		} else
		{
			if (null == this.root.next)
				return;
			else
				this.root.next.remove(this.root, data);
		}
	}

	@Override
	public void removeAll(T data)
	{
		if (this.isEmpty())
			return;

		while (this.contains(data))
			this.remove(data);
	}

	@Override
	public void remove(Integer index)
	{
		if (null == index || index < 0 || index >= this.count)
		{
			return;
		}

		if (this.isEmpty())
			return;

		if (0 == index)
		{
			Node nodeDel = this.root;
			this.root = this.root.next;
			nodeDel.next = null;
			this.count--;
		} else
		{
			if (null != this.root.next)
			{
				this.index = 1;
				this.root.next.remove(this.root, index);
			} else
			{
				return;
			}
		}
	}

	@Override
	public boolean contains(T data)
	{
		if (this.isEmpty())
			return false;
		else
			return this.root.contains(data);
	}

	@Override
	public T get(Integer index)
	{
		if (this.isEmpty())
			return null;

		if (null == index || index < 0 || index >= this.count)
			return null;

		this.index = 0;
		return this.root.get(index);
	}

	@Override
	public void replace(Integer index, T newData) throws Exception
	{
		if (null == index || index < 0 || index >= this.count)
			throw new Exception("index 错误!");
		if (null == newData)
			throw new Exception("newData不能为空");

		Node node = this.findByIndex(index);
		if (null == node)
			return;
		else
		{
			node.data = newData;
		}
	}

	@Override
	public void replace(T oldData, T newData)
	{
		if (null == oldData || null == newData)
			return;

		Node node = this.findByValue(oldData);
		if (null != node)
			node.data = newData;
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

	private Node findByValue(T data)
	{
		return this.root.findByValue(data);
	}

	private Node findByIndex(Integer index)
	{
		this.index = 0;
		return this.root.findByIndex(index);
	}
}