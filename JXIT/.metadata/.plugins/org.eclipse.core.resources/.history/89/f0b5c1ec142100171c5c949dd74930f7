package org.lyk.vo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import utils.StringUtils;

 

public class BeanOperate
{
	private Object obj;
	private String properties ;
	private String value;
	
	public BeanOperate(Object obj, String properties, String value) throws Exception
	{
		super();
		this.obj = obj;
		this.properties = properties;
		this.value = value;
		this.handleProperties();
	}
	
	public void handleProperties() throws Exception
	{
		String[] allProperties = this.properties.split("\\.");
		for(int i = 0; i < allProperties.length-1; i++)
		{ 
			Method getter = this.obj.getClass().getMethod("get" + StringUtils.initCap(allProperties[i]));
			this.obj = getter.invoke(this.obj);
		}
		Field field = this.obj.getClass().getDeclaredField(allProperties[allProperties.length-1]);
		Method setter = this.obj.getClass().getMethod("set" + StringUtils.initCap(allProperties[allProperties.length-1]), field.getType());
		
		String fieldType = field.getType().getSimpleName();
		if(Const.DATATYPE.STRING.name().equalsIgnoreCase(fieldType))
		{
			setter.invoke(this.obj, this.value);
		}
		else if(Const.DATATYPE.INTEGER.name().equalsIgnoreCase(fieldType) || Const.DATATYPE.INT.name().equals(fieldType))
		{
			if(StringUtils.validateRegex(this.value, "\\d+"))
			{
				setter.invoke(this.obj, Integer.parseInt(this.value));
			}
		} else if(Const.DATATYPE.DOUBLE.name().equalsIgnoreCase(fieldType))
		{
			if(StringUtils.validateRegex(this.value, "\\d+(\\.\\d)?"))
			{
				setter.invoke(this.obj, Double.parseDouble(this.value));
			}
			
		}else if(Const.DATATYPE.DATE.name().equalsIgnoreCase(fieldType))
		{
			if(StringUtils.validateRegex(this.value, "\\d{4}-\\d{2}-\\d{4}"))
			{
				setter.invoke(this.obj, new SimpleDateFormat("yyyy-MM-dd").parse(this.value));
			}else if(StringUtils.validateRegex(this.value, "\\d{4}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2}"))
			{
				setter.invoke(this.obj, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.value));
			}else if(StringUtils.validateRegex(this.value, "\\d{4}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"))
			{
				setter.invoke(this.obj, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(this.value));
			}
		}
	}
	
	
}
