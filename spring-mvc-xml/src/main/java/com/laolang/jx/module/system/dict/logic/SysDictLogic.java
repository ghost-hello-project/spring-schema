package com.laolang.jx.module.system.dict.logic;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.laolang.jx.framework.common.util.JacksonUtil;
import com.laolang.jx.module.system.dict.req.SysDictTypeListReq;
import com.laolang.jx.module.system.dict.rsp.SysDictTypeListRsp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SysDictLogic {

    public SysDictTypeListRsp typeList(SysDictTypeListReq req) {
        log.info("req:\n{}", JacksonUtil.toXml(req, true));
        SysDictTypeListRsp rsp = new SysDictTypeListRsp();
        BeanUtils.copyProperties(req, rsp);

        return rsp;
    }
}
