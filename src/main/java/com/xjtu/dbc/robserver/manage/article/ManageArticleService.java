package com.xjtu.dbc.robserver.manage.article;

import com.xjtu.dbc.robserver.common.model.article.Article;
import com.xjtu.dbc.robserver.common.model.inform.InformDto;

import java.util.Map;

public interface ManageArticleService {
    Map<String, Object> getInformList(InformDto informDto);

    void pass(int reportid);

    void ban(Integer reportid);

    Article getType(Integer reportid);
}
