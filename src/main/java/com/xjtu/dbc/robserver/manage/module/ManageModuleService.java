package com.xjtu.dbc.robserver.manage.module;

import com.xjtu.dbc.robserver.manage.module.entity.ModuleVO;

import java.util.List;

public interface ManageModuleService {
    /**
     * 检查模块是否可用
     * @param path 请求路径
     * @return 可用返回 true; 不可用返回 false
     */
    Boolean moduleAvailable(String path);

    /**
     * 获取所有的模块, 并组织成树形
     * @return 树形组织的模块列表
     */
    List<ModuleVO> getModulesTree();

    /**
     * 将模块及其子模块设为可以访问
     * @param moduleId 模块 ID
     */
    void setModuleAvailable(Integer moduleId);

    /**
     * 将模块及其子模块设为不可访问
     * @param moduleId 模块 ID
     */
    void setModuleUnavailable(Integer moduleId);
}
