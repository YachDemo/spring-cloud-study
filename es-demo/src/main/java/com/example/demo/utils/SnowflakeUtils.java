package com.example.demo.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 雪花算法生成id
 * @author YanCh
 * Create by 2019-12-27 14:31
 **/
public class SnowflakeUtils {
    /**
     * 每一部分所占位数
     */
    private final long unusedBits = 1L;
    private final long timestampBits = 41L;
    private final long dataCenterIdBits = 5L;
    private final long workerIdBits = 5L;
    private final long sequenceBits = 12L;

    private final long dataCenterIdShift;

    /**
     * 数据中心编码，初始化后不可修改
     * 最大值: 2^5-1 取值范围: [0,31]
     */
    private final long dataCenterId;

    /**
     * 机器或进程编码，初始化后不可修改
     * 最大值: 2^5-1 取值范围: [0,31]
     */
    private final long workerId;

    /**
     * 序列号
     * 最大值: 2^12-1 取值范围: [0,4095]
     */
    private long sequence = 0L;

    /** 上次执行生成 ID 方法的时间戳 */
    private long lastTimestamp = -1L;

    /**
     * 生成序列号
     */
    public synchronized long nextId() {
        long currTimestamp = timestampGen();

        if (currTimestamp < lastTimestamp) {
            throw new IllegalStateException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                            lastTimestamp - currTimestamp));
        }

        if (currTimestamp == lastTimestamp) {
            // 2^12-1
            long maxSequence = -1L ^ (-1L << sequenceBits);
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) { // overflow: greater than max sequence
                currTimestamp = waitNextMillis(currTimestamp);
            }

        } else { // reset to 0 for next period/millisecond
            sequence = 0L;
        }

        // track and memo the time stamp last snowflake ID generated
        lastTimestamp = currTimestamp;

        /**
         * 向左的位移
         */
        long timestampShift = sequenceBits + dataCenterIdBits + workerIdBits;
        /**
         * 起始时间戳，初始化后不可修改
         */
        // 2016-01-01
        long epoch = 1451606400000L;
        return ((currTimestamp - epoch) << timestampShift) | //
                (dataCenterId << dataCenterIdShift) | //
                (workerId << sequenceBits) | // new line for nice looking
                sequence;
    }

    /**
     *
     * @param dataCenterId 数据中心编码，初始化后不可修改,最大值: 2^5-1 取值范围: [0,31]
     * @param workerId 机器或进程编码，初始化后不可修改，最大值: 2^5-1 取值范围: [0,31]
     */
    public SnowflakeUtils(long dataCenterId, long workerId) {
        /*
         * 每一部分最大值
         */
        // 2^5-1
        long maxDataCenterId = ~(-1L << dataCenterIdBits);
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("dataCenter Id can't be greater than %d or less than 0", maxDataCenterId));
        }
        // 2^5-1
        long maxWorkerId = ~(-1L << workerIdBits);
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }

        this.dataCenterId = dataCenterId;
        this.workerId = workerId;
        dataCenterIdShift = sequenceBits + workerIdBits;
    }

    /**
     * 追踪调用 waitNextMillis 方法的次数
     */
    private final AtomicLong waitCount = new AtomicLong(0);

    public long getWaitCount() {
        return waitCount.get();
    }

    /**
     * 循环阻塞直到下一秒
     */
    protected long waitNextMillis(long currTimestamp) {
        waitCount.incrementAndGet();
        while (currTimestamp <= lastTimestamp) {
            currTimestamp = timestampGen();
        }
        return currTimestamp;
    }

    /**
     * 获取当前时间戳
     */
    public long timestampGen() {
        return System.currentTimeMillis();
    }
}
