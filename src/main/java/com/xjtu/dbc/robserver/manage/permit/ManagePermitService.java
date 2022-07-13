package com.xjtu.dbc.robserver.manage.permit;

public interface ManagePermitService {
    /**
     * 检查模块是否可用
     * @param path 请求路径
     * @return 可用返回 true; 不可用返回 false
     */
    Boolean moduleAvailable(String path);
}
