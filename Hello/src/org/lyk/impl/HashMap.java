package org.lyk.impl;

import java.util.ArrayList;
import org.lyk.interfaces.IMap;

public class HashMap<K,V> implements IMap<K, V>
{
	private class KeyValue
	{
		private K key;
		private V value;
		
		public KeyValue(K key, V value)
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
	private int maxSize = 10; 
	private Object[] table;
	
	
	
	public HashMap()
	{
		this.table = new Object[this.maxSize];
		for(int i = 0; i < this.maxSize; i++)
		{
			this.table[i] = new java.util.ArrayList<KeyValue>();
		}
	}
	
	@Override
	public void put(K key, V value)
	{
		int index = this.getIndex(key);
		KeyValue  kv = this.find(key);
		if(kv == null)
		{
			((java.util.List<KeyValue>)this.table[index]).add(new KeyValue(key, value));
		}
		else
		{
			kv.setValue(value);
		}
	}

	@Override
	public boolean remove(K key)
	{
		int index = this.getIndex(key);
		java.util.List<KeyValue> kvs = (java.util.List<KeyValue>)this.table[index];
		int listIndex = -1;
		for(KeyValue kv : kvs)
		{
			if(kv.key.equals(key))
			{
				listIndex = kvs.indexOf(kv);
			}
		}
		if(listIndex != -1)
		{
			kvs.remove(listIndex);
			return true;
		}
		return false;
	}

	@Override
	public V get(K key)
	{ 
		KeyValue kv= this.find(key);
		if(kv != null)
			return kv.getValue();
		else
			return null;
	}

	@Override
	public boolean contains(K key)
	{ 
		if(this.get(key) != null)
			return true;
		else
			return false;
	}

	@Override
	public void replace(K key, V value)
	{
		KeyValue kv = this.find(key);
		if(kv != null)
		{
			kv.setValue(value);
			
		}
	}
	
	
	
	private int getIndex(K key)
	{
		return Math.abs(key.hashCode())%this.maxSize;
	}
	
	private KeyValue find(K key)
	{
		int index = this.getIndex(key);
		java.util.List<KeyValue> kvs = (java.util.List<KeyValue>)this.table[index]; 
		for(KeyValue kv : kvs)
		{
			if(kv.key.equals(key))
			{
				return kv;
			}
		}
		
		return null;
	}
	
	
}
