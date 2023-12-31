package shop.stopyc.pojo.exception;


import shop.stopyc.pojo.dto.StatusCode;

/**
 * @description: 自定义异常，用于封装异常信息，对异常进行分类
 * @author: stop.yc
 * @create: 2022-07-24 19:10
 **/
public class BusinessException extends RuntimeException {

    private StatusCode statusCode;

    public BusinessException(StatusCode statusCode) {
        super(statusCode.getMsg());
        this.statusCode = statusCode;
    }

    public BusinessException(StatusCode statusCode, Throwable cause) {
        super(statusCode.getMsg(), cause);
        this.statusCode = statusCode;
    }

    public BusinessException(String message) {
        super(message);
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setCode(StatusCode code) {
        this.statusCode = code;
    }

}
