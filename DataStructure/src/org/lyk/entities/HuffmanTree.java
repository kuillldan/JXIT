package org.lyk.entities;

import org.lyk.entities.List.Node;
import org.omg.CORBA.Current;

public class HuffmanTree<T extends Comparable<T>>
{
	class PriorityNode implements Comparable<PriorityNode>
	{
		T data;
		int count = 0;

		public PriorityNode(T data, int count)
		{
			super();
			this.data = data;
			this.count = count;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + count;
			result = prime * result + ((data == null) ? 0 : data.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PriorityNode other = (PriorityNode) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;

			if (data == null)
			{
				if (other.data != null)
					return false;
			} else if (!data.equals(other.data))
				return false;
			return true;
		}

		private HuffmanTree getOuterType()
		{
			return HuffmanTree.this;
		}

		@Override
		public int compareTo(HuffmanTree<T>.PriorityNode o)
		{
			if (this.count < o.count)
				return -1;
			else if (this.count > o.count)
				return 1;
			else
				return 0;
		}
	}

	class HuffmanNode
	{
		PriorityNode data;
		HuffmanNode left;
		HuffmanNode right;
		String code = "?";

		public HuffmanNode(PriorityNode data)
		{
			this.data = data;
		}
	}

	private java.util.List<PriorityNode> pnList = null;
	private java.util.List<HuffmanNode> hfList = null;
	private StringBuffer codes = new StringBuffer();

	public void setPriorityList(T[] source)
	{
		java.util.List<PriorityNode> pnList = new java.util.ArrayList<>();
		for (T item : source)
		{
			PriorityNode temp = new PriorityNode(item, 1);
			if (!pnList.contains(temp))
			{
				pnList.add(temp);
			} else
			{
				int index = pnList.indexOf(temp);
				PriorityNode pn = pnList.get(index);
				pn.count++;
			}
		}
		pnList.sort((pn1, pn2) ->
		{
			return pn1.compareTo(pn2);
		});
		this.pnList = pnList;
	}

	public void setHuffmanList()
	{
		java.util.List<HuffmanNode> hfList = new java.util.ArrayList<>();
		for (PriorityNode item : this.pnList)
		{
			HuffmanNode temp = new HuffmanNode(item);
			hfList.add(temp);
		}
		this.hfList = hfList;
	}

	public void setHuffmanTree()
	{
		while (this.hfList.size() > 1)
		{
			HuffmanNode node0 = this.hfList.get(0);
			HuffmanNode node1 = this.hfList.get(1);
			PriorityNode pn = new PriorityNode(null, node0.data.count + node1.data.count);
			HuffmanNode newHFNode = new HuffmanNode(pn);
			newHFNode.code = "?";

			if (node0.data.count < node1.data.count)
			{
				node0.code = "0";
				newHFNode.left = node0;
				node1.code = "1";
				newHFNode.right = node1;
			} else
			{
				node1.code = "0";
				newHFNode.left = node1;
				node0.code = "1";
				newHFNode.right = node0;
			}

			this.hfList.remove(0);
			this.hfList.remove(0);
			this.hfList.add(newHFNode);
			this.hfList.sort((hfNode1, hfNode2) ->
			{
				if (hfNode1.data.count < hfNode2.data.count)
					return -1;
				else if (hfNode1.data.count > hfNode2.data.count)
					return 1;
				else
					return 0;
			});
		}
	}

	public void encodeHuffmanTree()
	{
		this.encodeInternal(this.hfList.get(0), "?");
		this.getAllCodes(this.hfList.get(0));
		//System.out.println(this.codes.toString());
	}

	private void encodeInternal(HuffmanNode hfNode, String preCode)
	{
		if ("?".equals(hfNode.code))
		{
			if (null != hfNode.left)
				encodeInternal(hfNode.left, "");
			if (null != hfNode.right)
				encodeInternal(hfNode.right, "");
		} else
		{
			// ·Ç¸ùÔªËØ
			hfNode.code = preCode + hfNode.code;
			if (null != hfNode.left)
				encodeInternal(hfNode.left, hfNode.code);
			if (null != hfNode.right)
				encodeInternal(hfNode.right, hfNode.code);
		}
	}

	private void getAllCodes(HuffmanNode node)
	{
		if (null == node.left && null == node.right)
		{ 
			this.codes.append(node.code);
		}
		if (null != node.left)
			getAllCodes(node.left);
		if (null != node.right)
			getAllCodes(node.right);
	}

	public String decode(String codes)
	{
		char[] charArray = codes.toCharArray();
		HuffmanNode current = this.hfList.get(0);
		for (char c : charArray)
		{
			if ('0' == c)
				current = current.left;
			else if ('1' == c)
				current = current.right;

			if (null == current.left && null == current.right)
			{
				System.out.print(current.data.data);
				current = this.hfList.get(0);
			}
		}
		return null;
	}

	public void find(T item)
	{
		this.findInternal(this.hfList.get(0), item);
	}

	private void findInternal(HuffmanNode node, T item)
	{
		if (null == node.left && null == node.right)
		{
			if (node.data.data.equals(item))
				this.codes.append(node.code);
		}

		if (null != node.left)
			findInternal(node.left, item);
		if (null != node.right)
			findInternal(node.right, item);

	}

	public String encodeContent(T[] charArray)
	{
		for (T c : charArray)
		{
			this.find(c);
		}

		return this.codes.toString();
	}
}
