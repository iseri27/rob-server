package com.xjtu.dbc.robserver.common.model.category;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Category {
    private Integer categoryid;
    private String categoryname;
    private Date createtime;
    private Integer creatorid;
}
