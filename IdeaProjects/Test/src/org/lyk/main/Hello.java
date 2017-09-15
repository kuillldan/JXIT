package org.lyk.main;

import org.lyk.factory.ServiceFactory;
import org.lyk.service.IDeptService;
import org.lyk.vo.Dept;

/**
 * Created by liuyuank on 9/15/2017.
 */
public class Hello
{
    public static void main(String[] args) throws Exception
    {
        IDeptService deptService = ServiceFactory.getDeptServiceInstance();
        Dept dept = new Dept();
        dept.setDeptno(12);
        dept.setDname("SSIT");
        dept.setLoc("CHONGQING");
        deptService.insert(dept);
    }
}
