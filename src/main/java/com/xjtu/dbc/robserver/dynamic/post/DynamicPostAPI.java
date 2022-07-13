package com.xjtu.dbc.robserver.dynamic.post;


import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.dynamic.comment.DynamicCommentService;
import com.xjtu.dbc.robserver.dynamic.comment.entity.DynamicCommentDto;
import com.xjtu.dbc.robserver.dynamic.home.DynamicHomeService;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeDto;
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

    @Resource
    private DynamicHomeService dynamicHomeService;


    /**
     * 发布用户动态
     * @param dynamicPostDto
     * @return Result(msg, dynamicPostDto)
     */
    @PostMapping("")
    public Result postDynamic(@RequestBody DynamicPostDto dynamicPostDto) {


        DynamicHomeDto authorDto = dynamicHomeService.getUserInfo(dynamicPostDto.getAuthorid());

        if(authorDto.getUserstatus()==201 || authorDto.getUserstatus()==202){  //如果当前用户被禁言或封禁
            return Result.fail(Result.ERR_CODE_BUSINESS, "当前用户状态不可发布动态！");
        }

        Integer maxId = dynamicPostService.getMaxDynamicId();
        dynamicPostDto.setArticleid(maxId + 1);
        dynamicPostDto.setArticletype(301);  //动态所对应的文本类型为301
        dynamicPostDto.setArticlestatus(402);  //动态的状态设置为402

        dynamicPostService.addDynamic(dynamicPostDto);
        return Result.success("发布动态成功!", dynamicPostDto);

    }


}
