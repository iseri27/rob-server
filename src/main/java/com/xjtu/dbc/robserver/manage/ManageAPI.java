package com.xjtu.dbc.robserver.manage;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.manage.entity.TagDto;
import com.xjtu.dbc.robserver.manage.impl.ManageServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Resource
    private RedisTemplate redisTemplate;

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

    /**
     * 添加敏感词，加到redis中.
     * 如果已存在或者长度不符合要求，均会报错.
     * @param word 待添加的敏感词
     * @return 添加结果
     */
    @PostMapping("/sensitive/add/{word}")
    public Result addSensitiveWord(@PathVariable("word") String word) {
        if (redisTemplate.opsForSet().isMember(Constants.SENSITIVE_KEY, word))
            return Result.fail(Result.BAD_REQUEST, "该敏感词已存在");
        if (word.length() < 2 || word.length() > 20)
            return Result.fail(Result.BAD_REQUEST, "敏感词长度不符合要求");
        redisTemplate.opsForSet().add(Constants.SENSITIVE_KEY, word);
        return Result.successMsg("添加敏感词成功");
    }

    /**
     * 过滤敏感词，将敏感词替换成相同长度的 *.
     * @param sentence 待过滤的句子.
     * @param replaceChar 用于替换的字符.
     * @see ManageServiceImpl
     * @return 过滤后的句子.
     */
    @GetMapping("/sensitive/filter/{sentence}/{replaceChar}")
    public Result filterSentence(@PathVariable("sentence") String sentence, @PathVariable("replaceChar") char replaceChar) {
        String cleanSentence = manageService.filter(sentence, replaceChar);
        return Result.success("过滤成功", cleanSentence);
    }

    @PostMapping("/sensitive/del/{word}")
    public Result delSensitiveWord(@PathVariable("word") String word) {
        redisTemplate.opsForSet().remove(Constants.SENSITIVE_KEY, word);
        return Result.successMsg("删除成功");
    }

    @PostMapping("/sensitive/update/{originWord}/{newWord}")
    public Result updateSensitiveWord(@PathVariable("originWord") String originWord, @PathVariable("newWord") String newWord) {
        if (redisTemplate.opsForSet().isMember(Constants.SENSITIVE_KEY, newWord))
            return Result.fail(Result.BAD_REQUEST, "新敏感词词已存在");
        if (newWord.length() < 2 || newWord.length() > 20)
            return Result.fail(Result.BAD_REQUEST, "敏感词长度不符合要求");
        redisTemplate.opsForSet().remove(Constants.SENSITIVE_KEY, originWord);
        redisTemplate.opsForSet().add(Constants.SENSITIVE_KEY, newWord);
        return Result.successMsg("修改敏感词成功");
    }
}
