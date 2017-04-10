package DAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import vo.Item;
import DAO.AbstractDAOImpl;
import DAO.IItemDAO;

public class ItemDAOImpl extends AbstractDAOImpl implements IItemDAO
{

	public ItemDAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 增加一个商品分类记录
	 */
	@Override
	public boolean doCreate(Item vo) throws Exception
	{
		String sql = " INSERT INTO item(title) VALUES (?) ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getTitle());
		return this.ps.executeUpdate() == 1;

	}

	/**
	 * 更新商品分类
	 */
	@Override
	public boolean doUpdate(Item vo) throws Exception
	{
		String sql = " UPDATE item SET title =? where iid =? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getTitle());
		this.ps.setInt(2, vo.getIid());
		System.out.println("[debug] sql: " + sql);
		System.out.println("[debug] title: " + vo.getTitle());
		System.out.println("[debug] iid: " + vo.getIid());
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception
	{
		return super.removeHandler("item", "iid", ids);
	}

	/**
	 * 根据商品分类ID找到商品分类记录
	 */
	@Override
	public Item findById(Integer id) throws Exception
	{
		Item vo = null;
		String  sql = " SELECT iid,title FROM item WHERE iid = ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, id);
		ResultSet rs = this.ps.executeQuery();
		if(rs.next())
		{
			vo = new Item();
			vo.setIid(rs.getInt("iid"));
			vo.setTitle(rs.getString("title"));
		}
		
		return vo;
	}

	/**
	 * 查询所有的商品分类
	 */
	@Override
	public List<Item> findAll() throws Exception
	{
		List<Item> allItems = new ArrayList<Item>();
		String sql = " SELECT iid,title FROM item ";
		this.ps = this.conn.prepareStatement(sql);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			Item vo = new Item();
			vo.setIid(rs.getInt("iid"));
			vo.setTitle(rs.getString("title"));
			allItems.add(vo);
		}
		return allItems;
	}

	@Override
	public List<Item> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

}
