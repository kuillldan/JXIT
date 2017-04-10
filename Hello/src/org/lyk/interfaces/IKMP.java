package org.lyk.interfaces;

public interface IKMP<T>
{
	public boolean comtains(T[] source, T[] target) throws Exception;
	public boolean strContain_BF(T[] source, T[] target);
}
