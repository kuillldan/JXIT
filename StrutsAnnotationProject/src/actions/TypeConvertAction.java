package actions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

@SuppressWarnings("serial")
@ParentPackage("root")
@Action("TypeConvertAction")
@InterceptorRefs({@InterceptorRef("generalInterceptor")})
@Results({@Result(name="input",location="/pages/typeConvertDemo/input.jsp")})
public class TypeConvertAction extends ActionSupport
{
	Set<Integer> ids  ;
	public void setIds(Set<Integer> ids)
	{
		this.ids = ids;
	}
	public Set<Integer> getIds()
	{
		return ids;
	}
	
	public void convert()
	{
		System.out.println("=================================================");
		System.out.println(this.ids);
		System.out.println("=================================================");
	}
}