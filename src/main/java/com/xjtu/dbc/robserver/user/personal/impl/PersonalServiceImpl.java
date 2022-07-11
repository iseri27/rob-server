package com.xjtu.dbc.robserver.user.personal.impl;

import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.common.Utils;
import com.xjtu.dbc.robserver.common.model.article.Article;
import com.xjtu.dbc.robserver.common.model.user.User;
import com.xjtu.dbc.robserver.user.personal.PersonalService;
import com.xjtu.dbc.robserver.user.personal.dao.PersonalDao;
import com.xjtu.dbc.robserver.user.personal.entity.ArticleDto;
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
        personalDao.disblock(myid,userid);
        personalDao.follow(myid,userid);
    }

    @Override
    public void block(Integer myid, Integer userid) {
        personalDao.disfollow(myid,userid);
        personalDao.block(myid,userid);
    }

    @Override
    public void disfollow(Integer myid, Integer userid) {
        personalDao.disfollow(myid,userid);
    }

    @Override
    public void disblock(Integer myid, Integer userid) {
        personalDao.disblock(myid,userid);
    }
}
