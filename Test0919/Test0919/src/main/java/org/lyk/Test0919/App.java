package org.lyk.Test0919;

import java.util.function.Function;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.sun.org.apache.bcel.internal.generic.NEW;

import ch05.UserPrefixCode;
 


public class App
{
	public static void main(String[] args) throws Exception
	{
		 UserPrefixCode userPrefixCode1 = new UserPrefixCode();
		 userPrefixCode1.setPrefixCode("U3001");
		 userPrefixCode1.setTerminalType("G");
		 userPrefixCode1.setUserIdSuffix("userPrefixCode1");
		 
		 UserPrefixCode userPrefixCode2 = new UserPrefixCode();
		 userPrefixCode2.setPrefixCode("U3002");
		 userPrefixCode2.setTerminalType("S");
		 userPrefixCode2.setUserIdSuffix("userPrefixCode2");
		 
		 UserPrefixCode userPrefixCode3 = new UserPrefixCode();
		 userPrefixCode3.setPrefixCode("U3001");
		 userPrefixCode3.setTerminalType("G");
		 userPrefixCode3.setUserIdSuffix("userPrefixCode3");
		 
		 UserPrefixCode userPrefixCode4 = new UserPrefixCode();
		 userPrefixCode4.setPrefixCode("U3002");
		 userPrefixCode4.setTerminalType("F");
		 userPrefixCode4.setUserIdSuffix("userPrefixCode4");
		 
		 List<UserPrefixCode> userPrefixCodes = new ArrayList<>();
//		 Set<UserPrefixCode> userPrefixCodes = new HashSet<>();
		 userPrefixCodes.add(userPrefixCode1);
		 userPrefixCodes.add(userPrefixCode2);
		 userPrefixCodes.add(userPrefixCode3);
		 userPrefixCodes.add(userPrefixCode4);
		 
		 UserPrefixCode key = new UserPrefixCode();
		 key.setPrefixCode("U3001");
		 key.setTerminalType("G");
		 Integer index = userPrefixCodes.indexOf(key);
		 
		 System.out.println(userPrefixCodes.get(index));
		
	} 
}
