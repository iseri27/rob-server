package com.xjtu.dbc.robserver.manage.module.entity;

import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.common.model.module.Module;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class ModuleVO {

    /**
     * 将 module 放置到合适的位置
     * @param moduleVO 模块树的结点
     * @param module 模块
     * @return 成功找到 module 的父模块返回 true; 未找到返回 false
     */
    public static boolean setChildrenTree(ModuleVO moduleVO, Module module) {
        if (moduleVO.getKey() != null && moduleVO.getKey().equals(module.getParentid())) {
            moduleVO.getChildren().add(new ModuleVO(module));
            return true;
        }

        for (ModuleVO moduleVONode : moduleVO.getChildren()) {
            boolean result = setChildrenTree(moduleVONode, module);
            if (result) {
                return true;
            }
        }

        return false;
    }

    private Integer key;
    private String moduleName;
    private String url;
    private String request;
    private Boolean available;
    private List<ModuleVO> children;

    public ModuleVO(Module module) {
        this.key = module.getModuleid();
        this.moduleName = module.getModulename();
        this.url = module.getUrl();
        this.request = module.getRequest();
        this.available = module.getModulestatus() == Constants.MODULE_STATUS_NORMAL;
        this.children =  new ArrayList<>();
    }

}
