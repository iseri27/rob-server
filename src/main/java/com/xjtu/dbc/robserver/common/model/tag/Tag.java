package com.xjtu.dbc.robserver.common.model.tag;

import com.xjtu.dbc.robserver.common.page.PageParam;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Tag {
    private Integer tagid;
    private String tagname;
    private Integer ownerid;
}
