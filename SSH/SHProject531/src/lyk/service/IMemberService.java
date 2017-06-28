package lyk.service;

import lyk.vo.MemberLogin;

public interface IMemberService
{
	public boolean insert(MemberLogin login) throws Exception;
}
