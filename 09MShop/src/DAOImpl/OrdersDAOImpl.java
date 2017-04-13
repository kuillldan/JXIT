package DAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import vo.Orders;
import DAO.AbstractDAOImpl;
import DAO.IOrdersDAO;

public class OrdersDAOImpl extends AbstractDAOImpl implements IOrdersDAO
{

	public OrdersDAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean doCreate(Orders vo) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Orders vo) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Orders findById(Integer id) throws Exception
	{
		 
		return null;
	}

	@Override
	public List<Orders> findAll() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
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
	public Integer findLastInsertId() throws Exception
	{
		String sql = " SELECT  LAST_INSERT_ID() ";
		this.ps = this.conn.prepareStatement(sql);
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
		{
			return rs.getInt(1);
		}

		return null;
	}

	@Override
	public boolean doCreateOrders(Orders vo) throws Exception
	{
		String sql = " INSERT INTO orders(mid,name,phone,address,credate,pay) VALUES(?,?,?,?,?,?) ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getMid());
		this.ps.setString(2, vo.getName());
		this.ps.setString(3, vo.getPhone());
		this.ps.setString(4, vo.getAddress());
		this.ps.setDate(5, new java.sql.Date(vo.getCredate().getTime()));
		this.ps.setDouble(6, vo.getPay());
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public List<Orders> findAllByMember(String mid, Integer currentPage, Integer lineSize) throws Exception
	{
		List<Orders> allOrders = new ArrayList<Orders>();
		String sql = " SELECT oid,mid,name,phone,address,credate,pay FROM orders WHERE mid=? LIMIT ?,? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, mid);
		this.ps.setInt(2, (currentPage - 1) * lineSize);
		this.ps.setInt(3, lineSize);
		ResultSet rs = this.ps.executeQuery();
		while (rs.next())
		{
			Orders vo = new Orders();
			vo.setOid(rs.getInt(1));
			vo.setMid(mid);
			vo.setName(rs.getString(3));
			vo.setPhone(rs.getString(4));
			vo.setAddress(rs.getString(5));
			vo.setCredate(rs.getDate(6));
			vo.setPay(rs.getDouble(7));
			allOrders.add(vo);
		}
		return allOrders;
	}

	@Override
	public Integer getAllCountByMember(String mid) throws Exception
	{
		String sql = " SELECT COUNT(*) FROM orders WHERE mid = ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, mid);
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
		{
			return rs.getInt(1);
		}

		return 0;
	}

	@Override
	public Orders findByIdAndMember(String mid, Integer oid) throws Exception
	{
		Orders vo = null;
		String sql = " SELECT oid,mid,name,phone,address,credate,pay FROM orders WHERE mid=? AND oid=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, mid);
		this.ps.setInt(2, oid);
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
		{
			vo = new Orders();
			vo.setOid(rs.getInt(1));
			vo.setMid(mid);
			vo.setName(rs.getString(3));
			vo.setPhone(rs.getString(4));
			vo.setAddress(rs.getString(5));
			vo.setCredate(rs.getDate(6));
			vo.setPay(rs.getDouble(7));
		}
		return vo;
	}

}
