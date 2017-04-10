package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.xml.crypto.Data;

import utils.StringUtils;
import vo.Column;
import vo.DataType;

 

public class Hello
{
	//表结构所在的目录
	public static final String sourceFolderName = "C:\\Users\\liuyuank\\Desktop\\project 1\\tables\\";
	
	//要生成的表结构所在的目录
	public static final String sqlFolderName = "C:\\Users\\liuyuank\\Desktop\\project 1\\sqls\\";
	
	
	public static final String recordsFolderName = "C:\\Users\\liuyuank\\Desktop\\project 1\\records\\";
	
	
	public static void main(String[] args)
	{
		try
		{
			//createTableSql();
			//generateData();
			createExecutionSql();
			//System.out.println(DataType.INT.name());
//			Random r = new Random();
//			for(int i = 0; i< 5; i++)
//			{
//				System.out.println(r.nextInt(5));
//			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		System.out.println("///Main done~~");
	}
	
	public static void createExecutionSql() throws Exception
	{
		Scanner scanner = new Scanner(new File("C:\\Users\\liuyuank\\Desktop\\project 1\\2\\tables\\SQL.csv"),"UTF-8");
		while(scanner.hasNextLine())
		{
			String newLine = scanner.nextLine();
			newLine = newLine.replace(",\",\"", "&\",\"");
			newLine = newLine.replace(",\",", "&\",");
			//System.out.println("第一次替换后:" + newLine);
			String[] allFields = newLine.split(",");
			String fileName = allFields[0];
			
			
			if(!StringUtils.isEmpty(allFields[1]))
			{
				fileName += "_" + allFields[1] ;
			}
			fileName += ".sql";
			
			StringBuffer sql = new StringBuffer();
			for(int i = 2; i < allFields.length; i++)
			{
				if(StringUtils.isEmpty(allFields[i]))
					continue;
				
				allFields[i] = allFields[i].replace("&\"", ",");
				allFields[i] = allFields[i].replace("\"", "");
				if(allFields[i].contains("#N/A") || allFields[i].contains("句") || allFields[i].contains("/*") )
					continue;
				 
				sql.append(" " + allFields[i] + " ");
			}
			
			System.out.println(fileName + ":" +sql.toString());
			
			
		}
		scanner.close();
	}
	
	
	public static void generateData(Integer recordsCount) throws Exception
	{
		File sourceFile = new File(sourceFolderName);
		File[] allFiles = sourceFile.listFiles();
		for(File eachFile : allFiles)
		{
			if(!eachFile.getName().endsWith(".csv"))
			{
				//System.out.println(eachFile.getName());
				continue;
			}
			
			String tableName = eachFile.getName().substring(0, eachFile.getName().lastIndexOf("."));
			Scanner scanner = new Scanner(new FileInputStream(eachFile));
			List<Column> allColumns = new ArrayList<>();
			while (scanner.hasNextLine())
			{
				String[] newLine = scanner.nextLine().split(",");
				String columnName = newLine[0];
				String isNull = newLine[1];
				String dataType = newLine[2];
				String length = newLine[3];
				
				Column column = new Column();
				column.setColumnName(columnName);
				column.setDataType(dataType);
				column.setLength(length);
				allColumns.add(column);
			}
			scanner.close();
			
			PrintStream ps = new PrintStream(new FileOutputStream(new File(recordsFolderName)));
			for(Integer i = 0;i < recordsCount; i++)
			{
				StringBuffer columnSql = new StringBuffer();
				StringBuffer datasSql = new StringBuffer();
				columnSql.append(" INSERT INTO ").append(tableName).append(" (");
				datasSql.append(" VALUES ( ");
				
				for(Column c : allColumns)
				{
					columnSql.append(c.getColumnName()).append(",");
					
					 
				}
				
				
				columnSql.append(") ");
				datasSql.append(");\r\n");
			}
			ps.close();
		}
	}
	
	public static void createTableSql() throws Exception
	{
		
		File sourceFile = new File(sourceFolderName);
		File[] allFiles = sourceFile.listFiles();

		for (File eachFile : allFiles)
		{
			if(!eachFile.getName().endsWith(".csv"))
			{
				//System.out.println(eachFile.getName());
				continue;
			}
			
			
			StringBuffer sql = new StringBuffer(); 
			String tableName = eachFile.getName().substring(0, eachFile.getName().lastIndexOf("."));
			sql.append(" DROP TABLE " + tableName + " IF EXISTS; \r\n");
			sql.append("CREATE TABLE ")
					.append(tableName);
			sql.append("\r\n(");
			Scanner scanner = new Scanner(new FileInputStream(eachFile));
			while (scanner.hasNextLine())
			{
				String[] newLine = scanner.nextLine().split(",");
				String columnName = newLine[0];
				String isNull = newLine[1];
				String dataType = newLine[2];
				String length = newLine[3]; 
				
				sql.append(columnName).append(" ");
				if ("CHAR".equals(dataType))
				{ 
					sql.append(DataType.VARCHAR.getRealType()).append("(");
				} else if ("NUMERIC".equals(dataType))
				{ 
					sql.append(DataType.INT.getRealType()).append("(");
				} else
				{
					scanner.close();
					throw new Exception("不支持的数据类型:" + dataType);
				}

				sql.append(length).append(")");
				if ("Y".equals(isNull))
				{
					sql.append(" NOT NULL,");
				} else if ("N".equals(isNull))
				{
					sql.append(" ,");
				} else
				{
					scanner.close();
					throw new Exception("不支持的NULL 属性：" + isNull);
				}

				//sql.append("\r\n");
			}
			
			
			sql = new StringBuffer(sql.substring(0, sql.lastIndexOf(","))); 
			sql.append(");\r\n");
			storeTableSql(sql, tableName); 
			scanner.close();
		} 
	}
	public static void storeTableSql (StringBuffer sql, String tableName) throws Exception
	{ 
		File sqlFile = new File(sqlFolderName + tableName + ".txt");
		PrintStream ps = new PrintStream(sqlFile);
		ps.println(sql.toString());
		ps.close();
		//System.out.println(tableName+ ".sql 创建完成");
		System.out.println(sql);
	}

	public static String getData(String dataType, String dataLength, String isNull)
	{
		StringBuffer data = null; 
		if(DataType.VARCHAR.getRealType().equals(dataType))
		{
			//生成一个随机长度的字符串
			Random random = new Random();
			Integer acturalLength = random.nextInt(Integer.parseInt(dataLength));
			if(acturalLength == 0 && "Y".equals(isNull))
			{
				return "NULL";
			}
			else
			{
				acturalLength++;
				
				for(int i = 0; i < acturalLength; i++)
				{
					
				}
			}
		}else if(DataType.INT.getRealType().equals(dataType))
		{
			//生成一个指定长度的数字 ??
			Random random = new Random();
			data = new StringBuffer(String.valueOf(random.nextInt(Integer.parseInt(dataLength))));
		}
		return data.toString();
	} 
	
//	public static String getRandomCharacter(Integer c)
//	{
//		switch(c)
//		case 1: 
//		{
//			return "a";
//			
//		}
//	}
}
