package convertors;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class IntegerSetConvertor extends DefaultTypeConverter
{
	@Override
	public Object convertValue(Map<String, Object> context, Object value, Class toType)
	{
		if(Set.class.equals(toType))
		{
			Set<Integer> ids = new HashSet<Integer>();
			String idsInString = ((String[])value)[0];
			//2|3|4|2
			String[] idsInStringArray = idsInString.split("\\|");
			for(String id : idsInStringArray)
			{
				try
				{
					ids.add(Integer.parseInt(id));
				}catch(NumberFormatException e)
				{}
			}
			return ids;
		}
		return null;
	}
}
