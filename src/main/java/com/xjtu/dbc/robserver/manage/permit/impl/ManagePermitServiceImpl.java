package com.xjtu.dbc.robserver.manage.permit.impl;

import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.manage.permit.ManagePermitService;
import com.xjtu.dbc.robserver.manage.permit.dao.ManagePermitDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service @Transactional
public class ManagePermitServiceImpl implements ManagePermitService {
    @Resource
    private ManagePermitDao managePermitDao;

    /**
     * 检查模块是否可用
     * @param path 请求路径
     * @return 可用返回 true; 不可用返回 false
     */
    @Override
    public Boolean moduleAvailable(String path) {
        Integer moduleId = managePermitDao.getModuleIdByPath(path);
        Integer parentId = managePermitDao.getParentModuleId(moduleId);

        Integer moduleStatus = managePermitDao.getModuleStatus(moduleId);
        Integer parentModuleStatus = managePermitDao.getModuleStatus(parentId);

        return moduleStatus       != null
                && moduleStatus       == Constants.MODULE_STATUS_NORMAL
                && !(parentModuleStatus != null && parentModuleStatus != Constants.MODULE_STATUS_NORMAL);
    }
}
