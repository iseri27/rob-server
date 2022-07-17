package com.xjtu.dbc.robserver.dynamic.vote;


import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeDto;
import com.xjtu.dbc.robserver.dynamic.report.DynamicReportService;
import com.xjtu.dbc.robserver.dynamic.report.entity.DynamicReportDto;
import com.xjtu.dbc.robserver.dynamic.vote.entity.DynamicVoteDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 动态点赞模块的API
 * @author 陈邦昕
 */
@RestController
@RequestMapping("/dynamic/vote")
public class DynamicVoteAPI {

    @Resource
    private DynamicVoteService dynamicVoteService;



    /**
     * 点赞点踩动态
     * @param dynamicVoteDto
     * @return Result(msg, dynamicVoteDto)
     */
    @PostMapping("")
    public Result vote( @RequestBody DynamicVoteDto dynamicVoteDto) {

//        DynamicVoteDto voteDto = dynamicVoteService.doVote(userid,articleid,type);

        if(dynamicVoteDto.getType()==0){  //取消点赞
            dynamicVoteService.deleteVote(dynamicVoteDto);
            return Result.success("取消点赞成功!", dynamicVoteDto);
        }
        else if(dynamicVoteDto.getType()==1){ //取消点踩，改为点赞
            dynamicVoteService.deleteVote(dynamicVoteDto);
            dynamicVoteDto.setVotetype(800);
            dynamicVoteService.doVote(dynamicVoteDto);
            return Result.success("取消点踩，改为点赞成功！", dynamicVoteDto);
        }
        else if(dynamicVoteDto.getType()==2){  //原先没有操作,直接点赞
            dynamicVoteDto.setVotetype(800);
            dynamicVoteService.doVote(dynamicVoteDto);
            return Result.success("点赞成功！", dynamicVoteDto);
        }
        else if(dynamicVoteDto.getType()==3){ //取消点踩
            dynamicVoteService.deleteVote(dynamicVoteDto);
            return Result.success("取消点踩成功！", dynamicVoteDto);
        }
        else if(dynamicVoteDto.getType()==4){  //取消点赞，改为点踩
            dynamicVoteService.deleteVote(dynamicVoteDto);
            dynamicVoteDto.setVotetype(801);
            dynamicVoteService.doVote(dynamicVoteDto);
            return Result.success("取消点赞，改为点踩成功！", dynamicVoteDto);
        }
        else if(dynamicVoteDto.getType()==5){   //原先没有操作,直接点踩
            dynamicVoteDto.setVotetype(801);
            dynamicVoteService.doVote(dynamicVoteDto);
            return Result.success("点踩成功！", dynamicVoteDto);
        }
        else{
            System.out.println("测试type: "+dynamicVoteDto.getType());
            return Result.success("测试原因: ", dynamicVoteDto);
        }




    }


}



