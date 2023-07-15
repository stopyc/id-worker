package shop.stopyc.strategy;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @program: id-worker
 * @description: 抽象分布式id策略类
 * @author: stop.yc
 * @create: 2023-07-15 09:34
 **/
@Component
public abstract class AbstractIdWorker {

    @PostConstruct
    public void init(){
        IdFactory.putStrategy(getStrategyName(), this);
    }
    public abstract long nextId();

    public abstract String getStrategyName();
}
