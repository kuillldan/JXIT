package bitool.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;


import java.sql.ResultSet;

import bitool.dao.IAccountManagementDAO;
import bitool.vo.AccountManagement;

public class AccountManagementDAOImpl implements IAccountManagementDAO
{
	Connection conn = null;
	public AccountManagementDAOImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	@Override
	public AccountManagement findByIpAddress(String ipAddress) throws Exception
	{
		AccountManagement vo = null;
		
		String sql = " SELECT aid,userID,transformedUserID,userType,ipAddress  FROM accountmanagement WHERE ipaddress = ? ";
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, ipAddress);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			vo = new AccountManagement();
			vo.setAid(rs.getInt("aid"));
			vo.setUserID("userID");
			vo.setTransformedUserID("transformedUserID");
			vo.setUserType("userType");
			vo.setIpAddress("ipAddress");
		}
		
		return vo;
	}

}
