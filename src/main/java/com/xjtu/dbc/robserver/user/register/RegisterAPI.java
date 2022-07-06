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
        int userNameCount = registerService.getUserCountByName(registerDto.getUsername());
        if (userNameCount > 0) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "该用户名已注册");
        }

        int userEmailCount = registerService.getUserCountByEmail(registerDto.getUseremail());
        if (userEmailCount > 0) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "该邮箱已被注册");
        }

        Integer maxId = registerService.getMaxId();
        registerDto.setUserid(maxId + 1);

        registerService.addUser(registerDto);
        return Result.success("注册成功!", registerDto.getUserid());
    }
}
