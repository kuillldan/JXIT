package DAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import utils.CONST;
import vo.Member;
import DAO.AbstractDAOImpl;
import DAO.IMemberDAO;

public class MemberDAOImpl extends AbstractDAOImpl implements IMemberDAO
{

	public MemberDAOImpl(Connection conn)
	{
		super(conn);
	}

	/**
	 * 根据传入vo向数据库插入一条member记录
	 */
	@Override
	public boolean doCreate(Member vo) throws Exception
	{
		String sql = " INSERT INTO member(mid,password,code,regdate,status,photo) VALUES(?,?,?,?,?,?) ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getMid());
		this.ps.setString(2, vo.getPassword());
		this.ps.setString(3, vo.getCode());
		this.ps.setDate(4, new java.sql.Date(vo.getRegdate().getTime()));
		this.ps.setInt(5, vo.getStatus());
		this.ps.setString(6, vo.getPhoto());
		return 1 == this.ps.executeUpdate();
	}

	@Override
	public boolean doUpdate(Member vo) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<String> ids) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 根据ID找到member记录
	 */
	@Override
	public Member findById(String id) throws Exception
	{
		Member vo = null;
		String sql = " SELECT mid,password,name,phone,address,code,status,regdate,photo FROM member WHERE mid =? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, id);
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
		{
			vo = new Member();
			vo.setMid(rs.getString("mid"));
			vo.setPassword(rs.getString("password"));
			vo.setName(rs.getString("name"));
			vo.setPhone(rs.getString("phone"));
			vo.setAddress(rs.getString("address"));
			vo.setCode(rs.getString("code"));
			vo.setStatus(rs.getInt("status"));
			vo.setRegdate(rs.getDate("regdate"));
			vo.setPhoto(rs.getString("photo"));
		}
		return vo;
	}

	@Override
	public List<Member> findAll() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 分页查询member列表
	 */
	@Override
	public List<Member> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception
	{
		List<Member> allMembers = new ArrayList<Member>();
		String sql = " SELECT mid,password,name,phone,address,code,status,regdate,photo FROM member WHERE " + column + " LIKE ? LIMIT ?,? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, super.getKeyWork(keyWord));
		this.ps.setInt(2, (currentPage-1)*lineSize);
		this.ps.setInt(3, lineSize);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			Member vo = new Member();
			vo.setMid(rs.getString("mid"));
			vo.setPassword(rs.getString("password"));
			vo.setName(rs.getString("name"));
			vo.setPhone(rs.getString("phone"));
			vo.setAddress(rs.getString("address"));
			vo.setCode(rs.getString("code"));
			vo.setStatus(rs.getInt("status"));
			vo.setRegdate(rs.getDate("regdate"));
			vo.setPhoto(rs.getString("photo"));
			allMembers.add(vo);
		}
		return allMembers;
	}
	
	@Override
	public List<Member> findAllSplitByStatus(Integer currentPage, Integer lineSize, String column,
			String keyWord, Integer status) throws Exception
	{
		List<Member> allMembers = new ArrayList<Member>();
		String sql = " SELECT mid,password,name,phone,address,code,status,regdate,photo FROM member WHERE status=? AND " + column + " LIKE ? LIMIT ?,? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, status);
		this.ps.setString(2, super.getKeyWork(keyWord));
		this.ps.setInt(3, (currentPage-1)*lineSize);
		this.ps.setInt(4, lineSize);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			Member vo = new Member();
			vo.setMid(rs.getString("mid"));
			vo.setPassword(rs.getString("password"));
			vo.setName(rs.getString("name"));
			vo.setPhone(rs.getString("phone"));
			vo.setAddress(rs.getString("address"));
			vo.setCode(rs.getString("code"));
			vo.setStatus(rs.getInt("status"));
			vo.setRegdate(rs.getDate("regdate"));
			vo.setPhoto(rs.getString("photo"));
			allMembers.add(vo);
		}
		return allMembers;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception
	{
		return super.countHandler("member", column, keyWord);
	}

	@Override
	public boolean findByCode(String mid, String code) throws Exception
	{
		String sql = " SELECT COUNT(*) FROM member WHERE mid = ? AND code = ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, mid);
		this.ps.setString(2, code);

		ResultSet rs = this.ps.executeQuery();

		if (rs.next())
		{ 

			if (rs.getInt(1) == 1)
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public boolean doUpdateStatus(String mid, Integer status) throws Exception
	{
		String sql = " UPDATE member SET status=? WHERE mid=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, status);
		this.ps.setString(2, mid);
		return this.ps.executeUpdate() == 1;
	}
	
	@Override
	public boolean doUpdateStatus(Set<String> ids, Integer status) throws Exception
	{
		if(ids.size() <= 0)
			return false;
		
		StringBuffer sql = new StringBuffer(" UPDATE member SET status=? WHERE mid IN (");
		Iterator<String> iter = ids.iterator();
		while(iter.hasNext())
		{
			sql.append("'").append(iter.next()).append("',");
		}
		sql.delete(sql.length()-1, sql.length());
		sql.append(")");
		this.ps = this.conn.prepareStatement(sql.toString());
		this.ps.setInt(1, status); 
		this.ps.executeUpdate();
		return true;
	}

	@Override
	public boolean findLogin(Member vo) throws Exception
	{
		String sql = " SELECT photo FROM member WHERE mid = ? AND password = ? AND status = "
				+ CONST.MemberStatus.ACTIVED.ordinal();
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getMid());
		this.ps.setString(2, vo.getPassword());
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
		{
			vo.setPhoto(rs.getString("photo"));
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public Integer getAllCountByStatus(String column, String keyWord, Integer status) throws Exception
	{
		String sql = " SELECT COUNT(*) FROM member WHERE status=? AND " + column + " LIKE ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1,status);
		this.ps.setString(2,this.getKeyWork(keyWord));
		ResultSet rs = this.ps.executeQuery();
		if (rs.next())
			return rs.getInt(1);
		
		return 0;
	}

	@Override
	public boolean doUpdateMember(Member vo) throws Exception
	{
		String sql = " UPDATE member SET name=?,phone=?,address=?,photo=? WHERE mid = ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, vo.getName());
		this.ps.setString(2, vo.getPhone());
		this.ps.setString(3, vo.getAddress());
		this.ps.setString(4, vo.getPhoto());
		this.ps.setString(5, vo.getMid());
		return this.ps.executeUpdate() == 1;
	}

	
 
}
