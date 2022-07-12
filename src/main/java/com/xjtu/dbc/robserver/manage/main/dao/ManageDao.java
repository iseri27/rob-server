package com.xjtu.dbc.robserver.manage.main.dao;

import com.xjtu.dbc.robserver.user.home.entity.Menu;

import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/12 9:02
 */
public interface ManageDao {
    List<Menu> getManageMenus();
}
