package com.xjtu.dbc.robserver.user.forget;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailUtil;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.user.forget.entity.ForgetDto;
import com.xjtu.dbc.robserver.user.register.RegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/forget")
public class ForgetAPI {
    @Resource
    private ForgetService forgetService;
    @Resource
    private RegisterService registerService;

    // 邮箱验证码过期时间, 10 min
    private static int timeout = 10 * 60 * 10000;
    // 自动清理缓存池的间隔, 60 s
    private static int delay = 60 * 10000;

    // 验证码缓存池, 存储的是 <session-id, code> 的键值对
    private static TimedCache<String, String> codeCachePool = new TimedCache<>(timeout);
    static {
        codeCachePool.schedulePrune(delay);
    }

    /**
     * 获取邮箱验证码
     * @param forgetDto {用户名, 用户邮箱, 性别 ...}
     * @return 邮箱验证码
     */
    @PostMapping("/getCode")
    public Result getCode(@RequestBody ForgetDto forgetDto) {
        Integer userEmailCount = registerService.getUserCountByEmail(forgetDto.getUseremail());
        if (userEmailCount <= 0) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "该邮箱未注册!");
        }

        // 获取验证码
        String code = RandomUtil.randomString(6);
        // 将验证码发送到邮箱
        MailUtil.send(forgetDto.getUseremail(), "验证码", code, false);
        // 获取一个 session-id
        String sessionId = IdUtil.simpleUUID();
        // 将 <session-id, 邮箱> 的键值对插入到 codeCachePool 中
        codeCachePool.put(sessionId, code, timeout);
        // 将 session-id 发送回前端
        return Result.success("邮件验证码已发送", sessionId);
    }


    /**
     * 验证邮箱验证码
     * @param forgetDto {..., 邮箱验证码}
     * @return 注册成功返回用户 ID, 注册失败返回提示信息
     */
    @PostMapping("/reset")
    public Result verifyCode(@RequestBody ForgetDto forgetDto) {
        Integer userEmailCount = registerService.getUserCountByEmail(forgetDto.getUseremail());
        if (userEmailCount <= 0) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "该邮箱未注册!");
        }

        String userCode = forgetDto.getCode();
        String sessionId = forgetDto.getSessionid();

        if (userCode == null || userCode.equals("")) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "验证码不能为空!");
        }

        // 从缓存池中获取验证码
        String code = codeCachePool.get(sessionId, false);
        // 验证邮箱验证码失败
        if (code == null || !code.equals(userCode)) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "验证码错误或过期");
        }

        try {
            // 将新用户添加到数据库并返回用户 ID
            forgetService.resetPassword(forgetDto.getUseremail(), forgetDto.getUserpwd());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_SYS, "系统错误");
        }

        return Result.successMsg("重置密码成功!");
    }
}
