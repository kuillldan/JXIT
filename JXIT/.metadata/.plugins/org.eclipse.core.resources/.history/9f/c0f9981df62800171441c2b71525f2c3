package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

import utils.General;
import vo.Admin;
import vo.Dept;
import vo.Employee;
import vo.Jobs;
import vo.Level;
import dao.AbstractDAOImpl;
import dao.IEmployeeDAO;
import enums.EmployeeStatus;

public class EmployeeDAOImpl extends AbstractDAOImpl implements IEmployeeDAO
{

	public EmployeeDAOImpl(Connection conn)
	{
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean doCreate(Employee vo) throws SQLException
	{
		String sql = " INSERT INTO employee(aid,did,levid,jid,ename,birthday,sex,idcard,dname,job,school,profession,grad,photo,indate,outdate,status,note,edu,sal) "
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		this.ps = this.conn.prepareStatement(sql); 
		this.ps.setString(1, vo.getAdmin().getAid());
		this.ps.setInt(2, vo.getDept().getDid());
		this.ps.setInt(3, vo.getLevel().getLevid());
		this.ps.setInt(4, vo.getJobs().getJid());
		this.ps.setString(5, vo.getEname());
		this.ps.setTimestamp(6, General.getSqlDate(vo.getBirthday()));
		this.ps.setString(7, vo.getSex());
		this.ps.setString(8, vo.getIdcard());
		this.ps.setString(9, vo.getDname());
		this.ps.setString(10, vo.getJob());
		this.ps.setString(11, vo.getSchool());
		this.ps.setString(12, vo.getProfession());
		this.ps.setTimestamp(13, General.getSqlDate(vo.getGrad()));
		this.ps.setString(14, vo.getPhoto());
		this.ps.setTimestamp(15, General.getSqlDate(vo.getIndate()));
		this.ps.setNull(16, Types.NULL);
		this.ps.setInt(17, vo.getStatus());
		this.ps.setString(18, vo.getNote());
		this.ps.setString(19, vo.getEdu());
		this.ps.setDouble(20, vo.getSal());
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doUpdate(Employee vo) throws SQLException
	{
		String sql = " UPDATE employee SET did=?,levid=?,jid=?,ename=?,birthday=?, "
				+ " sex=?,idcard=?,dname=?,job=?,school=?,profession=?,grad=?,photo=?, "
				+ " indate=?,outdate=?,status=?,note=?,edu=?,sal=? WHERE eid=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, vo.getDept().getDid());
		this.ps.setInt(2, vo.getLevel().getLevid());
		this.ps.setInt(3, vo.getJobs().getJid());
		this.ps.setString(4, vo.getEname());
		this.ps.setTimestamp(5, General.getSqlDate(vo.getBirthday()));
		this.ps.setString(6, vo.getSex());
		this.ps.setString(7, vo.getIdcard());
		this.ps.setString(8, vo.getDname());
		this.ps.setString(9, vo.getJob());
		this.ps.setString(10, vo.getSchool());
		this.ps.setString(11, vo.getProfession());
		this.ps.setTimestamp(12, General.getSqlDate(vo.getGrad()));
		this.ps.setString(13, vo.getPhoto());
		this.ps.setTimestamp(14, General.getSqlDate(vo.getIndate()));
		this.ps.setTimestamp(15, General.getSqlDate(vo.getOutdate()));
		this.ps.setInt(16, vo.getStatus());
		this.ps.setString(17, vo.getNote());
		this.ps.setString(18, vo.getEdu());
		this.ps.setDouble(19, vo.getSal());
		this.ps.setInt(20, vo.getEid());
		return this.ps.executeUpdate() == 1;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee findById(Integer id) throws SQLException
	{
		Employee vo = null;
		String sql = " SELECT eid,aid,did,levid,jid,ename,birthday,sex, "
				+ " idcard,dname,job,school,profession,grad,photo,indate, "
				+ " outdate,status,note,edu,sal FROM employee WHERE eid=? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, id);
		ResultSet rs = this.ps.executeQuery();
		if(rs.next())
		{
			vo = new Employee();
			vo.setEid(rs.getInt("eid"));
			
			Admin admin = new Admin();
			admin.setAid(rs.getString("aid"));
			vo.setAdmin(admin);
			
			Dept dept = new Dept();
			dept.setDid(rs.getInt("did"));
			vo.setDept(dept);
			
			Level level = new Level();
			level.setLevid(rs.getInt("levid"));
			vo.setLevel(level);
			
			Jobs jobs = new Jobs();
			jobs.setJid(rs.getInt("jid"));
			vo.setJobs(jobs);
			
			vo.setEname(rs.getString("ename"));
			vo.setBirthday(rs.getTimestamp("birthday"));
			vo.setSex(rs.getString("sex"));
			vo.setIdcard(rs.getString("idcard"));
			vo.setDname(rs.getString("dname"));
			vo.setJob(rs.getString("job"));
			vo.setSchool(rs.getString("job"));
			vo.setProfession(rs.getString("profession"));
			vo.setGrad(rs.getTimestamp("grad"));
			vo.setPhoto(rs.getString("photo"));
			vo.setIndate(rs.getTimestamp("indate"));
			vo.setOutdate(rs.getTimestamp("outdate"));
			vo.setStatus(rs.getInt("status"));
			vo.setNote(rs.getString("note"));
			vo.setEdu(rs.getString("edu"));
			vo.setSal(rs.getDouble("sal"));
		}
		return vo;
	}

	@Override
	public List<Employee> findAll() throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws SQLException
	{
		List<Employee> allEmployees = new ArrayList<Employee>();
		
		String sql = " SELECT eid,aid,did,levid,jid,ename,birthday,sex, "
				+ " idcard,dname,job,school,profession,grad,photo,indate, "
				+ " outdate,status,note,edu,sal FROM employee WHERE " + column +" LIKE ? LIMIT ?,? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, super.getKeyWork(keyWord));
		super.setLimit(ps, keyWord, currentPage, lineSize, 2, 3);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			Employee vo = new Employee();
			vo = new Employee();
			vo.setEid(rs.getInt("eid"));
			
			Admin admin = new Admin();
			admin.setAid(rs.getString("aid"));
			vo.setAdmin(admin);
			
			Dept dept = new Dept();
			dept.setDid(rs.getInt("did"));
			vo.setDept(dept);
			
			Level level = new Level();
			level.setLevid(rs.getInt("levid"));
			vo.setLevel(level);
			
			Jobs jobs = new Jobs();
			jobs.setJid(rs.getInt("jid"));
			vo.setJobs(jobs);
			
			vo.setEname(rs.getString("ename"));
			vo.setBirthday(rs.getTimestamp("birthday"));
			vo.setSex(rs.getString("sex"));
			vo.setIdcard(rs.getString("idcard"));
			vo.setDname(rs.getString("dname"));
			vo.setJob(rs.getString("job"));
			vo.setSchool(rs.getString("job"));
			vo.setProfession(rs.getString("profession"));
			vo.setGrad(rs.getTimestamp("grad"));
			vo.setPhoto(rs.getString("photo"));
			vo.setIndate(rs.getTimestamp("indate"));
			vo.setOutdate(rs.getTimestamp("outdate"));
			vo.setStatus(rs.getInt("status"));
			vo.setNote(rs.getString("note"));
			vo.setEdu(rs.getString("edu"));
			vo.setSal(rs.getDouble("sal"));
			allEmployees.add(vo);
		}
		return allEmployees;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws SQLException
	{
		return super.countHandler("employee", column, keyWord);
	}

	@Override
	public boolean doUpdateStatus(List<Integer> ids, Date outdate) throws Exception
	{
		StringBuffer sql = new StringBuffer(" UPDATE employee SET status=?,outdate=? WHERE eid IN( ");
		for(Integer id : ids)
		{
			sql.append(id).append(",");
		}
		sql.delete(sql.length()-1, sql.length());
		sql.append(")");
		
		this.ps = this.conn.prepareStatement(sql.toString());
		this.ps.setInt(1, EmployeeStatus.OUT.ordinal());
		this.ps.setTimestamp(2, General.getSqlDate(outdate));
		this.ps.executeUpdate();
		
		return true;
	}

	@Override
	public List<Employee> findAllSplitByStatus(Integer currentPage, Integer lineSize, String column, String keyWord,
			Integer status) throws SQLException
	{
		List<Employee> allEmployees = new ArrayList<Employee>();
		
		String sql = " SELECT eid,aid,did,levid,jid,ename,birthday,sex, "
				+ " idcard,dname,job,school,profession,grad,photo,indate, "
				+ " outdate,status,note,edu,sal FROM employee WHERE status=? AND " + column +" LIKE ? LIMIT ?,? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, status);
		this.ps.setString(2, super.getKeyWork(keyWord));
		super.setLimit(ps, keyWord, currentPage, lineSize, 3, 4);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next())
		{
			Employee vo = new Employee();
			vo = new Employee();
			vo.setEid(rs.getInt("eid"));
			
			Admin admin = new Admin();
			admin.setAid(rs.getString("aid"));
			vo.setAdmin(admin);
			
			Dept dept = new Dept();
			dept.setDid(rs.getInt("did"));
			vo.setDept(dept);
			
			Level level = new Level();
			level.setLevid(rs.getInt("levid"));
			vo.setLevel(level);
			
			Jobs jobs = new Jobs();
			jobs.setJid(rs.getInt("jid"));
			vo.setJobs(jobs);
			
			vo.setEname(rs.getString("ename"));
			vo.setBirthday(rs.getTimestamp("birthday"));
			vo.setSex(rs.getString("sex"));
			vo.setIdcard(rs.getString("idcard"));
			vo.setDname(rs.getString("dname"));
			vo.setJob(rs.getString("job"));
			vo.setSchool(rs.getString("job"));
			vo.setProfession(rs.getString("profession"));
			vo.setGrad(rs.getTimestamp("grad"));
			vo.setPhoto(rs.getString("photo"));
			vo.setIndate(rs.getTimestamp("indate"));
			vo.setOutdate(rs.getTimestamp("outdate"));
			vo.setStatus(rs.getInt("status"));
			vo.setNote(rs.getString("note"));
			vo.setEdu(rs.getString("edu"));
			vo.setSal(rs.getDouble("sal"));
			allEmployees.add(vo);
		}
		return allEmployees;
	}

	@Override
	public Integer getAllCountByStatus(String column, String keyWord, Integer status) throws Exception
	{
		String sql = " SELECT COUNT(*) FROM emplyee WHERE status=? AND " + column + " LIKE ? ";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, status);
		this.ps.setString(2, super.getKeyWork(keyWord));
		ResultSet rs = this.ps.executeQuery();
		if(rs.next())
		{
			return rs.getInt(1);
		}
		return null;
	}

}
