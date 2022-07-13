package com.xjtu.dbc.robserver.manage.tag.impl;

import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.common.Utils;
import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.manage.tag.TagService;
import com.xjtu.dbc.robserver.manage.tag.dao.TagDao;
import com.xjtu.dbc.robserver.manage.tag.entity.TagDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toolgood.words.StringSearch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/12 8:50
 */
@Transactional @Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagDao tagDao;
    
    @Override
    public Map<String, Object> getTagList(TagDto tagDto) {
        return Utils.getPage(tagDto, () -> tagDao.selectTags(tagDto));
    }

    @Override
    public void addTag(Tag tag) {
        tagDao.insertTag(tag);
    }

    @Override
    public void deleteTag(Tag tag) {
        tagDao.deleteRelation(tag.getTagid());
        tagDao.deleteTag(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        tagDao.updateTag(tag);
    }


}
