package org.lyk.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lyk.constant.CommonConstant;
import org.lyk.vo.Emp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages/image/*")
public class ImgAction extends AbstractAction
{
	@RequestMapping("getImage")
	public void show(HttpServletRequest request, HttpServletResponse response, String photo)
	{
		// 读取本地图片输入流
		try
		{
			HttpSession session = request.getSession();
			Emp emp = (Emp) session.getAttribute(CommonConstant.EMP);
			String parentFolder = super.getString(CommonConstant.EMP_PHOTO_SAVE_PATH);
			if (!parentFolder.endsWith("/"))
				parentFolder += "/";

			String photoFullPath = parentFolder + emp.getPhoto();

			String JPG = "image/jpeg;charset=GB2312";
			// 本地文件路径
			File file = new File(photoFullPath);
			// 获取输出流
			OutputStream outputStream = response.getOutputStream();
			FileInputStream fileInputStream = new FileInputStream(file);
			// 读数据
			byte[] data = new byte[fileInputStream.available()];
			fileInputStream.read(data);
			fileInputStream.close();
			// 回写
			response.setContentType(JPG);
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
