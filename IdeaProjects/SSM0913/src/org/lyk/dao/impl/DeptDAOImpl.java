package org.lyk.dao.impl;

import org.lyk.dao.IDeptDAO;
import org.lyk.vo.Dept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liuyuank on 9/13/2017.
 */
@Repository("deptDAOImpl")
public class DeptDAOImpl implements IDeptDAO
{
    private static Logger logger = LoggerFactory.getLogger("logfile");
    @Override
    public boolean doCreate(Dept dept) throws Exception
    {
        logger.info("[数据层]向数据库插入数据 " + dept );
        return true;
    }

    @Override
    public List<Dept> findAll() throws Exception
    {
        logger.info("[数据层]从数据库检索数据 ");
        return null;
    }
}
