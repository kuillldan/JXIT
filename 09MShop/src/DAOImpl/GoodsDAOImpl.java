package DAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import vo.Goods;
import vo.Item;
import DAO.AbstractDAOImpl;
import DAO.IGoodsDAO;

public class GoodsDAOImpl extends AbstractDAOImpl implements IGoodsDAO
{

	public GoodsDAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 增加商品记录
	 */
	@Override
	public boolean doCreate(Goods vo) throws Exception
	{
		String sql = " INSERT INTO goods (iid,aid,title,pubdate,price,amount,bow,note,photo,status) VALUES(?,?,?,?,?,?,?,?,?,?) ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, vo.getItem().getIid());
		this.ps.setString(2, vo.getAid());
		this.ps.setString(3, vo.getTitle());
		this.ps.setDate(4, new java.sql.Date(vo.getPubdate().getTime()));
		this.ps.setDouble(5, vo.getPrice());
		this.ps.setInt(6, vo.getAmount());
		this.ps.setInt(7, vo.getBow());
		this.ps.setString(8, vo.getNote());
		this.ps.setString(9, vo.getPhoto());
		this.ps.setInt(10, vo.getStatus());
		return this.ps.executeUpdate() == 1;
	}

	/**
	 * iid,title,price,amount,note,photo,status
	 */
	@Override
	public boolean doUpdate(Goods vo) throws Exception
	{
		String sql = " UPDATE goods SET iid=?,title=?,price=?,amount=?,note=?,photo=?,status=? WHERE gid=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, vo.getItem().getIid());
		this.ps.setString(2, vo.getTitle());
		this.ps.setDouble(3, vo.getPrice());
		this.ps.setInt(4, vo.getAmount());
		this.ps.setString(5, vo.getNote());
		this.ps.setString(6, vo.getPhoto());
		this.ps.setInt(7, vo.getStatus());
		this.ps.setInt(8, vo.getGid());
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception
	{
		return super.removeHandler("goods","gid", ids);
	}

	/**
	 * 通过id找到对应的商品记录
	 */
	@Override
	public Goods findById(Integer id) throws Exception
	{
		Goods vo = null;
		String sql = " SELECT gid,iid,aid,title,pubdate,price,amount,bow,note,photo,status FROM goods WHERE gid=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, id);
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
		{
			vo = new Goods();
			
			vo.setGid(rs.getInt("gid"));
			Item item = new Item();
			item.setIid(rs.getInt("iid"));
			vo.setItem(item);

			vo.setAid(rs.getString("aid"));
			vo.setTitle(rs.getString("title"));
			vo.setPubdate(rs.getDate("pubdate"));
			vo.setPrice(rs.getDouble("price"));
			vo.setAmount(rs.getInt("amount"));
			vo.setBow(rs.getInt("bow"));
			vo.setNote(rs.getString("note"));
			vo.setPhoto(rs.getString("photo"));
			vo.setStatus(rs.getInt("status")); 
		}
		return vo;
	}

	@Override
	public List<Goods> findAll() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 分页查询
	 */
	@Override
	public List<Goods> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception
	{
		List<Goods> allGoods = new ArrayList<Goods>();
		String sql = " SELECT gid,iid,aid,title,pubdate,price,amount,bow,note,photo,status FROM goods WHERE "
				+ column + " LIKE ? LIMIT ?,? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, super.getKeyWork(keyWord));
		this.ps.setInt(2, (currentPage - 1) * lineSize);
		this.ps.setInt(3, lineSize);
		ResultSet rs = this.ps.executeQuery();
		while (rs.next())
		{
			Goods vo = new Goods();

			vo.setGid(rs.getInt("gid"));

			Item item = new Item();
			item.setIid(rs.getInt("iid"));
			vo.setItem(item);

			vo.setAid(rs.getString("aid"));
			vo.setTitle(rs.getString("title"));
			vo.setPubdate(rs.getDate("pubdate"));
			vo.setPrice(rs.getDouble("price"));
			vo.setAmount(rs.getInt("amount"));
			vo.setBow(rs.getInt("bow"));
			vo.setNote(rs.getString("note"));
			vo.setPhoto(rs.getString("photo"));
			vo.setStatus(rs.getInt("status"));

			allGoods.add(vo);
		}
		return allGoods;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception
	{
		String sql = " SELECT COUNT(*) FROM goods WHERE " + column + " LIKE ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, super.getKeyWork(keyWord));
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
		{
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public List<Goods> findAllSplitByStatus(Integer currentPage, Integer lineSize, String column,
			String keyWord, Integer status) throws Exception
	{
		List<Goods> allGoods = new ArrayList<Goods>();
		String sql = " SELECT gid,iid,aid,title,pubdate,price,amount,bow,note,photo,status FROM goods WHERE status=? AND "
				+ column + " LIKE ? LIMIT ?,? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, status);
		this.ps.setString(2, super.getKeyWork(keyWord));
		this.ps.setInt(3, (currentPage - 1) * lineSize);
		this.ps.setInt(4, lineSize);
		ResultSet rs = this.ps.executeQuery();
		while (rs.next())
		{
			Goods vo = new Goods();

			vo.setGid(rs.getInt("gid"));

			Item item = new Item();
			item.setIid(rs.getInt("iid"));
			vo.setItem(item);

			vo.setAid(rs.getString("aid"));
			vo.setTitle(rs.getString("title"));
			vo.setPubdate(rs.getDate("pubdate"));
			vo.setPrice(rs.getDouble("price"));
			vo.setAmount(rs.getInt("amount"));
			vo.setBow(rs.getInt("bow"));
			vo.setNote(rs.getString("note"));
			vo.setPhoto(rs.getString("photo"));
			vo.setStatus(rs.getInt("status"));

			allGoods.add(vo);
		}
		return allGoods;
	}

	@Override
	public Integer getAllCountByStatus(String column, String keyWord, Integer status) throws Exception
	{
		String sql = " SELECT COUNT(*) FROM goods WHERE status=? AND " + column + " LIKE ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, status);
		this.ps.setString(2, super.getKeyWork(keyWord));
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
		{
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public boolean doUpdateStatus(Set<Integer> ids, Integer status) throws Exception
	{
		if (ids.size() <= 0)
		{
			return false;
		}

		String sql = " UPDATE goods SET status = ? WHERE gid=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, status);
		Iterator<Integer> iter = ids.iterator();
		while (iter.hasNext())
		{
			this.ps.setInt(2, iter.next());
			this.ps.addBatch();
		}
		int[] results = this.ps.executeBatch();
		boolean flag = true;
		for (int item : results)
		{
			if (item == 0)
			{
				flag = false;
				continue;
			}
		}
		return flag;
	}

	@Override
	public List<Goods> findAllByItem(Integer iid, Integer currentPage, Integer lineSize, String column,
			String keyWord, Integer status) throws Exception
	{
		List<Goods> allGoods = new ArrayList<Goods>();
		String sql = " SELECT gid,iid,aid,title,pubdate,price,amount,bow,note,photo,status FROM goods WHERE iid=? AND status=? AND "
				+ column + " LIKE ? LIMIT ?,? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, iid);
		this.ps.setInt(2, status);
		this.ps.setString(3, super.getKeyWork(keyWord));
		this.ps.setInt(4, (currentPage - 1) * lineSize);
		this.ps.setInt(5, lineSize);
		ResultSet rs = this.ps.executeQuery();
		while (rs.next())
		{
			Goods vo = new Goods();

			vo.setGid(rs.getInt("gid"));

			Item item = new Item();
			item.setIid(rs.getInt("iid"));
			vo.setItem(item);

			vo.setAid(rs.getString("aid"));
			vo.setTitle(rs.getString("title"));
			vo.setPubdate(rs.getDate("pubdate"));
			vo.setPrice(rs.getDouble("price"));
			vo.setAmount(rs.getInt("amount"));
			vo.setBow(rs.getInt("bow"));
			vo.setNote(rs.getString("note"));
			vo.setPhoto(rs.getString("photo"));
			vo.setStatus(rs.getInt("status"));

			allGoods.add(vo);
		}
		return allGoods;
	}

	@Override
	public Integer getAllCountByItem(Integer iid, String column, String keyWord, Integer status)
			throws Exception
	{
		String sql = " SELECT COUNT(*) FROM goods WHERE iid=? AND status=? AND " + column + " LIKE ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, iid);
		this.ps.setInt(2, status);
		this.ps.setString(3, super.getKeyWork(keyWord));
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
		{
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public boolean doUpdateBow(Integer gid) throws Exception
	{
		String sql = " UPDATE goods SET bow = bow+1 WHERE gid =? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, gid);
		return this.ps.executeUpdate() > 0 ;
	}

	@Override
	public List<Goods> findAllByIds(Set<Integer> ids) throws Exception
	{
		if(ids.size() <= 0)
			return null;
		List<Goods> allGoods = new ArrayList<Goods>();
		StringBuffer sql = new StringBuffer(" SELECT gid,iid,aid,title,pubdate,price,amount,bow,note,photo,status FROM goods WHERE gid IN( ");
		for(Integer id : ids)
		{
			sql.append(id).append(",");
		}
		sql.delete(sql.length()-1, sql.length());
		sql.append(")");
		this.ps = this.conn.prepareStatement(sql.toString());
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			Goods vo = new Goods();

			vo.setGid(rs.getInt("gid"));

			Item item = new Item();
			item.setIid(rs.getInt("iid"));
			vo.setItem(item);

			vo.setAid(rs.getString("aid"));
			vo.setTitle(rs.getString("title"));
			vo.setPubdate(rs.getDate("pubdate"));
			vo.setPrice(rs.getDouble("price"));
			vo.setAmount(rs.getInt("amount"));
			vo.setBow(rs.getInt("bow"));
			vo.setNote(rs.getString("note"));
			vo.setPhoto(rs.getString("photo"));
			vo.setStatus(rs.getInt("status"));

			allGoods.add(vo);
		}
		return allGoods;
	}

	@Override
	public boolean doUpdateAmount(Integer gid, Integer amount) throws Exception
	{
		String sql = " UPDATE goods SET amount = amount+"+amount+" WHERE gid = ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, gid);
		return this.ps.executeUpdate() == 1;
	}
}
