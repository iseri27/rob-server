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
public class ManagePermitModuleImpl implements ManageModuleService {
    @Resource
    private ManageModuleDao manageModuleDao;

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

        return moduleStatus       != null
                && moduleStatus       == Constants.MODULE_STATUS_NORMAL
                && !(parentModuleStatus != null && parentModuleStatus != Constants.MODULE_STATUS_NORMAL);
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
                    if (moduleVO.getKey().equals(module.getParentid())) {
                        moduleVO.getChildren().add(new ModuleVO(module));
                    }
                }
            }
        }
        return moduleVOForest;
    }
}
