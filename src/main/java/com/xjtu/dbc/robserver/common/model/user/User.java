package com.xjtu.dbc.robserver.common.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter
public class User {
    private Integer userid;
    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userbirth;
    private Integer userexp;
    private Integer usersex;
    private String userpwd;
    private String useravatar;
    private String useremail;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date regtime;
    private String userabout;
    private Integer conis;
    private Integer roleid;
    private Integer userstatus;
}
