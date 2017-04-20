package org.lyk.service.impl;

import java.util.List;

import org.lyk.factory.DAOFactory;
import org.lyk.service.IT01Service;
import org.lyk.vo.T01;

import dbc.DatabaseConnection;

public class T01ServiceImpl implements IT01Service
{
	DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public List<T01> list() throws Exception
	{
		try
		{
			List<T01> allT01s = DAOFactory.getIt01daoInstance(this.dbc.getConnection()).findAll();
			return allT01s;
			
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}
	@Override
	public boolean insert() throws Exception
	{
		try
		{
			return DAOFactory.getIt01daoInstance(this.dbc.getConnection()).doCreate(null);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			this.dbc.close();
		}
	}

}
