package com.xjtu.dbc.robserver.blog.publish;

import com.xjtu.dbc.robserver.common.model.article.BlogVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter

public class BlogEditDto extends BlogVO {
    private List <String> tags; // 博客的tag
}
