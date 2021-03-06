package servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
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
					Method method = this.getClass().getMethod(status);
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

	protected void saveFiles(String content) throws IOException, SmartUploadException
	{
		System.out.println("[debug] saveFiles()******");
		if (this.isUpload())
		{
			SmartFiles smartFiles = this.smartUpload.getFiles();
			for (int i = 0; i < smartFiles.getCount(); i++)
			{
				SmartFile smartFile = smartFiles.getFile(i);
				if (smartFile.getSize() > 0 && smartFile.getContentType().contains(content))
				{
					// 有文件上传
					String fileName = this.generateFileName(smartFile);
					String filePath = this.request.getServletContext().getRealPath("/photos/") + this.getUploadFolder()
							+ File.separator + fileName;
					File fileToBeSaved = new File(filePath);
					if (!fileToBeSaved.getParentFile().exists())
					{
						fileToBeSaved.getParentFile().mkdirs();
					}
					System.out.println("[debug] 准备保存文件:" + filePath);
					smartFile.saveAs(filePath);
				}
			}
		} else
		{
			System.out.println("[debug] 没有文件上传****");
		}
	}

	protected abstract String getUploadFolder();

	protected String generateFileName(SmartFile smartFile)
	{
		return UUID.randomUUID().toString() + "." + smartFile.getFileExt();
	}

	protected boolean isUpload()
	{
		try
		{
			System.out.println("[debug] 请求类型" + this.request.getContentType());
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
		System.out.println("[debug] 在AbstractServlet中的column值1:" + column);
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
			if (!StringUtils.isEmpty(smartRequest.getParameter("column")))
			{
				column = smartRequest.getParameter("column");
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
			if (!StringUtils.isEmpty(this.request.getParameter("column")))
			{
				column = this.request.getParameter("column");
			}
			if (!StringUtils.isEmpty(this.request.getParameter("keyWord")))
			{
				keyWord = this.request.getParameter("keyWord");
			}
		}

		this.request.setAttribute("currentPage", currentPage);
		this.request.setAttribute("lineSize", lineSize);
		this.request.setAttribute("columns", columns);
		this.request.setAttribute("column", column);
		this.request.setAttribute("keyWord", keyWord);

		System.out.println("[debug] 在AbstractServlet中的column值2:" + column);
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

	protected abstract String getColumns();

	protected abstract String getColumn();
}
