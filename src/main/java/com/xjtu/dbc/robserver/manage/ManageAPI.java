package com.xjtu.dbc.robserver.manage;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.manage.entity.TagDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 管理模块的API，包括敏感词管理、tag管理等.
 */
@RestController
@RequestMapping("/manage")
public class ManageAPI {
    @Resource
    private ManageService manageService;

    @Resource
    private CommonService commonService;

    /**
     * 查询tagList，支持tagName的模糊搜索及tagid的准确搜索.
     * @param tagDto
     * @see TagDto
     * @return
     */
    @GetMapping("/tag/select")
    public Result getTags(TagDto tagDto) {
        Map<String, Object> data = manageService.getTagList(tagDto);
        return Result.success("获取tag列表成功", data);
    }
}
