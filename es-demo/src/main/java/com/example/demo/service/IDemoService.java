package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Demo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author YanCh
 * Create by 2020-02-26 16:22
 **/
public interface IDemoService extends IService<Demo> {
    String insert(Demo demo);

    void insertFetch(List<Demo> list);

    Page senPage();
}
