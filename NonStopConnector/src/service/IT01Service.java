package service;

import vo.*;
import java.util.List;
 

public interface IT01Service
{
	public List<T01> list() throws Exception;
	
	public boolean insert() throws Exception;
}
