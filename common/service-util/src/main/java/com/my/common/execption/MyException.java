package com.my.common.execption;

import com.my.common.result.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @program: oa-parent
 * @description: 自定义全局异常
 * @author: DY
 * @create: 2023-07-30 16:58
 **/
@Data
@ToString
public class MyException extends RuntimeException {
    private Integer code;
    private String message;

    /**
     * 接收枚举类型对象
     *
     * @param resultCodeEnum
     */
    /**
     * 通过状态码和错误消息创建异常对象
     *
     * @param code
     * @param message
     */
    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public MyException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }


}
