package com.xjtu.dbc.robserver.question.bookmark;

import com.xjtu.dbc.robserver.question.bookmark.entity.BookmarkDto;

public interface BookmarkService {

    /*
    * 新增收藏
    */
    void addBookmark(BookmarkDto bookmarkDto);

    /*
     * 判断是否收藏
     */
    int ifBookmark(BookmarkDto bookmarkDto);

    /*
     * 删除收藏
     */
    void deleteBookmark(BookmarkDto bookmarkDto);
}
