package com.xjtu.dbc.robserver.manage.module.impl;

import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.common.model.module.Module;
import com.xjtu.dbc.robserver.manage.module.ManageModuleService;
import com.xjtu.dbc.robserver.manage.module.dao.ManageModuleDao;
import com.xjtu.dbc.robserver.manage.module.entity.ModuleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service @Transactional
public class ManageModuleImpl implements ManageModuleService {
    @Resource
    private ManageModuleDao manageModuleDao;

    /**
     * 根据请求路径获得模块 ID
     * @param path 请求路径
     * @return 模块 ID
     */
    @Override
    public Integer getModuleIdByPath(String path) {
        return manageModuleDao.getModuleIdByPath(path);
    }

    /**
     * 检查模块是否可用
     * @param path 请求路径
     * @return 可用返回 true; 不可用返回 false
     */
    @Override
    public Boolean moduleAvailable(String path) {
        Integer moduleId = manageModuleDao.getModuleIdByPath(path);
        Integer parentId = manageModuleDao.getParentModuleId(moduleId);

        Integer moduleStatus = manageModuleDao.getModuleStatus(moduleId);
        Integer parentModuleStatus = manageModuleDao.getModuleStatus(parentId);

        return moduleStatus != null
                && moduleStatus != Constants.MODULE_STATUS_STOP_USE
                && (parentModuleStatus == null || parentModuleStatus != Constants.MODULE_STATUS_STOP_USE);
    }

    /**
     * 获取某一模块的状态
     * @param moduleId 模块 ID
     * @return 模块的状态
     */
    @Override
    public Integer getModuleStatus(Integer moduleId) {
        return manageModuleDao.getModuleStatus(moduleId);
    }

    /**
     * 获取所有的模块, 并组织成树形
     * @return 树形组织的模块列表
     */
    @Override
    public List<ModuleVO> getModulesTree() {
        List<ModuleVO> moduleVOForest = new ArrayList<>();
        List<Module> modules = manageModuleDao.getModules();

        log.info("Module 列表" + modules);

        for (Module module: modules) {
            if (module.getParentid() == null) {
                moduleVOForest.add(new ModuleVO(module));
            }
        }

        for (Module module: modules) {
            if (module.getParentid() != null) {
                for (ModuleVO moduleVO: moduleVOForest) {
                    boolean result = ModuleVO.setChildrenTree(moduleVO, module);
                    if (result) {
                        break;
                    }
                }
            }
        }
        return moduleVOForest;
    }

    /**
     * 将模块及其子模块设为可以访问
     * @param moduleId 模块 ID
     */
    @Override
    public void setModuleAvailable(Integer moduleId) {
        manageModuleDao.setModuleStatus(moduleId, Constants.MODULE_STATUS_NORMAL);
    }

    /**
     * 将模块及其子模块设为不可访问
     * @param moduleId 模块 ID
     */
    @Override
    public void setModuleUnavailable(Integer moduleId) {
        manageModuleDao.setModuleStatus(moduleId, Constants.MODULE_STATUS_STOP_USE);
    }

    /**
     * 将模块设为保护状态
     *
     * @param moduleId 模块 ID
     */
    @Override
    public void setModuleProtected(Integer moduleId) {
        manageModuleDao.setModuleStatus(moduleId, Constants.MODULE_STATUS_PROTECT);
    }
}
