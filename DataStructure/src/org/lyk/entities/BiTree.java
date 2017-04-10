package org.lyk.entities;

import java.util.HashMap;
import java.util.Map;

import org.lyk.interfaces.IBiTree;

public class BiTree<T extends Comparable<T>> implements IBiTree<T>
{
	private class Node<N extends Comparable<N>>
	{
		private N data;
		private Node<N> left;
		private Node<N> right;

		private Node(N data)
		{
			if (null == data)
				return;
			this.data = data;
		}

		public void addWithOrder(N data)
		{
			// if(this.data.compareTo(data) < 0)
			if (data.compareTo(this.data) < 0)
			{
				if (null == this.left)
				{
					this.left = new Node<N>(data);
					BiTree.this.count++;
				} else
					this.left.addWithOrder(data);
			} else
			{
				if (null == this.right)
				{
					this.right = new Node<N>(data);
					BiTree.this.count++;
				} else
					this.right.addWithOrder(data);
			}
		}

		public void toArrayFirst(Object[] retVal)
		{
			retVal[BiTree.this.foot++] = this.data;
			if (null != this.left)
				this.left.toArrayFirst(retVal);
			if (null != this.right)
				this.right.toArrayFirst(retVal);
		}

		public void toArrayMiddle(Object[] retVal)
		{
			if (null != this.left)
				this.left.toArrayMiddle(retVal);
			retVal[BiTree.this.foot++] = this.data;
			if (null != this.right)
				this.right.toArrayMiddle(retVal);
		}

		public void toArrayLast(Object[] retVal)
		{
			if (null != this.left)
				this.left.toArrayLast(retVal);
			if (null != this.right)
				this.right.toArrayLast(retVal);
			retVal[BiTree.this.foot++] = this.data;
		}
	}

	private int count = 0;
	private Node<T> root = null;
	private int foot = 0;

	@Override
	public void addWithOrder(T data)
	{
		if (null == data)
			return;

		if (null == this.root)
		{
			this.root = new Node<T>(data);
			this.count++;
		} else
		{
			this.root.addWithOrder(data);
		}
	}

	@Override
	public void addFirst(T[] data, T endFlag)
	{
		if (null == data)
		{
			return;
		}

		this.foot = 0;
		this.root = this.addFirstInternal(data, endFlag, this.root);
	}

	//todo 增加元素时可能会有异常
	private Node<T> addFirstInternal(T[] data, T endFlag, Node<T> node)
	{
		if (data[this.foot].equals(endFlag))
		{
			this.foot++;
			return null;
		} else
		{
			node.data = data[this.foot];
			this.foot++;
			node.left = this.addFirstInternal(data, endFlag, node.left);
			node.right = this.addFirstInternal(data, endFlag, node.right);
			return node;
		}
	}

	@Override
	public Object[] toArrayFirst()
	{
		if (this.isEmpty())
			return null;
		Object[] retVal = new Object[this.count];
		this.foot = 0;
		this.root.toArrayFirst(retVal);
		return retVal;
	}

	@Override
	public Object[] toArrayMiddle()
	{
		if (this.isEmpty())
			return null;
		Object[] retVal = new Object[this.count];
		this.foot = 0;
		this.root.toArrayMiddle(retVal);
		return retVal;
	}

	@Override
	public Object[] toArrayLast()
	{
		if (this.isEmpty())
			return null;
		Object[] retVal = new Object[this.count];
		this.foot = 0;
		this.root.toArrayLast(retVal);
		return retVal;
	}

	@Override
	public int size()
	{
		return this.count;
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
	public void delete(T data)
	{
		if (this.isEmpty())
			return;

		Map<String, Object> nodeDel = this.find(data);
		if (null == nodeDel)
			return;

		Node<T> node = (Node<T>) nodeDel.get("node");
		Node<T> parent = (Node<T>) nodeDel.get("parent");
		String direction = (String) nodeDel.get("direction");
		if (null == node.left && null == node.right)
		{
			if (null == direction)
			{
				this.root = null;
			} else
			{
				if ("left".equals(direction))
				{
					parent.left = null;
				} else if ("right".equals(direction))
				{
					parent.right = null;
				}
			}
			this.count--;
			return;
		}

		if (null != node.left && null == node.right)
		{
			// 该节点只有左子树
			if (null == direction)
			{
				Node<T> temp = this.root;
				this.root = this.root.left;
				temp.left = null;
			} else
			{
				if ("left".equals(direction))
				{
					parent.left = node.left;
					node.left = null;
				} else if ("right".equals(direction))
				{
					parent.right = node.left;
					node.left = null;
				}
			}
			this.count--;
			return;
		}

		if (null == node.left && null != node.right)
		{
			// 该节点只有右子树
			if (null == direction)
			{
				Node<T> temp = this.root;
				this.root = this.root.right;
				temp.right = null;
			} else
			{
				if ("left".equals(direction))
				{
					parent.left = node.right;
					node.right = null;
				} else if ("right".equals(direction))
				{
					parent.right = node.right;
					node.right = null;
				}
			}
			this.count--;
			return;
		}

		Node<T> subsessor = this.getSubsessor(node);
		if (null != subsessor)
		{
			//System.out.println("subsessor:" + subsessor.data);
			subsessor.left = node.left;
			if (null == direction)
			{
				this.root = subsessor;
			} else if ("left".equals(direction))
			{
				parent.left = subsessor;
			} else if ("right".equals(direction))
			{
				parent.right = subsessor;
			}
			this.count--;
		}
		
	}

	private Node<T> getSubsessor(Node<T> nodeDel)
	{
		Node<T> parent = nodeDel;
		Node<T> subsessor = nodeDel.right;
		subsessor = this.getSubsessorInternal(parent, subsessor, nodeDel);
		return subsessor;
	}

	private Node<T> getSubsessorInternal(Node<T> parent, Node<T> subsessor, Node<T> nodeDel)
	{
		if (null != subsessor.left)
		{
			parent = subsessor;
			subsessor = subsessor.left;
			return getSubsessorInternal(parent, subsessor, nodeDel);
		} else
		{
			if (subsessor != nodeDel.right)
			{
				parent.left = subsessor.right;
				subsessor.right = nodeDel.right;
			}
			return subsessor;
		}
	}

	private Map<String, Object> find(T data)
	{
		return this.findInternal(this.root, null, null, data);
	}

	private Map<String, Object> findInternal(Node<T> node, Node<T> parent, String direction, T data)
	{
		Map<String, Object> retVal = null;
		if (node.data.equals(data))
		{
			retVal = new HashMap<>();
			retVal.put("node", node);
			retVal.put("parent", parent);
			retVal.put("direction", direction); 
			return retVal;
		} else
		{
			if (null != node.left)
			{
				// parent = node;
				// node = node.left;
				retVal = this.findInternal(node.left, node, "left", data);
				if(null != retVal)
					return retVal;
			}

			if (null != node.right)
			{
				// parent = node;
				// node = node.right;
				retVal = this.findInternal(node.right, node, "right", data);
				if(null != retVal)
					return retVal;
			}
		}
		return retVal;
	}

	@Override
	public boolean contains(T data)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
