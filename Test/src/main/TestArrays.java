package main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import javafx.scene.control.Tab;
import factory.ServiceFactory;
import service.IDeptService;
import service.impl.DeptServiceImpl; 
import utils.BeanOperator;
import utils.StringUtils;
import vo.Dept;
import vo.Employee;

public class TestArrays
{
	public static void output(int[] array)
	{
		
	}

	public static void main(String[] args)
	{ 
		String msg = "helloShit";
		String seed = "fdklh8937845%&*(^*()%6fdhjaku689";
		String encodedString = Base64.getEncoder().encodeToString(Base64.getEncoder().encodeToString((msg+seed).getBytes()).getBytes());
		System.out.println(encodedString);
		System.out.println(new String(Base64.getDecoder().decode(new String(Base64.getDecoder().decode(encodedString)))));
		
		System.out.println();
	}
}
