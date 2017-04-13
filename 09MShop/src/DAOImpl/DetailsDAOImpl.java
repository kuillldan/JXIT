package DAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import vo.Details;
import vo.Goods;
import vo.Orders;
import DAO.AbstractDAOImpl;
import DAO.IDetailsDAO;

public class DetailsDAOImpl extends AbstractDAOImpl implements IDetailsDAO
{

	public DetailsDAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean doCreate(Details vo) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Details vo) throws Exception
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
	public Details findById(Integer id) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Details> findAll() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Details> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
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
	public boolean doCreateBatch(List<Details> vos) throws Exception
	{
		if(vos.size() <= 0)
			return false;
		
		String sql = " INSERT INTO details(oid,gid,title,price,amount) VALUES(?,?,?,?,?) ";
		this.ps = this.conn.prepareStatement(sql);
		for(Details details : vos)
		{
			this.ps.setInt(1, details.getOrder().getOid());
			this.ps.setInt(2, details.getGoods().getGid());
			this.ps.setString(3, details.getTitle());
			this.ps.setDouble(4, details.getPrice());
			this.ps.setInt(5, details.getAmount());
			
			this.ps.addBatch();
		}
		this.ps.executeBatch();
		return true;
	}

	@Override
	public List<Details> findAllByOrders(Integer oid) throws Exception
	{
		List<Details> allDetails = new ArrayList<Details>();
		String sql = " SELECT odid,oid,gid,title,price,amount FROM details WHERE oid =? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, oid);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			Details vo = new Details();
			vo.setOdid(rs.getInt(1));
			
			Orders orders = new Orders();
			orders.setOid(rs.getInt(2));
			vo.setOrder(orders);
			
			Goods goods = new Goods();
			goods.setGid(rs.getInt(3));
			vo.setGoods(goods);
			
			vo.setTitle(rs.getString(4));
			vo.setPrice(rs.getDouble(5));
			vo.setAmount(rs.getInt(6));
			
			allDetails.add(vo);
		}
		return allDetails;
	}

}
