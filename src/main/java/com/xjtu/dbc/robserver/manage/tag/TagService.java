package com.xjtu.dbc.robserver.manage.tag;

import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.manage.tag.entity.TagDto;

import java.util.Map;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/12 8:48
 */
public interface TagService {
    Map<String, Object> getTagList(TagDto tag);

    void addTag(Tag tag);

    void deleteTag(Tag tag);

    void updateTag(Tag tag);
}
