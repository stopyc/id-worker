package shop.stopyc.strategy.support;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import shop.stopyc.strategy.AbstractIdWorker;
import shop.stopyc.strategy.IdGenType.IdStrategyType;


/**
 * @program: id-worker
 * @description: 雪花算法
 * @author: stop.yc
 * @create: 2023-07-15 09:36
 **/
@Component
public class Snowflake extends AbstractIdWorker {

    /**
     * 开始时间戳(41bit)
     * 2023-07-15 09:59:53
     */
    private final long startTime = 1689386393000L;

    /**
     * 5bit
     * 机器id所占的位数
     */
    private final long workerIdBits = 5L;

    /**
     * 5bit
     * 数据标识id所占的位数
     */
    private final long datacenterIdBits = 5L;

    /**
     * 12bit
     * 支持的最大序列号位数
     */
    private final long sequenceBits = 12L;

    /**
     * 这个是二进制运算，就是5bit最大能表示的十进制数为32,所以可以指定0~31
     * 最大机器id
     */
    private final long maxWorkerId = ~(-1L << workerIdBits);

    /**
     * 最大数据标识id
     * 0~31
     */
    private final long maxDatacenterId = ~(-1L << datacenterIdBits);

    /**
     * 自增序列号最大到4095
     */
    private final long sequenceMask = ~(-1L << sequenceBits);

    /**
     * 时间截向左移22位
     */
    private final long timestampLeftShift = sequenceBits
                                            + workerIdBits
                                            + datacenterIdBits;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private final long dataCenterIdShift = sequenceBits + workerIdBits;

    /**
     * 机器id向左移12位
     */
    private final long workerIdShift = sequenceBits;

    @Value("${worker-id}")
    private long workerId;

    @Value("${datacenter-id}")
    private long dataCenterId;

    /**
     * 一毫秒内序号(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成id的时间戳
     */
    private long lastTimestamp = -1L;

    private long lastTimestampMaxSequence = 0L;


    @Override
    public long nextId() {

        long now = now();

        //TODO:时钟回拨问题
        if (now < lastTimestamp) {
            long offset = lastTimestamp - now;
            if (offset < 5) {
                try {
                    wait(offset << 1);
                    now = now();
                    if (now < lastTimestamp) {
                        throw new RuntimeException("时间戳异常");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new RuntimeException("时间戳异常");
            }
        }

        if (now == lastTimestamp) {
            //与掩码相与，保证序列号范围在0~4095
            sequence = (sequence + 1) & sequenceMask;
            //如果溢出了(一般很难,因为对于1ms来说4095这个数字很大了)
            if (sequence == 0) {
                now = tilNextMillis(lastTimestamp);
            }
        } else {
            //如果是新的一毫秒,那么序列号就是0
            sequence = 0;
            lastTimestampMaxSequence = sequence;
        }

        lastTimestamp = now;

        return ((now - startTime) << timestampLeftShift)
                | (dataCenterId << dataCenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    private long now() {
        return System.currentTimeMillis();
    }

    private long tilNextMillis(long lastTimestamp) {
        long now = now();
        while (now <= lastTimestamp) {
            now = now();
        }
        return now;
    }



















    @Override
    public String getStrategyName() {
        return IdStrategyType.SNOWFLAKE.getStrategyName();
    }

    private Snowflake() {

    }
}
