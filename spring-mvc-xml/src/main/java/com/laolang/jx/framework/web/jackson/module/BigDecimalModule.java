package com.laolang.jx.framework.web.jackson.module;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * jackson BigDecimal 序列化
 */
public class BigDecimalModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public BigDecimalModule() {
        super();
        addSerializer(BigDecimal.class, new JsonSerializer<BigDecimal>() {
            @Override
            public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers)
                    throws IOException {
                DecimalFormat format = new DecimalFormat("0.00");
                gen.writeString(format.format(value));
            }
        });
    }

}
