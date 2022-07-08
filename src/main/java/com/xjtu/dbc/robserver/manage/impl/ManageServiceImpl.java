package com.xjtu.dbc.robserver.manage.impl;

import com.xjtu.dbc.robserver.common.Utils;
import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.common.page.QueryAction;
import com.xjtu.dbc.robserver.manage.ManageService;
import com.xjtu.dbc.robserver.manage.dao.ManageDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Transactional @Service
public class ManageServiceImpl implements ManageService {
    @Resource
    private ManageDao manageDao;

    @Override
    public Map<String, Object> getTagList(Tag tag) {
        QueryAction<Tag> query = () -> manageDao.selectTags(tag);
        return Utils.getPage(tag, query);
    }
}
