package com.mssql.com.mssql.service.impl;

import com.mssql.mapper.com.mssql.service.KqInfoService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.mssql.mapper.KqInfoMapper;
import com.mssql.model.KqInfo;

/**
 * @author YanCh
 * @version v1.0
 * Create by 2020-04-23 13:12
 **/
@Service
public class KqInfoServiceImpl implements KqInfoService {

    @Resource
    private KqInfoMapper kqInfoMapper;

    @Override
    public int deleteByPrimaryKey(String kqId) {
        return kqInfoMapper.deleteByPrimaryKey(kqId);
    }

    @Override
    public int insert(KqInfo record) {
        return kqInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(KqInfo record) {
        return kqInfoMapper.insertSelective(record);
    }

    @Override
    public KqInfo selectByPrimaryKey(String kqId) {
        return kqInfoMapper.selectByPrimaryKey(kqId);
    }

    @Override
    public int updateByPrimaryKeySelective(KqInfo record) {
        return kqInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(KqInfo record) {
        return kqInfoMapper.updateByPrimaryKey(record);
    }

}

