package com.xjtu.dbc.robserver.manage.sensitive;

import com.xjtu.dbc.robserver.manage.sensitive.entity.SensitiveWordDto;

import java.util.Map;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/12 8:42
 */
public interface SensitiveService {
    String filter(String sentence, char replaceChar);

    Map<String, Object> getSensitiveWordList(SensitiveWordDto sensitiveWordDto);
}
