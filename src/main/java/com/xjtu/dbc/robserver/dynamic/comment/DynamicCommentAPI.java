package com.xjtu.dbc.robserver.dynamic.comment;


import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.dynamic.comment.entity.DynamicCommentDto;
import com.xjtu.dbc.robserver.dynamic.home.DynamicHomeService;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeListDto;
import com.xjtu.dbc.robserver.dynamic.report.DynamicReportService;
import com.xjtu.dbc.robserver.dynamic.report.entity.DynamicReportDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dynamic/comment")
public class DynamicCommentAPI {

    @Resource
    private DynamicCommentService dynamicCommentService;

    @Resource
    private DynamicHomeService dynamicHomeService;

    /*
     * 动态详情页的评论显示
     * */
    @GetMapping("")
    public Result getCommnent(Integer articleid) {

        System.out.println("测试articleid: "+articleid);
        List<DynamicCommentDto> listDto = dynamicCommentService.getDynamicCommentList(articleid);

        //获取每条评论的点赞数与点踩数
        //这里调用了之前在DynamicHome中写的赞数与踩数的统计的方法
        for(int i=0; i<listDto.size();i++){
            listDto.get(i).setLike_num(dynamicHomeService.getLikenumByAriticleid(listDto.get(i).getArticleid()));
            listDto.get(i).setDislike_num(dynamicHomeService.getDislikenumByAriticleid(listDto.get(i).getArticleid()));
        }

        //还需要得到用户的粉丝数 动态数
        return Result.success("获取动态的评论列表成功!", listDto);
    }


    @PostMapping("publishcomment")
    public Result publishComment(@RequestBody DynamicCommentDto dynamicCommentDto) {

        Integer maxId = dynamicCommentService.getMaxCommentId();
        dynamicCommentDto.setArticleid(maxId + 1);
        dynamicCommentDto.setArticletype(303);  //评论所对应的文本类型为303
        dynamicCommentDto.setArticlestatus(402);  //评论的状态设置为402

        dynamicCommentService.addComment(dynamicCommentDto);
        return Result.success("发布动态的评论成功!", dynamicCommentDto);
    }

}
