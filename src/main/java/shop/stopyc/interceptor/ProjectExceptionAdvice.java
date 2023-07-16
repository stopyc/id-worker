package shop.stopyc.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.SystemException;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.stopyc.pojo.dto.ResultDTO;
import shop.stopyc.pojo.exception.BusinessException;

import static shop.stopyc.constant.ResultEnum.*;


/**
 * @program: id-worker
 * @description: 全局异常处理器
 * @author: stop.yc
 * @create: 2022-07-24 19:10
 **/

@RestControllerAdvice
@Slf4j
@Order(1)
public class ProjectExceptionAdvice {

    /**
     * 处理自定义异常SystemException
     **/
    @ExceptionHandler(SystemException.class)
    public ResultDTO doSystemException(SystemException ex) {
        log.error("异常: {}", ex.getMessage());
        return ResultDTO.fail(ABNORMAL_CLOCK_CALLBACK);
    }

    @ExceptionHandler(BusinessException.class)
    public ResultDTO doBusinessException(BusinessException ex) {
        log.error("异常: {}", ex.getMessage());
        return ResultDTO.fail(BUSINESS_FAIL);
    }


    /**
     * 除了自定义的异常处理器，保留对Exception类型的异常处理，用于处理非预期的异常
     **/
    @ExceptionHandler(Exception.class)
    public ResultDTO doOtherException(Exception ex) {
        log.error("异常:{}", ex.getMessage());
        return ResultDTO.fail(UNKNOWN_ERROR);
    }
}
