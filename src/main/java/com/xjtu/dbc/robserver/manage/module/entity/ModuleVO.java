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
