package org.lyk.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.lyk.vo.Member;

public class TestInsertMember
{
	public static void main(String[] args) throws Exception
	{
		InputStream is = Resources.getResourceAsStream("mybatis.cfg.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Member member = new Member();
		member.setMid("2000");
		member.setName("sheldon");
		member.setBirthday(new Date(1987, 10, 18));
		member.setAge(30);
		member.setNote("a good boy");
		member.setSalary(9999.0);
		Integer rowsUpdated = sqlSession.insert("org.lyk.vo.mapping.MemberNS.doCreate", member);
		System.out.println("更新行数:" + rowsUpdated);
		sqlSession.commit();
		sqlSession.close();
	}
}
