package com.xjtu.dbc.robserver.user.register;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailUtil;
import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.user.register.entity.RegisterDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/user/register")
public class RegisterAPI {
    @Resource
    private CommonService commonService;
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
     * @param registerDto {用户名, 用户邮箱, 性别 ...}
     * @return 邮箱验证码
     */
    @PostMapping("/getCode")
    public Result getCode(@RequestBody RegisterDto registerDto) {
        Result result = checkUserInfo(registerDto);

        if (result.getCode() != Result.SUCCESS_CODE) {
            return result;
        }

        // 获取验证码
        String code = RandomUtil.randomString(6);
        // 将验证码发送到邮箱
        MailUtil.send(registerDto.getUseremail(), "验证码", code, false);
        // 获取一个 session-id
        String sessionId = IdUtil.simpleUUID();
        // 将 <session-id, 邮箱> 的键值对插入到 codeCachePool 中
        codeCachePool.put(sessionId, code, timeout);
        // 将 session-id 发送回前端
        return Result.success("邮件验证码已发送", sessionId);
    }

    /**
     * 验证邮箱验证码
     * @param registerDto {..., 邮箱验证码}
     * @return 注册成功返回用户 ID, 注册失败返回提示信息
     */
    @PostMapping("")
    public Result verifyCode(@RequestBody RegisterDto registerDto) {
        Result result = checkUserInfo(registerDto);

        if (result.getCode() != Result.SUCCESS_CODE) {
            return result;
        }

        String userCode = registerDto.getCode();
        String sessionId = registerDto.getSessionid();

        if (userCode == null || userCode.equals("")) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "验证码不能为空!");
        }

        // 从缓存池中获取验证码
        String code = codeCachePool.get(sessionId, false);
        // 验证邮箱验证码失败
        if (code == null || !code.equals(userCode)) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "验证码错误或过期");
        }

        // 新用户的 ID
        Integer userId;

        try {
            // 将新用户添加到数据库并返回用户 ID
            registerService.addUser(registerDto);
            userId = registerDto.getUserid();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_SYS, "系统错误");
        }

        // 为用户创建关注列表、粉丝列表、私信列表与黑名单
        registerService.createUserList(userId, Constants.USERLIST_FOLLOW);
        registerService.createUserList(userId, Constants.USERLIST_FANS);
        registerService.createUserList(userId, Constants.USERLIST_BLACKLIST);
        registerService.createUserList(userId, Constants.USERLIST_CHAT);
        // 为用户创建收藏夹
        registerService.createBookmark(userId);

        return Result.success("注册成功！", userId);
    }

    private Result checkUserInfo(RegisterDto registerDto) {
        int userNameCount = registerService.getUserCountByName(registerDto.getUsername());
        if (userNameCount > 0) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "该用户名已注册");
        }

        int userEmailCount = registerService.getUserCountByEmail(registerDto.getUseremail());
        if (userEmailCount > 0) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "该邮箱已被注册");
        }

        return Result.success();
    }




}
