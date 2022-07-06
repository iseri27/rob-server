package com.xjtu.dbc.robserver.user.login;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.user.login.entity.LoginDto;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/user/login")
public class LoginAPI {
    @Resource
    private CommonService commonService;

    @Resource
    private LoginService loginService;

    @PostMapping("")
    public Result login(@RequestBody LoginDto loginDto) {
        Integer cnt = null;
        Integer userId = null;
        if (loginDto.getUserid() != null) {
            userId = loginDto.getUserid();
        } else if (loginDto.getUseremail() != null) {
            userId = loginService.getUserIdByEmail(loginDto.getUseremail());
        } else if (loginDto.getUsername() != null) {
            userId = loginService.getUserIdByName(loginDto.getUsername());
        } else {
        }

        cnt = loginService.verifyById(userId, loginDto.getUserpwd());
        if (cnt == 1) {
            String token = TokenUtils.loginSign(Integer.toString(loginDto.getUserid()), loginDto.getUserpwd());
            return Result.success("登录成功", token);
        } else {
            return Result.fail(Result.ERR_CODE_BUSINESS, "帐号或密码错误");
        }
    }
}
