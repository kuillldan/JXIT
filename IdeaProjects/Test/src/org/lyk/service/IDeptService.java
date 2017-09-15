package org.lyk.service;

import org.lyk.vo.Dept;

import java.util.List;

/**
 * Created by liuyuank on 9/15/2017.
 */
public interface IDeptService
{
    public boolean insert(Dept dept) throws Exception;
    public List<Dept> findAll() throws Exception;
}
