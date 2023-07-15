package shop.stopyc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import shop.stopyc.strategy.IdFactory;
import shop.stopyc.strategy.IdGenType.IdStrategyType;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class IdWorkerApplicationTests {

    @Test
    void test1() {
        long id = IdFactory.nextId(IdStrategyType.SNOWFLAKE);
        System.out.println(Long.toBinaryString(id));
    }

    @Test
    void test2() {
        assertThrows(RuntimeException.class, () -> {
            IdFactory.nextId(IdStrategyType.UUID);
        });
    }

    @Test
    void test3() {
        for (int i = 0; i < 1000; i++) {
            long id = IdFactory.nextId(IdStrategyType.SNOWFLAKE);
            System.out.println(Long.toBinaryString(id));
        }
    }

    @Test
    void test4() {
        for (int i = 0; i < 4096; i++) {
            long id = IdFactory.nextId(IdStrategyType.SNOWFLAKE);
            System.out.println(Long.toBinaryString(id));
        }
    }

    @Test
    void test5() {
        for (int i = 0; i < 4097; i++) {
            long id = IdFactory.nextId(IdStrategyType.SNOWFLAKE);
            System.out.println(Long.toBinaryString(id));
        }
    }

    @Test
    void test6() {
        long start = IdFactory.nextId(IdStrategyType.SNOWFLAKE);
        long end = 0;
        for (int i = 0; i < 4097; i++) {
            end = IdFactory.nextId(IdStrategyType.SNOWFLAKE);
        }
        System.out.println(Long.toBinaryString(start));
        System.out.println(Long.toBinaryString(end));
    }
}


