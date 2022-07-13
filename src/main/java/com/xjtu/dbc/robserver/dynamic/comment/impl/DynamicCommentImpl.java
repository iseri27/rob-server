package com.xjtu.dbc.robserver.dynamic.comment.impl;

import com.xjtu.dbc.robserver.dynamic.comment.DynamicCommentService;
import com.xjtu.dbc.robserver.dynamic.comment.dao.DynamicCommentDao;
import com.xjtu.dbc.robserver.dynamic.comment.entity.DynamicCommentDto;
import com.xjtu.dbc.robserver.dynamic.home.dao.DynamicHomeDao;
import com.xjtu.dbc.robserver.dynamic.report.entity.DynamicReportDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class DynamicCommentImpl implements DynamicCommentService {

    @Resource
    private DynamicCommentDao dynamicCommentDao;

    @Override
    public List<DynamicCommentDto> getDynamicCommentList(Integer articleid){
        return dynamicCommentDao.getDynamicCommentList(articleid);
    }


    /**
     * 判断是否在黑名单中
     * @param authorid,articleid
     */
    @Override
    public Boolean is_in_blacklist( Integer authorid,Integer articleid) {

        if( dynamicCommentDao.is_in_blacklist(authorid,articleid) >=1){
            return  true;
        }
        else{
            return false;
        }

    }

    /**
     * 新增评论
     * @param dynamicCommentDto {评论内容, 作者编号, 被回复这编号, 回复根节点编号}
     */
    @Override
    public  void addComment(DynamicCommentDto dynamicCommentDto) {
        dynamicCommentDao.addComment(dynamicCommentDto);
    }



    /**
     * 获取当前最大的评论记录的编号
     * @return 最大的评论记录的编号
     */
    @Override
    public Integer getMaxCommentId() {
        return dynamicCommentDao.getMaxCommentId();
    }
}
