package com.xjtu.dbc.robserver.dynamic.home;

import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeListDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicMyHomeListDto;

import java.util.List;

public interface DynamicHomeService {

    /**
     * 根据主页用户的编号获取用户信息
     * @param userid 用户编号
     * @return 用户的信息
     */
    DynamicHomeDto getUserInfo(Integer userid);

    /**
     * 根据主页用户的编号获取用户的动态列表,用于他人主页的动态的展示
     * @param userid 用户编号
     * @return 用户的动态列表
     */
    List<DynamicHomeListDto> getDynamicList(Integer userid);


    /**
     * 根据主页用户的编号获取用户的动态列表,用于自己主页的动态的展示
     * @param userid 用户编号
     * @return 用户的动态列表
     */
    List<DynamicMyHomeListDto> getMyDynamicList(Integer userid);

    /**
     * 根据用户的编号来获取该用户的粉丝数
     * @param userid  用户的编号
     * @return 该用户的粉丝数
     */
    int getFansnumByUserid(Integer userid);



    /**
     * 根据用户的编号来获取该用户的动态数
     * @param userid  用户的编号
     * @return 该用户的动态数
     */
    int getDynamicnumByUserid(Integer userid);




    /**
     * 根据动态的编号来获取该动态的点赞数
     * @param articleid  动态的编号
     * @return 该动态获得的赞数
     */
    int getLikenumByAriticleid(Integer articleid);


    /**
     * 根据动态的编号来获取该动态的点踩数
     * @param articleid  动态的编号
     * @return 该动态获得的踩数
     */
    int getDislikenumByAriticleid(Integer articleid);


    /**
     * 根据动态的编号来获取该动态的评论数
     * @param articleid  动态的编号
     * @return 该动态获得的评论数
     */
    int getCommentnumByArticleid(Integer articleid);


}
