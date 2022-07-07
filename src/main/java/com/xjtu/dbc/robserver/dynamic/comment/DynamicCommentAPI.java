package com.xjtu.dbc.robserver.dynamic.comment;


import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.dynamic.comment.entity.DynamicCommentDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeListDto;
import com.xjtu.dbc.robserver.dynamic.report.DynamicReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dynamic/comment")
public class DynamicCommentAPI {

    @Resource
    private DynamicCommentService dynamicCommentService;

    /*
     * 动态详情页的评论显示
     * */
    @GetMapping("")
    public Result getCommnent(Integer articleid) {

        List<DynamicCommentDto> listDto = dynamicCommentService.getDynamicCommentList(articleid);

        //获取每条评论的点赞数与点踩数
//        for(int i=0; i<listDto.size();i++){
//            listDto.get(i).setLike_num(dynamicHomeService.getLikenumByAriticleid(listDto.get(i).getArticleid()));
//            listDto.get(i).setDislike_num(dynamicHomeService.getDislikenumByAriticleid(listDto.get(i).getArticleid()));
//        }

        //还需要得到用户的粉丝数 动态数
        return Result.success("获取动态的评论列表成功!", listDto);
    }

}
