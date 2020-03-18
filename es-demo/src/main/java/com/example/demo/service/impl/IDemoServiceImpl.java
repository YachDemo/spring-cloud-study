package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.annotation.Master;
import com.example.demo.entity.Demo;
import com.example.demo.mapper.DemoMapper;
import com.example.demo.service.IDemoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 * @author YanCh
 * Create by 2020-02-26 16:22
 **/
@Service
public class IDemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements IDemoService {

    @Override
    @Transactional(rollbackFor = Exception.class,value = "transactionManager")
    public String insert(Demo demo) {
        baseMapper.insert(demo);
//        int i = 1/0;
//        System.out.println(i);
        Demo demo1 = new Demo();
        demo1.setId(123333L);
        demo1.setUsername("sadsadsa");
        baseMapper.updateById(demo1);
        return "";
    }

    @Override
    public void insertFetch(List<Demo> list) {
        baseMapper.insertFetch(list);
    }

    @Override
    @Master
    public Page senPage() {

        Page page = new Page(1,10);
        QueryWrapper wrapper = new QueryWrapper<>()
                .in("id","123333","549900868355497992");
        return baseMapper.selectPage(page,wrapper);
    }

}
