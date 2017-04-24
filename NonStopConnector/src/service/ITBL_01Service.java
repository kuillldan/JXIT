package service;

import vo.*;
import java.util.List;
 

public interface ITBL_01Service
{
	public List<TBL_01> list() throws Exception;
	
	public boolean insert() throws Exception;
}
