package com.xjtu.dbc.robserver.blog.reply;

import com.xjtu.dbc.robserver.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController//本注解说明本类的对象将作为受spring容器管理的对象，并且说明这是一个控制器组件(Bean)
@RequestMapping("/blog")//本注解说明本控制器内的所有路径都以之开头
public class BlogReplyAPI {

}
