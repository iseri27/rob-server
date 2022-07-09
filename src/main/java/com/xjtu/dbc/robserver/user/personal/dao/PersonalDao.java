package com.xjtu.dbc.robserver.user.personal.dao;

import com.xjtu.dbc.robserver.common.model.article.Article;
import com.xjtu.dbc.robserver.common.model.user.User;

import java.util.List;

public interface PersonalDao {

    void getMyAvatar(Integer uid, String url);

    void changeInformation(User user);

    User checkPassword(User user);

    void changePassword(User user);

    List<Article> getArtical(Integer userid);
}
