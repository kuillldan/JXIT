package servlet.back;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import vo.Member;

/**
 * Servlet implementation class MemberServletBack
 */
@WebServlet("/pages/back/admin/member/MemberServletBack/*")
public class MemberServletBack extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public MemberServletBack()
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
		String status = General.getStatus(request);
		String path = CONST.pageError;

		if ("list".equals(status))
		{
			path = this.list(request);
		} else if ("listByStatus".equals(status))
		{
			path = this.listByStatus(request);
		} else if ("updateMembersStatus".equals(status))
		{
			path = this.updateMembersStatus(request);
		} else if ("show".equals(status))
		{
			path = this.show(request);
		} else if ("updateStatus".equals(status))
		{
			path = this.updateStatus(request);
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

	protected String updateStatus(HttpServletRequest request)
	{
		String referer = request.getHeader("referer");
		String msg = null;
		String url = CONST.memberServletBackURL + referer.substring(referer.lastIndexOf("/") + 1);
		String status = request.getParameter("status");
		String mid = request.getParameter("mid");
		if (StringUtils.isEmpty(mid))
		{
			msg = "请提供要操作的ID";
		} else if (StringUtils.isEmpty(status))
		{
			msg = "请提供状态";
		}

		if (!StringUtils.isEmpty(msg))
		{
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return CONST.pageForward;
		}

		try
		{
			Set<String> ids = new HashSet<String>();
			ids.add(mid);

			switch (status)
			{
				case "locked":
				{
					msg = "该会员状态已锁定";
					ServiceBackFactory.getIMemberServiceBackInstance().disableMembers(ids);
					break;
				}
				case "actived":
				{
					msg = "该会员状态已激活";
					ServiceBackFactory.getIMemberServiceBackInstance().activeMembers(ids);
					break;
				}
				default :
				{
					msg = "暂不能更新为你设定的状态";
					break;
				}
			}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return CONST.pageForward; 
		} catch (Exception e)
		{
			e.printStackTrace();
			return CONST.pageError;
		}

	}

	protected String show(HttpServletRequest request)
	{
		String referer = request.getHeader("referer");
		String msg = null;
		String url = CONST.memberServletBackURL + referer.substring(referer.lastIndexOf("/") + 1);

		String mid = request.getParameter("mid");
		if (StringUtils.isEmpty(mid))
		{
			msg = "请提供用户登录ID";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return CONST.pageMemberShow;
		} else
		{
			try
			{
				Member member = ServiceBackFactory.getIMemberServiceBackInstance().show(mid);
				request.setAttribute("member", member);
				return CONST.pageMemberShow;
			} catch (Exception e)
			{
				e.printStackTrace();
				return CONST.pageError;
			}
		}
	}

	protected String updateMembersStatus(HttpServletRequest request)
	{
		String type = request.getParameter("type");
		String msg = null;
		String url = null;
		String _ids = request.getParameter("ids");
		String referer = request.getHeader("referer");
		url = CONST.memberServletBackURL + referer.substring(referer.lastIndexOf("/") + 1);

		try
		{
			if (StringUtils.isEmpty(_ids))
			{
				msg = "你还未选择任何要操作的数据。";
			} else
			{
				Set<String> ids = new HashSet<String>();
				String[] result = _ids.split("\\|");
				for (String mid : result)
				{
					ids.add(mid);
				}
				if ("active".equals(type))
				{
					if (ServiceBackFactory.getIMemberServiceBackInstance().activeMembers(ids))
					{
						msg = "用户状态更新成功";
					} else
					{
						msg = "用户状态更新失败";
					}
				}
				if ("disable".equals(type))
				{
					if (ServiceBackFactory.getIMemberServiceBackInstance().disableMembers(ids))
					{
						msg = "用户状态更新成功";
					} else
					{
						msg = "用户状态更新失败";
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return CONST.pageForward;
	}

	protected String listByStatus(HttpServletRequest request)
	{
		try
		{
			Integer currentPage = 1;
			Integer lineSize = 5;
			String columns = "用户名:mid|真实姓名:name|联系电话:phone|地址:address";
			String column = "mid";
			String keyWord = "";
			Integer parameterValue = Integer.parseInt(request.getParameter("status"));

			try
			{
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			} catch (Exception e)
			{
			}
			try
			{
				lineSize = Integer.parseInt(request.getParameter("lineSize"));
			} catch (Exception e)
			{
			}
			if (!StringUtils.isEmpty(request.getParameter("column")))
			{
				column = request.getParameter("column");
			}
			if (!StringUtils.isEmpty(request.getParameter("keyWord")))
			{
				keyWord = request.getParameter("keyWord");
			}

			Map<String, Object> map = ServiceBackFactory.getIMemberServiceBackInstance().listByStatus(
					currentPage, lineSize, column, keyWord, parameterValue);
			List<Member> allMembers = (List<Member>) map.get("allMembers");
			Integer allCount = (Integer) map.get("allCount");
			request.setAttribute("allMembers", allMembers);
			request.setAttribute("allCount", allCount);

			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lineSize", lineSize);
			request.setAttribute("columns", columns);
			request.setAttribute("column", column);
			request.setAttribute("keyWord", keyWord); 
			request.setAttribute("parameterKey", "status");
			request.setAttribute("parameterValue", parameterValue);
		} catch (Exception e)
		{
			e.printStackTrace();
			return CONST.pageError;
		}

		return CONST.pageMemberListByStatus;
	}

	protected String list(HttpServletRequest request)
	{
		try
		{
			Integer currentPage = 1;
			Integer lineSize = 5;
			String columns = "用户名:mid|真实姓名:name|联系电话:phone|地址:address";
			String column = "mid";
			String keyWord = "";

			try
			{
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			} catch (Exception e)
			{
			}
			try
			{
				lineSize = Integer.parseInt(request.getParameter("lineSize"));
			} catch (Exception e)
			{
			}
			if (!StringUtils.isEmpty(request.getParameter("column")))
			{
				column = request.getParameter("column");
			}
			if (!StringUtils.isEmpty(request.getParameter("keyWord")))
			{
				keyWord = request.getParameter("keyWord");
			}

			Map<String, Object> map = ServiceBackFactory.getIMemberServiceBackInstance().list(currentPage,
					lineSize, column, keyWord);
			List<Member> allMembers = (List<Member>) map.get("allMembers");
			Integer allCount = (Integer) map.get("allCount");

			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lineSize", lineSize);
			request.setAttribute("columns", columns);
			request.setAttribute("column", column);
			request.setAttribute("keyWord", keyWord);
			request.setAttribute("allMembers", allMembers);
			request.setAttribute("allCount", allCount);
		} catch (Exception e)
		{
			e.printStackTrace();
			return CONST.pageError;
		}

		return CONST.pageMemberList;
	}
}
