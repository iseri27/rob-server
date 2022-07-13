package com.xjtu.dbc.robserver.manage.permit.dao;

public interface ManagePermitDao {

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
}
