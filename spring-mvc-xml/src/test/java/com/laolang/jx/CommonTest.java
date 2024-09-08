package com.laolang.jx;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator.Feature;
import com.laolang.jx.framework.common.core.R;
import com.laolang.jx.framework.common.util.JacksonUtil;
import com.laolang.jx.framework.web.jackson.module.Jdk8TimeModule;
import com.laolang.jx.module.system.dict.rsp.SysDictTypeListRsp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonTest {

    private XmlMapper om;

    @BeforeClass
    public void BeforeClass() {
        om = new XmlMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);
        om.registerModule(new Jdk8TimeModule());
        om.configure(Feature.WRITE_XML_DECLARATION, true);
    }

    @Test
    public void testOne() throws JsonProcessingException {
        SysDictTypeListRsp rsp = new SysDictTypeListRsp();
        rsp.setGroupCode("system");
        R<SysDictTypeListRsp> r = R.ok(rsp);
        System.out.println(JacksonUtil.toXml(r));
        System.out.println(JacksonUtil.toXml(r, true));
    }

}
