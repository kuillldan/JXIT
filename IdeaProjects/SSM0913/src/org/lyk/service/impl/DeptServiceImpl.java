package org.lyk.service.impl;

import org.lyk.dao.IDeptDAO;
import org.lyk.service.IDeptService;
import org.lyk.vo.Dept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuyuank on 9/13/2017.
 */
@Service("deptServiceImpl")
public class DeptServiceImpl implements IDeptService
{
    private static final Logger logger = LoggerFactory.getLogger("logfile");

    @Resource(name = "deptDAOImpl")
    private IDeptDAO deptDAO;

    @Override
    public boolean insert(Dept dept) throws Exception
    {
        logger.info("[业务层] 调用数据层插入数据");
        return this.deptDAO.doCreate(dept);
    }

    @Override
    public List<Dept> findAll() throws Exception
    {
        logger.info("[业务层] 调用数据层查询所有数据");
        return this.deptDAO.findAll();
    }
}
