package com.xjtu.dbc.robserver.manage.article;

import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.model.inform.Inform;
import com.xjtu.dbc.robserver.common.model.inform.InformDto;
import org.springframework.web.bind.annotation.*;

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


    /**
     * 不进行禁言操作.
     * @param reportid
     * @return
     */
    @PostMapping("/pass/{reportid}")
    public Result passInform(@PathVariable("reportid") Integer reportid) {
        manageArticleService.pass(reportid);
        return Result.successMsg("处理成功");
    }

    @PostMapping("/ban/{reportid}")
    public Result ban(@PathVariable("reportid") Integer reportid) {
        manageArticleService.ban(reportid);
        return Result.successMsg("禁言成功");
    }
}
