package com.xjtu.dbc.robserver.blog.publish;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xjtu.dbc.robserver.common.model.article.Article;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter @Setter

public class BlogDto extends Article{
    private String ownername;
    private List <String> tags; // 帖子带有的tag
}
