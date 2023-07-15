package shop.stopyc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.stopyc.strategy.IdFactory;

/**
 * @program: id-worker
 * @description: id获取接口
 * @author: stop.yc
 * @create: 2023-07-15 10:41
 **/
@RestController
@RequestMapping(value = "/id", produces = "application/json;charset=UTF-8")
public class IdController {
    @GetMapping("/nextId/{strategyType}")
    public long nextId(@PathVariable("strategyType") String strategyType) {
        return IdFactory.nextId(strategyType);
    }
}