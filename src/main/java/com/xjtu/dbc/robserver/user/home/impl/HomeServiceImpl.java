package com.xjtu.dbc.robserver.user.home.impl;

import com.xjtu.dbc.robserver.common.model.module.Module;
import com.xjtu.dbc.robserver.user.home.HomeService;
import com.xjtu.dbc.robserver.user.home.dao.HomeDao;
import com.xjtu.dbc.robserver.user.home.entity.Menu;
import com.xjtu.dbc.robserver.user.home.entity.UnReadCount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/9 9:49
 */
@Service
@Transactional
public class HomeServiceImpl implements HomeService {
    @Resource
    private HomeDao homeDao;

    /**
     * 根据用户id获取其能访问的菜单
     * @param userid 用户id
     * @return 菜单列表
     */
    @Override
    public List<Menu> getMenus(Integer userid) {

        return homeDao.getMenusByUserId(userid);
    }

    @Override
    public UnReadCount getUnReadNum(Integer userid) {
        return homeDao.getUnReadNum(userid);
    }
}
