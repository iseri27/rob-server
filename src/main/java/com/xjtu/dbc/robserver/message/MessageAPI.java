package com.xjtu.dbc.robserver.message;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.message.entity.ChatFriend;
import com.xjtu.dbc.robserver.message.entity.MessageDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/10 16:23
 */
@RestController
@RequestMapping("/message")
public class MessageAPI {

    @Resource
    MessageService messageService;

    @Resource
    CommonService commonService;

    @GetMapping("/getChatList")
    public Result getChatList(@RequestHeader("Token") String token) {
        int userid = TokenUtils.getUserInfo(token, commonService).getUserid();
        List<ChatFriend> chatFriends = messageService.getChatFriends(userid);
        return Result.success("获取私信列表成功", chatFriends);
    }


    /**
     * 建立与某人的私信，如果已经建立过私信则不添加
     *
     * @param token
     * @param friendid 朋友的id
     * @return
     */
    @PostMapping("/build/{friendid}")
    public Result addChat(@RequestHeader("Token") String token, @PathVariable("friendid") Integer friendid) {
        int userid = TokenUtils.getUserInfo(token, commonService).getUserid();
        messageService.buildChat(userid, friendid);
        messageService.buildChat(friendid, userid);
        return Result.successMsg("创建私信成功");
    }

    /**
     * 开始聊天
     *
     * @param token
     * @param friendid 朋友的id
     * @return
     */
    @PostMapping("/start/{friendid}")
    public Result chatWithFriend(@RequestHeader("Token") String token, @PathVariable("friendid") Integer friendid) {
        int myid = TokenUtils.getUserInfo(token, commonService).getUserid();
        messageService.updateUnRead(myid, friendid);//更新未读
        List<MessageDto> histroty = messageService.getMessageHistory(myid, friendid);//获取消息历史
        return Result.success("开始聊天成功",histroty);
    }


}
