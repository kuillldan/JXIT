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
	public static final Integer ADMIN_ACTID = 1;

	public static final String MSG = "msg";

	public static final String NOPHOTO_JPG = "nophoto.jpg";
	public static final Integer BUFFER_SIZE = 1024*1024;//1M
}
