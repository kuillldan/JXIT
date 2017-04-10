package service.front;

import vo.Member;

public interface IMemberServiceFront
{
	/**
	 * 实现用户在前台注册功能
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean regist(Member vo) throws Exception;
	
	public boolean active(String mid, String code) throws Exception;
	
	public boolean login(Member vo) throws Exception;
}
