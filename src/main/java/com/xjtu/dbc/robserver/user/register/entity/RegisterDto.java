package com.xjtu.dbc.robserver.user.register.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class RegisterDto {
    private Integer userid;
    private String userpwd;
    private String username;
    private String usersex;
    private String useravatar;
    private String useremail;
    private Integer roleid;
    private Integer userstatus;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userbirth;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date regtime;
}
