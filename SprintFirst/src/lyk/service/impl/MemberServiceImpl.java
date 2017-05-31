package lyk.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lyk.dao.IMemberDAO;
import lyk.dao.impl.MemberDAOImpl;
import lyk.service.IMemberService;
import lyk.vo.Member;


@Service
public class MemberServiceImpl implements IMemberService
{
	@Resource
	private IMemberDAO memberDAOImpl;
	
	@Override
	public boolean insert(Member vo) throws Exception
	{
		return this.memberDAOImpl.doCreate();
	}

}
