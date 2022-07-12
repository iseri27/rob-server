package com.xjtu.dbc.robserver.manage.sensitive;

import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.manage.sensitive.entity.SensitiveWordDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/12 8:41
 */
@RestController
@RequestMapping("/manage/sensitive")
public class SensitiveAPI {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private SensitiveService sensitiveService;
    //敏感词管理
    /**
     * 添加敏感词，加到redis中.
     * 如果已存在或者长度不符合要求，均会报错.
     * @param word 待添加的敏感词
     * @return 添加结果
     * @author 杨兆瑞
     */
    @PostMapping("/add/{word}")
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
     * @see com.xjtu.dbc.robserver.manage.sensitive.impl.SensitiveServiceImpl
     * @return 过滤后的句子.
     * @author 杨兆瑞
     */
    @GetMapping("filter/{sentence}/{replaceChar}")
    public Result filterSentence(@PathVariable("sentence") String sentence, @PathVariable("replaceChar") char replaceChar) {
        String cleanSentence = sensitiveService.filter(sentence, replaceChar);
        return Result.success("过滤成功", cleanSentence);
    }

    /**
     * 删除敏感词.
     * @param word 要删除的敏感词.
     * @return 删除结果
     * @author 杨兆瑞
     */
    @PostMapping("/del/{word}")
    public Result delSensitiveWord(@PathVariable("word") String word) {
        redisTemplate.opsForSet().remove(Constants.SENSITIVE_KEY, word);
        return Result.successMsg("删除成功");
    }

    /**
     * 修改敏感词.
     * @param originWord 原敏感词
     * @param newWord 新敏感词
     * @return 修改结果.
     * @author 杨兆瑞
     */
    @PostMapping("update/{originWord}/{newWord}")
    public Result updateSensitiveWord(@PathVariable("originWord") String originWord, @PathVariable("newWord") String newWord) {
        if (redisTemplate.opsForSet().isMember(Constants.SENSITIVE_KEY, newWord))
            return Result.fail(Result.BAD_REQUEST, "新敏感词词已存在");
        if (newWord.length() < 2 || newWord.length() > 20)
            return Result.fail(Result.BAD_REQUEST, "敏感词长度不符合要求");
        redisTemplate.opsForSet().remove(Constants.SENSITIVE_KEY, originWord);
        redisTemplate.opsForSet().add(Constants.SENSITIVE_KEY, newWord);
        return Result.successMsg("修改敏感词成功");
    }

    /**
     * 敏感词获取.
     * @param sensitiveWordDto 支持模糊搜索. {@link SensitiveWordDto}
     * @return 敏感词列表.
     * @author 杨兆瑞
     */
    @GetMapping("/get")
    public Result getSensitiveWords(SensitiveWordDto sensitiveWordDto) {
        Map<String, Object> data = sensitiveService.getSensitiveWordList(sensitiveWordDto);
        return Result.success("获取敏感词列表成功", data);
    }
}
