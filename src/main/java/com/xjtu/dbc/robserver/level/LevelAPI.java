package com.xjtu.dbc.robserver.level;

import com.xjtu.dbc.robserver.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/level")
public class LevelAPI {
//    @Resource
//    private LevelService levelService;

    @GetMapping(value = "/get")
    public Result getLevel() {
        return Result.success("获取用户等级成功！", 0);
    }
}
