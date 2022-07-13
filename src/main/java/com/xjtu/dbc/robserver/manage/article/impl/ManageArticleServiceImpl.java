package com.xjtu.dbc.robserver.manage.article.impl;

import com.xjtu.dbc.robserver.common.Utils;
import com.xjtu.dbc.robserver.common.model.inform.InformDto;
import com.xjtu.dbc.robserver.manage.article.ManageArticleService;
import com.xjtu.dbc.robserver.manage.article.dao.ManageArticleDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Transactional @Service
public class ManageArticleServiceImpl implements ManageArticleService {

    @Resource
    private ManageArticleDao manageArticleDao;

    @Override
    public Map<String, Object> getInformList(InformDto informDto) {
        return Utils.getPage(informDto, () -> manageArticleDao.getInformList(informDto));
    }

    @Override
    public void pass(int reportid) {
        manageArticleDao.updateReportStatus(reportid, 502);
    }

    @Override
    public void ban(Integer reportid) {
        manageArticleDao.updateReportStatus(reportid, 501);
        manageArticleDao.ban(reportid);
        manageArticleDao.updateArticleStatus(reportid, 403);
    }
}
