package com.xjtu.dbc.robserver.manage.sensitive.impl;

import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.common.Utils;
import com.xjtu.dbc.robserver.manage.sensitive.SensitiveService;
import com.xjtu.dbc.robserver.manage.sensitive.entity.SensitiveWordDto;
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

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/12 8:43
 */
@Transactional@Service
public class SensitiveServiceImpl implements SensitiveService {

    @Resource
    private RedisTemplate redisTemplate;



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
