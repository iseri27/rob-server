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

@RestController
@RequestMapping("/dynamic/home")
public class DynamicHomeAPI {
    @Resource
    private CommonService commonService;
    @Resource
    private DynamicHomeService dynamicHomeService;





    /*
     * 获取动态页信息栏的信息,本接口用于获取登陆用户的
     * */
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


    @GetMapping("otherInfo")
    public Result getUInfo(Integer Userid) {
//        @RequestParam("Userid")
        Integer userid;

        userid = Userid;

        DynamicHomeDto homeDto = dynamicHomeService.getUserInfo(userid);
        homeDto.setFollow_num(dynamicHomeService.getFollownumByUserid(userid));
        homeDto.setFans_num(dynamicHomeService.getFansnumByUserid(userid));
        homeDto.setDynamic_num(dynamicHomeService.getDynamicnumByUserid(userid));

        //还需要得到用户的粉丝数 动态数
        return Result.success("获取他人的个人信息成功!", homeDto);
    }


    /*
     * 获取输入用户id对应的自己的动态列表
     * */
    @GetMapping("dList")
    public Result getDList(@RequestParam("Userid") Integer Userid) {
        System.out.println("测试进入1");
        List<DynamicMyHomeListDto> listDto = dynamicHomeService.getDynamicList(Userid);
        System.out.println("测试进入2");
        //获取每条评论的点赞数与点踩数
        for(int i=0; i<listDto.size();i++){
              System.out.println("测试进入3");
//            listDto.get(i).setLike_num(dynamicHomeService.getLikenumByAriticleid(listDto.get(i).getDynamicid()));
//            listDto.get(i).setDislike_num(dynamicHomeService.getDislikenumByAriticleid(listDto.get(i).getDynamicid()));
//            listDto.get(i).setComment_num(dynamicHomeService.getCommentnumByArticleid(listDto.get(i).getDynamicid()));
            listDto.get(i).setIs_search_visible(1); //初始搜索栏可见值为1，表示可见
            listDto.get(i).setVote_type(dynamicHomeService.getVoteTypeByU_A_id(listDto.get(i).getUserid(),listDto.get(i).getDynamicid())); // vote_type表示用户赞踩的情况
        }

        //还需要得到用户的粉丝数 动态数
        return Result.success("获取动态主页的动态列表成功!", listDto);
    }





    /*
     * 动态详情页面
     * */
    @GetMapping("detail")
    public Result getDdetail(@RequestParam("articleid") Integer articleid) {

        DynamicMyHomeListDto detailDto = dynamicHomeService.getDynamic(articleid);

        //获取动态的点赞数与点踩数
        detailDto.setLike_num(dynamicHomeService.getLikenumByAriticleid(detailDto.getDynamicid()));
        detailDto.setDislike_num(dynamicHomeService.getDislikenumByAriticleid(detailDto.getDynamicid()));
        detailDto.setComment_num(dynamicHomeService.getCommentnumByArticleid(detailDto.getDynamicid()));
        detailDto.setVote_type(dynamicHomeService.getVoteTypeByU_A_id(detailDto.getUserid(),detailDto.getDynamicid())); // vote_type表示用户赞踩的情况


        return Result.success("获取动态的详情的信息成功!", detailDto);
    }


    /*
     * 访问的自己的动态主页时调用的接口，可以看到自己的动态以及自己关注的人的主页，有一定问题
     * */
    @GetMapping("mydList")
    public Result getmyDList(@RequestParam("Userid") Integer Userid) {


        List<DynamicMyHomeListDto> listDto2 = dynamicHomeService.getMyDynamicList(Userid);

        //获取每条评论的点赞数与点踩数
        for(int i=0; i<listDto2.size();i++){
            listDto2.get(i).setIs_search_visible(1); //初始搜索栏可见值为1，表示可见
            listDto2.get(i).setVote_type(dynamicHomeService.getVoteTypeByU_A_id(listDto2.get(i).getUserid(),listDto2.get(i).getDynamicid())); // vote_type表示用户赞踩的情况 其中 vote_type的值为 0:未投票  800:赞  801:踩
        }

        //还需要得到用户的粉丝数 动态数
        return Result.success("获取我的动态主页的动态列表成功!", listDto2);
    }





}
