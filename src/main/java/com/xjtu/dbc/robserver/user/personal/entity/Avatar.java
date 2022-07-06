package com.xjtu.dbc.robserver.user.personal.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class Avatar {
    private MultipartFile multipartFile;
    private String prefix;
    private String url;
}
