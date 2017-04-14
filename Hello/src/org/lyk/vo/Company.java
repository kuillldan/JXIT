package org.lyk.vo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Company
{
	private Double[] priceList;

	public Double[] getPriceList()
	{
		return priceList;
	}

	public void setPriceList(Double[] priceList)
	{
		this.priceList = priceList;
	}

	@Override
	public String toString()
	{
		return "Company [priceList=" + Arrays.toString(priceList) + "]";
	}
 }
