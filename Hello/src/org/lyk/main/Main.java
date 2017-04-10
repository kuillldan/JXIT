package org.lyk.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author Producer
 */
class VideoGame
{

	private String title;
	private int year;
	private String rating;
	private String[] platforms;

	public VideoGame()
	{
	}

	public VideoGame(String title, int year, String rating, String[] platforms)
	{
		this.title = title;
		this.year = year;
		this.rating = rating;
		this.platforms = platforms;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public String getRating()
	{
		return rating;
	}

	public void setRating(String rating)
	{
		this.rating = rating;
	}

	public String[] getPlatforms()
	{
		return platforms;
	}

	public void setPlatforms(String[] platforms)
	{
		this.platforms = platforms;
	}

	@Override
	public String toString()
	{
		String result = "";
		result += "\nTitle: " + getTitle() + "\nYear: " + getYear() + "\nRating: " + getRating()
				+ "\nPlatforms: ";
		for (String platform : getPlatforms())
		{
			result += platform + "  ";
		}
		return result;
	}

}

public class Main
{

	public static void main(String[] args)
	{ 
		String[] allVideos = {};
		for(String video : allVideos)
		{
			System.out.println(video);
		}
		
		
		
		System.out.println("///Main done~~");
	}
}