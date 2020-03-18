package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程安全的数据源切换
 * @author YanCh
 * Create by 2020-02-26 16:08
 **/
@Slf4j
public class DbContextHolder {

    private static final ThreadLocal<DBTypeEnum> CONTEXT_HOLDER = new ThreadLocal<>();

    private static final AtomicInteger COUNTER = new AtomicInteger(-1);

    public static void set(DBTypeEnum dbType) {
        CONTEXT_HOLDER.set(dbType);
    }

    public static DBTypeEnum get() {
        return CONTEXT_HOLDER.get();
    }

    static void master() {
        set(DBTypeEnum.MASTER);
        System.out.println("切换到master");
    }

    static void slave() {
        //  轮询
        int index = COUNTER.getAndIncrement() % 2;
        if (COUNTER.get() > 9999) {
            COUNTER.set(-1);
        }
        set(DBTypeEnum.SLAVE1);
        log.info(index +"->切换到slave1");
    }

    /**
     * 清除上下文数据
     */
    static void clearDbType() {
        CONTEXT_HOLDER.remove();
    }
}
