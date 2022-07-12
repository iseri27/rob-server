package com.xjtu.dbc.robserver.manage.sensitive.entity;

import com.xjtu.dbc.robserver.common.page.PageParam;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SensitiveWordDto extends PageParam {
    private String part;
}
