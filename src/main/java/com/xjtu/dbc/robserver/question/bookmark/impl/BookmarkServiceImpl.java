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

    @Override
    public void addBookmark(BookmarkDto bookmarkDto) {
        bookmarkDao.addBookmark(bookmarkDto);
    }

    @Override
    public int ifBookmark(BookmarkDto bookmarkDto) {
        return bookmarkDao.ifBookmark(bookmarkDto);
    }

    @Override
    public void deleteBookmark(BookmarkDto bookmarkDto) {
        bookmarkDao.deleteBookmark(bookmarkDto);
    }
}
