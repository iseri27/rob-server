package com.xjtu.dbc.robserver.manage.module.dao;

import com.xjtu.dbc.robserver.common.model.module.Module;
import com.xjtu.dbc.robserver.manage.module.entity.ModuleDto;

import java.util.List;

public interface ManageModuleDao {

    /**
     * 根据请求路径获取模块的 ID
     * @param path 请求路径
     * @return 模块 ID
     */
    Integer getModuleIdByPath(String path);

    /**
     * 获取父模块的 ID
     * @param moduleId 模块 ID
     * @return 该模块的父模块 ID
     */
    Integer getParentModuleId(Integer moduleId);

    /**
     * 获取模块的状态
     * @param moduleId 模块 ID
     * @return 模块状态
     */
    Integer getModuleStatus(Integer moduleId);

    /**
     * 获取所有的 module
     * @return 所有 module 组成的的列表
     */
    List<Module> getModules();

    /**
     * 设置模块及其子模块的状态
     * @param moduleId 模块 ID
     * @param moduleStatus 模块状态
     */
    void setModuleStatus(Integer moduleId, Integer moduleStatus);

    /**
     * 新增模块
     * @param moduleDto 模块参数
     */
    void addModule(ModuleDto moduleDto);

    /**
     * 修改模块
     * @param moduleDto 模块参数
     */
    void updateModule(ModuleDto moduleDto);

    /**
     * 删除模块
     * @param moduleId
     */
    void deleteModule(Integer moduleId);
}
