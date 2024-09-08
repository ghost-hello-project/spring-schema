package com.laolang.jx.framework.common.exception;

import com.laolang.jx.framework.common.consts.BizCode;
import com.laolang.jx.framework.common.consts.CommonStatusCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String code;
    private final String msg;

    public BusinessException(String message) {
        this.code = CommonStatusCode.ERROR.getCode();
        this.msg = message;
    }

    public BusinessException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String code, String msg, String message) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(BizCode bizCode) {
        super();
        this.code = bizCode.getCode();
        this.msg = bizCode.getMsg();
    }

}
