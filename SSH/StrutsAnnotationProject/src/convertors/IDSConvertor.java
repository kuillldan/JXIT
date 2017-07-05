package convertors;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class IDSConvertor extends DefaultTypeConverter
{
	@Override
	public Object convertValue(Map<String, Object> context, Object value, Class toType)
	{
		if(Set.class.equals(toType))
		{
			String ids = ((String[])value)[0];
			String[] idsInStringArray = ids.split("\\|");
			Set<Integer> idsInSet = new HashSet<Integer>();
			for(String id : idsInStringArray)
			{
				idsInSet.add(Integer.parseInt(id));
			}
			return idsInSet;
		}
		
		return  null;
	}
}