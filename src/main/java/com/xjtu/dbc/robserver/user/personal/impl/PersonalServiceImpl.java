package com.xjtu.dbc.robserver.user.personal.impl;

import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.common.Utils;
import com.xjtu.dbc.robserver.common.model.article.Article;
import com.xjtu.dbc.robserver.common.model.user.User;
import com.xjtu.dbc.robserver.user.personal.PersonalService;
import com.xjtu.dbc.robserver.user.personal.dao.PersonalDao;
import com.xjtu.dbc.robserver.user.personal.entity.ArticleDto;
import com.xjtu.dbc.robserver.user.personal.entity.FollowDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service @Transactional
public class PersonalServiceImpl implements PersonalService {
    @Resource
    private PersonalDao personalDao;
    @Override
    public void getMyAvatar(Integer uid, String url) {
        personalDao.getMyAvatar(uid, url);
    }

    @Override
    public void changeInformation(User user) {
        personalDao.changeInformation(user);
    }

    @Override
    public User checkPassword(User user) {
        return personalDao.checkPassword(user);
    }

    @Override
    public void changePassword(User user) {
        personalDao.changePassword(user);
    }

    @Override
    public Map<String,Object> getArtical(ArticleDto articleDto ) {
        articleDto.setType(Constants.ARTICLE_TYPE_BLOG);
        return Utils.getPage(articleDto, () -> personalDao.getArtical(articleDto));
    }

    @Override
    public Integer getRelationship(Integer myid, Integer userid) {
        return personalDao.getRelationship(myid, userid);
    }

    @Override
    public void follow(Integer myid, Integer userid) {
        disblock(myid,userid);
        personalDao.follow(personalDao.getListid(myid,Constants.USERLIST_FOLLOW),userid);
    }

    @Override
    public void block(Integer myid, Integer userid) {
        disfollow(myid,userid);
        personalDao.follow(personalDao.getListid(myid,Constants.USERLIST_BLACKLIST),userid);
    }

    @Override
    public void disfollow(Integer myid, Integer userid) {
        personalDao.disfollow(personalDao.getListid(myid,Constants.USERLIST_FOLLOW),userid);
    }

    @Override
    public void disblock(Integer myid, Integer userid) {
        personalDao.disfollow(personalDao.getListid(myid,Constants.USERLIST_BLACKLIST),userid);
    }

    @Override
    public void deleteBlog(Integer articleid) {
        personalDao.deleteBlog(articleid);
    }

    @Override
    public List<FollowDto> getFollow(Integer userid, int type) {
        return personalDao.getFollow(personalDao.getListid(userid,type));
    }

    @Override
    public List<FollowDto> getFans(Integer userid) {
        return personalDao.getFans(userid,Constants.USERLIST_FOLLOW);
    }

    @Override
    public Integer getFollowNum(Integer userid) {
        return personalDao.getFollowNum(personalDao.getListid(userid,Constants.USERLIST_FOLLOW));
    }

    @Override
    public Integer getFansNum(Integer userid) {
        return personalDao.getFansNum(userid,Constants.USERLIST_FOLLOW);
    }

    @Override
    public Integer getFavoritesNum(Integer userid) {
        return personalDao.getFavoritesNum(personalDao.getFavoritesid(userid));
    }

    @Override
    public Integer getHistoryNum(Integer userid) {
        return personalDao.getHistoryNum(userid, Constants.HISTORY_BROWSE);
    }

    @Override
    public Integer getHuntNum(Integer userid) {
        return personalDao.getHuntNum(userid,Constants.ARTICLE_TYPE_QUESTION);
    }
}
