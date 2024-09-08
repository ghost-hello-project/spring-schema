package com.laolang.jx.framework.common.core;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BasePojo {
    private Long id;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private String remark;
    private Integer version;
}
