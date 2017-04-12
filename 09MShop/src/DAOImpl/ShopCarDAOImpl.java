package DAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vo.Admin;
import vo.Goods;
import vo.Member;
import vo.ShopCar;
import DAO.AbstractDAOImpl;
import DAO.IShopCarDAO;

public class ShopCarDAOImpl extends AbstractDAOImpl implements IShopCarDAO
{

	public ShopCarDAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean doCreate(ShopCar vo) throws Exception
	{
		String sql = " INSERT INTO shopCar (mid,gid,amount) VALUES(?,?,1) ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getMember().getMid());
		this.ps.setInt(2, vo.getGoods().getGid()); 
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doUpdate(ShopCar vo) throws Exception
	{
		String sql = " UPDATE shopCar SET amount=? WHERE mid=? AND gid=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, vo.getAmount());
		this.ps.setString(2, vo.getMember().getMid());
		this.ps.setInt(3, vo.getGoods().getGid());
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ShopCar findById(Integer id) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShopCar> findAll() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShopCar> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
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

	@Override
	public boolean doUpdateAmount(String mid, Integer gid) throws Exception
	{
		String sql = " UPDATE shopCar SET amount=amount+1 WHERE mid = ? AND gid = ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, mid);
		this.ps.setInt(2, gid);
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public ShopCar findByMemberAndGoods(String mid, Integer gid) throws Exception
	{
		ShopCar vo = null;
		String sql = " SELECT mid,gid,amount FROM shopCar WHERE mid=? AND gid=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, mid);
		this.ps.setInt(2, gid);
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
		{
			vo = new ShopCar();
			Member member = new Member();
			member.setMid(mid);
			vo.setMember(member);

			Goods goods = new Goods();
			goods.setGid(gid);
			vo.setGoods(goods);

			vo.setAmount(rs.getInt("amount"));

		}
		return vo;
	}

	@Override
	public boolean doRemoveByMember(String mid) throws Exception
	{
		String sql = " DELETE FROM shopCar WHERE mid=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, mid);
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doCreateBatch(String mid, Set<Integer> gids) throws Exception
	{
		if (gids.size() <= 0)
			return false;

		String sql = " INSERT INTO shopCar (mid,gid,amount) VALUES (?,?,?) ";
		this.ps = this.conn.prepareStatement(sql);
		for (Integer gid : gids)
		{
			this.ps.setString(1, mid);
			this.ps.setInt(2, gid);
			this.ps.setInt(3, 1);
			this.ps.addBatch();
		}

		int[] results = this.ps.executeBatch();
		boolean flag = true;
		for (int result : results)
		{
			if (result <= 0)
			{
				flag = false;
				break;
			}
		}

		return flag;
	}

	@Override
	public boolean doRemoveByMemberAndGoods(String mid, Set<Integer> gids) throws Exception
	{
		if(gids.size() <= 0)
			return false;
		
		StringBuffer sql = new StringBuffer(" DELETE from shopCar WHERE mid =? AND gid IN( ");
		for(Integer gid : gids)
		{
			sql.append(gid).append(",");
		}
		sql.delete(sql.length()-1, sql.length());
		sql.append(" ); ");
		this.ps = this.conn.prepareStatement(sql.toString());
		this.ps.setString(1, mid);
		return this.ps.executeUpdate() == gids.size();
	}

	@Override
	public Map<Integer, Integer> findAllByMember(String mid) throws Exception
	{
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		String sql = " SELECT mid,gid,amount FROM shopCar WHERE mid=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, mid);
		
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			map.put(rs.getInt("gid"), rs.getInt("amount"));
		}
		return map;
	}

}
