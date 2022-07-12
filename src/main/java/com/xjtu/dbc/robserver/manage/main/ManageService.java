package com.xjtu.dbc.robserver.manage.main;

import com.xjtu.dbc.robserver.user.home.entity.Menu;

import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/12 9:00
 */
public interface ManageService {
    List<Menu> getMenus();
}
