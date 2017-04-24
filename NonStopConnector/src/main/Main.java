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
	
	public static void main(String[] args) throws Exception
	{ 
		File file = new File(TABLE_DEFINITION_FOLDER);
		File[] allFiles = file.listFiles();
		for(File eachTable : allFiles)
		{
			DataGenerator dg = new DataGenerator(eachTable,RECORDS_COUNT,CATALOG,SCHEMA);
			StringBuffer insertSQL = dg.getInsertSQL();
			String insertSqlFileName = dg.getTableName() + ".sql";
			PrintStream ps = new PrintStream(new FileOutputStream(new File(INSERT_SQL_FOLDER + insertSqlFileName), false));
			ps.print(insertSQL.toString());
			ps.close();
			System.gc();
			System.out.println("***" + insertSqlFileName + " 生成完成");
		}
		
		System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));
		System.out.println("///Main done~~");
	}
}