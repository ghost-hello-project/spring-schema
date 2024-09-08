package com.laolang.jx.framework.web.jackson.factory;

import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

/**
 * 自定义 XmlMapper 的 Factory, 主要是为了启用一些 Feature
 */
public class CustomXmlFactory extends XmlFactory {

    private static final long serialVersionUID = 1L;

    public CustomXmlFactory() {
        super();
        configureFeature();
    }

    private void configureFeature() {
        configureFeatureEnable();
    }

    private void configureFeatureEnable() {
        configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
    }

}
