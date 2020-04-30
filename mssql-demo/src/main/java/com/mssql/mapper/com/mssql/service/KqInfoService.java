package com.mssql.mapper.com.mssql.service;

import com.mssql.model.KqInfo;

/**
 * @author YanCh
 * @version v1.0
 * Create by 2020-04-23 13:12
 **/
public interface KqInfoService {


    int deleteByPrimaryKey(String kqId);

    int insert(KqInfo record);

    int insertSelective(KqInfo record);

    KqInfo selectByPrimaryKey(String kqId);

    int updateByPrimaryKeySelective(KqInfo record);

    int updateByPrimaryKey(KqInfo record);

}

