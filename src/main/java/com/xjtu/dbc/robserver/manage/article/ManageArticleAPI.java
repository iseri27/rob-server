package com.xjtu.dbc.robserver.manage.article;

import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.model.inform.Inform;
import com.xjtu.dbc.robserver.common.model.inform.InformDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manage/article")
public class ManageArticleAPI {

    @Resource
    private ManageArticleService manageArticleService;

    @GetMapping("/get")
    public Result getInforms(InformDto informDto) {
        Map<String, Object> data = manageArticleService.getInformList(informDto);
        return Result.success("获取举报成功", data);
    }
}
