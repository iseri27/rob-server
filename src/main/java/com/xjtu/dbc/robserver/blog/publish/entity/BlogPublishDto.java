package com.xjtu.dbc.robserver.blog.publish.entity;

import com.xjtu.dbc.robserver.common.model.article.Article;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter

public class BlogPublishDto extends Article {
    // 博客的tag
    private List <Integer> tags;
}
