package service.back;

import java.util.List;
import java.util.Set;

import vo.Level;

public interface ILevelServiceBack
{
	public boolean insert(Level level)throws Exception;
	public boolean delete(Set<Integer> ids) throws Exception;
	public List<Level> list()throws Exception;
	public boolean update(Level level) throws Exception;
}
