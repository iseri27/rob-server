package com.xjtu.dbc.robserver.dynamic.home;


import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;

import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeListDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicMyHomeListDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



/**
 * 动态图主页模块的API,主要包括获取各种动态信息
 * @author 陈邦昕
 */
@RestController
@RequestMapping("/dynamic/home")
public class DynamicHomeAPI {

    @Resource
    private CommonService commonService;
    @Resource
    private DynamicHomeService dynamicHomeService;


    /**
     * 获取用户信息(本人).
     * @param token
     * @return Result(msg, homeDto)
     */
    @GetMapping("uInfo")
    public Result getUInfo(@RequestHeader("Token") String token) {

        Integer userid;

        userid = TokenUtils.getUserInfo(token, commonService).getUserid();

        DynamicHomeDto homeDto = dynamicHomeService.getUserInfo(userid);
        homeDto.setFollow_num(dynamicHomeService.getFollownumByUserid(userid));
        homeDto.setFans_num(dynamicHomeService.getFansnumByUserid(userid));
        homeDto.setDynamic_num(dynamicHomeService.getDynamicnumByUserid(userid));

        //还需要得到用户的粉丝数 动态数
        return Result.success("获取动态主页的个人信息成功!", homeDto);
    }


    /**
     * 获取其他用户的信息.
     * @param Userid
     * @return Result(msg, homeDto)
     */
    @GetMapping("otherInfo")
    public Result getUInfo(Integer Userid) {

        Integer userid;

        userid = Userid;

        DynamicHomeDto homeDto = dynamicHomeService.getUserInfo(userid);
        homeDto.setFollow_num(dynamicHomeService.getFollownumByUserid(userid));
        homeDto.setFans_num(dynamicHomeService.getFansnumByUserid(userid));
        homeDto.setDynamic_num(dynamicHomeService.getDynamicnumByUserid(userid));

        //还需要得到用户的粉丝数 动态数
        return Result.success("获取他人的个人信息成功!", homeDto);
    }



    /**
     * 获取动态列表(只获取用户主页对应用户的动态).
     * @param Userid
     * @return Result(msg, listDto)
     */
    @GetMapping("dList")
    public Result getDList(@RequestParam("Userid") Integer Userid) {

        List<DynamicMyHomeListDto> listDto = dynamicHomeService.getDynamicList(Userid);


        for(int i=0; i<listDto.size();i++){
            listDto.get(i).setIs_search_visible(1); //初始搜索栏可见值为1，表示可见
            listDto.get(i).setVote_type(dynamicHomeService.getVoteTypeByU_A_id(listDto.get(i).getUserid(),listDto.get(i).getDynamicid())); // vote_type表示用户赞踩的情况
        }

        return Result.success("获取动态主页的动态列表成功!", listDto);
    }


    /**
     * 获取动态列表(包括用户自身与其关注的用户的动态).
     * @param Userid
     * @return Result(msg, listDto2)
     */
    @GetMapping("mydList")
    public Result getmyDList(@RequestParam("Userid") Integer Userid) {

        List<DynamicMyHomeListDto> listDto2 = dynamicHomeService.getMyDynamicList(Userid);
        for(int i=0; i<listDto2.size();i++){
            listDto2.get(i).setIs_search_visible(1); //初始搜索栏可见值为1，表示可见
            listDto2.get(i).setVote_type(dynamicHomeService.getVoteTypeByU_A_id(listDto2.get(i).getUserid(),listDto2.get(i).getDynamicid())); // vote_type表示用户赞踩的情况 其中 vote_type的值为 0:未投票  800:赞  801:踩
        }

        return Result.success("获取我的动态主页的动态列表成功!", listDto2);
    }




    /**
     * 删除用户动态.
     * @param articleid
     * @return Result(msg, deleteDto)
     */
    @GetMapping("delete")
    public Result deleteDynamic(@RequestParam("articleid") Integer articleid) {
        Integer deleteDto = dynamicHomeService.deleteDynamic(articleid);
        return Result.success("删除动态成功!", deleteDto);
    }

    /**
     * 获取动态详情信息.
     * @param articleid
     * @return Result(msg, detailDto)
     */
    @GetMapping("detail")
    public Result getDdetail(@RequestParam("articleid") Integer articleid) {

        DynamicMyHomeListDto detailDto = dynamicHomeService.getDynamic(articleid);


        detailDto.setLike_num(dynamicHomeService.getLikenumByAriticleid(detailDto.getDynamicid()));             //获取动态的点赞数
        detailDto.setDislike_num(dynamicHomeService.getDislikenumByAriticleid(detailDto.getDynamicid()));     //获取动态的点踩数
        detailDto.setComment_num(dynamicHomeService.getCommentnumByArticleid(detailDto.getDynamicid()));      //获取动态的评论数
        detailDto.setVote_type(dynamicHomeService.getVoteTypeByU_A_id(detailDto.getUserid(),detailDto.getDynamicid())); // vote_type表示用户赞踩的情况


        return Result.success("获取动态的详情的信息成功!", detailDto);
    }



}
