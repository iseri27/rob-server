package com.xjtu.dbc.robserver.blog.publish.entity;

import com.xjtu.dbc.robserver.common.model.tag.Tag;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TagDto extends Tag {
    private String tagNameNew;
}
