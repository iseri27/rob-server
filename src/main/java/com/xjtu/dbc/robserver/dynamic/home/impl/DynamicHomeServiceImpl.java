package com.xjtu.dbc.robserver.dynamic.home.impl;


import com.xjtu.dbc.robserver.dynamic.home.DynamicHomeService;
import com.xjtu.dbc.robserver.dynamic.home.dao.DynamicHomeDao;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeListDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicMyHomeListDto;
import com.xjtu.dbc.robserver.user.register.dao.RegisterDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class DynamicHomeServiceImpl implements DynamicHomeService {

    @Resource
    private DynamicHomeDao dynamicHomeDao;

    @Override
    public DynamicHomeDto getUserInfo(Integer userid){
        return dynamicHomeDao.getUserInfo(userid);
    }

    @Override
    public List<DynamicMyHomeListDto> getDynamicList(Integer userid){
        System.out.println("测试输出的userid: " +  userid );
        return dynamicHomeDao.getDynamicList(userid);
    }

    @Override
    public List<DynamicMyHomeListDto> getMyDynamicList(Integer userid){
        System.out.println("测试进入2");
        return dynamicHomeDao.getMyDynamicList(userid);
    }




    @Override
    public DynamicMyHomeListDto getDynamic(Integer dynamicid){
        return dynamicHomeDao.getDynamic(dynamicid);
    }

    @Override
    public Integer deleteDynamic(Integer articleid){
        return dynamicHomeDao.deleteDynamic(articleid);
    }


    @Override
    public int getFollownumByUserid(Integer userid){
        return  dynamicHomeDao.getFollownumByUserid(userid);
    }

    @Override
    public int getFansnumByUserid(Integer userid){
        return  dynamicHomeDao.getFansnumByUserid(userid);
    }

    @Override
    public int getDynamicnumByUserid(Integer userid){
        return  dynamicHomeDao.getDynamicnumByUserid(userid);
    }



    @Override
    public int getLikenumByAriticleid(Integer articleid){
        return dynamicHomeDao.getLikenumByAriticleid(articleid);
    }

    @Override
    public int getDislikenumByAriticleid(Integer articleid){
        return dynamicHomeDao.getDislikenumByAriticleid(articleid);
    }

    @Override
    public int getCommentnumByArticleid(Integer articleid){
        return dynamicHomeDao.getCommentnumByArticleid(articleid);
    }



    /**
     * 判断是否在黑名单中
     * @param userid,articleid
     */
    @Override
    public Boolean is_in_blacklist( Integer userid,Integer loginid) {
        System.out.println("测试进入1 :"+ dynamicHomeDao.is_in_blacklist(userid,loginid));
        if( dynamicHomeDao.is_in_blacklist(userid,loginid) >=1){
            return  true;
        }
        else{
            return false;
        }

    }

    @Override
    public int getVoteTypeByU_A_id(Integer userid,Integer articleid){
        if(dynamicHomeDao.getVoteTypeByU_A_id(userid,articleid) !=null){
            return dynamicHomeDao.getVoteTypeByU_A_id(userid,articleid);
        }
        else{
            return 0;
        }

    }

}
