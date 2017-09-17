package main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import dbc.DatabaseConnection;
import vo.Employee;

public class Hello
{
	public static void main(String[] args) throws Exception
	{
		Integer a = new Integer(3);
		Integer b = 3;
		String sa = "xxx";
		String sb = new String("xxx");
		System.out.println(sb == "xxx");
	}

	public static void func(String... strs)
	{
		System.out.println(strs.length);
	}
}
