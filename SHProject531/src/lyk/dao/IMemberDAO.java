package lyk.dao;

import lyk.vo.MemberLogin;

public interface IMemberDAO
{
	public boolean doCreate(MemberLogin login) throws Exception ;
}
