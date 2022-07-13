package com.xjtu.dbc.robserver.user.home;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.CurrentUser;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.user.home.entity.Menu;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/9 8:49
 */
@RestController
@RequestMapping("/user/home")
public class HomeAPI {

    @Resource
    private HomeService homeService;

    @Resource
    private CommonService commonService;

    /**
     * 根据当前用户获取导航栏菜单
     *
     * @return
     */
    @GetMapping("/menus")
    public Result menus(@RequestHeader("Token") String token) {
        Integer userId = TokenUtils.getUserInfo(token, commonService).getUserid();
        List<Menu> menuList = homeService.getMenus(userId);
        return Result.success("获得菜单成功", menuList);
    }

    /**
     * 根据当前用户获取未读消息数目
     * @param token
     * @return
     */
    @GetMapping("/count")
    public Result getUnReadNum(@RequestHeader("Token") String token) {
        Integer userId = TokenUtils.getUserInfo(token, commonService).getUserid();
        return Result.success("获取未读消息数成功", homeService.getUnReadNum(userId));
    }

    /**
     * 退出系统
     * @param token
     * @return
     */
    @DeleteMapping("/exit")
    public Result exit(@RequestHeader("Token") String token){
        //在服务端清除缓存的token
        TokenUtils.removeToken(token);
        return Result.successMsg("退出登录成功");
    }
}
