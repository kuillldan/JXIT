package org.lyk.main;


import static org.junit.Assert.fail;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lyk.StartSpringBoot;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes=StartSpringBoot.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestDataSource
{
	@Resource
	private DataSource dataSource;
	
	@Test
	public void testDataSource()
	{
		try
		{
			System.out.println(this.dataSource.getConnection());
		} catch (SQLException e)
		{
			fail();
		}
	}
}
