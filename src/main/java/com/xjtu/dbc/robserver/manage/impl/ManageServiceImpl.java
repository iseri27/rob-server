package com.xjtu.dbc.robserver.manage.impl;

import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.common.Utils;
import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.common.page.QueryAction;
import com.xjtu.dbc.robserver.manage.ManageService;
import com.xjtu.dbc.robserver.manage.dao.ManageDao;
import com.xjtu.dbc.robserver.manage.entity.SensitiveWordDto;
import com.xjtu.dbc.robserver.manage.entity.TagDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toolgood.words.StringSearch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional @Service
public class ManageServiceImpl implements ManageService {
    @Resource
    private ManageDao manageDao;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> getTagList(TagDto tagDto) {
        return Utils.getPage(tagDto, () -> manageDao.selectTags(tagDto));
    }

    @Override
    public void addTag(Tag tag) {
        manageDao.insertTag(tag);
    }

    @Override
    public void deleteTag(Tag tag) {
        manageDao.deleteRelation(tag.getTagid());
        manageDao.deleteTag(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        manageDao.updateTag(tag);
    }

    @Override
    public String filter(String sentence, char replaceChar) {
        Set<String> sensitiveWordSet = redisTemplate.opsForSet().members(Constants.SENSITIVE_KEY);
        List<String> sensitiveWordList = new ArrayList<>(sensitiveWordSet);
        StringSearch search = new StringSearch();
        search.SetKeywords(sensitiveWordList);
        return search.Replace(sentence, replaceChar);
    }

    /**
     * 从Redis中获取敏感词集合，再经过过滤获得敏感词列表.
     * 使用了lambda表达式和Java8 stream.
     * @param sensitiveWordDto
     * @return 敏感词列表
     */
    @Override
    public Map<String, Object> getSensitiveWordList(SensitiveWordDto sensitiveWordDto) {
        return Utils.getPage(sensitiveWordDto, () -> {
            String part = sensitiveWordDto.getPart();
            Set<String> sensitiveWordSet = redisTemplate.opsForSet().members(Constants.SENSITIVE_KEY);
            List<String> sensitiveWordList = sensitiveWordSet
                    .stream()
                    .filter((word) -> {
                        if (part != null && "" != part && !word.contains(part))
                            return false;
                        return true;
                    })
                    .collect(Collectors.toList());
            return sensitiveWordList;
        });
    }
}
