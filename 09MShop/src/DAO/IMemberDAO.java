package DAO;
 

import java.util.List;
import java.util.Set;

import vo.Member;

public interface IMemberDAO extends IDAO<String, Member>
{
	/**
	 * 通过mid以及code查询对应的member账号
	 * @param mid
	 * @param code
	 * @return
	 */
	public boolean findByCode(String mid, String code) throws Exception;
	
	/**
	 * 用户mid以及提供的code与数据库中的code相匹配，则将该用于status更为激活状态
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateStatus(String mid,Integer status)throws Exception;
	
	/**
	 * 批量更新status
	 * @param ids
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateStatus(Set<String> ids,Integer status)throws Exception;
	
	public boolean findLogin(Member vo) throws Exception;
	
	public List<Member> findAllSplitByStatus(Integer currentPage, Integer lineSize, String column, String keyWord, Integer status)throws Exception;
	public Integer getAllCountByStatus(String column, String keyWord, Integer status)throws Exception;
	
	/**
	 * 更新用户的信息: 真实姓名、电话、地址、照片
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateMember(Member vo) throws Exception;
	
	
}