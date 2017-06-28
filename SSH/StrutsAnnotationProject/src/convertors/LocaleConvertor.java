package convertors;

import java.util.Locale;
import java.util.Map;

import utils.StringUtils;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;


public class LocaleConvertor extends DefaultTypeConverter
{
	@Override
	public Object convertValue(Map<String, Object> context, Object value, Class toType)
	{
		if(Locale.class.equals(toType))
		{
			String values = ((String[])value)[0];
			if(StringUtils.isEmpty(values))
				return null;
			Locale loc = new Locale(values.split("_")[0], values.split("_")[1]);
			return loc;
		}
		return null;
	}
}
