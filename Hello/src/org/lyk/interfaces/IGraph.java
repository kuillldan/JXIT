package org.lyk.interfaces;

public interface IGraph
{
	
	public void add(char data) throws Exception;
	public void setRelation(int x,int y) throws Exception;
	public void showBreathFirst(int x) throws Exception;
	public void showDeepFirst(int x) throws Exception;
	public void toMSTBreathFirst(int x ) throws Exception;
	public void toMSTDeepFirst(int x) throws Exception;
}
