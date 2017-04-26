package com.face.util;

import org.apache.log4j.Logger;

public class CreateTableUtil {
	private static final Logger logger = Logger.getLogger(CreateTableUtil.class);

	private static final String TABLE_PREFIX = "TBL_";

	private static final String COLUMN_PREFIX = "COL_";

	public static String generateTable(int tableNum, int columnNum) {
		logger.info("generateTable");
		StringBuffer str = new StringBuffer();

		if (tableNum < 1 || columnNum < 1)
			return null;

		for (int i = 1; i <= tableNum; i++) {
			String t_ind = leftPad(String.valueOf(i), 2);
			str.append("DROP TABLE IF EXISTS " + TABLE_PREFIX + t_ind + ";\n");
			str.append("CREATE TABLE " + TABLE_PREFIX + t_ind + "(");

			for (int n = 1; n <= columnNum; n++) {
				String c_ind = leftPad(String.valueOf(n), 3);
				str.append(COLUMN_PREFIX + t_ind + "_" + c_ind + " CHAR(10) ");
				if (n != columnNum)
					str.append(", ");
			}

			str.append(");\n");
		}

		return str.toString();
	}

	public static String leftPad(String origin, int len) {
		int remain = len - origin.length();
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < remain; i++) {
			res.append("0");
		}
		res.append(origin);
		return res.toString();
	}
}
