package com.laolang.jx.framework.web.jackson.module;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * jackson Long 序列化
 */
public class LongModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public LongModule() {
        super();
        addSerializer(Long.class, ToStringSerializer.instance);
        addSerializer(Long.TYPE, ToStringSerializer.instance);
    }
}
