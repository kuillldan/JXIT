package utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class HibernateConfiguation
{
	private String url;
	private String driver;
	private String username;
	private String password;

	private Map<String, String> allProperties = new HashMap<String, String>();

	public HibernateConfiguation()
	{
		// TODO Auto-generated constructor stub
	}

	public void configure() throws DocumentException
	{
		File cfgFile = new File("C:\\D\\JXIT\\temp\\hibernate.cfg.xml");
		SAXReader reader = new SAXReader();
		Document document = reader.read(cfgFile);
		Element root = document.getRootElement();
		Element session_factory = root.element("session-factory");
		List<Element> allProperties = session_factory.elements("property");

		for (Element property : allProperties)
		{
			String propertyName = property.attributeValue("name");
			String value = property.getTextTrim();
			this.allProperties.put(propertyName, value);
		}

		this.url = this.allProperties.get("connection.url");
		this.driver = this.allProperties.get("connection.driver_class");
		this.username = this.allProperties.get("connection.username");
		this.password = this.allProperties.get("connection.password");
	}

	public Connection getConnection()
	{
		try
		{
			Class.forName(this.driver);
			Connection conn;
			conn = DriverManager.getConnection(this.url, this.username, this.password);
			return conn;
		} catch (Exception e)
		{
			return null;
		}

	}
}