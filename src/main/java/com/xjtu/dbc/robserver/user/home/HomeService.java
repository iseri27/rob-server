package com.xjtu.dbc.robserver.user.home;

import com.xjtu.dbc.robserver.common.CurrentUser;
import com.xjtu.dbc.robserver.user.home.entity.Menu;

import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/9 8:49
 */
public interface HomeService {
    List<Menu> getMenus(Integer userid);
}
