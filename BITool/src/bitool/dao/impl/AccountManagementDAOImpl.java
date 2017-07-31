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
		
		String sql = " SELECT aid,userID,transformedUserID,userType,ipAddress  FROM accountManagement WHERE ipaddress = ? ";
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, ipAddress);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			vo = new AccountManagement();
			vo.setAid(rs.getInt("aid"));
			vo.setUserID(rs.getString("userID"));
			vo.setTransformedUserID(rs.getString("transformedUserID"));
			vo.setUserType(rs.getString("userType"));
			vo.setIpAddress(rs.getString("ipAddress"));
		}
		
		return vo;
	}

}
