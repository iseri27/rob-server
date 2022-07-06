package com.xjtu.dbc.robserver.user.register;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.user.register.entity.RegisterDto;
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
    public Result register(@RequestBody RegisterDto registerDto) {
        if (registerDto == null) {
            System.out.println("为什么是 NULL ? ");
            return Result.fail(Result.ERR_CODE_BUSINESS, "NULL");
        }
        int cnt = registerService.getUserCountByName(registerDto.getUsername());
        if (cnt > 0) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "该用户名已注册");
        }

        Integer maxId = registerService.getMaxId();
        registerDto.setUserid(maxId + 1);

        registerService.addUser(registerDto);
        return Result.success();
    }
}
