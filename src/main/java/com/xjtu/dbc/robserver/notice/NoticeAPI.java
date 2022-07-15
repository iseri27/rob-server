package com.xjtu.dbc.robserver.notice;


import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.common.model.message.Message;
import com.xjtu.dbc.robserver.notice.dao.NoticeDao;
import com.xjtu.dbc.robserver.notice.entity.Notice;
import com.xjtu.dbc.robserver.notice.entity.NoticeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/notice")
@Slf4j
public class NoticeAPI {
    @Resource
    NoticeService noticeService;
    @Resource
    CommonService commonService;


    /**
     * 根据类型获取通知列表
     * @param token
     * @param type 点赞/关注/审核/回答
     * @return
     */
    @GetMapping("/get/{type}")
    public Result getNoticeByType(@RequestHeader("Token") String token, @PathVariable("type") Integer type) {
        Integer userId = TokenUtils.getUserInfo(token, commonService).getUserid();
        List<Notice> notices = noticeService.getNoticeByType(userId, type);
        return Result.success("获取未读通知成功", notices);
    }


    /**
     * 根据内容发送通知
     * @param token
     * @param noticeDto [类型，文章id，接收者id，时间]
     * @return
     */
    @PostMapping("/send")
    public Result sendNotice(@RequestHeader("Token") String token, @RequestBody NoticeDto noticeDto) {

        Integer userId = TokenUtils.getUserInfo(token, commonService).getUserid();
        Message message = new Message();
        message.setArticleid(noticeDto.getArticleid());
        message.setSenderid(userId);
        message.setReceiverid(noticeDto.getReceiverid());
        message.setTime(noticeDto.getTime());
        Integer type = noticeDto.getType();//获取类型
        log.info(noticeDto.toString());
        message.setMsgtype(type);
        message.setMsgcontent(Constants.MSG_CONTENT[type-1102]);//获取系统通知内容
        message.setMsgstatus(0);//设置为未读
        log.info(message.toString());
        noticeService.sendNotice(message);

        return Result.success("发送通知成功", message);

    }
}
