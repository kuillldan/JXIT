package org.lyk.factory;

import org.lyk.service.IDeptService;
import org.lyk.service.impl.DeptServiceImpl;

import java.lang.reflect.Proxy;

/**
 * Created by liuyuank on 9/15/2017.
 */
public class ServiceFactory
{
    public static IDeptService getDeptServiceInstance()
    {
        IDeptService deptServiceImpl = new DeptServiceImpl();
        ServiceProxy h = new ServiceProxy(deptServiceImpl);
        return (IDeptService)Proxy.newProxyInstance(deptServiceImpl.getClass().getClassLoader(),deptServiceImpl.getClass().getInterfaces(),h);
    }
}
