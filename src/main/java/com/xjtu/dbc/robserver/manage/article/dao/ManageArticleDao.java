package com.xjtu.dbc.robserver.manage.article.dao;

import com.xjtu.dbc.robserver.common.model.inform.Inform;
import com.xjtu.dbc.robserver.common.model.inform.InformDto;

import java.util.List;

public interface ManageArticleDao {

    List<Inform> getInformList(InformDto informDto);

    void updateReportStatus(int reportid, int status);

    void ban(Integer reportid);

    void updateArticleStatus(Integer reportid, int status);
}
