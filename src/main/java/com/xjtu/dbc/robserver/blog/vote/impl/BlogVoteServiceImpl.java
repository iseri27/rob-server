package com.xjtu.dbc.robserver.blog.vote.impl;


import com.xjtu.dbc.robserver.blog.vote.BlogVoteService;
import com.xjtu.dbc.robserver.blog.vote.VoteDto;
import com.xjtu.dbc.robserver.blog.vote.dao.BlogVoteDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service @Transactional
public class BlogVoteServiceImpl implements BlogVoteService {
    @Resource
    private BlogVoteDao voteDao;

    @Override
    public int getVotesIfClickLike(VoteDto dto){
        System.out.println("类型：");
        System.out.println(dto.getVotetype());
        //原来点赞
        if(dto.getVotetype()==1) {
            voteDao.deleteVote(dto);
            return 0;
        }
        //原来点踩,再点一次就是取消点踩并点赞
        if(dto.getVotetype()==-1){
            voteDao.updateVote(dto);
            return 1;
        }
        //原来既没点赞又没点踩
        if(dto.getVotetype()==0){
            voteDao.insertLike(dto);
            return 2;
        }

        return -1;
    }

    @Override
    public int getVotesIfClickDislike(VoteDto dto) {
        //原来点踩
        if(dto.getVotetype()==-1) {
            voteDao.deleteVote(dto);
            return 0;
        }
         //原来点赞,再点一次就是取消点赞并点踩
        if(dto.getVotetype()==1){
            voteDao.updateVote(dto);
            return 1;
        }
        //原来既没点赞又没点踩
        if(dto.getVotetype()==0){
            voteDao.insertDislike(dto);
            return 2;
        }

        return -1;
    }

    @Override
    public int[] getVotes(VoteDto dto){
        int[] data3=new int[3];
        int hadLiked=voteDao.hadLikedOrNot(dto);
        int hadDisliked=voteDao.hadDislikedOrNot(dto);
        data3[0]=voteDao.findLikes(dto);
        data3[1]=voteDao.findDislikes(dto);
        if(hadLiked > 0)
            data3[2]=1;
        else if(hadDisliked > 0)
            data3[2]=-1;
        else
            data3[2]=0;

        return data3;
    }
}
