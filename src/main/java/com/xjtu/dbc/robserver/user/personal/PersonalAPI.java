package com.xjtu.dbc.robserver.user.personal;

import com.xjtu.dbc.robserver.common.*;
import com.xjtu.dbc.robserver.common.model.article.Article;
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

    @GetMapping("/get/user")
    public Result getUserById(Integer userid){
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


    @GetMapping("/article")
    public Result getArtical( ArticleDto articleDto){
        return Result.success("获取成功", personalService.getArtical(articleDto));
    }

    @GetMapping("/relationship")
    public Result getRelationship(@RequestHeader("Token") String token, User user){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        Integer myid = currentUser.getUserid();
        return Result.success("获取成功",personalService.getRelationship(myid, user.getUserid()));
    }

    @PostMapping("/follow")
    public Result follow(@RequestHeader("Token") String token,@RequestBody User user){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        Integer myid = currentUser.getUserid();
        personalService.follow(myid, user.getUserid());
        return Result.successMsg("成功关注");
    }
    @PostMapping("/block")
    public Result block(@RequestHeader("Token") String token,@RequestBody User user){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        Integer myid = currentUser.getUserid();
        personalService.block(myid, user.getUserid());
        return Result.successMsg("成功关注");
    }
    @PostMapping("/disfollow")
    public Result disfollow(@RequestHeader("Token") String token,@RequestBody User user){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        Integer myid = currentUser.getUserid();
        personalService.disfollow(myid, user.getUserid());
        return Result.successMsg("成功关注");
    }
    @PostMapping("/disblock")
    public Result disblock(@RequestHeader("Token") String token,@RequestBody User user){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        Integer myid = currentUser.getUserid();
        personalService.disblock(myid, user.getUserid());
        return Result.successMsg("成功关注");
    }
    @PostMapping("/delete/blog")
    public Result deleteBlog(@RequestBody Article article){
        personalService.deleteBlog(article.getArticleid());
        return Result.successMsg("删除成功");
    }
    @GetMapping("/get/follow")
    public Result getFollow(User user){
        return Result.success("获取成功", personalService.getFollow(user.getUserid(), Constants.USERLIST_FOLLOW));
    }
    @GetMapping("/get/block")
    public Result getBlock(User user){

        return Result.success("获取成功", personalService.getFollow(user.getUserid(), Constants.USERLIST_BLACKLIST));
    }
}
