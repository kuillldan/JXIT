package com.face.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.Logger;

public class SqlGenerateUtil {

	private static final Logger logger = Logger.getLogger(SqlGenerateUtil.class);

	private static final String[] TABLE_INDEX = ResourceBundle.getBundle("app").getString("table.index").split(",");

	private static final String COLUMN_PREFIX = ResourceBundle.getBundle("app").getString("table.colprefix");

	private static final int MAX_COL_NUM = Integer
			.valueOf(ResourceBundle.getBundle("app").getString("table.maxcolumnnum"));

	private static String getDelete(int tabInd) {
		StringBuffer str = new StringBuffer();
		str.append("DELETE ");
		str.append(getFrom(tabInd));
		return str.toString();
	}

	public static String getDelete(int tabInd, String[] colInd, String[] val) {
		logger.debug("getDelete");
		StringBuffer str = new StringBuffer();
		str.append(getDelete(tabInd));
		str.append(getWhere(tabInd, colInd, val));
		return str.toString();
	}

	public static String getInsert(int tabInd) {
		logger.debug("getInsert");
		StringBuffer str = new StringBuffer();
		str.append("INSERT INTO  " + TABLE_INDEX[tabInd-1] + CreateTableUtil.leftPad(String.valueOf(tabInd), 2));
		str.append("(");
		for (int i = 1; i <= MAX_COL_NUM; i++) {
			str.append(COLUMN_PREFIX + CreateTableUtil.leftPad(String.valueOf(tabInd), 2) + "_"
					+ CreateTableUtil.leftPad(String.valueOf(i), 3));
			if (i < MAX_COL_NUM)
				str.append(", ");
		}
		str.append(")");
		str.append("VALUES (");
		
		Set<Integer> col_number = new HashSet<Integer>();
		for(String s: ResourceBundle.getBundle("app").getString("table.coltype.numeric").split(",")){
			col_number.add(Integer.valueOf(s));
		}
		
		Map<Integer, Integer> keys = new HashMap<Integer, Integer>();
		for(String s: ResourceBundle.getBundle("app").getString("table.key.index").split(",")){
			String[] tmp = s.split(":");
			keys.put(Integer.valueOf(tmp[0]), Integer.valueOf(tmp[1]));
		}
		
		for (int i = 1; i <= MAX_COL_NUM; i++) {
			boolean isKey = keys.containsKey(i);
			boolean isNumeric = col_number.contains(i);
			str.append(isNumeric?"":"'");
			if(isKey){
				int len = keys.get(i);
				for(int n=0;n<len;n++){
					str.append("9");
				}
			}else{
				str.append("0");
			}
			str.append(isNumeric?"":"'");
			if (i < MAX_COL_NUM)
				str.append(", ");
		}
		str.append(")");
		return str.toString();
	}

	private static String getUpdate(int tabInd) {
		StringBuffer str = new StringBuffer();
		str.append("UPDATE " + TABLE_INDEX[tabInd-1] + CreateTableUtil.leftPad(String.valueOf(tabInd), 2));
		str.append(" SET ");
		
		Set<Integer> col_number = new HashSet<Integer>();
		for(String s: ResourceBundle.getBundle("app").getString("table.coltype.numeric").split(",")){
			col_number.add(Integer.valueOf(s));
		}
		
		Map<Integer, Integer> keys = new HashMap<Integer, Integer>();
		for(String s: ResourceBundle.getBundle("app").getString("table.key.index").split(",")){
			String[] tmp = s.split(":");
			keys.put(Integer.valueOf(tmp[0]), Integer.valueOf(tmp[1]));
		}

		for (int i = 1; i <= MAX_COL_NUM; i++) {
			if(keys.containsKey(i))
				continue;
			
			str.append(COLUMN_PREFIX + CreateTableUtil.leftPad(String.valueOf(tabInd), 2) + "_"
					+ CreateTableUtil.leftPad(String.valueOf(i), 3));
			str.append("=");
			boolean isNumeric = col_number.contains(i);
			str.append(isNumeric?"":"'");
			str.append("1");
			str.append(isNumeric?"":"'");
			if (i < MAX_COL_NUM)
				str.append(", ");
		}
		return str.toString();
	}

	public static String getUpdate(int tabInd, String[] colInd, String[] val) {
		logger.debug("getUpdate");
		StringBuffer str = new StringBuffer();
		str.append(getUpdate(tabInd));
		str.append(getWhere(tabInd, colInd, val));
		return str.toString();
	}

	public static String getSelect(int tabInd, int padnum, int type) {
		logger.debug("getSelect");
		StringBuffer str = new StringBuffer();
		str.append("SELECT ");
		for (int i = 1; i <= MAX_COL_NUM - type; i++) {
			str.append(COLUMN_PREFIX + CreateTableUtil.leftPad(String.valueOf(tabInd), 2) + "_"
					+ CreateTableUtil.leftPad(String.valueOf(i), 3));
			int n = padnum;
			while (n > 0) {
				str.append(" ");
				n--;
			}
			if (i < MAX_COL_NUM - type)
				str.append(", ");
		}
		str.append(getFrom(tabInd));
		return str.toString();
	}

	public static String getSelect(int tabInd, int padnum, int type, String[] colInd, String[] val) {
		StringBuffer str = new StringBuffer();
		str.append(getSelect(tabInd, padnum, type));
		str.append(getWhere(tabInd, colInd, val));
		return str.toString();
	}

	private static String getFrom(int tabInd) {
		StringBuffer str = new StringBuffer();
		str.append(" FROM " + TABLE_INDEX[tabInd-1] + CreateTableUtil.leftPad(String.valueOf(tabInd), 2));
		return str.toString();
	}

	private static String getWhere(int tabInd, String[] colInd, String[] val) {
		StringBuffer str = new StringBuffer();

		if (colInd.length > 0) {
			
			Set<Integer> col_number = new HashSet<Integer>();
			for(String s: ResourceBundle.getBundle("app").getString("table.coltype.numeric").split(",")){
				col_number.add(Integer.valueOf(s));
			}
			
			str.append(" WHERE ");
			for (int i = 0; i < colInd.length; i++) {
				str.append(COLUMN_PREFIX + CreateTableUtil.leftPad(String.valueOf(tabInd), 2) + "_"
						+ CreateTableUtil.leftPad(String.valueOf(colInd[i]), 3));
				str.append("=");
				boolean isNumeric = col_number.contains(Integer.valueOf(colInd[i]));
				str.append(isNumeric?"":"'");
				str.append(val[i]);
				str.append(isNumeric?"":"'");
				if (i != colInd.length - 1)
					str.append(" AND ");
			}
		}

		return str.toString();
	}
}
