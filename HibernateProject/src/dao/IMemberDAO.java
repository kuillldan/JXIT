package dao;

import vo.Member;

public interface IMemberDAO
{
	public boolean doCreate(Member vo) throws Exception;
}
