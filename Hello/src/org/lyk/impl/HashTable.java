package org.lyk.impl;

import org.lyk.interfaces.IMap;

public class HashTable<K, V> implements IMap<K, V>
{
	/**
	 * Key-Value pair 存放键值对
	 * @author liuyuank
	 *
	 */
	private class KeyValue
	{
		K key;
		V value;

		private KeyValue(K key, V value)
		{
			this.key = key;
			this.value = value;
		}

		public K getKey()
		{
			return key;
		}

		public void setKey(K key)
		{
			this.key = key;
		}

		public V getValue()
		{
			return value;
		}

		public void setValue(V value)
		{
			this.value = value;
		}

	}

	private Object[] table;
	private int maxSize = 10;
	private int currentAmmount = 0;

	public HashTable()
	{
		this.table = new Object[this.maxSize];
	}

	public HashTable(int maxSize) throws Exception
	{
		if (0 == maxSize || maxSize < 0 || maxSize > 100)
		{
			throw new Exception("table容量非法!");
		}

		this.maxSize = maxSize;
		this.table = new Info[maxSize];
	}

	/**
	 * 增加一个键值对
	 * @param key
	 * @param value
	 */
	public void put(K key, V value)
	{ 
		//将hashCode映射到数组下标
		int hashCode =  Math.abs(key.hashCode())% this.maxSize;
		
		//将元素插入到数组中，如果该位置已经被占用，则循环查找下一个位置，直到找到合适的位置，或发现数组已满，退出循环
		while (this.table[hashCode] != null
				&& (this.currentAmmount < this.maxSize))
		{
			hashCode++;
			hashCode = hashCode % this.maxSize;
		}

		if (this.currentAmmount == this.maxSize)
		{
			//数组已满
			System.out.println("Hash table 已满");
		} else
		{
			//找到合适位置
			this.table[hashCode] = new KeyValue(key, value); 
			this.currentAmmount++;
		}
	}

	/**
	 * 与add方法同样的算法，根据key值找到数组中元素，然后将改元素设置为null
	 * @param key
	 * @return
	 */
	public boolean remove(K key)
	{
		int hashCode = Math.abs(key.hashCode()) % this.maxSize;
		int count = 0;
		while (this.table[hashCode] != null && count < this.maxSize)
		{
			if (((KeyValue) this.table[hashCode]).getKey().equals(key))
			{
				this.table[hashCode] = null;
				return true;
			}
			count++;
			hashCode++;
			hashCode = hashCode%this.maxSize;
		}

		return false;
	}

	public V get(K key)
	{
		int hashCode = Math.abs(key.hashCode()) % this.maxSize;
		int count = 0;
		while (this.table[hashCode] != null && count < this.maxSize)
		{
			if (key.equals(((KeyValue)this.table[hashCode]).getKey()))
				return ((KeyValue) this.table[hashCode]).getValue();
			
			hashCode++;
			count++;
			hashCode = hashCode%this.maxSize;
		}
		return null;
	}

	public boolean contains(K key)
	{
		if (this.get(key) != null)
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	public void replace(K key, V value)
	{
		KeyValue kv = this.find(key);
		if(kv != null)
		{ 
			kv.setValue(value);
		}
	}
	
	private KeyValue find(K key)
	{
		int hashCode = Math.abs(key.hashCode()) % this.maxSize;
		int count = 0;
		while (this.table[hashCode] != null && count < this.maxSize)
		{
			if (key.equals(((KeyValue)this.table[hashCode]).getKey()))
				return ((KeyValue) this.table[hashCode]);
			
			hashCode++;
			count++;
			hashCode = hashCode%this.maxSize;
		}
		return null;
	}
}
