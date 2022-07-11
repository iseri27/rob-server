package com.xjtu.dbc.robserver.user.home.dao;

import com.xjtu.dbc.robserver.common.model.module.Module;
import com.xjtu.dbc.robserver.user.home.entity.Menu;

import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/9 9:49
 */
public interface HomeDao {
    List<Menu> getMenusByUserId(Integer userid);
    Integer getUnReadNum(Integer userid);
}
