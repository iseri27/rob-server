package com.xjtu.dbc.robserver.manage.main;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.user.home.HomeService;
import com.xjtu.dbc.robserver.user.home.entity.Menu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/12 8:57
 */
@RestController
@RequestMapping("/manage")
public class ManageAPI {
    @Resource
    private ManageService manageService;

    @Resource
    private CommonService commonService;

    /**
     * 获取管理子菜单
     *
     * @return
     */
    @GetMapping("/menus")
    public Result menus() {
        List<Menu> menuList = manageService.getMenus();
        return Result.success("获得菜单成功", menuList);
    }
}
