package com.xjtu.dbc.robserver.user.personal.entity;

import com.xjtu.dbc.robserver.common.page.PageParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticleDto extends PageParam {
    Integer userid;
    int type;
}
