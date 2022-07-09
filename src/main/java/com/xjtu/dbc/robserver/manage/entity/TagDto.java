package com.xjtu.dbc.robserver.manage.entity;

import com.xjtu.dbc.robserver.common.page.PageParam;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TagDto extends PageParam {
    private Integer tagid;
    private String tagname;
    private Integer ownerid;
}
