package com.xjtu.dbc.robserver.manage.main.impl;

import com.xjtu.dbc.robserver.manage.main.ManageService;
import com.xjtu.dbc.robserver.manage.main.dao.ManageDao;
import com.xjtu.dbc.robserver.user.home.entity.Menu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/12 9:01
 */
@Transactional@Service
public class ManageServiceImpl implements ManageService {
    @Resource
    ManageDao manageDao;
    @Override
    public List<Menu> getMenus() {
        return manageDao.getManageMenus();
    }
}
