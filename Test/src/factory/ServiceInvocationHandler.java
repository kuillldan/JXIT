package factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import dbc.DatabaseConnection;

public class ServiceInvocationHandler implements InvocationHandler
{
	private DatabaseConnection dbc = new DatabaseConnection();
	private Object realObject;
	
	public ServiceInvocationHandler(Object realObject)
	{
		super();
		this.realObject = realObject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		Object retVal = method.invoke(this.realObject, args);
		this.dbc.close();
		return retVal;
	}

}
