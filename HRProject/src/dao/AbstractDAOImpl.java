package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
 

public abstract class AbstractDAOImpl
{
	protected Connection conn;
	protected PreparedStatement ps;
	
	public AbstractDAOImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	/**
	 * 根据ids删除对应数据
	 * @param tableName
	 * @param column
	 * @param ids
	 * @return
	 */
	public boolean removeHandler(String tableName, String column, Set<?> ids) throws SQLException
	{
		if(ids.size() <= 0)
			return false;
		 
		StringBuffer sql = new StringBuffer(" DELETE FROM ");
		sql.append(tableName).append(" WHERE ").append(column).append(" IN( ");
		Iterator<?> iter = ids.iterator();
		while(iter.hasNext())
		{
			sql.append(iter.next()).append(",");
		} 
		sql.delete(sql.length()-1, sql.length());
		sql.append(")"); 
		this.ps = this.conn.prepareStatement(sql.toString());
		return this.ps.executeUpdate() == ids.size();
	}
	
	/**
	 * 根据关键字统计记录总条数
	 * @param tableName
	 * @param column
	 * @param keyWord
	 * @return
	 */
	public Integer countHandler(String tableName, String column, String keyWord) throws SQLException
	{
		String sql = " SELECT COUNT(*) FROM " + tableName + " WHERE " + column + " LIKE ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1,this.getKeyWork(keyWord));
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
			return rs.getInt(1);
		
		return 0;
	}
	
	protected void setLimit(PreparedStatement ps, String keyWord,Integer currentPage, Integer lineSize,Integer index1,Integer index2) throws SQLException
	{
		this.ps.setInt(index1, (currentPage - 1) * lineSize);
		this.ps.setInt(index2, lineSize);
	}
	
	protected String getKeyWork(String keyWord)
	{
		return "%"+keyWord+"%";
	}
	
	protected Set<String> findAllPhotosByIds(String tableName, String photoColumn, String idColumn, Set<?> ids)throws SQLException
	{
		Set<String> allPhotos = new HashSet<String>();
		return allPhotos;
	}
}
