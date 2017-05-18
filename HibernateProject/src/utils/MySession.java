package utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private String idColumn;
	
	public MySession()
	{ 
	}
	
	public void save(Object vo) throws DocumentException
	{
		this.vo = vo;
		String fileName = this.vo.getClass().getSimpleName() + ".hbm.xml";
		this.parseHBMFile(fileName);
		
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
		this.idColumn = idElement.element("column").attributeValue("mid");
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
