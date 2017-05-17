package convertors;

import java.util.Locale;
import java.util.Map;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class LocaleConvertor extends DefaultTypeConverter
{
	@Override
	public Object convertValue(Map<String, Object> context, Object value, Class toType)
	{
		if(Locale.class.equals(toType))
		{
			String cityAndLanguage = ((String[])value)[0];
			Locale loc = new Locale(cityAndLanguage.split("_")[0], cityAndLanguage.split("_")[1]);
			return loc;
		}
		return null;
	}
}
