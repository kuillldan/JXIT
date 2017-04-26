package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import utils.Column;
import utils.DataGenerator;
import utils.General;
import vo.TBL_01;
import vo.TBL_03;
import factory.ServiceFactory;

public class Main
{
	public static final String CATALOG = "CTFAKSPRD";
	public static final String SCHEMA = "SCFAKPRD";
	public static final Integer RECORDS_COUNT = 20;
	
	public static final String TABLE_DEFINITION_FOLDER = "C:\\D\\NonStop\\tableDefinition\\";
	public static final String INSERT_SQL_FOLDER = "C:\\D\\NonStop\\datas\\";
	public static final String PROPERTIES_FOLDER = "C:\\D\\NonStop\\properties\\";
	
	
	public static void main(String[] args) throws Exception
	{ 
		
		generateVariableFiles();
		System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));
		System.out.println("///Main done~~");
	}
	
	public static void generatePropertyFiles() throws Exception
	{
		File file = new File(TABLE_DEFINITION_FOLDER);
		File[] allFiles = file.listFiles();
		for(File eachTable : allFiles)
		{
			DataGenerator dg = new DataGenerator(eachTable,RECORDS_COUNT,CATALOG,SCHEMA);
			StringBuffer properties = dg.getProperties();
			String propertiesFileName = dg.getTableName() + ".properties";
			PrintStream ps = new PrintStream(new FileOutputStream(new File(PROPERTIES_FOLDER + propertiesFileName), false));
			ps.print(properties.toString());
			ps.close();
			System.gc();
			System.out.println("***" + propertiesFileName + " 生成完成");
		}
	}
	
	public static void generateVariableFiles() throws Exception
	{
		//C:\sheldon\apache-jmeter-2.13\bin\sheldon\data\TBL_01.txt
		//C:\\sheldon\\apache-jmeter-2.13\\bin\\sheldon\\data\\TBL_01.txt
		String variableFolderPath  = "C:\\sheldon\\apache-jmeter-2.13\\bin\\sheldon\\data\\";
		File variableFolder = new File(variableFolderPath);
		if(!variableFolder.exists())
		{
			variableFolder.mkdirs();
		}
		
		for(int index = 1; index <= 14; index++)
		{
			String variableFilePath = null;
			if(index < 10)
			{
				variableFilePath = variableFolderPath + "TBL_0" + index + ".txt";
			}
			else
			{
				variableFilePath = variableFolderPath + "TBL_" + index + ".txt";
			}
			File variableFile = new File(variableFilePath);
			if(variableFile.createNewFile())
			{
				System.out.println(variableFilePath.substring(variableFilePath.lastIndexOf("\\")) + "创建成功");
			}
		}
	}
	
	
	
	//C:\sheldon\apache-jmeter-2.13\bin\sheldon\data\TBL_01.txt
	
}