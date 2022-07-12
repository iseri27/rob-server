package com.xjtu.dbc.robserver.question.bookmark.dao;

import com.xjtu.dbc.robserver.question.bookmark.entity.BookmarkDto;

public interface BookmarkDao {
    void addBookmark(BookmarkDto bookmarkDto);

    int ifBookmark(BookmarkDto bookmarkDto);

    void deleteBookmark(BookmarkDto bookmarkDto);

}
