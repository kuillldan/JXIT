package cn.mldn.test;

import java.io.Reader;
import java.util.Date;

import cn.mldn.vo.News;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class TestNewsUpdate {

	public static void main(String[] args) throws Exception {
		// 1、要读取整个项目的IBatis资源文件
		Reader reader = Resources.getResourceAsReader("ibatis.cfg.xml") ;
		// 2、要求加载所需要的文件内容，与SessionFactory的功能类似，返回的是一个操作对象
		// 这个返回的操作对象与Hibernate中的Session相同。
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader) ;
		// 3、实现数据的增加操作
		News vo = new News() ;
		vo.setNid(1);
		vo.setTitle("人生不努力，就如吃饭不吃饱");
		vo.setPubdate(new Date());
		vo.setContent("你的人生可能就失败了");
		System.out.println(sqlMap.update("NewsNS.doUpdate", vo));
		reader.close(); 
	}

}
