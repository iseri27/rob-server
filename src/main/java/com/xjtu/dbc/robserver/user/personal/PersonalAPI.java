package com.xjtu.dbc.robserver.user.personal;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.CurrentUser;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.common.model.user.User;
import com.xjtu.dbc.robserver.user.personal.entity.ArticleDto;
import com.xjtu.dbc.robserver.user.personal.entity.Avatar;
import org.bouncycastle.jcajce.provider.symmetric.Serpent;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author Ichabod
 * @version 1.0.2
 * @date 2022/7/7
 */
@RestController
@RequestMapping("/user/personal")
public class PersonalAPI {

    @Resource
    private CommonService commonService;

    @Resource
    private PersonalService personalService;


    @GetMapping("/get")
    public Result getCurrentUser(@RequestHeader("Token") String token){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        Integer userid = currentUser.getUserid();
        return Result.success("获取成功",commonService.getUserWithoutPasswordById(userid));
    }

    /**
     * 修改头像
     * @param avatar 头像
     * @param token 获取id
     * @return 修改成功
     */
    @PostMapping("/avatar")
    public Result getAvatar(@RequestBody Avatar avatar ,@RequestHeader("Token") String token){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        Integer userId = currentUser.getUserid();
        String url = avatar.getUrl();
        personalService.getMyAvatar(userId, url);
        return Result.successMsg("头像修改成功");
    }

    /**
     * 修改用户基本信息，不能修改邮箱密码
     * @param user 存储用户修改的信息
     * @param token 获取id
     * @return 修改成功
     */
    @PostMapping("/information")
    public Result changeInformation(@RequestBody User user ,@RequestHeader("Token") String token){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        user.setUserid(currentUser.getUserid());
        personalService.changeInformation(user);
        return Result.successMsg("个人信息修改成功");
    }

    /**
     * 修改密码前检查原始密码是否正确
     * @param user 存储原始密码
     * @param token 获取id
     * @return 密码正确/错误
     */
    @GetMapping("/password/check")
    public Result checkPassword(User user ,@RequestHeader("Token") String token){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        user.setUserid(currentUser.getUserid());
        if(user.getUserpwd().equals(personalService.checkPassword(user).getUserpwd())){
            return Result.successMsg("原始密码正确");
        }
        else {
            return Result.fail(Result.ERR_CODE_BUSINESS,"密码错误！");
        }
    }

    /**
     * 修改用户密码
     * @param user 存储密码
     * @param token 获取id
     * @return 修改成功
     */
    @PostMapping("/password/change")
    public Result changePassword(@RequestBody User user ,@RequestHeader("Token") String token){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        user.setUserid(currentUser.getUserid());
        personalService.changePassword(user);
        return Result.successMsg("密码修改完成");
    }

    /**
     *
     * @param token 获取当前用户id
     * @return 返回该用户发布的所有博文（包括草稿，审核中，隐藏贴）
     */
    @GetMapping("/article")
    public Result getArtical(@RequestHeader("Token") String token, ArticleDto articleDto){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        articleDto.setUserid(currentUser.getUserid());
        return Result.success("获取成功", personalService.getArtical(articleDto));
    }
}
