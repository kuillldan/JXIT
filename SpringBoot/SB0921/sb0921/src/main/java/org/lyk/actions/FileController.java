package org.lyk.actions;

import static org.hamcrest.CoreMatchers.nullValue;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileController
{
	@RequestMapping("/uploadPre")
	public String uploadPre()
	{

		return "file_upload";
	}

	@RequestMapping("/upload")
	@ResponseBody
	public String upload(HttpServletRequest request, String desc)
	{
		System.out.println("描述信息:" + desc);
		if(request instanceof MultipartHttpServletRequest)
		{
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
			MultipartFile photo1 = mRequest.getFile("photo1");
			MultipartFile photo2 = mRequest.getFile("photo2");
			MultipartFile photo3 = mRequest.getFile("photo3");
			saveFile(photo1);
			saveFile(photo2);
			saveFile(photo3);
			return "文件上传成功";
		}
		else
		{
			return "未检测到文件上传";
		}
	}
	
	private boolean saveFile(MultipartFile file)
	{
		if (file != null && file.getSize() > 0)
		{
			System.out.println("文件名称:" + file.getOriginalFilename());
			System.out.println("文件大小:" + file.getSize());
			System.out.println("文件类型:" + file.getContentType());
			return true;
		}
		else
		{
			return false;
		}
	}
}
