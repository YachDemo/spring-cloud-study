package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.example.demo.config.DbContextHolder.clearDbType;

/**
 * 
 * @author YanCh
 * Create by 2020-02-26 16:18
 **/
@Aspect
@Component
@Slf4j
@Order(1)
public class DataSourceAop {

    @Pointcut("!@annotation(com.example.demo.annotation.Master) && " +
            "(execution(* com.example.demo.service..*.select*(..)) || " +
            "execution(* com.example.demo.service..*.get*(..)) || " +
            "execution(* com.example.demo.service..*.find*(..)))")
    public void readPointCut(){  }

    @Pointcut("@annotation(com.example.demo.annotation.Master) " +
            "|| execution(* com.example.demo.service..*.insert*(..)) " +
            "|| execution(* com.example.demo.service..*.update*(..))" +
            "|| execution(* com.example.demo.service..*.delete*(..))" +
            "|| execution(* com.baomidou.mybatisplus.extension.service..*.insert*(..))")
    public void writePointcut() {    }

    @Before("readPointCut()")
    public void read(){
        log.info("------switch read-------");
        DbContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write(){
        log.info("------switch write-------");
        DbContextHolder.master();
    }

    @After("readPointCut()")
    public void readRemove(){
        clearDbType();
    }

    @After("writePointcut()")
    public void writeRemove(){
        clearDbType();
    }
}
