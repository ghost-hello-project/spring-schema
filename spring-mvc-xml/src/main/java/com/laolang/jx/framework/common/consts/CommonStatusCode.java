package com.laolang.jx.framework.common.consts;

public enum CommonStatusCode implements BizCode {
    /**
     * 业务操作成功.
     */
    OK("200", "操作成功"),
    /**
     * 业务操作失败.
     */
    FAILED("1", "操作失败"),
    /**
     * 无权访问.
     */
    UNAUTHORIZED("401", "无权访问"),
    /**
     * 请求地址不存在.
     */
    NOT_FOUND("404", "请求地址不存在"),
    /**
     * 服务器内部错误.
     */
    ERROR("500", "服务器内部错误");

    /**
     * 业务状态码.
     */
    private final String code;

    /**
     * 提示信息.
     */
    private final String msg;

    CommonStatusCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
