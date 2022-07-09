package com.xjtu.dbc.robserver.level;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 等级模块的API，包括经验值及系统代币的增、查.
 * @author 杨兆瑞
 */
@RestController
@RequestMapping(value = "/level")
public class LevelAPI {
    @Resource
    private LevelService levelService;

    @Resource
    private CommonService commonService;

    /**
     * 获取用户经验值.
     * @param token
     * @return Result(msg, exp)
     */
    @GetMapping(value = "/exp/get")
    public Result getExp(@RequestHeader("Token") String token) {
        int userID = TokenUtils.getUserInfo(token, commonService).getUserid();
        int exp = levelService.getExp(userID);
        return Result.success("获取用户经验值成功！", exp);
    }

    @GetMapping("/getWithToken")
    public Result getLevelWithToken(@RequestHeader("Token") String token) {
        int userID = TokenUtils.getUserInfo(token, commonService).getUserid();
        return getLevel(userID);
    }

    @GetMapping("/get/{userID}")
    public Result getLevel(@PathVariable("userID") int userID) {
        int exp = levelService.getExp(userID);
        int value;
        if (exp <= 30) {
            value = 1;
        } else if (exp <= 80) {
            value = 2;
        } else if (exp <= 150) {
            value = 3;
        } else if (exp <= 300) {
            value = 4;
        } else if (exp <= 666) {
            value = 5;
        } else {
            value = 6;
        }

        Level level = new Level(value);
        return Result.success("获取等级成功", level);
    }

    /**
     * 增加用户经验值，比如签到时增加.
     * @param token
     * @param num 增量
     * @return
     */
    @PostMapping("/exp/add/{num}")
    public Result addExp(@RequestHeader("Token") String token, @PathVariable("num") int num) {
        if (num <= 0) {
            return Result.fail(Result.BAD_REQUEST, "经验增加值必须大于0");
        }
        int userID = TokenUtils.getUserInfo(token, commonService).getUserid();
        levelService.addExp(userID, num);
        return Result.successMsg("经验增加成功！");
    }

    /**
     * 获取用户代币数量.
     * @param token
     * @return Result(msg, num)
     */
    @GetMapping(value = "/cans/get")
    public Result getCans(@RequestHeader("Token") String token) {
        int userID = TokenUtils.getUserInfo(token, commonService).getUserid();
        int num = levelService.getCans(userID);
        return Result.success("获取用户代币数量成功！", num);
    }

    /**
     * 改变用户代币数量，如果是减少且减少后小于0则不会变化，并return Result.fail().
     * @param token
     * @param num 增量
     * @return
     */
    @PostMapping("/cans/change/{num}")
    public Result updateCans(@RequestHeader("Token") String token, @PathVariable("num") int num) {
        int userID = TokenUtils.getUserInfo(token, commonService).getUserid();

        if (num < 0) {
            int currentNum = levelService.getCans(userID);
            if (num + currentNum < 0)
                return Result.fail(Result.BAD_REQUEST, "代币数量不足");
        }

        levelService.updateCans(userID, num);
        return Result.successMsg("代币数量改变成功！");
    }
}
