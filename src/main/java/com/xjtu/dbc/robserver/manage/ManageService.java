package com.xjtu.dbc.robserver.manage;

import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.manage.entity.SensitiveWordDto;
import com.xjtu.dbc.robserver.manage.entity.TagDto;

import java.util.List;
import java.util.Map;

public interface ManageService {
    Map<String, Object> getTagList(TagDto tag);

    void addTag(Tag tag);

    void deleteTag(Tag tag);

    void updateTag(Tag tag);

    String filter(String sentence, char replaceChar);

    Map<String, Object> getSensitiveWordList(SensitiveWordDto sensitiveWordDto);
}
