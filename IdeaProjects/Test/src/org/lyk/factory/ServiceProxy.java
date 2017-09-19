package org.lyk.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by liuyuank on 9/15/2017.
 */
public class ServiceProxy implements InvocationHandler
{
    private Object serviceImpl;

    public ServiceProxy(Object serviceImpl)
    {
        this.serviceImpl = serviceImpl;
    }


    private void openConnection()
    {
        System.out.println("**代理，打开服务连接**");
    }

    private void closeConnection()
    {
        System.out.println("**代理，关闭服务连接****代理");
    }

    private void setAutoCommit(boolean autoCommit)
    {
        System.out.println("**代理，设置自动提交:" + autoCommit + "**");
    }

    private void commit()
    {
        System.out.println("**代理,提交到数据库**");
    }

    private void rollBack()
    {
        System.out.println("**代理,事物回滚**");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        this.openConnection();
        if (method.getName().contains("insert"))
        {
            try
            {

                this.setAutoCommit(false);
                Object retVal = method.invoke(this.serviceImpl, args);
                this.commit();
                this.setAutoCommit(true);
                return retVal;
            } catch (Exception e)
            {
                this.rollBack();
                throw e;
            } finally
            {
                this.closeConnection();
            }
        } else
        {
            System.out.println("******" + (this.serviceImpl == null));
            System.out.println("******" + (args == null));
            System.out.println("******" + (method == null));

            Object retVal = method.invoke(this.serviceImpl, args);
            this.closeConnection();
            return retVal;
        }
    }
}
