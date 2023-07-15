package shop.stopyc.strategy;

import shop.stopyc.strategy.IdGenType.IdStrategyType;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: id-worker
 * @description: 分布式Id工厂
 * @author: stop.yc
 * @create: 2023-07-15 09:37
 **/
public class IdFactory {
    private static final Map<String, AbstractIdWorker> ID_STRATEGY_MAP = new ConcurrentHashMap<>(5);

    public static long nextId(IdStrategyType idStrategyType){
        return getStrategy(idStrategyType.getStrategyName()).nextId();
    }

    public static long nextId(String strategyType){
        return getStrategy(strategyType).nextId();
    }

    static AbstractIdWorker getStrategy(String strategyType){
        return Optional.ofNullable(ID_STRATEGY_MAP.get(strategyType))
                .orElseThrow(() -> new RuntimeException("没有找到对应的策略"));
    }

    static void putStrategy(String strategy, AbstractIdWorker idWorker){
        ID_STRATEGY_MAP.put(strategy, idWorker);
    }

    private IdFactory(){

    }
}
