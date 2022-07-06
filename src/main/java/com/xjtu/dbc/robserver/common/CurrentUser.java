package com.xjtu.dbc.robserver.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CurrentUser {
    public static final String SESSION_ATTR_NAME="CurrentUser";

    private Integer userid;
    private String username;
    private String useravatar;

    public CurrentUser() {
    }

    public CurrentUser(Integer userId) {
        this.userid = userId;
    }

    public CurrentUser(Integer userId, String userName, String avatar) {
        this.userid = userId;
        this.username = userName;
        this.useravatar = avatar;
    }
}
