package factory;

import service.*;
import service.impl.TBL_01ServiceImpl;
import service.impl.TBL_03ServiceImpl;
import service.impl.TBL_04ServiceImpl;

public class ServiceFactory
{
	public static ITBL_01Service getIt01ServiceInstance()
	{
		return new TBL_01ServiceImpl();
	}
	
	public static ITBL_03Service getIt03ServiceInstance()
	{
		return new TBL_03ServiceImpl();
	}
	
	public static ITBL_04Service getItbl_04ServiceInstance()
	{
		return new TBL_04ServiceImpl();
	}
}
