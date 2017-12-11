package org.lyk.dao;

import java.util.List;

import org.lyk.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDAO extends JpaRepository<Member,String>
{
	//@Query("select m FROM Member m WHERE m.name=:name")
	public List<Member> findByName(String name);
	
	public List<Member> findByNameLike(String name);
	
	
	public List<Member> listByCondition();
}
