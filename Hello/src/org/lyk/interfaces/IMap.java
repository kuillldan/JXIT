package org.lyk.interfaces;

public interface IMap<K,V>
{
	/**
	 * 根据key值增加一个value，如果key重复，则新元素替换旧元素
	 * @param key
	 * @param value
	 */
	public void put(K key, V value);
	/**
	 * 根据key值移除value
	 * @param key
	 * @return
	 */
	public boolean remove(K key);
	public V get(K key);
	public boolean contains(K key);
	public void replace(K key, V value);
	
}
