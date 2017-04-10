package org.lyk.interfaces;

public interface IList<T>
{
	
	/**
	 * �жϸ�List�Ƿ�Ϊ��
	 * @return ListΪ���򷵻�ture,���򷵻�false
	 */
	public boolean isEmpty();
	/**
	 * ���ص�ǰ������
	 * @return
	 */
	public Integer size();
	
	/**
	 * ��equals�����ж������Ƿ�����ض���Ԫ��
	 * @param data
	 * @return
	 */
	public boolean contains(T data);
	
	
	/**
	 * ����һ��Ԫ��
	 * @param data
	 */
	public void add(T data);
	/**
	 * �Ƴ�һ��Ԫ�أ�����ж��Ԫ�أ���ֻ���Ƴ���һ��
	 * @param data
	 */
	public void removeFirst(T data);
	/**
	 * �Ƴ�����dataԪ��
	 * @param data
	 */
	public void removeAll(T data);
	/**
	 * �Ƴ�λ��Ϊindex��Ԫ�أ�����±�Խ�磬�򲻻��Ƴ��κ�Ԫ��
	 * @param index
	 */
	public void removeFirt(Integer index);
	
	/**
	 * ��ȡָ��index��Ԫ��
	 * @param index
	 * @return
	 */
	public T get(Integer index);
	/**
	 * ��ָ��indexλ�õ�Ԫ���滻ΪnewData
	 * @param index
	 * @param newData
	 */
	public void replace(Integer index, T newData);
	/**
	 * ����һ��oldData�滻ΪnewData
	 * @param oldData
	 * @param newData
	 */
	public void replace(T oldData, T newData);
	
	
	
	/**
	 * ������������oldData�滻
	 * @param oldData
	 * @param newData
	 */
	public void replaceAll(T oldData, T newData);
	
	/**
	 * ������ת��Ϊ����
	 * @return
	 */
	public Object[] toArray();
	
}