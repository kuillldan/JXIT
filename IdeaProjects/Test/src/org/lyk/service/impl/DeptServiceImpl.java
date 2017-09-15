package org.lyk.service.impl;

import org.lyk.dao.IDeptDAO;
import org.lyk.dao.impl.DeptDAOImpl;
import org.lyk.service.IDeptService;
import org.lyk.vo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuyuank on 9/15/2017.
 */
@Service("deptServiceImpl")
public class DeptServiceImpl implements IDeptService
{
    private IDeptDAO deptDAOImpl = new DeptDAOImpl();
    @Override
    public boolean insert(Dept dept) throws Exception
    {
        System.out.println("******" + (this.deptDAOImpl == null));
        return this.deptDAOImpl.doCreate(dept) ;
    }

    @Override
    public List<Dept> findAll() throws Exception
    {
        return this.deptDAOImpl.findAll();
    }
}
