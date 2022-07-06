package com.xjtu.dbc.robserver.user.login.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginDto {
    private Integer userid;
    private String username;
    private String useremail;
    private String userpwd;
}
