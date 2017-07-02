package org.lyk.service;

import java.util.Map;
import java.util.Set;

import org.lyk.vo.Message;

public interface IMessageService
{
	public boolean insert(Message msg) throws Exception;
	public boolean update(Message msg) throws Exception;
	public boolean delete(Set<Integer> ids) throws Exception;
	public Message get(Integer id)  throws Exception;
	public Map<String,Object> list(String column,String keyWord,Integer currentPage,Integer lineSize) throws Exception;
}
