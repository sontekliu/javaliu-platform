/**
 * <br> 项目名：work-base
 * <br> 文件名：SnowFlake.java
 * <br> Copyright 2017 Javaliu
 */
package com.javaliu.platform.utils;

/**
 * <br> 类 名：SnowFlake
 * <br> 描 述：雪花算法
 * <br> 作 者：javaliu
 * <br> 创 建：2017年07月31日
 * <br> 版 本：V1.0
 */

/**
 * SnowFlake 的结构如下(每部分使用 "-" 分割)：
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 * 得到的值，这里的的开始时间戳，一般是我们的id生成器开始使用的时间，由我们程序来指定的
 *（如下下面程序 SnowFlake 类的 START_STAMP 属性）。
 * 41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69
 * 10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位mechineId
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号
 * 加起来刚好64位，为一个Long型。
 * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞
 * (由数据中心ID和机器ID作区分)，并且效率较高，经测试，SnowFlake每秒能够产生26万ID左右
 */
public class SnowFlake {
    /**
     * 起始的时间戳
     */
    private static final long START_STAMP = 1480166465631L;

    //数据中心占用的位数
    private static final long DATA_CENTER_BIT = 5;
    //机器标识占用的位数
    private static final long MACHINE_BIT = 5;
    //序列号占用的位数
    private static final long SEQUENCE_BIT = 12;

    // 数据中心的最大值是 31
    private static final long MAX_DATACENTER_NUM = -1L ^ (-1L << DATA_CENTER_BIT);
    // 机器标示的最大值是 31
    private static final long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    // 序列号的最大值是 4095
    private static final long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    // 机器标示左移 12 位
    private static final long MACHINE_LEFT = SEQUENCE_BIT;
    // 数据中心左移 12 + 5 位
    private static final long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    // 时间戳左移 (12 + 5) + 5 位
    private static final long TIMESTMP_LEFT = DATACENTER_LEFT + DATA_CENTER_BIT;

    private long dataCenterId;  //数据中心
    private long machineId;    //机器标识
    private long sequence = 0L; //序列号
    private long lastStamp = -1L;//上一次时间戳

    /**
     * 获取雪花算法实例
     * @return
     */
    public static SnowFlake getInstance(){
        return SnowFlakeHolder.SNOWFLAKE_INSTANCE;
    }

    /**
     * 构造方法私有化
     */
    private SnowFlake(){ }

    /**
     * 构造方法私有化
     * @param dataCenterId
     * @param machineId
     */
    private SnowFlake(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATACENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    private static class SnowFlakeHolder{
        private static final SnowFlake SNOWFLAKE_INSTANCE = new SnowFlake(1, 1);
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStamp = getNewTimeMillis();
        if (currStamp < lastStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStamp == lastStamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大,即此时在同一毫秒内产生了4096个sequence
            // 4096 & 4095 == 0L
            if (sequence == 0L) {
                currStamp = getNextMill();
            }
        } else {
            // 不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStamp = currStamp;

        return (currStamp - START_STAMP) << TIMESTMP_LEFT //时间戳部分
                | dataCenterId << DATACENTER_LEFT      //数据中心部分
                | machineId << MACHINE_LEFT            //机器标识部分
                | sequence;                            //序列号部分
    }

    /**
     * 得到下一个毫秒数
     * @return
     */
    private long getNextMill() {
        long mill = getNewTimeMillis();
        while (mill <= lastStamp) {
            mill = getNewTimeMillis();
        }
        return mill;
    }

    /**
     * 产生一个新的时间戳
     * @return
     */
    private long getNewTimeMillis() {
        return System.currentTimeMillis();
    }
}
