package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import com.tandem.t4jdbc.SQLMXDataSource;

public class InsertDataMain
{
	public static final Integer BULK = 50;
	public static void main(String[] args)
	{
		ResourceBundle resource = ResourceBundle.getBundle("t4jdbc");
		Connection conn = null;
		System.out.println("[debug] 准备数据源");
		try
		{
			// data source configuration
			SQLMXDataSource ds = new SQLMXDataSource();
			ds.setUrl(resource.getString("jdbc.url"));
			ds.setCatalog(resource.getString("jdbc.catalog"));
			ds.setSchema(resource.getString("jdbc.schema"));
			ds.setUser(resource.getString("jdbc.username"));
			ds.setPassword(resource.getString("jdbc.password"));
			conn = ds.getConnection();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		System.out.println("[debug]: 数据源生成成功");

		int maxsql = Integer.valueOf(ResourceBundle.getBundle("insert").getString("table.maxsql"));

		String[] tables = ResourceBundle.getBundle("insert").getString("table.index").split(",");

		boolean truncate = Boolean.valueOf(ResourceBundle.getBundle("insert").getString("table.truncate"))
				.booleanValue();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			StringBuffer sql = null;
			for (String table : tables)
			{
				if (truncate)
				{
					// clear data in tables;
					sql = new StringBuffer();
					sql.append("delete from " + table + ";");
					ps = conn.prepareStatement(sql.toString());
					System.out.println("表(" + table + ")数据已删除. ");
					ps.executeUpdate();
				}
			}

			for (String table : tables)
			{
				int maxcolLength = Integer.valueOf(ResourceBundle.getBundle("insert").getString(
						"table.maxcolumn." + table));

				Set<Integer> colIndex = new HashSet<Integer>();
				for (String s : ResourceBundle.getBundle("insert").getString("table.incre." + table).split(","))
				{
					colIndex.add(Integer.valueOf(s));
				}

				int tmaxsql = Integer.valueOf(ResourceBundle.getBundle("insert").getString("table.maxsql." + table));

				// 设置每张表的SQL INSERT 语句
				sql = new StringBuffer();
				sql.append("INSERT INTO " + table);
				sql.append(" VALUES (");
				for (int n = 1; n <= maxcolLength; n++)
				{
					sql.append("?");

					if (n < maxcolLength)
						sql.append(",");
				}
				sql.append(");"); 
				ps = conn.prepareStatement(sql.toString());

				for (int i = 1; i <= maxsql; i++)
				{
					// 生成的SQL语句不能大于每张表指定的数据量
					if (tmaxsql > 0 && i > tmaxsql)
						break;

					// 清除过表
					// if (sql != null)
					// {

					// }

					// System.out.println("[debug] table: " + table);
					ArrayList<ColumnInfo> list = TableParser.parseTable(table);

					StringBuffer val = new StringBuffer();

					val.append("INSERT INTO " + table);
					val.append(" VALUES (");

					for (int m = 1; m <= maxcolLength; m++)
					{
						String strval = null;
						int intval = -1;
						ColumnInfo colInfo = list.get(m - 1);
						if (colInfo.getType().equals("CHAR"))
						{
							strval = InsertDataMain.valGen(colInfo.getType(), colInfo.len, i);
							val.append("'");
							val.append(strval);
							val.append("'");
							ps.setString(m, strval);
						} else
						{
							intval = Integer.parseInt(InsertDataMain.valGen(colInfo.getType(), colInfo.len, i));
							val.append(intval);
							ps.setInt(m, intval);
						}
						if (m < maxcolLength)
							val.append(",");
					}

					val.append(");");
					ps.addBatch(); 
					// ps.executeUpdate();

					if (i % BULK == 0)
					{
						System.out.println("成功生成" + i + "条" + table +"数据");
						ps.executeBatch();
					}
				}
				ps.executeBatch();
			}

			// conn.commit();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (Exception e1)
		{
			e1.printStackTrace();
		} finally
		{
			if (rs != null)
			{
				try
				{
					rs.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}

			if (ps != null)
			{
				try
				{
					ps.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}

			if (conn != null)
			{
				try
				{
					conn.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}

		System.out.println("[debug ] Main done//~~");
	}

	public static String valGen(String type, int len, int seed)
	{
		int max = (int) (Math.pow(10, len) - 1);
		int val = seed % max;
		return String.valueOf(val);
	}

}