package org.lyk.dao.impl;

import org.lyk.dao.IDeptDAO;
import org.lyk.vo.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liuyuank on 9/15/2017.
 */
@Repository("deptDAOImpl")
public class DeptDAOImpl implements IDeptDAO
{
    @Override
    public boolean doCreate(Dept dept) throws Exception
    {
        System.out.println("[数据层]向数据库插入数据:" + dept);
        return false;
    }

    @Override
    public List<Dept> findAll() throws Exception
    {
        System.out.println("[数据层]从数据库查询数据.");
        return null;
    }
}
