package shop.stopyc.constant;

import lombok.Getter;
import shop.stopyc.pojo.dto.StatusCode;

/**
 * 状态枚举
 *
 * @author YC104
 */
@Getter
public enum ResultEnum implements StatusCode {
    //自定义
    //通用
    UNKNOWN_ERROR(500, "您的网络异常,请稍后刷新页面重试~"),
    SUCCESS(200, "请求成功"),
    UNAUTHORIZED(401, "认证失败"),
    FORBIDDEN(403, "权限不足"),
    RESOURCE_NOT_FOUND(404, "资源未找到"),
    PARAMETER_NOT_VALID(400, "参数不合法"),
    BUSINESS_FAIL(400, "业务失败"),
    ABNORMAL_CLOCK_CALLBACK(50000, "异常的时钟回拨,请联系管理员"),

    ;
    /**
     * 编号
     */
    private final int code;
    /**
     * 信息
     */
    private final String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
