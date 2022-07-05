package com.xjtu.dbc.robserver.common;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CurrentUser {
    public static final String SESSION_ATTR_NAME="CurrentUser";
    public static final String IMG_DIR = "D:/Documents/img";
    public static final String DEFAULT_PASSWORD = "111111";
    public static final String AVATAR_PREFIX = "AVATAR_";

    private Integer u_id;
    private String u_name;
    private String u_avatar;


    public CurrentUser() {
    }

    public CurrentUser(Integer userId) {
        this.u_id = userId;
    }

    public CurrentUser(Integer userId, String userName, String avatar) {
        this.u_id = userId;
        this.u_name = userName;
        this.u_avatar = avatar;
    }
}
