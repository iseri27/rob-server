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

    // tag增删改查
    /**
     * 查询tagList，支持tagName的模糊搜索及tagid的准确搜索.
     * 分页查找.
     * @param tagDto
     * @see TagDto
     * @author 杨兆瑞
     * @return
     */
    @GetMapping("/tag/select")
    public Result getTags(@RequestHeader("Token")String token, TagDto tagDto) {
        int userID = TokenUtils.getUserInfo(token, commonService).getUserid();
        tagDto.setOwnerid(userID);
        Map<String, Object> data = manageService.getTagList(tagDto);
        return Result.success("获取tag列表成功", data);
    }

    /**
     * 添加tag.
     * @param token
     * @param tag
     * @author 杨兆瑞
     * @return
     */
    @PostMapping("/tag/add")
    public Result addTag(@RequestHeader("Token") String token, @RequestBody Tag tag) {
        Integer userID = TokenUtils.getUserInfo(token, commonService).getUserid();
        tag.setOwnerid(userID);
        manageService.addTag(tag);
        return Result.successMsg("添加tag成功");
    }

    /**
     * 删除tag，仅仅根据tagid.
     * @param tag
     * @author 杨兆瑞
     * @return
     */
    @PostMapping("/tag/delete")
    public Result deleteTag(@RequestBody Tag tag) {
        manageService.deleteTag(tag);
        return Result.successMsg("删除tag成功");
    }

    /**
     * 修改tag.
     * @param tag
     * @author 杨兆瑞
     * @return
     */
    @PostMapping("/tag/update")
    public Result updateTag(@RequestBody Tag tag) {
        manageService.updateTag(tag);
        return Result.successMsg("修改tag成功");
    }
}
