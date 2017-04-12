package servlet.back;

import java.awt.geom.FlatteningPathIterator;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartFiles;
import com.jspsmart.upload.SmartRequest;
import com.jspsmart.upload.SmartUpload;

import factories.DAOFactory;
import factories.ServiceBackFactory;
import utils.CONST;
import utils.General;
import utils.StringUtils;
import vo.Goods;
import vo.Item;

/**
 * Servlet implementation class GoodsServletBack
 */
@WebServlet("/pages/back/admin/goods/GoodsServletBack/*")
public class GoodsServletBack extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GoodsServletBack()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		String status = General.getStatus(request);
		String path = CONST.pageError;

		if ("insertPre".equals(status))
		{
			path = this.insertPre(request);
		} else if ("insert".equals(status))
		{
			path = this.insert(request, response);
		} else if ("list".equals(status))
		{
			path = this.list(request);
		} else if ("updateStatus".equals(status))
		{
			path = this.updateStatus(request);
		}else if("updatePre".equals(status))
		{
			path = this.updatePre(request);
		}
		else if("update".equals(status))
		{
			path = this.update(request,response);
		}
		else if("deleteAll".equals(status))
		{
			path = this.deleteAll(request);
		}

		request.getRequestDispatcher(path).forward(request, response);
	}
	
	public String deleteAll(HttpServletRequest request)
	{
		try
		{
			String msg = null;
			String url = null;
			String referer = request.getHeader("referer");
			
			String[] _ids = request.getParameter("ids").split("\\|");
			
			Set<Integer> ids = new HashSet<Integer>();
			List<String> allPhotos = new ArrayList<String>();
			for(String id : _ids)
			{
				ids.add(Integer.parseInt(id.split(":")[0]));
				if(!CONST.noPhoto.equals(id.split(":")[1]))
				{
					allPhotos.add(request.getServletContext().getRealPath("/photos/goods/")+id.split(":")[1]);
				}
			}
			if(ServiceBackFactory.getIGoodsServiceBackInstance().deleteAll(ids))
			{
				General.removePhotos(allPhotos);
				msg = "商品信息删除成功";
			}
			else
			{
				msg = "商品信息删除失败";
			}
			url = CONST.pageGoodsServletURL + referer.substring(referer.lastIndexOf("/") + 1);
			return General.setMsgAndUrlInRequest(request, msg, url);
		}
		catch(Exception e)
		{
			return General.setSystemError(e);
		}
	}

	public String updatePre(HttpServletRequest request)
	{
		try
		{
			Integer gid = Integer.parseInt(request.getParameter("gid"));
			Map<String, Object> map = ServiceBackFactory.getIGoodsServiceBackInstance().updatePre(gid);
			List<Item> allItems = (List<Item>)map.get("allItems");
			Goods goods = (Goods)map.get("goods");
			request.setAttribute("allItems", allItems);
			request.setAttribute("goods", goods);
			
			String referer = request.getHeader("referer");
			String backUrl = CONST.pageGoodsServletURL + referer.substring(referer.lastIndexOf("/") + 1);
			request.setAttribute("backUrl", backUrl);
			
			return CONST.pageGoodsUpdateJSP;
		} catch (Exception e)
		{
			return General.setSystemError(e);
		}
	}
	
	public String update(HttpServletRequest _request,HttpServletResponse _response)
	{
		try
		{
			String msg = null;
			String url = null;
			
			SmartUpload smart = new SmartUpload();
			smart.initialize(super.getServletConfig(), _request, _response);
			smart.upload();
			SmartRequest request = smart.getRequest();
			
			String backUrl = request.getParameter("backUrl");
			Integer gid = Integer.parseInt(request.getParameter("gid"));
			Integer iid = Integer.parseInt(request.getParameter("iid"));
			String title = request.getParameter("title");//validate
			Date pubdate =  new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("pubdate"));
			Double price = Double.parseDouble(request.getParameter("price"));
			Integer amount = Integer.parseInt(request.getParameter("amount"));
			Integer bow = Integer.parseInt(request.getParameter("bow"));
			String note = request.getParameter("note"); // validate
			Integer status = Integer.parseInt(request.getParameter("status"));
			String oldPhoto = request.getParameter("oldPhoto"); // validate
			String photo = CONST.noPhoto;
			
			if(StringUtils.isEmpty(title) || StringUtils.isEmpty(note) || StringUtils.isEmpty(oldPhoto))
			{
				msg = "提供的参数不正确";
				url = CONST.pageError;
				return General.setMsgAndUrlInRequest(_request, msg, backUrl);
			}
			
//			SmartFiles files= smart.getFiles();
//			boolean hasNewPhoto = false;
//			if(files.getCount() > 0 && files.getSize() > 0 && files.getFile(0).getContentType().contains("image"))
//				hasNewPhoto = true;
//			
//			if(CONST.noPhoto.equals(oldPhoto))
//			{ 
//				if(hasNewPhoto)
//				{
//					//原来没有 现在有
//					photo = UUID.randomUUID() + "." + files.getFile(0).getFileExt();
//					//保存新照片
//					files.getFile(0).saveAs(_request.getServletContext().getRealPath("/photos/goods/" + photo));
//				}
//				else
//				{
//					//原来没有 现在也没有
//					photo = CONST.noPhoto;
//				}
//			}
//			else
//			{
//				if(hasNewPhoto)
//				{
//					//原来有 现在有
//					photo = UUID.randomUUID() + "." + files.getFile(0).getFileExt();
//					//保存新照片
//					files.getFile(0).saveAs(_request.getServletContext().getRealPath("/photos/goods/" + photo));
//					
//					//删掉旧照片
//					List<String> photos = new ArrayList<String>();
//					photos.add(_request.getServletContext().getRealPath("/photos/goods/" + oldPhoto));
//					General.removePhotos(photos);
//				}
//				else
//				{
//					//原来有 现在没有
//					photo = oldPhoto;
//				}
//			}
//			
			photo = General.updatePhoto(smart, oldPhoto, oldPhoto, _request, "/photos/goods/");
			Goods vo = new Goods();
			vo.setGid(gid);
			Item item = new Item();
			item.setIid(iid);
			vo.setItem(item);
			vo.setTitle(title);
			vo.setPubdate(pubdate);
			vo.setPrice(price);
			vo.setAmount(amount);
			vo.setBow(bow);
			vo.setNote(note);
			vo.setStatus(status);
			vo.setPhoto(photo);
			
			if(ServiceBackFactory.getIGoodsServiceBackInstance().update(vo))
			{
				msg = "商品信息更新成功";
			}
			else
			{
				msg = "商品信息更新失败";
			}
			url = backUrl;
			return General.setMsgAndUrlInRequest(_request, msg, backUrl);
		}
		catch(Exception e)
		{
			return General.setSystemError(e);
		}
	}

	public String updateStatus(HttpServletRequest request)
	{
		String msg = null;
		String url = null;
		String referer = request.getHeader("referer");

		Integer status = Integer.parseInt(request.getParameter("status"));
		String[] _ids = request.getParameter("ids").split("\\|");
		Set<Integer> ids = new HashSet<Integer>();
		for (String id : _ids)
		{ 
			ids.add(Integer.parseInt(id.split(":")[0]));
		}

		try
		{
			if (CONST.GoodsStatus.DOWN.ordinal() == status)
			{
				if (ServiceBackFactory.getIGoodsServiceBackInstance().updateDown(ids))
				{
					msg = "更新状态成功";
				} else
				{
					msg = "更新状态失败";
				}
			} else if (CONST.GoodsStatus.UP.ordinal() == status)
			{
				if (ServiceBackFactory.getIGoodsServiceBackInstance().updateUp(ids))
				{
					msg = "更新状态成功";
				} else
				{
					msg = "更新状态失败";
				}
			} else if (CONST.GoodsStatus.DELETED.ordinal() == status)
			{
				if (ServiceBackFactory.getIGoodsServiceBackInstance().updateDelete(ids))
				{
					msg = "更新状态成功";
				} else
				{
					msg = "更新状态失败";
				}
			}
			url = "/pages/back/admin/goods/GoodsServletBack/"
					+ referer.substring(referer.lastIndexOf("/") + 1);
			return General.setMsgAndUrlInRequest(request, msg, url);
		} catch (Exception e)
		{
			return General.setSystemError(e);
		}
	}

	public String list(HttpServletRequest request)
	{
		String columns = "商品名:title|发布者:aid";
		Integer currentPage = 1;
		Integer lineSize = 5;
		String column = "title";
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

		try
		{
			String status = request.getParameter("status");
			Map<String, Object> map = null;
			if (!StringUtils.isEmpty(status))
			{
				map = ServiceBackFactory.getIGoodsServiceBackInstance().listByStatus(currentPage, lineSize,
						column, keyWord, Integer.parseInt(status));

				request.setAttribute("parameterKey", "status");
				request.setAttribute("parameterValue", status);
				// System.out.println("[debug] parameterKey: " +
				// request.getAttribute("parameterKey") + ", parameterValue:" +
				// request.getAttribute("parameterValue") );
			} else
			{
				map = ServiceBackFactory.getIGoodsServiceBackInstance().list(currentPage, lineSize, column,
						keyWord);
			}

			List<Goods> allGoods = (List<Goods>) map.get("allGoods");
			Integer allCount = (Integer) map.get("allCount");
			Integer toalPages = (allCount + lineSize - 1) / lineSize;
			request.setAttribute("allGoods", allGoods);
			request.setAttribute("allCount", allCount);

			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lineSize", lineSize);
			request.setAttribute("columns", columns);
			request.setAttribute("column", column);
			request.setAttribute("keyWord", keyWord);
			request.setAttribute("totalPages", toalPages);

			return CONST.pageGoodsBackListJSP;
		} catch (Exception e)
		{
			return General.setSystemError(e);
		}
	}

	public String insertPre(HttpServletRequest request)
	{
		try
		{
			List<Item> allItems = (List<Item>) ServiceBackFactory.getIGoodsServiceBackInstance().insertPre()
					.get("allItems");
			request.setAttribute("allItems", allItems);
			return CONST.pageGoodsInsertJSP;
		} catch (Exception e)
		{
			return General.setSystemError(e);
		}

	}

	public String insert(HttpServletRequest _request, HttpServletResponse _response)
	{
		String msg = null;
		String url = null;

		try
		{
			SmartUpload smart = new SmartUpload();
			smart.initialize(super.getServletConfig(), _request, _response);
			smart.upload();

			SmartRequest request = smart.getRequest();
			Integer iid = Integer.parseInt(request.getParameter("iid"));
			String aid = (String) _request.getSession().getAttribute("aid");
			String title = request.getParameter("title");
			Date pubdate = new Date(System.currentTimeMillis());
			Double price = Double.parseDouble(request.getParameter("price"));
			Integer amount = Integer.parseInt(request.getParameter("amount"));
			Integer bow = 1;
			String note = request.getParameter("note");
			String photo = CONST.noPhoto;
			Integer status = Integer.parseInt(request.getParameter("status"));

			// System.out.println("[debug] title:" + title + ",note:" + note);
			if (StringUtils.isEmpty(title) || StringUtils.isEmpty(note))
			{
				msg = "提供的商品信息不完整";
				url = CONST.pageGoodsInsertPreURL;

				return General.setMsgAndUrlInRequest(_request, msg, url);
			}

			if (StringUtils.isEmpty("note"))
			{
				note = "";
			}

			SmartFiles files = smart.getFiles();
			// System.out.println("[debug]" +
			// files.getFile(0).getContentType());
			if (files.getCount() > 0 && files.getSize() > 0
					&& files.getFile(0).getContentType().contains("image"))
			{
				// 有文件上传
				// 生成文件名
				photo = UUID.randomUUID().toString() + "." + files.getFile(0).getFileExt();
			}

			Goods vo = new Goods();

			Item item = new Item();
			item.setIid(iid);
			vo.setItem(item);

			vo.setAid(aid);
			vo.setTitle(title);
			vo.setPubdate(pubdate);
			vo.setPrice(price);
			vo.setAmount(amount);
			vo.setBow(bow);
			vo.setNote(note);
			vo.setPhoto(photo);
			vo.setStatus(status);

			// System.out.println("[debug] goods:");
			// System.out.println(vo);
			// System.out.println("path:" +
			// _request.getServletContext().getRealPath("/photos/goods/")
			// + vo.getPhoto());
			if (ServiceBackFactory.getIGoodsServiceBackInstance().insert(vo))
			{
				if (!CONST.noPhoto.equals(photo))
				{
					files.getFile(0).saveAs(
							_request.getServletContext().getRealPath("/photos/goods/") + vo.getPhoto());
				}
				msg = "商品添加成功";
				url = CONST.pageGoodsInsertPreURL;
				return General.setMsgAndUrlInRequest(_request, msg, url);
			}

		} catch (Exception e)
		{
			return General.setSystemError(e);
		}

		return CONST.pageForward;
	}
}
