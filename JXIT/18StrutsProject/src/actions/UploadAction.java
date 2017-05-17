package actions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import utils.UploadFileHelper;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Container;

import exceptions.NotSupportedFileException;

@SuppressWarnings("serial")
public class UploadAction extends ActionSupport
{
	String info;
	File clientFile ;
	String clientFileName;
	String clientFileContentType;
	
	public void setClientFile(File clientFile)
	{
		this.clientFile = clientFile;
	}
	public void setInfo(String info)
	{
		this.info = info;
	}
	public void setClientFileName(String clientFileName)
	{
		this.clientFileName = clientFileName;
	}
	public void setClientFileContentType(String clientFileContentType)
	{
		this.clientFileContentType = clientFileContentType;
	}
	
	public String upload()
	{ 
		System.out.println("=================================");
		Map<String, List<String>> errors = super.getFieldErrors();
		Set<Map.Entry<String, List<String>>> entrys =  errors.entrySet();
		for(Map.Entry<String, List<String>> entry : entrys )
		{
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		System.out.println("=================================");
		return "good";
	}
}
