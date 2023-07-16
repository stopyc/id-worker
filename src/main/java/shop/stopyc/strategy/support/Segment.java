package shop.stopyc.strategy.support;

import shop.stopyc.strategy.AbstractIdWorker;


/**
 * @program: id-worker
 * @description: 数据库切片生成策略类
 * @author: stop.yc
 * @create: 2023-07-15 21:31
 **/
public class Segment extends AbstractIdWorker {
    @Override
    public long nextId() {
        return 0;
    }

    @Override
    public String getStrategyName() {
        return null;
    }
}
