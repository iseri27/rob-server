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

        List<DynamicMyHomeListDto> listDto = dynamicHomeService.getDynamicList(Userid);

        //获取每条评论的点赞数与点踩数
        for(int i=0; i<listDto.size();i++){
            listDto.get(i).setLike_num(dynamicHomeService.getLikenumByAriticleid(listDto.get(i).getArticleid()));
            listDto.get(i).setDislike_num(dynamicHomeService.getDislikenumByAriticleid(listDto.get(i).getArticleid()));
            listDto.get(i).setComment_num(dynamicHomeService.getCommentnumByArticleid(listDto.get(i).getArticleid()));
            listDto.get(i).setIs_search_visible(1); //初始搜索栏可见值为1，表示可见
        }

        //还需要得到用户的粉丝数 动态数
        return Result.success("获取动态主页的动态列表成功!", listDto);
    }

    @GetMapping("followDList")
    public Result getFollowDList(@RequestParam("Userid") Integer Userid) {

        List<DynamicMyHomeListDto> listDto3 = dynamicHomeService.getFollowDynamicList(Userid);

        //获取每条评论的点赞数与点踩数
        for(int i=0; i<listDto3.size();i++){
            listDto3.get(i).setLike_num(dynamicHomeService.getLikenumByAriticleid(listDto3.get(i).getArticleid()));
            listDto3.get(i).setDislike_num(dynamicHomeService.getDislikenumByAriticleid(listDto3.get(i).getArticleid()));
            listDto3.get(i).setComment_num(dynamicHomeService.getCommentnumByArticleid(listDto3.get(i).getArticleid()));
            listDto3.get(i).setIs_search_visible(1); //初始搜索栏可见值为1，表示可见
        }

        //还需要得到用户的粉丝数 动态数
        return Result.success("获取动态主页的动态列表成功!", listDto3);
    }



    /*
     * 动态详情页面
     * */
    @GetMapping("detail")
    public Result getDdetail(@RequestParam("articleid") Integer articleid) {

        DynamicMyHomeListDto detailDto = dynamicHomeService.getDynamic(articleid);

        //获取动态的点赞数与点踩数
        detailDto.setLike_num(dynamicHomeService.getLikenumByAriticleid(detailDto.getArticleid()));
        detailDto.setDislike_num(dynamicHomeService.getDislikenumByAriticleid(detailDto.getArticleid()));
        detailDto.setComment_num(dynamicHomeService.getCommentnumByArticleid(detailDto.getArticleid()));



        return Result.success("获取动态的详情的信息成功!", detailDto);
    }


    /*
     * 访问的自己的动态主页时调用的接口，可以看到自己的动态以及自己关注的人的主页，有一定问题
     * */
    @GetMapping("mydList")
    public Result getmyDList(@RequestParam("Userid") Integer Userid) {


//        Integer userid;
//        userid = TokenUtils.getUserInfo(token, commonService).getUserid();

        List<DynamicMyHomeListDto> listDto2 = dynamicHomeService.getMyDynamicList(Userid);

        //获取每条评论的点赞数与点踩数
        for(int i=0; i<listDto2.size();i++){
            listDto2.get(i).setLike_num(dynamicHomeService.getLikenumByAriticleid(listDto2.get(i).getArticleid()));
            listDto2.get(i).setDislike_num(dynamicHomeService.getDislikenumByAriticleid(listDto2.get(i).getArticleid()));
            listDto2.get(i).setComment_num(dynamicHomeService.getCommentnumByArticleid(listDto2.get(i).getArticleid()));
            listDto2.get(i).setIs_search_visible(1); //初始搜索栏可见值为1，表示可见
        }

        //还需要得到用户的粉丝数 动态数
        return Result.success("获取我的动态主页的动态列表成功!", listDto2);
    }




}
