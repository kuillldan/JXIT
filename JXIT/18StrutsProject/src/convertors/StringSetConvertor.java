package convertors;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class StringSetConvertor extends DefaultTypeConverter
{
	@Override
	public Object convertValue(Map<String, Object> context, Object value, Class toType)
	{
		if(Set.class.equals(toType))
		{
			Set<String> nids = new HashSet<String>();
			String nidsInString  = ( (String[])value)[0];
			
			for(String nid : nidsInString.split("\\|"))
			{
				nids.add(nid);
			}
			return nids;
		}
		return null;
	}
}
