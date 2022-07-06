package com.xjtu.dbc.robserver.common.model.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class User {
    private Integer userid;
    private String username;
    private Date userbirth;
    private Integer userexp;
    private Integer usersex;
    private String userpwd;
    private String useravatar;
    private String useremail;
    private Date regtime;
    private String userabout;
    private Integer conis;
    private Integer roleid;
    private Integer userstatus;
}
