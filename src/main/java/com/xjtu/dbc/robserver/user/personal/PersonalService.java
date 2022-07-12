package com.xjtu.dbc.robserver.user.personal;


import com.xjtu.dbc.robserver.common.model.article.Article;
import com.xjtu.dbc.robserver.common.model.user.User;
import com.xjtu.dbc.robserver.user.personal.entity.ArticleDto;
import com.xjtu.dbc.robserver.user.personal.entity.FollowDto;

import java.util.List;
import java.util.Map;

public interface PersonalService {

    void getMyAvatar(Integer uid, String url);

    void changeInformation(User user);

    User checkPassword(User user);

    void changePassword(User user);

    Map<String,Object> getArtical(ArticleDto articleDto);

    Integer getRelationship(Integer myid,Integer userid);

    void follow(Integer myid,Integer userid);

    void block(Integer myid,Integer userid);

    void disfollow(Integer myid,Integer userid);

    void disblock(Integer myid,Integer userid);

    void deleteBlog(Integer articleid);

    List<FollowDto> getFollow(Integer userid, int type);
}
