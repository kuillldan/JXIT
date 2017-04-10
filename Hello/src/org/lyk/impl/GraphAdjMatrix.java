package org.lyk.impl;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

import org.lyk.interfaces.IGraph;

public class GraphAdjMatrix implements IGraph
{

	class Node
	{
		private boolean wasVisited;
		private char data;

		public Node(char data)
		{
			this.data = data;
			this.wasVisited = false;
		}
	}

	private Node[] nodes;
	private int[][] adjMetrix;
	private int maxSize;
	private int index;

	public GraphAdjMatrix()
	{
		this.maxSize = 6;
		this.index = 0;
		this.nodes = new Node[this.maxSize];
		this.adjMetrix = new int[this.maxSize][this.maxSize];
	}

	/**
	 * 增加节点
	 * 
	 * @param data
	 * @throws Exception
	 */
	public void add(char data) throws Exception
	{
		if (this.index >= this.maxSize)
		{
			throw new Exception("数组已满");
		} else
		{
			this.nodes[index++] = new Node(data);
		}
	}

	/**
	 * 设置图中各个节点关系
	 * 
	 * @param x
	 * @param y
	 * @throws Exception
	 */
	public void setRelation(int x, int y) throws Exception
	{
		if (x < this.maxSize && y < this.maxSize)
		{
			this.adjMetrix[x][y] = 1;
			this.adjMetrix[y][x] = 1;
		} else
		{
			throw new Exception("下标错误!");
		}
	}

	/**
	 * 广度优先对图进行遍历
	 * 
	 * @param x
	 * @throws Exception
	 */
	public void showBreathFirst(int x) throws Exception
	{
		if (x >= 0 && x < this.index)
		{
			java.util.List<Integer> list = new java.util.ArrayList<>();
			int current = 0;
			list.add(x);
			this.nodes[list.get(current)].wasVisited = true;
			while (current < list.size())
			{
				System.out.println(this.nodes[list.get(current)].data);

				int nextSuccessor = this.getNextSuccessor(list.get(current));
				while (nextSuccessor != -1)
				{
					list.add(nextSuccessor);
					this.nodes[nextSuccessor].wasVisited = true;
					nextSuccessor = this.getNextSuccessor(list.get(current));
				}
				current++;
			}

			this.resetNodes();
		} else
		{
			throw new Exception("下标越界");
		}
	}

	/**
	 * 深度优先对图进行遍历
	 * 
	 * @param x
	 * @throws Exception
	 */
	public void showDeepFirst(int x) throws Exception
	{
		if (x < this.index && x >= 0)
		{
			Stack<Integer> stack = new Stack<>();
			stack.push(x);
			System.out.println(this.nodes[x].data);
			this.nodes[x].wasVisited = true;
			while (!stack.isEmpty())
			{
				int temp = stack.peek();
				int nextSuccessor = this.getNextSuccessor(temp);
				if (nextSuccessor == -1)
				{
					stack.pop();
				} else
				{
					stack.push(nextSuccessor);
					System.out.println(this.nodes[nextSuccessor].data);
					this.nodes[nextSuccessor].wasVisited = true;
				}
			}
			this.resetNodes();
		} else
		{
			throw new Exception("下标错误");
		}

	}

	/**
	 * 广度优先-最小生成树
	 * 
	 * @param x
	 * @throws Exception
	 */
	public void toMSTBreathFirst(int x) throws Exception
	{
		if (x >= 0 && x < this.index)
		{
			java.util.List<Integer> list = new java.util.ArrayList();
			int current = 0;
			list.add(x);
			this.nodes[list.get(current)].wasVisited = true;
			while (current < this.index)
			{
				int successor = this.getNextSuccessor(list.get(current));
				while (successor != -1)
				{
					list.add(successor);
					System.out.println(this.nodes[list.get(current)].data
							+ "->" + this.nodes[successor].data);
					this.nodes[successor].wasVisited = true;
					successor = this.getNextSuccessor(list.get(current));
				}
				current++;
			}
		} else
		{
			throw new Exception("下标错误");
		}
	}

	/**
	 * 深度优先求最小生成树
	 * 
	 * @param x
	 * @throws Exception
	 */
	public void toMSTDeepFirst(int x) throws Exception
	{
		if (x < this.index && x >= 0)
		{
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(x);
			this.nodes[x].wasVisited = true;
			while (!stack.isEmpty())
			{
				int current = stack.peek();
				int nextSuccessor = this.getNextSuccessor(current);
				if (nextSuccessor == -1)
				{
					stack.pop();
				} else
				{
					stack.push(nextSuccessor);
					this.nodes[nextSuccessor].wasVisited = true;
					System.out.print(this.nodes[current].data);
					System.out.print("-");
					System.out.print(this.nodes[nextSuccessor].data);
					System.out.print(" ");
				}
			}

			this.resetNodes();
		} else
		{
			throw new Exception("下标错误");
		}
	}

	private int getNextSuccessor(int x)
	{
		for (int i = 0; i < this.maxSize; i++)
		{
			if (this.adjMetrix[x][i] != 0 && this.nodes[i].wasVisited == false)
			{
				return i;
			}
		}
		return -1;
	}

	private void resetNodes()
	{
		for (int i = 0; i < this.index; i++)
		{
			this.nodes[i].wasVisited = false;
		}
	}
}
