package actions;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@ParentPackage(value="root")
@Action(value = "CityAction", results = { @Result(name = "listJSP", location = "/pages/city/list.jsp") })
public class CityAction extends ActionSupport
{
	public void list()
	{
		JSONArray allCities = new JSONArray();
		for(int i = 1; i <= 10; i++)
		{
			JSONObject eachCity = new JSONObject();
			eachCity.put("cityID", i);
			eachCity.put("cityName", "city-"+i);
			allCities.add(eachCity);
		}
		JSONObject retVal = new JSONObject();
		retVal.put("allCities", allCities);
		try
		{
			System.out.println(retVal.toString());
			ServletActionContext.getResponse().getWriter().print(retVal.toString());
		} catch (IOException e)
		{ 
			e.printStackTrace();
		} 
	}
}
