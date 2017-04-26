package main;

import java.util.ArrayList;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class TableParser
{

	public static ArrayList<ColumnInfo> parseTable(String table)
	{

		ResourceBundle resource = ResourceBundle.getBundle(table);
		int i = 1;
		ArrayList<ColumnInfo> list = new ArrayList<ColumnInfo>();
		try
		{
			while (true)
			{
				String[] tmp = resource.getString(String.valueOf(i)).split(",");
				ColumnInfo colInfo = new ColumnInfo();
				colInfo.setId(i);
				colInfo.setColName(tmp[0].trim());
				colInfo.setType(tmp[1].trim());
				colInfo.setLenth(Integer.parseInt(tmp[2].trim()));
				list.add(colInfo);
				i++;
			}
		} catch (MissingResourceException e)
		{
			//System.out.println(("the end of table " + table + " on " + i));
		}
		return list;
	}

}
