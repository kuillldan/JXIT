package org.lyk.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.text.*;

import org.lyk.entities.BiTree;
import org.lyk.entities.HuffmanTree;
import org.lyk.entities.KMP;
import org.lyk.interfaces.IList;

import com.sun.mail.imap.protocol.Item;

import entities0301.List;

/**
 * @author liuyuank
 *
 */
public class Main
{
	public static void main(String[] args) throws Exception
	{
		Set<Integer> ids = new HashSet<>();
		for(Integer id : ids)
		{
			System.out.println(id);
		}
		System.out.println("///~main done");
	}

	public static void print(File file)
	{
		File[] allFiles = file.listFiles();
		
		for (File item : allFiles)
		{
			if (item.isDirectory())
			{
				print(item);
			} else
			{
				System.out.println(item);
			}
		}

		System.out.println("//################################");
	}
}