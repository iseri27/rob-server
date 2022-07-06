package com.xjtu.dbc.robserver.common;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan("com.xjtu.dbc.robserver.common.dao")
@ServletComponentScan("com.xjtu.dbc.robserver.common.base")
public class CommonConfig {

    private static ApplicationContext applicationContext;
    private static Environment env;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        CommonConfig.applicationContext = applicationContext;
        CommonConfig.env = CommonConfig.applicationContext.getEnvironment();
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Environment getEnv() {
        return env;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods(RequestMethod.GET.name(), RequestMethod.POST.name(), RequestMethod.PUT.name(), RequestMethod.DELETE.name());
            }

//            /**
//             * 该类对静态资源请求（图片）的路径进行重映射
//             * 如 http://localhost:8080/img/vivy.png 会被 D:/Documents/Projects/MyBlog/static/img/vivy.png 代替
//             * 因此，在数据库中存储的图片路径并不包括 D:/Documents/Projects/MyBlog/static/img/ 这一部分
//             * 而返回给客户端时，将会返回替换之久的路径，即 http...
//             */
//            @Override
//            public void addResourceHandlers(ResourceHandlerRegistry registry) {
//                registry.addResourceHandler("/file/**").addResourceLocations("file:" + Utils.getImgPrefix());
//            }
        };
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
