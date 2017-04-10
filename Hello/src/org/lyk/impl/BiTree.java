package org.lyk.impl;

import java.util.HashMap;
import java.util.Map;

import org.lyk.interfaces.IBiTree;

public class BiTree<T extends Comparable<T>> implements IBiTree<T>
{
	private class Node
	{
		private T data;
		private Node left;
		private Node right;

		private Node(T data)
		{
			if (null == data)
				return;
			else
				this.data = data;
		}

		private void addWithOrder(T[] dataArray, Node root)
		{
			if (BiTree.this.index >= dataArray.length)
				return;

			if (dataArray[BiTree.this.index].compareTo(this.data) < 0)
			{
				if (null != this.left)
				{
					// BiTree.this.index++;
					this.left.addWithOrder(dataArray, root);
				} else
				{
					this.left = new Node(dataArray[BiTree.this.index]);
					BiTree.this.count++;
					BiTree.this.index++;
				}
			} else
			{
				if (null != this.right)
				{
					// BiTree.this.index++;
					this.right.addWithOrder(dataArray, root);
				} else
				{
					this.right = new Node(dataArray[BiTree.this.index]);
					BiTree.this.count++;
					BiTree.this.index++;
				}
			}

			root.addWithOrder(dataArray, root);
		}

		private void toArrayFist(Object[] retVal)
		{
			retVal[BiTree.this.index++] = this.data;
			if (null != this.left)
				this.left.toArrayFist(retVal);
			if (null != this.right)
				this.right.toArrayFist(retVal);
		}

		public void toArrayMiddle(Object[] retVal)
		{
			if (null != this.left)
				this.left.toArrayMiddle(retVal);
			retVal[BiTree.this.index++] = this.data;
			if (null != this.right)
				this.right.toArrayMiddle(retVal);
		}

		public void toArrayLast(Object[] retVal)
		{
			if (null != this.left)
				this.left.toArrayLast(retVal);
			if (null != this.right)
				this.right.toArrayLast(retVal);
			retVal[BiTree.this.index++] = this.data;
		}

		public Node find(T data)
		{
			if (this.data.equals(data))
			{
				return this;
			} else
			{
				Node target = null;
				if (null != this.left)
					target = this.left.find(data);

				if (null != target)
					return target;

				if (null != this.right)
					target = this.right.find(data);
				return target;
			}
		}

		public Node addFirst(T[] dataArray, T endFlag)
		{
			if (endFlag.equals(dataArray[BiTree.this.index]))
			{
				BiTree.this.index++;
				return null;
			} else
			{
				Node node = new Node(dataArray[BiTree.this.index++]);
				BiTree.this.count++;
				node.left = addFirst(dataArray, endFlag);
				node.right = addFirst(dataArray, endFlag);
				return node;
			}
		}

		public Map<String, Object> findWithRelation(Node current, Node parent,
				String direction, T data)
		{
			if (current.data.equals(data))
			{
				Map<String, Object> retVal = new HashMap<String, Object>();
				retVal.put("current", current);
				retVal.put("parent", parent);
				retVal.put("direction", direction);
				return retVal;
			} else
			{
				Map<String, Object> retVal = null;
				if (null != current.left)
				{
					retVal = current.findWithRelation(current.left, current,
							"left", data);
				}
				if (null != retVal)
					return retVal;

				if (null != current.right)
				{
					retVal = current.findWithRelation(current.right, current,
							"right", data);
				}
				return retVal;
			}
		}
	}

	// ===================================
	private Integer count;
	private Node root;

	private Integer index;

	public BiTree()
	{
		this.count = 0;
		this.root = null;
		this.index = 0;
	}

	@Override
	public void addWithOrder(T[] dataArray)
	{
		if (null == dataArray || 0 == dataArray.length)
		{
			return;
		}

		if (this.isEmpty())
		{
			this.root = new Node(dataArray[0]);
			this.count++;
		}

		this.index = 1;
		this.root.addWithOrder(dataArray, this.root);

	}

	@Override
	public void addFirst(T[] dataArray, T endFlag)
	{
		if (this.isEmpty())
		{
			if (endFlag.equals(dataArray[0]))
				return;

			this.root = new Node(dataArray[0]);
		}

		this.index = 0;
		this.count = 0;
		this.root = this.root.addFirst(dataArray, endFlag);
	}

	@Override
	public Object[] toArrayFirst()
	{
		if (this.isEmpty())
			return null;
		Object[] retVal = new Object[this.count];
		this.index = 0;
		this.root.toArrayFist(retVal);
		return retVal;
	}

	@Override
	public Object[] toArrayMiddle()
	{
		if (this.isEmpty())
			return null;
		Object[] retVal = new Object[this.count];
		this.index = 0;
		this.root.toArrayMiddle(retVal);
		return retVal;
	}

	@Override
	public Object[] toArrayLast()
	{
		if (this.isEmpty())
			return null;
		Object[] retVal = new Object[this.count];
		this.index = 0;
		this.root.toArrayLast(retVal);
		return retVal;
	}

	@Override
	public Integer size()
	{
		return this.count;
	}

	@Override
	public Boolean isEmpty()
	{
		if (0 == this.count && null == this.root)
			return true;
		else
			return false;
	}

	@Override
	public Boolean contains(T data)
	{
		if (null == data || this.isEmpty())
			return false;
		Node target = this.find(data);
		if (null == target)
			return false;
		else
			return true;

	}

	public Node find(T data)
	{
		if (this.isEmpty())
			return null;
		else
			return this.root.find(data);
	}

	public Map<String, Object> findWithRelation(T data)
	{
		if (this.isEmpty())
			return null;

		return this.root.findWithRelation(this.root, null, null, data);
	}

	@Override
	public void delete(T data)
	{
		if (this.isEmpty())
			return;

		Map<String, Object> map = this.findWithRelation(data);
		Node target = (Node) map.get("current");
		Node parent = (Node) map.get("parent");
		String direction = (String) map.get("direction");

		if (null == target.left && null == target.right)
		{
			if (null == parent)
			{
				this.root = null;
				this.count--;
				return;
			} else if ("left".equals(direction))
			{
				parent.left = null;
			} else
			{
				parent.right = null;
			}
			
			this.count--;
			return;
		}
		
		if(null != target.left && null == target.right)
		{
			if(null == parent)
			{
				Node nodeDel = this.root;
				this.root = this.root.left;
				nodeDel.left = null;
			}
			else if("left".equals(direction))
			{
				parent.left = target.left;
			}
			else
			{
				parent.right = target.left;
			}
			
			this.count--;
			return;
		}
		
		if(null == target.left && null != target.right)
		{
			if(null == parent)
			{
				Node nodeDel = this.root;
				this.root = this.root.right;
				nodeDel.right = null;
			}
			else if("left".equals(direction))
			{
				parent.left = target.right;
			}
			else
			{
				parent.right = target.right;
			}
			this.count--;
			return;
		}
		
		Node successor = this.findSuccessor(target, target, target.right);
		successor.left = target.left;
		if(null == parent)
		{
			this.root = successor;
		}
		else if("left".equals(direction))
		{
			parent.left = successor;
		}
		else
		{
			parent.right = successor;
		}
		this.count--;
		return;
	}
	
	private Node findSuccessor(Node nodeDel,Node successorParent,Node successor)
	{
		if(null != successor.left)
		{
			successor = this.findSuccessor(nodeDel, successor,successor.left);
		}
		else
		{
			if(successor != nodeDel.right)
			{
				successorParent.left = successor.right;
				successor.right = nodeDel.right;
			}
		}
		return successor;
	}

	@Override
	public void replace(T oldData, T newData)
	{
		Node target = this.find(oldData);
		if(null != target)
		{
			target.data = newData;
		}
	}
}
