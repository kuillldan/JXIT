package servlet.back;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factories.ServiceBackFactory;
import utils.CONST;
import utils.General;
import utils.StringUtils;
import vo.Item;

/**
 * Servlet implementation class ItemServletBack
 */
@WebServlet("/pages/back/admin/item/ItemServletBack/*")
public class ItemServletBack extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ItemServletBack()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		String path = CONST.pageError;
		String status = General.getStatus(request);
		if ("insert".equals(status))
		{
			path = this.insert(request);
		} else if ("update".equals(status))
		{
			path = this.update(request);
		} else if ("delete".equals(status))
		{
			path = this.delete(request);
		}
		else if("list".equals(status))
		{
			path = this.list(request);
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

	protected String insert(HttpServletRequest request)
	{
		String msg = null;
		String url = null;

		String title = request.getParameter("title");

		if (StringUtils.isEmpty(title))
		{
			msg = "请提供商品分类名称";
			url = CONST.pageItemInsertJSP;

			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return CONST.pageForward;
		}

		try
		{
			Item vo = new Item();
			vo.setTitle(title);
			if (ServiceBackFactory.getIItemServiceBackInstance().insert(vo))
			{
				msg = "商品分类增加成功";
			} else
			{
				msg = "商品分类增加失败";
			}
			url = CONST.pageItemInsertJSP;
			return General.setMsgAndUrlInRequest(request, msg, url);
		} catch (Exception e)
		{
			e.printStackTrace();
			return CONST.pageError;
		}
	}

	protected String update(HttpServletRequest request)
	{
		String msg = null;
		String url = null;

		String iid = request.getParameter("iid");
		String title = request.getParameter("title");
		if (StringUtils.isEmpty(iid) || StringUtils.isEmpty(title))
		{
			msg = "请提供要更新的商品id和名称";
			url = CONST.pageItemListURL;

			return General.setMsgAndUrlInRequest(request, msg, url);
		}

		try
		{
			Item vo = new Item();
			vo.setIid(Integer.parseInt(iid));
			vo.setTitle(title);
			if (ServiceBackFactory.getIItemServiceBackInstance().update(vo))
			{
				msg = "商品分类更新成功";
			} else
			{
				msg = "商品分类更新失败";
			}
			url = CONST.pageItemListURL;

			return General.setMsgAndUrlInRequest(request, msg, url);
		} catch (Exception e)
		{
			return General.setSystemError(e);
		}
	}

	protected String delete(HttpServletRequest request)
	{
		String msg = null;
		String url = null;

		String[] _ids = request.getParameter("ids").split("\\|");
		Set<Integer> ids = new HashSet<Integer>();
		for (String iid : _ids)
		{
			ids.add(Integer.parseInt(iid));
		}

		try
		{
			if (ServiceBackFactory.getIItemServiceBackInstance().delete(ids))
			{
				msg = "商品分类删除成功";
			} else
			{
				msg = "商品分类删除失败";
			}
			url = CONST.pageItemListURL;
			return General.setMsgAndUrlInRequest(request, msg, url);
		} catch (Exception e)
		{
			return General.setSystemError(e);
		}
	}

	protected String list(HttpServletRequest request)
	{
		try
		{
			List<Item> allItems = ServiceBackFactory.getIItemServiceBackInstance().list();
			request.setAttribute("allItems", allItems);
			return CONST.itemListURL;
		} catch (Exception e)
		{
			return General.setSystemError(e);
		}
	}
}
