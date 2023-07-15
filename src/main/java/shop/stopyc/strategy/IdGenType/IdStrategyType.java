package shop.stopyc.strategy.IdGenType;

import lombok.Getter;

/**
 * @author YC104
 */

@Getter
public enum IdStrategyType {
    /**
     * 雪花算法
     */
    SNOWFLAKE("snowflake"),
    /**
     * UUID
     */
    UUID("uuid"),
    /**
     * 自增
     */
    INCREMENT("increment");

    private final String strategyName;

    IdStrategyType(String strategyName) {
        this.strategyName = strategyName;
    }
}
