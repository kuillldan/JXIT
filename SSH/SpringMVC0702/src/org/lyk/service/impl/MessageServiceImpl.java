package org.lyk.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.lyk.service.IMessageService;
import org.lyk.vo.Message;
import org.lyk.vo.Type;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements IMessageService
{

	@Override
	public boolean insert(Message msg) throws Exception
	{
		System.out.println("*** 【增加新消息】" + msg);
		return true;
	}

	@Override
	public boolean update(Message msg) throws Exception
	{
		System.out.println("*** 【修改消息】" + msg);
		return true;
	}

	@Override
	public boolean delete(Set<Integer> ids) throws Exception
	{
		System.out.println("*** 【增删除加新消息】" + ids);
		return true;
	}

	@Override
	public Message get(Integer id) throws Exception
	{
		System.out.println("*** 【根据ID查询消息】" + id);
		Message msg = new Message();
		msg.setNid(1);
		msg.setPrice(99.6);
		msg.setPubdate(new Date());
		msg.setTitle("重庆将出现高温天气");
		Type type = new Type();
		type.setName("社会新闻");
		msg.setType(type);
		return msg;
	}

	@Override
	public Map<String, Object> list(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>();
		List<Message> allMessages = new ArrayList<Message>();
		Integer allMessagesCount = 58;
		
		for(int i = (currentPage - 1) * lineSize; i < (currentPage * lineSize); i++)
		{
			Random random = new Random();
			Message msg = new Message();
			msg.setNid(i);
			msg.setPrice(random.nextDouble() * 100);
			msg.setPubdate(new Date());
			msg.setTitle("重庆将出现高温天气" + i);
			Type type = new Type();
			type.setName("社会新闻" + i);
			msg.setType(type);
			allMessages.add(msg);
		}
		map.put("allMessages", allMessages);
		map.put("allMessagesCount", allMessagesCount);
		return map;
	}

}
