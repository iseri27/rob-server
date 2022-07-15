package com.xjtu.dbc.robserver.question.create.entity;

import com.xjtu.dbc.robserver.common.model.article.Article;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter@Setter
public class QuestionPublishDto extends Article {
    // 博客的tag
    private List<Integer> tags;
}
