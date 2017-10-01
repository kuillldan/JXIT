package org.lyk.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonConstant
{
	public static final Logger LOGGER = LoggerFactory.getLogger(CommonConstant.LOGFILE);

	public static final String LOGFILE = "logfile";
	public static final String MAPPING_PREFIX = "org.lyk.vo.mapping.";

	public static final String FORWARD_JSP = "/forward.jsp";
	public static final String ERROR_JSP = "error.jsp";
	public static final String ADMIN_ADD_JSP = "admin.add.jsp";

	public static final String EMP = "emp";
	public static final String ALLITEMS = "allItems";
	public static final Integer ADMIN_ACTID = 1;

	public static final String MSG = "msg";

	public static final String NOPHOTO_JPG = "nophoto.jpg";
	public static final Integer BUFFER_SIZE = 1024 * 1024;// 1M

	public static final String EMP_PHOTO_SAVE_PATH = "EMP.PHOTO.SAVE.PATH";

	//Messages
	public static final String NOT_AUTHORIZED = "not.authorized";
	public static final String LOGOUT_SUCCESS = "logout.success";
	public static final String UNKNOWN_ERROR = "unknown.error";
	public static final String DEPT_LIST_ACTION = "dept.list.action";
	public static final String GROUPS_LIST_JSP = "groups.list.jsp";
}
