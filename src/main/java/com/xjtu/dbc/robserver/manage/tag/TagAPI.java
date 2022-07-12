package com.xjtu.dbc.robserver.manage.tag;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.manage.tag.entity.TagDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/12 8:47
 */
@RestController
@RequestMapping("/manage/tag")
public class TagAPI {

    @Resource
    private TagService tagService;

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
    @GetMapping("/select")
    public Result getTags(@RequestHeader("Token")String token, TagDto tagDto) {
        int userID = TokenUtils.getUserInfo(token, commonService).getUserid();
        tagDto.setOwnerid(userID);
        Map<String, Object> data = tagService.getTagList(tagDto);
        return Result.success("获取tag列表成功", data);
    }

    /**
     * 添加tag.
     * @param token
     * @param tag
     * @author 杨兆瑞
     * @return
     */
    @PostMapping("/add")
    public Result addTag(@RequestHeader("Token") String token, @RequestBody Tag tag) {
        Integer userID = TokenUtils.getUserInfo(token, commonService).getUserid();
        tag.setOwnerid(userID);
        tagService.addTag(tag);
        return Result.successMsg("添加tag成功");
    }

    /**
     * 删除tag，仅仅根据tagid.
     * @param tag
     * @author 杨兆瑞
     * @return
     */
    @PostMapping("/delete")
    public Result deleteTag(@RequestBody Tag tag) {
        tagService.deleteTag(tag);
        return Result.successMsg("删除tag成功");
    }

    /**
     * 修改tag.
     * @param tag {@link Tag}
     * @author 杨兆瑞
     * @return 修改结果.
     */
    @PostMapping("/update")
    public Result updateTag(@RequestBody Tag tag) {
        tagService.updateTag(tag);
        return Result.successMsg("修改tag成功");
    }
}
