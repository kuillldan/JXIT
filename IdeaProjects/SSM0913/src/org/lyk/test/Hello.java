package org.lyk.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.lyk.vo.Dept;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liuyuank on 9/13/2017.
 */
public class Hello
{
    public static void main(String[] args) throws IOException
    {
        InputStream is = Resources.getResourceAsStream("mybatis.cfg.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        Dept dept = new Dept();
        dept.setDeptno(22);
        dept.setDname("SHIT");
        System.out.println(session.insert("org.lyk.vo.mapping.DeptNS.doCreate",dept));
        session.commit();
        session.close();
    }
}
