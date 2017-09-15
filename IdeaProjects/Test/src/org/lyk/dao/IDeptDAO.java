package org.lyk.dao;

import org.lyk.vo.Dept;

import java.util.List;

/**
 * Created by liuyuank on 9/15/2017.
 */
public interface IDeptDAO
{
    public boolean doCreate(Dept dept) throws Exception;
    public List<Dept> findAll() throws Exception;
}
