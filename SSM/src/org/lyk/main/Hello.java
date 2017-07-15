package org.lyk.main;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Date;

import org.lyk.vo.News;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class Hello
{
	public static void main(String[] args) throws Exception
	{
		News news = new News();
		news.setTitle("重庆出现高位天气");
		news.setContent("重庆将出现持续的大面积高温天气");
		news.setPubdate(new Date());

		Reader reader = Resources.getResourceAsReader("ibatis.cfg.xml");

		SqlMapClient sqlClient = SqlMapClientBuilder.buildSqlMapClient(reader);
		sqlClient.insert("NewsNS.doCreate", news);
		System.out.println("//Main done");
	}
}
