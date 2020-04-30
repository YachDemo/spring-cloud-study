package com.mssql.mapper;

import com.mssql.model.KqInfo;

/**
 * 
 * @author YanCh
 * @version v1.0
 * Create by 2020-04-23 13:11
 **/
public interface KqInfoMapper {
    int deleteByPrimaryKey(String kqId);

    int insert(KqInfo record);

    int insertSelective(KqInfo record);

    KqInfo selectByPrimaryKey(String kqId);

    int updateByPrimaryKeySelective(KqInfo record);

    int updateByPrimaryKey(KqInfo record);
}