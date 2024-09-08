package com.laolang.jx.framework.common.consts;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

public class CommonEntityConsts {

    @Getter
    @AllArgsConstructor
    public enum DefaultValue {
        /**
         *
         */
        YES("Y", "默认值"),
        NO("N", "非默认值");
        private final String value;
        private final String desc;

        private static final Map<String, DefaultValue> value_map = Maps.newHashMap();

        static {
            for (DefaultValue e : values()) {
                value_map.put(e.getValue(), e);
            }
        }

        public static DefaultValue getByValue(String value) {
            return value_map.get(value);
        }
    }

    @Getter
    @AllArgsConstructor
    public enum Status {
        /**
         *
         */
        NORMAL("0", "正常"),
        STOP("1", "停用");
        private final String value;
        private final String desc;

        private static final Map<String, Status> value_map = Maps.newHashMap();

        static {
            for (Status e : values()) {
                value_map.put(e.getValue(), e);
            }
        }

        public static Status getByValue(String value) {
            return value_map.get(value);
        }
    }
}