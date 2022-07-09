package com.xjtu.dbc.robserver.blog.detail;

import com.xjtu.dbc.robserver.common.model.article.Article;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class BlogDetailDto extends Article {
    private List <String> tags; // 博客的tag
    private String authorname; //博客作者的姓名
    private String authoravatar; //博客作者的头像
    private String categoryname;//分区名字
    private String levelname;//用户等级名
}
