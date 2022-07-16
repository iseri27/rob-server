package com.xjtu.dbc.robserver.question.bookmark.impl;

import com.xjtu.dbc.robserver.question.bookmark.BookmarkService;
import com.xjtu.dbc.robserver.question.bookmark.dao.BookmarkDao;
import com.xjtu.dbc.robserver.question.bookmark.entity.BookmarkDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class BookmarkServiceImpl implements BookmarkService {

    @Resource
    BookmarkDao bookmarkDao;

    /**
     * 添加收藏
     * @param bookmarkDto{articleid 文章id ，userid 用户id}
     */
    @Override
    public void addBookmark(BookmarkDto bookmarkDto) {
        bookmarkDao.addBookmark(bookmarkDto);
    }

    /**
     * 当前用户是否收藏该文章
     * @param bookmarkDto{articleid 文章id ，userid 用户id}
     * @return 收藏关系
     */
    @Override
    public int ifBookmark(BookmarkDto bookmarkDto) {
        return bookmarkDao.ifBookmark(bookmarkDto);
    }

    /**
     * 取消收藏该文章
     * @param bookmarkDto{articleid 文章id ，userid 用户id}
     */
    @Override
    public void deleteBookmark(BookmarkDto bookmarkDto) {
        bookmarkDao.deleteBookmark(bookmarkDto);
    }
}
