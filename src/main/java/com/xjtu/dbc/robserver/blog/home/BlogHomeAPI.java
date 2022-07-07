package com.xjtu.dbc.robserver.blog.home;

import com.xjtu.dbc.robserver.common.CommonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/blog/home")
public class BlogHomeAPI {
    @Resource
    private CommonService commonService;
}
