package com.laolang.jx.framework.common.util;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator.Feature;
import com.google.common.collect.Lists;
import com.laolang.jx.framework.common.consts.CommonStatusCode;
import com.laolang.jx.framework.common.exception.BusinessException;
import com.laolang.jx.framework.web.jackson.module.BigDecimalModule;
import com.laolang.jx.framework.web.jackson.module.Jdk8TimeModule;
import com.laolang.jx.framework.web.jackson.module.LongModule;

import cn.hutool.core.collection.CollUtil;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JacksonUtil {

    /**
     * 默认的 xmlMapper, 不带格式化
     */
    private static XmlMapper xmlMapper;
    /**
     * 带格式化的 xmlMapper
     */
    private static XmlMapper xmlMapperPretty;

    /**
     * 默认的 objectMapper, 不带格式化
     */
    private static ObjectMapper objectMapper;
    /**
     * 带格式化的 objectMapper
     */
    private static ObjectMapper objectMapperPretty;

    /**
     * 格式化需要的 Feature
     */
    private static final List<SerializationFeature> DEFAULT_PRETTY_SERIALIZATIONFEATURE = Lists
            .newArrayList(SerializationFeature.INDENT_OUTPUT);

    /**
     * ****************************************************************************
     * 初始化相关
     * ****************************************************************************
     */
    static {
        initXmlMapper();
        initObjectMapper();
    }

    /**
     * 初始化 xmlMapper 和 xmlMapperPretty
     */
    private static void initXmlMapper() {
        xmlMapper = new XmlMapper();
        registerModule(xmlMapper);
        enableXmlFormatFeature(xmlMapper, Lists.newArrayList(Feature.WRITE_XML_DECLARATION), null);

        xmlMapperPretty = new XmlMapper();
        registerModule(xmlMapperPretty);
        enableFeatures(xmlMapperPretty, DEFAULT_PRETTY_SERIALIZATIONFEATURE, null);
        enableXmlFormatFeature(xmlMapperPretty, Lists.newArrayList(Feature.WRITE_XML_DECLARATION), null);
    }

    /**
     * 初始化 objectMapper 和 objectMapperPretty
     */
    private static void initObjectMapper() {
        objectMapper = new ObjectMapper();
        registerModule(objectMapper);

        objectMapperPretty = new ObjectMapper();
        registerModule(objectMapperPretty);
        enableFeatures(objectMapperPretty, DEFAULT_PRETTY_SERIALIZATIONFEATURE, null);
    }

    /**
     * 注册自定义 Module
     */
    private static void registerModule(ObjectMapper om) {
        om.registerModule(new BigDecimalModule());
        om.registerModule(new LongModule());
        om.registerModule(new Jdk8TimeModule());
    }

    /**
     * 开启 Feature
     */
    private static void enableFeatures(ObjectMapper om, List<SerializationFeature> serializationFeatures,
            List<DeserializationFeature> deserializationFeatures) {
        if (CollUtil.isNotEmpty(serializationFeatures)) {
            for (SerializationFeature feature : serializationFeatures) {
                om.enable(feature);
            }
        }
        if (CollUtil.isNotEmpty(deserializationFeatures)) {
            for (DeserializationFeature feature : deserializationFeatures) {
                om.enable(feature);
            }
        }
    }

    /**
     * 开启 xml 特有的 Featurer
     */
    private static void enableXmlFormatFeature(XmlMapper xm, List<ToXmlGenerator.Feature> generatorFeatures,
            List<FromXmlParser.Feature> parseFeatures) {
        if (CollUtil.isNotEmpty(generatorFeatures)) {
            for (ToXmlGenerator.Feature feature : generatorFeatures) {
                xm.configure(feature, true);
            }
        }
        if (CollUtil.isNotEmpty(parseFeatures)) {
            for (FromXmlParser.Feature feature : parseFeatures) {
                xm.configure(feature, true);
            }
        }
    }

    /**
     * ****************************************************************************
     * json 相关
     * ****************************************************************************
     */

    public static String toJson(Object o) {
        return toJson(o, false, true);
    }

    public static String toJson(Object o, boolean pretty) {
        return toJson(o, pretty, true);
    }

    public static String toJson(Object o, boolean pretty, boolean ignoreError) {
        try {
            if (pretty) {
                return objectMapperPretty.writeValueAsString(o);
            }
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            if (!ignoreError) {
                throw new BusinessException(CommonStatusCode.FAILED.getCode(), e.getMessage());
            }
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ****************************************************************************
     * xml 相关
     * ****************************************************************************
     */

    public static String toXml(Object o) {
        return toXml(o, false, true);
    }

    public static String toXml(Object o, boolean pretty) {
        return toXml(o, pretty, true);
    }

    public static String toXml(Object o, boolean pretty, boolean ignoreError) {
        try {
            if (pretty) {
                return xmlMapperPretty.writeValueAsString(o);
            }
            return xmlMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            if (!ignoreError) {
                throw new BusinessException(CommonStatusCode.FAILED.getCode(), e.getMessage());
            }
            e.printStackTrace();
        }
        return null;
    }
}
