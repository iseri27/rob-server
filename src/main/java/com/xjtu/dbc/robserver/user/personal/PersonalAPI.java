package com.xjtu.dbc.robserver.user.personal;

import com.xjtu.dbc.robserver.common.*;
import com.xjtu.dbc.robserver.common.model.article.Article;
import com.xjtu.dbc.robserver.common.model.user.User;
import com.xjtu.dbc.robserver.user.personal.entity.ArticleDto;
import com.xjtu.dbc.robserver.user.personal.entity.Avatar;
import com.xjtu.dbc.robserver.user.personal.entity.NumDto;
import org.bouncycastle.jcajce.provider.symmetric.Serpent;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author 李家乐
 * @version 1.0.8
 * @date 2022/7/12
 */
@RestController
@RequestMapping("/user/personal")
public class PersonalAPI {

    @Resource
    private CommonService commonService;

    @Resource
    private PersonalService personalService;


    /**
     * 获取当前用户信息
     * @param token 用户id
     * @return 当前用户信息，不含密码
     */
    @GetMapping("/get")
    public Result getCurrentUser(@RequestHeader("Token") String token){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        Integer userid = currentUser.getUserid();
        return Result.success("获取成功",commonService.getUserWithoutPasswordById(userid));
    }

    /**
     * 获取页面用户信息
     * @param user 用户id
     * @return 用户信息，不含密码
     */
    @GetMapping("/get/user")
    public Result getUserById(User user){
        return Result.success("获取成功",commonService.getUserWithoutPasswordById(user.getUserid()));
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
        personalService.useCoins(user.getUserid());
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
     * 获取一个用户写的博文
     * @param articleDto 文章分页信息
     * @return 分页文章
     */
    @GetMapping("/article")
    public Result getArtical( ArticleDto articleDto){
        return Result.success("获取成功", personalService.getArtical(articleDto));
    }

    /**
     * 判断两个用户的关系（关注和拉黑与否）
     * @param token 获取当前用户id
     * @param user 页面用户id
     * @return 返回二者关系，具体参照Contents的常量值
     */
    @GetMapping("/relationship")
    public Result getRelationship(@RequestHeader("Token") String token, User user){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        Integer myid = currentUser.getUserid();
        return Result.success("获取成功",personalService.getRelationship(myid, user.getUserid()));
    }

    /**
     * 关注用户
     * @param token 当前用户id
     * @param user 要关注的用户的id
     * @return 成功关注
     */
    @PostMapping("/follow")
    public Result follow(@RequestHeader("Token") String token,@RequestBody User user){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        Integer myid = currentUser.getUserid();
        personalService.follow(myid, user.getUserid());
        return Result.successMsg("成功关注");
    }

    /**
     * 拉黑用户
     * @param token 当前用户id
     * @param user 要拉黑的用户的id
     * @return 成功拉黑
     */
    @PostMapping("/block")
    public Result block(@RequestHeader("Token") String token,@RequestBody User user){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        Integer myid = currentUser.getUserid();
        personalService.block(myid, user.getUserid());
        return Result.successMsg("成功拉黑");
    }

    /**
     * 取关用户
     * @param token 当前用户id
     * @param user 要取关的用户的id
     * @return 成功取关
     */
    @PostMapping("/disfollow")
    public Result disfollow(@RequestHeader("Token") String token,@RequestBody User user){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        Integer myid = currentUser.getUserid();
        personalService.disfollow(myid, user.getUserid());
        return Result.successMsg("成功取关");
    }

    /**
     * 取黑用户
     * @param token 当前用户id
     * @param user 要取黑的用户的id
     * @return 成功取黑
     */
    @PostMapping("/disblock")
    public Result disblock(@RequestHeader("Token") String token,@RequestBody User user){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        Integer myid = currentUser.getUserid();
        personalService.disblock(myid, user.getUserid());
        return Result.successMsg("成功取黑");
    }

    /**
     * 删除一个博文
     * @param article 获取文章id
     * @return 删除成功
     */
    @PostMapping("/delete/blog")
    public Result deleteBlog(@RequestBody Article article){
        personalService.deleteBlog(article.getArticleid());
        return Result.successMsg("删除成功");
    }

    /**
     * 获取关注列表
     * @param user 页面用户id
     * @return 关注列表
     */
    @GetMapping("/get/follow")
    public Result getFollow(User user){
        return Result.success("获取成功", personalService.getFollow(user.getUserid(), Constants.USERLIST_FOLLOW));
    }

    /**
     * 获取黑名单
     * @param user 页面用户id
     * @return 黑名单
     */
    @GetMapping("/get/block")
    public Result getBlock(User user){
        return Result.success("获取成功", personalService.getFollow(user.getUserid(), Constants.USERLIST_BLACKLIST));
    }

    /**
     * 获取粉丝列表
     * @param user 页面用户id
     * @return 粉丝列表
     */
    @GetMapping("/get/fans")
    public Result getFans(User user){
        return Result.success("获取成功", personalService.getFans(user.getUserid()));
    }

    /**
     * 获取页面的一些数据信息，具体查看NumDto
     * @param user 页面用户id
     * @return NumDto中的对应数据
     */
    @GetMapping("/get/num")
    public Result getNum(User user){
        NumDto numDto = new NumDto();
        numDto.setFollow(personalService.getFollowNum(user.getUserid()));
        numDto.setFans(personalService.getFansNum(user.getUserid()));
        numDto.setFavorites(personalService.getFavoritesNum(user.getUserid()));
        numDto.setHistory(personalService.getHistoryNum(user.getUserid()));
        numDto.setHunt(personalService.getHuntNum(user.getUserid()));
        return Result.success("获取成功",numDto);
    }

    @GetMapping("/get/favorites")
    public Result getFavorites(ArticleDto articleDto){
        return Result.success("获取成功",personalService.getFavorites(articleDto));
    }

    @PostMapping("/delete/favorite")
    public Result deleteFavorite(@RequestHeader("Token") String token,@RequestBody Article article){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        personalService.deleteFavorite(currentUser.getUserid(),article.getArticleid());
        return Result.successMsg("成功取消收藏");
    }

    @GetMapping("/get/history")
    public Result getHistory(ArticleDto articleDto){
        return Result.success("获取成功",personalService.getHistory(articleDto));
    }

    @PostMapping("/delete/history")
    public Result deleteHistory(@RequestHeader("Token") String token,@RequestBody Article article){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        personalService.deleteHistory(currentUser.getUserid(),article.getArticleid());
        return Result.successMsg("成功取消收藏");
    }

    @GetMapping("/checkBlock")
    public Result checkBlock(@RequestHeader("Token") String token, User user){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        System.out.println(personalService.getRelationship( user.getUserid(), currentUser.getUserid()));
        if(personalService.getRelationship( user.getUserid(), currentUser.getUserid()) != null && personalService.getRelationship( user.getUserid(), currentUser.getUserid()) == Constants.USERLIST_BLACKLIST){
            return Result.success("你被对方拉黑了",false);
        }
        else return Result.success("你没被拉黑",false);
    }

}
