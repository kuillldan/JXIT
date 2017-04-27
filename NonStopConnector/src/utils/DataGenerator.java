package utils;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import enums.DataTypes;
import exceptions.NotSupportedDataTypeException;

public class DataGenerator
{
	private File tableInfo;
	private Integer totalRecords = 1000;
	private String catalog;
	private String schema;
	private String tableName;

	public DataGenerator(File tableInfo, Integer totalRecords, String catalog, String schema)
	{
		super();
		this.tableInfo = tableInfo;
		this.totalRecords = totalRecords;
		this.catalog = catalog;
		this.schema = schema;
		this.tableName = this.tableInfo.getName().substring(0, this.tableInfo.getName().lastIndexOf("."));
	}

	public DataGenerator()
	{
		super();
	}

	public File getTableInfo()
	{
		return tableInfo;
	}

	public void setTableInfo(File tableInfo)
	{
		this.tableInfo = tableInfo;
	}

	public Integer getTotalRecords()
	{
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords)
	{
		this.totalRecords = totalRecords;
	}

	public String getCatalog()
	{
		return catalog;
	}

	public void setCatalog(String catalog)
	{
		this.catalog = catalog;
	}

	public String getSchema()
	{
		return schema;
	}

	public void setSchema(String schema)
	{
		this.schema = schema;
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public StringBuffer getObjects() throws Exception
	{
		StringBuffer object = new StringBuffer();
		List<Column> allColumns = this.getColumnInfo();
		object.append("package vo;\r\n");
		object.append("public class ").append(this.tableName).append("\r\n");
		object.append("{\r\n"); 
		for (Column column : allColumns)
		{
			object.append("private ").append(this.convertNormalSQLTypeToJavaType(column.getDataType())).append(" ").append(column.getColumnName()).append(" ;\r\n");
		}
		
		object.append("\r\n\r\n");
		for(Column column : allColumns)
		{
			//GETTER
			object.append("public ").append(this.convertNormalSQLTypeToJavaType(column.getDataType())).append("  get").append(column.getColumnName()).append("()\r\n");
			object.append("{ return this.").append(column.getColumnName()).append("; ").append("}\r\n");
			
			//SETTER
			object.append("public void set").append(column.getColumnName()).append("(").append(this.convertNormalSQLTypeToJavaType(column.getDataType())).append(" ").append(column.getColumnName()).append(")\r\n");
			object.append("{ this.").append(column.getColumnName()).append("=").append(column.getColumnName()).append(";\r\n");
			object.append("}\r\n");
		}
		object.append("}");
		
		return object;
	}
	
	private String convertNormalSQLTypeToJavaType(String sqlType) throws NotSupportedDataTypeException
	{
		if(DataTypes.CHAR.getRealType().equals(sqlType))
			return "String";
		else if(DataTypes.NUMERIC.getRealType().equals(sqlType))
			return "Integer";
		else
			throw new NotSupportedDataTypeException();
	}

	public StringBuffer getProperties() throws Exception
	{
		StringBuffer properties = new StringBuffer();

		List<Column> allColumns = this.getColumnInfo();

		int index = 1;
		for (Column column : allColumns)
		{
			properties.append(index++).append("=");
			properties.append(column.getColumnName()).append(",");
			properties.append(this.convertNormalSQLTypeToNonStopSQLType(column.getDataType())).append(",");
			properties.append(column.getLength()).append("\r\n");
		}

		return properties;
	}

	public StringBuffer getInsertSQL() throws Exception
	{
		List<Column> allColumns = this.getColumnInfo();

		String sqlHeader = " INSERT INTO " + this.catalog + "." + this.schema + "." + this.tableName + " (";
		// INSERT INTO CTFAKSPRD.SCFAKPRD.TBL_03 (
		StringBuffer sql = new StringBuffer();
		StringBuffer values = null;

		for (Integer i = 0; i < this.totalRecords; i++)
		{
			sql.append(sqlHeader);
			values = new StringBuffer();

			for (Column column : allColumns)
			{
				sql.append(column.getColumnName()).append(",");
				if (DataTypes.CHAR.getRealType().equals(column.getDataType()))
				{
					values.append("'");
					values.append(this.generateString(column.getLength()));
					values.append("'");
				} else if (DataTypes.NUMERIC.getRealType().equals(column.getDataType()))
				{
					values.append(this.generateInt(column.getLength()));
				} else
				{
					throw new NotSupportedDataTypeException();
				}
				values.append(",");
			}
			sql.delete(sql.length() - 1, sql.length());
			values.delete(values.length() - 1, values.length());

			sql.append(") VALUES (");
			sql.append(values);
			sql.append(");");
			sql.append("\r\n");
		}
		return sql;
	}

	private BigInteger generateInt(Integer maxLength)
	{
		BigInteger data = null;
		Integer realLength = null;
		StringBuffer dataInString = new StringBuffer();
		Random rand = new Random();
		realLength = rand.nextInt(maxLength + 1);
		while (realLength == 0)
		{
			realLength = rand.nextInt(maxLength + 1);
		}

		String head = String.valueOf(rand.nextInt(10));
		while (head.equals("0"))
		{
			head = String.valueOf(rand.nextInt(10));
		}
		dataInString.append(head);
		for (int i = 1; i < realLength; i++)
		{
			dataInString.append(String.valueOf(rand.nextInt(10)));
		}
		data = new BigInteger(dataInString.toString());
		return data;
	}

	private String generateString(Integer maxLength)
	{
		StringBuffer dataInStringBuffer = new StringBuffer();
		Integer realLength = null;

		Random rand = new Random();
		realLength = rand.nextInt(maxLength + 1);
		while (realLength == 0)
		{
			realLength = rand.nextInt(maxLength + 1);
		}

		for (int i = 0; i < realLength; i++)
		{
			int singleChar = rand.nextInt(126);
			while (singleChar < 48 || (singleChar > 57 && singleChar < 64) || (singleChar > 90 && singleChar < 97))
			{
				singleChar = rand.nextInt(126);
			}
			dataInStringBuffer.append((char) singleChar);
		}
		return dataInStringBuffer.toString();
	}

	public List<Column> getColumnInfo() throws Exception
	{
		List<Column> allColumns = new ArrayList<Column>();
		Scanner scanner = new Scanner(this.tableInfo);
		while (scanner.hasNextLine())
		{
			String[] allInfo = scanner.nextLine().split("\t");
			Column column = new Column();
			column.setColumnName(allInfo[0]);
			column.setNotNull(allInfo[9]);
			column.setDataType(allInfo[11]);
			column.setLength(allInfo[16]);
			allColumns.add(column);
		}
		scanner.close();
		return allColumns;
	}

	private String convertNormalSQLTypeToNonStopSQLType(String normalSQLType) throws NotSupportedDataTypeException
	{
		if (DataTypes.CHAR.getRealType().equals(normalSQLType))
		{
			return "CHAR";
		} else if (DataTypes.NUMERIC.getRealType().equals(normalSQLType))
		{
			return "NUMERIC";
		} else
		{
			throw new NotSupportedDataTypeException();
		}
	}
}
