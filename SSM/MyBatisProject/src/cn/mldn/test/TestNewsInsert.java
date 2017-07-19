package cn.mldn.test;

import java.io.Reader;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.mldn.vo.News;

public class TestNewsInsert {
	public static void main(String[] args) throws Exception {
		// 1、读取mybatis的核心配置文件 —— mybatis.cfg.xml文件
		Reader reader = Resources.getResourceAsReader("mybatis.cfg.xml");
		// 2、创建SqlSessionFactory工厂类
		SqlSessionFactory factory = new SqlSessionFactoryBuilder()
				.build(reader);
		// 3、通过SqlSessionFactory工厂类取得SqlSession对象
		SqlSession session = factory.openSession() ;// 取得Session接口对象
		// 4、实现数据的保存
		News vo = new News() ;
		vo.setTitle("欢迎访问www.mldn.cn");
		vo.setContent("世界和平，大家快乐！");
		vo.setPubdate(new Date());
		// 保存的时候要设置使用的SQL语句
		int len = session.insert("cn.mldn.vo.mapping.NewsNS.doCreate",vo) ;
		System.out.println("操作影响的数据行数：" + len);
		System.out.println("当前的id：" + vo.getNid());
		session.close();	// 关闭Session
		reader.close();
	}
}
