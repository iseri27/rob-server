package com.xjtu.dbc.robserver.blog.home.entity;

import com.xjtu.dbc.robserver.common.page.PageParam;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BlogDto extends PageParam {
    private Integer categoryId;
}
