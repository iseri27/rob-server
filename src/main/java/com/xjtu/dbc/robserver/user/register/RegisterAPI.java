package com.xjtu.dbc.robserver.user.register;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/register")
public class RegisterAPI {
    @Resource
    private CommonService commonService;
    @Resource
    private RegisterService registerService;

    @PostMapping("")
    public Result register(@RequestBody String username) {

        return Result.success();
    }
}
