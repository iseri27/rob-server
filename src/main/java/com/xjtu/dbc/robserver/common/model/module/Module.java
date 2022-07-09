package com.xjtu.dbc.robserver.common.model.module;

import lombok.Data;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/9 8:52
 */
@Data
public class Module {
    private Integer moduleid;
    private String modulename;
    private String url;
    private String request;
    private Integer parenid;
}
