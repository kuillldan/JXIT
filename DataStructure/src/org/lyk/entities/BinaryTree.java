package org.lyk.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BinaryTree<T extends Comparable<T> > implements Serializable
{
	private class Node implements Serializable
	{
		private T data;
		private Node left;
		private Node right;
		
		private Node(T data)
		{
			this.data = data;
		}
		
		private void add(T data)
		{
			if(this.data.compareTo(data)<0)
			{
				if(this.right == null)
					this.right = new Node(data);
				else
					this.right.add(data);
			}
			else
			{
				if(this.left == null)
					this.left = new Node(data);
				else
					this.left.add(data); 
			}
		}
		
		private void addToArray(Object[] retVal)
		{
			if(this.left != null)
				this.left.addToArray(retVal);
			
			retVal[BinaryTree.this.foot++] = this.data;
			
			if(this.right != null)
				this.right.addToArray(retVal);
		}
	}
	//========================================
	private int count;
	private int foot;
	private Node root;
	
	public BinaryTree()
	{
		// TODO Auto-generated constructor stub
	}
	
	public boolean isEmpty()
	{
		if(this.root == null && this.count == 0)
			return true;
		else
			return false;
	}
	
	
	public void add(T data)
	{
		if(data == null)
			return ; 
		
		if(this.isEmpty())
			this.root = new Node(data);
		else
			this.root.add(data);
		
		this.count++;
	}
	
	public Object[] toArray()
	{
		if (this.isEmpty())
		{
			return null;
		}
		
		Object[] retVal = new Object[this.count];
		this.foot = 0;
		this.root.addToArray(retVal);
		return retVal;
	}
}
