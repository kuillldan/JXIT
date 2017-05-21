package utils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
 

public class MySession
{
	
	private Object vo;
	private String className;
	private String tableName;
	private String idName;
	private String idType ;
	private String generator;
	private Map<String, String> fieldTypeMap = new HashMap<String, String>();
	private Map<String, String> fieldColumnMap = new HashMap<String, String>();
	private Map<Integer, String> indexFieldMap = new HashMap<Integer, String>();
	private String idColumn; 
	private Connection conn;
	private PreparedStatement ps ;
	
	public MySession()
	{ 
		HibernateConfiguation configure = new HibernateConfiguation();
		try
		{
			configure.configure();
		} catch (DocumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.conn = configure.getConnection();
	}
	
	public void save(Object vo) throws Exception
	{
		this.vo = vo;
		String fileName = this.vo.getClass().getSimpleName() + ".hbm.xml";
		this.parseHBMFile(fileName);
		String sql = this.createInsertSQL();
		this.setPreparedStatement(sql);
		int rowCount = this.ps.executeUpdate(); 
		this.conn.close();
		System.out.println(rowCount == 1 ? "增加成功" : "增加失败");
	}
	
	private void setPreparedStatement(String sql) throws Exception
	{
		this.ps = this.conn.prepareStatement(sql);
		
		Set<Map.Entry<Integer, String>> entrySet = this.indexFieldMap.entrySet();
		for(Map.Entry<Integer, String> entry : entrySet)
		{
			Integer index = entry.getKey();
			String fieldName = entry.getValue();
			String columnName = this.fieldColumnMap.get(fieldName);
			String fieldType = this.fieldTypeMap.get(fieldName);
			String fieldTypeSimpleName = fieldType.substring(fieldType.lastIndexOf(".") + 1);
			System.out.println("[debug] index:" + index + ",字段名:" + fieldName + ",字段类型:" + fieldType + ",数据库字段名:" + columnName);
			Method getter = this.vo.getClass().getDeclaredMethod("get" + StringUtils.initCap(fieldName));
			switch(fieldTypeSimpleName)
			{
				case "String":
				{ 
					String value = (String)getter.invoke(this.vo);
					this.ps.setString(index, value);
					break;
				}
				case "Integer":
				{
					Integer value = (Integer)getter.invoke(this.vo);
					this.ps.setInt(index, value);
					break;
				}
				case "Double" :
				{
					Double value = (Double)getter.invoke(this.vo);
					this.ps.setDouble(index, value);
					break;
				}
				case "Date":
				{
					Date value = (Date)getter.invoke(this.vo);
					this.ps.setTimestamp(index,new java.sql.Timestamp(value.getTime()));
					break;
				}
				default :
				{
					System.out.println("不支持的数据类型");
					throw new Exception("不支持的数据类型!");
				}
			}
		}
		
	}
	
	public String createInsertSQL()
	{
		StringBuffer sql = new StringBuffer();
		StringBuffer values = new StringBuffer();
		Integer index = 1;
		
		sql.append(" INSERT INTO ").append(this.tableName).append(" (");
		values.append(" VALUES( ");
		if("assigned".equals(this.generator))
		{
			System.out.println("[debug] idColumn:" + this.idColumn);
			sql.append(this.idColumn).append(",");
			values.append("?,");
			this.indexFieldMap.put(index++, this.idColumn);
		}
		
		Set<Map.Entry<String, String>> fieldSet = this.fieldTypeMap.entrySet();
		for(Map.Entry<String, String> field : fieldSet )
		{
			String fieldName = field.getKey();
			if(!fieldName.equals(this.idName))
			{
				String columnName = this.fieldColumnMap.get(fieldName);
				sql.append(columnName).append(",");
				values.append("?,");
				this.indexFieldMap.put(index++, fieldName);
			}
		}
		
		sql.delete(sql.length()-1, sql.length());
		values.delete(values.length()-1, values.length());
		sql.append(" ) ");
		values.append(" ) ");
		
		return sql.toString() + values.toString();
	}
	
	public void parseHBMFile(String fileName) throws DocumentException
	{
		File voHbmXML = new File("C:" + File.separator + "D" + File.separator + "jxit" + File.separator + "temp" + File.separator + fileName);
		SAXReader sax = new SAXReader();
		Document document = sax.read(voHbmXML);
		Element hmRoot = document.getRootElement();
		Element classElement = hmRoot.element("class");
		
		this.className = classElement.attributeValue("name");
		this.tableName = classElement.attributeValue("table");
		Element idElement = classElement.element("id");
		this.idName = idElement.attributeValue("name");
		this.idType = idElement.attributeValue("type");
		this.fieldTypeMap.put(idName, idType);
		this.idColumn = idElement.element("column").attributeValue("name");
		this.fieldColumnMap.put(idName, idColumn);
		
		Element generatorElement = idElement.element("generator");
		this.generator = generatorElement.attributeValue("class");
		
		List<Element> allProperties = classElement.elements("property");
		for(Element eachProperty : allProperties)
		{
			String fieldName = eachProperty.attributeValue("name");
			String fieldType = eachProperty.attributeValue("type");
			String columnName = eachProperty.element("column").attributeValue("name");
			this.fieldTypeMap.put(fieldName, fieldType);
			this.fieldColumnMap.put(fieldName, columnName);
		}
		
		System.out.println("");
	}
}