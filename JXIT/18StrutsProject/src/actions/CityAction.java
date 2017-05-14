package actions;

 
import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CityAction extends ActionSupport
{
	public void list()
	{ 
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		JSONObject all = new JSONObject();
		JSONArray array = new JSONArray();
		
		
		for(int i = 1; i <= 10; i++)
		{
			JSONObject obj = new JSONObject();
			obj.put("cid", i);
			obj.put("title", "城市-"+i);
			array.add(obj);
		}
		all.put("cityList", array);
		
		try
		{
			ServletActionContext.getResponse().getWriter().print(all);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
