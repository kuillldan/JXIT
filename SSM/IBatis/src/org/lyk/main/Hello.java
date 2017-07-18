package org.lyk.main;

import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lyk.vo.News;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class Hello
{
	public static void main(String[] args) throws Exception
	{
		News news = new News();
		// news.setNid(7);
		news.setTitle("重庆出现高温天气");
		news.setContent("重庆将出现持续的大面高温天气");
		news.setPubdate(new Date());

		Reader reader = Resources.getResourceAsReader("ibatis.cfg.xml");

		SqlMapClient sqlClient = SqlMapClientBuilder.buildSqlMapClient(reader);
		
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("column", "title");
		params.put("keyWord", "%重庆%");
		params.put("start", 7);
		params.put("end", 5);
		
		System.out.println(sqlClient.queryForObject("NewsNS.getAllCount", params));
		
		System.out.println("//Main done");
	}
}
