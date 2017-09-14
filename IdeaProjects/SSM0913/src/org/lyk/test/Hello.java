package org.lyk.test;

import org.lyk.service.IDeptService;
import org.lyk.vo.Dept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liuyuank on 9/13/2017.
 */
public class Hello
{
    public static void main(String[] args)
    {
        Logger logger = LoggerFactory.getLogger("logfile");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        IDeptService deptService = ctx.getBean("deptServiceImpl", IDeptService.class);
        Dept dept = new Dept();
        dept.setDname("SSIT");
        try
        {
            deptService.insert(dept);
        } catch (Exception e)
        {
            logger.error(e.getMessage(),e);
        }
        System.out.println("//main done");
    }
}
