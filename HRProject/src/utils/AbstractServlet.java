package utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.BeanOperator;
import utils.CONST;
import utils.General;
import utils.StringUtils;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartFiles;
import com.jspsmart.upload.SmartRequest;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

@SuppressWarnings("serial")
public abstract class AbstractServlet extends HttpServlet
{
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected SmartUpload smartUpload;

	protected Integer currentPage;
	protected Integer lineSize;
	protected String columns;
	protected String column;
	protected String keyWord;

	public AbstractServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		this.request = request;
		this.response = response;

		String path = CONST.errorPage;
		String status = General.getRequestStatus(request);

		try
		{
			BeanOperator bo = new BeanOperator(this);
			Map<String, String> errors = new HashMap<String, String>();
			boolean isEncrypted = false;

			if (this.request.getContentType() != null && this.request.getContentType().contains("multipart/form-data"))
				isEncrypted = true;
			if (isEncrypted)
			{
				// 请求已封装
				this.smartUpload = new SmartUpload();
				this.smartUpload.initialize(super.getServletConfig(), this.request, this.response);
				this.smartUpload.upload();
				SmartRequest smartRequest = smartUpload.getRequest();
				 
				bo.validateParameters(errors, status, request, smartRequest, true);
				if (errors.size() <= 0)
				{
					// 验证通过 - 需要绑定数据
					bo.setValueAutomatic(request, smartRequest, true);
					Method method = this.getClass().getMethod(status);
					path = (String) method.invoke(this);
				} else
				{
					// 验证不通过
					request.setAttribute("errors", errors.values());
					path = CONST.errorPage;
				}
			} else
			{
				// 请求未封装
				bo.validateParameters(errors, status, request, null, false);
				if (errors.size() <= 0)
				{
					// 验证通过 - 需要绑定数据
					bo.setValueAutomatic(request, null, false); 
					Method method = this.getClass().getDeclaredMethod(status);
					method.setAccessible(true);
					path = (String) method.invoke(this);
				} else
				{
					// 验证不通过
					request.setAttribute("errors", errors.values());
					path = CONST.errorPage;
				}
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			path = CONST.errorPage;
		}
		 
		this.request.getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * 保存上传文件，返回一文件名
	 * @param content
	 * @return
	 * @throws IOException
	 * @throws SmartUploadException
	 */
	protected List<String> saveFiles(String content) throws IOException, SmartUploadException
	{ 
		if (this.isUpload())
		{
			List<String> allFileNames = new ArrayList<String>();
			SmartFiles smartFiles = this.smartUpload.getFiles();
			for (int i = 0; i < smartFiles.getCount(); i++)
			{
				SmartFile smartFile = smartFiles.getFile(i);
				if (smartFile.getSize() > 0 && smartFile.getContentType().contains(content))
				{
					// 有文件上传
					String fileName = this.generateFileName(smartFile);
					allFileNames.add(fileName);
					String filePath = this.request.getServletContext().getRealPath("/photos/") + this.getUploadFolder()
							+ File.separator + fileName;
					File fileToBeSaved = new File(filePath);
					if (!fileToBeSaved.getParentFile().exists())
					{
						fileToBeSaved.getParentFile().mkdirs();
					} 
					smartFile.saveAs(filePath);
				}
			}
			return allFileNames;
		} else
		{ 
			return null;
		}
	}

	protected abstract String getUploadFolder();

	protected String generateFileName(SmartFile smartFile)
	{
		return UUID.randomUUID().toString() + "." + smartFile.getFileExt();
	}

	protected String updatePhoto() throws Exception
	{
		SmartRequest smartRequest = this.smartUpload.getRequest();
		String oldPhoto = smartRequest.getParameter("oldPhoto");
		if(StringUtils.isEmpty(oldPhoto))
			return null;
		
		
		if(this.isEncryped() && this.isUpload())
		{ 
			//保存新上传的文件
			List<String> allPhotoNames = this.saveFiles("image");
			
			if(null == allPhotoNames)
			{
				return oldPhoto;
			}
			else
			{
				if(!CONST.noPhoto.equals(oldPhoto))
				{
					//删除旧文件
					String containingFolder = this.request.getServletContext().getRealPath("/photos/" + this.getUploadFolder() + "/");
					File oldPhotoFile = new File(containingFolder + oldPhoto);
					if(oldPhotoFile.exists())
					{
						oldPhotoFile.delete();
					}
				}
				return allPhotoNames.get(0);
			}
		}
		else
		{
			return oldPhoto;
		}
	}
		
	protected boolean isUpload()
	{
		try
		{ 
			if (this.request.getContentType() != null && this.request.getContentType().contains("multipart/form-data")
					&& this.smartUpload.getFiles().getSize() > 0)
			{
				return true;
			} else
			{
				return false;
			}
		} catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	protected void handleSplit()
	{
		currentPage = 1;
		lineSize = 5;
		columns = this.getColumns();
		column = this.getColumn(); 
		keyWord = "";

		if (this.isEncryped())
		{// 请求已经封装
			
			SmartRequest smartRequest = this.smartUpload.getRequest(); 
			try
			{
				currentPage = Integer.parseInt(smartRequest.getParameter("currentPage"));
			} catch (Exception e)
			{
			}
			try
			{
				lineSize = Integer.parseInt(smartRequest.getParameter("lineSize"));
			} catch (Exception e)
			{
			}
			if (!StringUtils.isEmpty(smartRequest.getParameter("columns")))
			{
				columns = smartRequest.getParameter("columns");
			}
			if (!StringUtils.isEmpty(smartRequest.getParameter("columnName")))
			{
				column = smartRequest.getParameter("columnName");
			}
			if (!StringUtils.isEmpty(smartRequest.getParameter("keyWord")))
			{
				keyWord = smartRequest.getParameter("keyWord");
			}
		} else
		{ 
			try
			{
				currentPage = Integer.parseInt(this.request.getParameter("currentPage"));
			} catch (Exception e)
			{
			}
			try
			{
				lineSize = Integer.parseInt(this.request.getParameter("lineSize"));
			} catch (Exception e)
			{
			}
			if (!StringUtils.isEmpty(this.request.getParameter("columns")))
			{
				columns = this.request.getParameter("columns");
			}
			if (!StringUtils.isEmpty(this.request.getParameter("columnName")))
			{
				column = this.request.getParameter("columnName");
			}
			if (!StringUtils.isEmpty(this.request.getParameter("keyWord")))
			{
				keyWord = this.request.getParameter("keyWord");
			}
		}

		this.request.setAttribute("currentPage", currentPage);
		this.request.setAttribute("lineSize", lineSize);
		this.request.setAttribute("columns", columns);
		this.request.setAttribute("columnName", column);
		this.request.setAttribute("keyWord", keyWord);
 
	}

	private boolean isEncryped()
	{
		if (this.request.getContentType() != null && this.request.getContentType().contains("multipart/form-data"))
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	protected boolean checkCode()
	{
		String code = this.request.getParameter("code");
		String rand=(String)this.request.getSession().getAttribute("rand");
		
		if(StringUtils.isTheSame(code, rand))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	protected String setMsgAndUrlInRequest(String msg , String url)
	{
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return CONST.forwardPage;
	}
	
	protected String setSystemError(Exception e)
	{
		e.printStackTrace();
		return CONST.errorPage;
	}
	
	protected String updateSuccessfull(String url)
	{
		return this.setMsgAndUrlInRequest(this.getTitle() + "信息更新成功", url);
	}
	
	protected String updateFailed(String url)
	{
		return this.setMsgAndUrlInRequest(this.getTitle() + "信息更新失败", url);
	}
	
	protected String insertSuccessfull(String url)
	{
		return this.setMsgAndUrlInRequest(this.getTitle() + "信息增加成功", url);
		
	}
	
	protected String insertFailed(String url)
	{
		return this.setMsgAndUrlInRequest(this.getTitle() + "信息增加失败", url);
		
	}
	
	protected String deleteSuccessfull(String url)
	{
		return this.setMsgAndUrlInRequest(this.getTitle() + "信息删除成功", url);
	}
	
	protected String deleteFailed(String url)
	{
		return this.setMsgAndUrlInRequest(this.getTitle() + "信息删除失败", url);
	}
	
	protected abstract String getTitle();

	protected abstract String getColumns();

	protected abstract String getColumn();
}
