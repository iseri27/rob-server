package com.xjtu.dbc.robserver.blog.publish;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.Map;

@RestController
@RequestMapping("/blog")
public class BlogPublishAPI {
    @Resource
    private BlogPublishService blogService;

    @Resource
    private CommonService commonService;

    @GetMapping("/recommend")
    public Result GetBlogList(String data, RequestHeader token) throws Exception {
        //String myid = TokenUtils.getUserInfo(token,commonService).getU_id();
        String dataStr = URLDecoder.decode(data,"UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();

        BlogDto dto = objectMapper.readValue(dataStr, BlogDto.class);

        Map<String, Object> page = blogService.getBlogList(dto);
        return Result.successData(page);
    }

}
