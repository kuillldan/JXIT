package org.lyk.service;

import java.util.List;

import org.lyk.vo.T01;

public interface IT01Service
{
	public List<T01> list() throws Exception;
	
	public boolean insert() throws Exception;
}
