package com.xjtu.dbc.robserver.question.bookmark;

import com.xjtu.dbc.robserver.question.bookmark.entity.BookmarkDto;

public interface BookmarkService {

    /**
     * 添加收藏
     * @param bookmarkDto{articleid 文章id ，userid 用户id}
     */
    void addBookmark(BookmarkDto bookmarkDto);

    /**
     * 当前用户是否收藏该文章
     * @param bookmarkDto{articleid 文章id ，userid 用户id}
     * @return 收藏关系
     */
    int ifBookmark(BookmarkDto bookmarkDto);

    /**
     * 取消收藏该文章
     * @param bookmarkDto{articleid 文章id ，userid 用户id}
     */
    void deleteBookmark(BookmarkDto bookmarkDto);
}
