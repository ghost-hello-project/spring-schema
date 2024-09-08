package com.laolang.jx.framework.common.core;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.laolang.jx.framework.common.consts.CommonStatusCode;
import com.laolang.jx.framework.common.exception.BusinessException;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@JacksonXmlRootElement(localName = "root")
public class R<T> {

    /**
     * 接口请求结果的业务状态吗.
     */
    private String code;

    /**
     * 判断接口请求是否成功的唯一标识.
     */
    private Boolean success;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 数据体.
     */
    private T body;

    /**
     * 扩充字段,正常情况下此字段为空，当此字段有值时，意味着当前接口结构不稳定，以后会修改,即保持 extra 为空.
     */
    private Map<String, Object> extra;

    /**
     * 接口返回时间
     */
    private LocalDateTime time;

    public static <T> R<T> build(String code, boolean success, String msg, T body) {
        R<T> ajax = new R<>();
        ajax.setCode(code);
        ajax.setSuccess(success);
        ajax.setMsg(msg);
        ajax.setBody(body);
        ajax.setExtra(null);
        ajax.time = LocalDateTime.now();

        return ajax;
    }

    @JsonIgnore
    public void setPropFromBusinessException(BusinessException e) {
        setMsg(e.getMsg());
        setCode(e.getCode());
        setSuccess(false);
    }

    public static <T> R<T> ok() {
        return ok(CommonStatusCode.OK.getCode(), CommonStatusCode.OK.getMsg());
    }

    public static <T> R<T> ok(String code, String msg) {
        return ok(code, msg, null);
    }

    public static <T> R<T> ok(String code, String msg, T body) {
        return build(code, true, msg, body);
    }

    public static <T> R<T> ok(T body) {
        return build(CommonStatusCode.OK.getCode(), true, CommonStatusCode.OK.getMsg(), body);
    }

    public static <T> R<T> failed() {
        return failed(CommonStatusCode.FAILED.getMsg());
    }

    public static <T> R<T> failed(String msg) {
        return build(CommonStatusCode.FAILED.getCode(), false, msg, null);
    }

    public static <T> R<T> error() {
        return error(CommonStatusCode.ERROR.getMsg());
    }

    public static <T> R<T> error(String msg) {
        return error(CommonStatusCode.ERROR.getCode(), msg);
    }

    public static <T> R<T> error(String code, String msg) {
        return build(code, false, msg, null);
    }

    public static <T> R<T> notFound() {
        return notFound(CommonStatusCode.NOT_FOUND.getMsg());
    }

    public static <T> R<T> notFound(String msg) {
        return build(CommonStatusCode.NOT_FOUND.getCode(), false, msg, null);
    }

    public static <T> R<T> unauthorized() {
        return build(CommonStatusCode.UNAUTHORIZED.getCode(), false, CommonStatusCode.UNAUTHORIZED.getMsg(), null);
    }
}