package entities0301;

import javax.xml.soap.Node;

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
		
		public boolean contains(T data)
		{
			if(this.data.equals(data))
				return true;
			else if(this.next != null)
				return this.next.contains(data);
			else
				return false;
		}

		public void add(T data)
		{
			if(this.next == null)
			{
				this.next = new Node(data);
				List.this.count++;
			}
			else
				this.next.add(data);
		}

		public void removeFirst(List<T>.Node previous, T data)
		{
			if(this.data.equals(data))
			{
				previous.next = this.next;
				this.next = null;
				List.this.count--;
			}
			else if(this.next != null)
				this.next.removeFirst(this, data);
		}

		public void removeFirst(List<T>.Node previous, Integer index)
		{
			if(index.equals(List.this.foot++))
			{
				this.removeCurrent(previous);
			}
			else
			{
				this.next.removeFirst(this, index);
			}
		}

		private void removeCurrent(Node previous)
		{
			previous.next = this.next;
			this.next = null;
			List.this.count++;
		}

		public T get(Integer index)
		{
			if(index.equals(List.this.foot++))
				return this.data;
			else
				return this.next.get(index);
		}

		public void replace(Integer index, T newData)
		{
			if(index.equals(List.this.foot++))
				this.data = newData;
			else
				this.next.replace(index, newData);
		}

		public void replace(T oldData, T newData)
		{
			if(this.data.equals(oldData))
				this.data = newData;
			else
				this.next.replace(oldData, newData);
		}
	}
	
	
	private Integer count =0;
	private Integer foot = 0;
	private Node root;
	
	@Override
	public boolean isEmpty()
	{
		if(this.count == 0 && this.root == null)
			return true;
		
		return false;
	}

	@Override
	public Integer size()
	{
		return this.count;
	}

	@Override
	public boolean contains(T data)
	{
		if(this.isEmpty() || data == null)
			return false;
		
		return this.root.contains(data);
	}

	@Override
	public void add(T data)
	{
		if(data == null)
			return ;
		
		
		if(this.isEmpty())
			this.addRoot(data);
		else
			this.root.add(data);
	}

	@Override
	public void removeFirst(T data)
	{
		if(this.isEmpty() || data == null)
			return;
		
		if(this.root.data.equals(data))
			this.removeRoot();
		else if(this.root.next != null)
			this.root.next.removeFirst(this.root,data);
	}

	private void removeRoot()
	{
		Node tobeRemoved = this.root;
		this.root = this.root.next;
		tobeRemoved.next = null;
		this.count--;
	}

	@Override
	public void removeAll(T data)
	{
		if(this.isEmpty() || data == null)
			return;
		
		while(this.contains(data))
			this.removeFirst(data);
	}

	@Override
	public void removeFirt(Integer index)
	{
		if(this.isEmpty() || index == null || index < 0 || index >= this.size())
		{
			return;
		}
		
		if(index == 0)
			this.removeRoot();
		else
		{
			this.foot = 1;
			this.root.next.removeFirst(this.root, index);
		}
	}

	@Override
	public T get(Integer index)
	{
		if(this.isEmpty() || index == null || index < 0 || index >= this.size())
		{
			return null;
		}
		
		this.foot = 0;
		return this.root.get(index);
	}

	@Override
	public void replace(Integer index, T newData)
	{
		if(this.isEmpty() || index == null || index < 0 || index >= this.size() || newData == null)
		{
			return;
		}
		
		this.foot = 0;
		this.root.replace(index,newData);
	}

	@Override
	public void replace(T oldData, T newData)
	{
		if(this.isEmpty() || oldData == null || newData == null)
		{
			return;
		}
		
		this.root.replace(oldData,newData);
	}

	@Override
	public void replaceAll(T oldData, T newData)
	{
		if(this.isEmpty() || oldData == null || newData == null)
		{
			return;
		}
		
		while(this.contains(oldData))
			this.replace(oldData, newData);
	}

	@Override
	public Object[] toArray()
	{
		if(this.isEmpty())
			return null;
		
		Object[] results = new Object[this.size()];
		for(int i = 0 ; i < this.size(); i++)
		{
			results[i] = this.get(i);
		}
		return results;
	}
	
	
	private void addRoot(T data)
	{
		this.root = new Node(data);
		this.count++;
	}
}
