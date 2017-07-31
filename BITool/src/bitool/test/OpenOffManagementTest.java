package bitool.test;

import static org.junit.Assert.*;

import org.junit.Test;

import bitool.enums.OpenOffMode;
import bitool.enums.OpenOffStatus;
import bitool.service.IOpenOffManagementService;
import bitool.service.impl.OpenOffManagementServiceImpl;
import bitool.vo.OpenOffManagement;

public class OpenOffManagementTest
{
	IOpenOffManagementService openOffManagmentService = new OpenOffManagementServiceImpl();

	@Test
	public void findOpenOffManagement_AssertNotNull()
	{
		try
		{
			assertNotNull(this.openOffManagmentService.findOpenOffManagement());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void updateTime_AssertTrue()
	{
		OpenOffManagement vo = new OpenOffManagement();
		vo.setStartTime("13:00");
		vo.setEndTime("19:00");
		try
		{
			//assertTrue(this.openOffManagmentService.updateTime(vo));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

//	@Test
//	public void updateStatus_AssertTrue()
//	{
//		try
//		{
//			assertTrue(this.openOffManagmentService.updateStatus(OpenOffStatus.OPEN.toString()));
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void updateMode_AssertTrue()
	{
		try
		{
		//	assertTrue(this.openOffManagmentService.updateMode(OpenOffMode.AUTO.toString()));
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
