package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Demo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YanCh
 * Create by 2020-02-26 16:29
 **/
@Mapper
public interface DemoMapper extends BaseMapper<Demo> {
    int insertFetch(List<Demo> list);
}
