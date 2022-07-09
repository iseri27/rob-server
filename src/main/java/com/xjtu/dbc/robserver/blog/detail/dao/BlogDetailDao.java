package com.xjtu.dbc.robserver.blog.detail.dao;

import com.xjtu.dbc.robserver.blog.detail.BlogDetailDto;
import com.xjtu.dbc.robserver.common.model.user.User;

import java.util.List;

public interface BlogDetailDao {
    BlogDetailDto selectBlogDetailDtoByArticleid(int articleid);

    List<String> selectTagListByArtileid(int articleid);

    User selectAuthorByArtileid(int articleid);

    String selectCategorynameByArtileid(int articleid);
}
