package service.back;

import java.util.Map;
import java.util.Set;

import vo.Member;

public interface IMemberServiceBack
{
	/**
	 * 分页查询member数据。以及总的数据量
	 * allMembers
	 * allCount
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> list(Integer currentPage, Integer lineSize, String column, String keyWord)throws Exception;
	
	public Map<String,Object> listByStatus(Integer currentPage, Integer lineSize, String column, String keyWord,Integer status)throws Exception;
	
	
	public boolean activeMembers(Set<String> ids) throws Exception;
	public boolean disableMembers(Set<String> ids) throws Exception;
	
	/**
	 * 查看mid对应人员的详细信息
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public Member show(String mid) throws Exception; 
}
