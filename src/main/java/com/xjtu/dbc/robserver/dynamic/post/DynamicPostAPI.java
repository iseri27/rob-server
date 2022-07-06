package com.xjtu.dbc.robserver.dynamic.post;


import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.dynamic.comment.DynamicCommentService;
import com.xjtu.dbc.robserver.dynamic.comment.entity.DynamicCommentDto;
import com.xjtu.dbc.robserver.dynamic.post.entity.DynamicPostDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dynamic/post")
public class DynamicPostAPI {

    @Resource
    private DynamicPostService dynamicPostService;

    @PostMapping("")
    public Result postDynamic(@RequestBody DynamicPostDto dynamicPostDto) {

        Integer maxId = dynamicPostService.getMaxDynamicId();
        dynamicPostDto.setArticleid(maxId + 1);
        dynamicPostDto.setArticletype(301);  //动态所对应的文本类型为301
        dynamicPostDto.setArticlestatus(401);  //动态的状态设置为401

        dynamicPostService.addDynamic(dynamicPostDto);
        return Result.success("发布动态成功!", dynamicPostDto);

    }


}
